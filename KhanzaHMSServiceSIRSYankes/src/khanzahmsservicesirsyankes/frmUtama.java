package khanzahmsservicesirsyankes;

import fungsi.SirsApi;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public class frmUtama extends JFrame {
   private Properties prop = new Properties();
   private Connection koneksi = koneksiDB.condb();
   private sekuel Sequel = new sekuel();
   private String requestXML;
   private String URL = "";
   private String utc = "";
   private String requestJson = "";
   private String namabed = "";
   private SirsApi api = new SirsApi();
   private HttpHeaders headers;
   private HttpEntity requestEntity;
   private PreparedStatement ps;
   private ResultSet rs;
   private int totaltt = 0;
   private int tersedia = 0;
   private int menunggu = 0;
   private int terpakai = 0;
   private JTextArea TeksArea;
   private JButton jButton1;
   private JButton jButton2;
   private JScrollPane jScrollPane1;

   public frmUtama() {
      this.initComponents();

      try {
         this.prop.loadFromXML(new FileInputStream("setting/database.xml"));
         this.URL = koneksiDB.URLAPISIRS();
      } catch (Exception var2) {
         System.out.println("E : " + var2);
      }

      this.setSize(390, 340);
      this.jam();
   }

   private void initComponents() {
      this.jScrollPane1 = new JScrollPane();
      this.TeksArea = new JTextArea();
      this.jButton1 = new JButton();
      this.jButton2 = new JButton();
      this.setDefaultCloseOperation(3);
      this.setTitle("SIMKES Khanza Service SIRANAP");
      this.TeksArea.setColumns(20);
      this.TeksArea.setRows(5);
      this.jScrollPane1.setViewportView(this.TeksArea);
      this.getContentPane().add(this.jScrollPane1, "Center");
      this.jButton1.setText("Keluar");
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            frmUtama.this.jButton1ActionPerformed(evt);
         }
      });
      this.getContentPane().add(this.jButton1, "Last");
      this.jButton2.setText("Update");
      this.jButton2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            frmUtama.this.jButton2ActionPerformed(evt);
         }
      });
      this.getContentPane().add(this.jButton2, "After");
      this.pack();
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      System.exit(0);
   }

   private void jButton2ActionPerformed(ActionEvent evt) {
      try {
         this.koneksi = koneksiDB.condb();
         this.TeksArea.append("Memulai update Siranap\n");
         this.ps = this.koneksi.prepareStatement("select siranap_ketersediaan_kamar.kode_ruang_siranap,siranap_ketersediaan_kamar.kelas_ruang_siranap,siranap_ketersediaan_kamar.kd_bangsal,bangsal.nm_bangsal,siranap_ketersediaan_kamar.kelas,siranap_ketersediaan_kamar.kapasitas,siranap_ketersediaan_kamar.tersedia,siranap_ketersediaan_kamar.tersediapria,siranap_ketersediaan_kamar.tersediawanita,siranap_ketersediaan_kamar.menunggu from siranap_ketersediaan_kamar inner join bangsal on siranap_ketersediaan_kamar.kd_bangsal=bangsal.kd_bangsal");

         try {
            this.rs = this.ps.executeQuery();

            while(this.rs.next()) {
               this.TeksArea.append("Mengirimkan kamar " + this.rs.getString("kode_ruang_siranap") + " " + this.rs.getString("nm_bangsal") + "\n");

               try {
                  this.totaltt = this.Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='" + this.rs.getString("kelas") + "' and kd_bangsal='" + this.rs.getString("kd_bangsal") + "'");
                  this.tersedia = this.Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='" + this.rs.getString("kelas") + "' and status='KOSONG' and kd_bangsal='" + this.rs.getString("kd_bangsal") + "'");
                  this.terpakai = this.Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='" + this.rs.getString("kelas") + "' and status='ISI' and kd_bangsal='" + this.rs.getString("kd_bangsal") + "'");
                  this.menunggu = this.Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='" + this.rs.getString("kelas") + "' and status='DIBERSIHKAN' and kd_bangsal='" + this.rs.getString("kd_bangsal") + "'");
                  this.namabed = this.Sequel.cariIsi("select nm_bangsal from bangsal where status='1' and kd_bangsal=?", this.rs.getString("kd_bangsal"));
                  this.headers = new HttpHeaders();
                  this.headers.setContentType(MediaType.APPLICATION_JSON);
                  this.headers.add("X-rs-id", koneksiDB.IDSIRS());
                  this.utc = String.valueOf(this.api.GetUTCdatetimeAsString());
                  this.headers.add("X-Timestamp", this.utc);
                  this.headers.add("X-pass", koneksiDB.PASSSIRS());
                  this.requestJson =    "{" +
                                            "\"id_tt\": \"," + this.rs.getString("kelas_ruang_siranap").substring(0, 2) + 
                                            "\"\n\"ruang\": \"," + this.namabed +
                                            "\"\n\"jumlah_ruang\": \"," + this.totaltt +
                                            "\"\n\"jumlah\": \"," + this.totaltt +
                                            "\"\n\"terpakai\": \"," + this.terpakai +
                                            "\"\n\"terpakai_suspek\": 0," +
                                            "\n\"terpakai_konfirmasi\":0," +
                                            "\n\"antrian\": 0," +
                                            "\n\"prepare\": 0," +
                                            "\n\"prepare_plan\": 0," +
                                            "\n\"covid\": 0," +
                                            "\n\"terpakai_dbd\": 0," +
                                            "\n\"terpakai_dbd_anak\": 0," +
                                            "\n\"jumlah_dbd\": \"" + this.totaltt + "\"" +
                                        "}";
                  this.requestEntity = new HttpEntity(this.requestJson, this.headers);
                  this.requestJson = (String)this.api.getRest().exchange(this.URL, HttpMethod.PUT, this.requestEntity, String.class, new Object[0]).getBody();
                  this.TeksArea.append("respon WS Kemkes : " + this.requestJson + "\n");
               } catch (Exception var8) {
                  System.out.println("Notifikasi Bridging : " + var8);
               }
            }
         } catch (Exception var9) {
            System.out.println("Notif Ketersediaan : " + var9);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }

         this.TeksArea.append("Proses update selesai\n");
      } catch (Exception var11) {
         System.out.println("Notif : " + var11);
      }

   }

   public static void main(String[] args) {
      try {
         LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            LookAndFeelInfo info = var1[var3];
            if ("Nimbus".equals(info.getName())) {
               UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException var5) {
         Logger.getLogger(frmUtama.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(frmUtama.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(frmUtama.class.getName()).log(Level.SEVERE, (String)null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         Logger.getLogger(frmUtama.class.getName()).log(Level.SEVERE, (String)null, var8);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new frmUtama()).setVisible(true);
         }
      });
   }

   private void jam() {
      ActionListener taskPerformer = new ActionListener() {
         private int nilai_jam;
         private int nilai_menit;
         private int nilai_detik;

         public void actionPerformed(ActionEvent e) {
            String nol_jam = "";
            String nol_menit = "";
            String nol_detik = "";
            Date now = Calendar.getInstance().getTime();
            this.nilai_jam = now.getHours();
            this.nilai_menit = now.getMinutes();
            this.nilai_detik = now.getSeconds();
            if (this.nilai_jam <= 9) {
               nol_jam = "0";
            }

            if (this.nilai_menit <= 9) {
               nol_menit = "0";
            }

            if (this.nilai_detik <= 9) {
               nol_detik = "0";
            }

            String jam = nol_jam + Integer.toString(this.nilai_jam);
            String menit = nol_menit + Integer.toString(this.nilai_menit);
            String detik = nol_detik + Integer.toString(this.nilai_detik);
            frmUtama.this.TeksArea.append(jam + ":" + menit + ":" + detik + "\n");
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new Date();
            if (menit.equals("01") && detik.equals("01")) {
               if (jam.equals("01") && menit.equals("01") && detik.equals("01")) {
                  frmUtama.this.TeksArea.setText("");
               }

               try {
                  frmUtama.this.koneksi = koneksiDB.condb();
                  frmUtama.this.TeksArea.append("Memulai update Siranap\n");
                  frmUtama.this.ps = frmUtama.this.koneksi.prepareStatement("select siranap_ketersediaan_kamar.kode_ruang_siranap,siranap_ketersediaan_kamar.kelas_ruang_siranap,siranap_ketersediaan_kamar.kd_bangsal,bangsal.nm_bangsal,siranap_ketersediaan_kamar.kelas,siranap_ketersediaan_kamar.kapasitas,siranap_ketersediaan_kamar.tersedia,siranap_ketersediaan_kamar.tersediapria,siranap_ketersediaan_kamar.tersediawanita,siranap_ketersediaan_kamar.menunggu from siranap_ketersediaan_kamar inner join bangsal on siranap_ketersediaan_kamar.kd_bangsal=bangsal.kd_bangsal");

                  try {
                     frmUtama.this.rs = frmUtama.this.ps.executeQuery();

                     while(frmUtama.this.rs.next()) {
                        frmUtama.this.TeksArea.append("Mengirimkan kamar " + frmUtama.this.rs.getString("kode_ruang_siranap") + " " + frmUtama.this.rs.getString("nm_bangsal") + "\n");

                        try {
                           frmUtama.this.totaltt = frmUtama.this.Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='" + frmUtama.this.rs.getString("kelas") + "' and kd_bangsal='" + frmUtama.this.rs.getString("kd_bangsal") + "'");
                           frmUtama.this.tersedia = frmUtama.this.Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='" + frmUtama.this.rs.getString("kelas") + "' and status='KOSONG' and kd_bangsal='" + frmUtama.this.rs.getString("kd_bangsal") + "'");
                           frmUtama.this.terpakai = frmUtama.this.Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='" + frmUtama.this.rs.getString("kelas") + "' and status='ISI' and kd_bangsal='" + frmUtama.this.rs.getString("kd_bangsal") + "'");
                           frmUtama.this.menunggu = frmUtama.this.Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='" + frmUtama.this.rs.getString("kelas") + "' and status='DIBERSIHKAN' and kd_bangsal='" + frmUtama.this.rs.getString("kd_bangsal") + "'");
                           frmUtama.this.namabed = frmUtama.this.Sequel.cariIsi("select nm_bangsal from bangsal where status='1' and kd_bangsal=?", frmUtama.this.rs.getString("kd_bangsal"));
                           frmUtama.this.headers = new HttpHeaders();
                           frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                           frmUtama.this.headers.add("X-rs-id", koneksiDB.IDSIRS());
                           frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                           frmUtama.this.headers.add("X-Timestamp", frmUtama.this.utc);
                           frmUtama.this.headers.add("X-pass", koneksiDB.PASSSIRS());
                           frmUtama.this.requestJson = "{" +
                                                            "\"id_tt\": \"," + frmUtama.this.rs.getString("kelas_ruang_siranap").substring(0, 2) +
                                                            "\"\n\"ruang\": \"," + frmUtama.this.namabed +
                                                            "\"\n\"jumlah_ruang\": \"," + frmUtama.this.totaltt +
                                                            "\"\n\"jumlah\": \"," + frmUtama.this.totaltt +
                                                            "\"\n\"terpakai\": \"," + frmUtama.this.terpakai +
                                                            "\"\n\"terpakai_suspek\": 0," +
                                                            "\n\"terpakai_konfirmasi\":0," +
                                                            "\n\"antrian\": 0," +
                                                            "\n\"prepare\": 0," +
                                                            "\n\"prepare_plan\": 0," +
                                                            "\n\"covid\": 0," +
                                                            "\n\"terpakai_dbd\": 0," +
                                                            "\n\"terpakai_dbd_anak\": 0," +
                                                            "\n\"jumlah_dbd\": \"" + frmUtama.this.totaltt + "\"" +
                                                        "}";
                           frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                           frmUtama.this.requestJson = (String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.PUT, frmUtama.this.requestEntity, String.class, new Object[0]).getBody();
                           frmUtama.this.TeksArea.append("respon WS Kemkes : " + frmUtama.this.requestJson + "\n");
                        } catch (Exception var17) {
                           System.out.println("Notifikasi Bridging : " + var17);
                        }
                     }
                  } catch (Exception var18) {
                     System.out.println("Notif Ketersediaan : " + var18);
                  } finally {
                     if (frmUtama.this.rs != null) {
                        frmUtama.this.rs.close();
                     }

                     if (frmUtama.this.ps != null) {
                        frmUtama.this.ps.close();
                     }

                  }

                  frmUtama.this.TeksArea.append("Proses update selesai\n");
               } catch (Exception var20) {
                  System.out.println("Notif : " + var20);
               }
            }

         }
      };
      (new Timer(10000, taskPerformer)).start();
   }
}
