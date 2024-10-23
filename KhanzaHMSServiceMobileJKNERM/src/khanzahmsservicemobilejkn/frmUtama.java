package khanzahmsservicemobilejkn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.ApiMobileJKN;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public class frmUtama extends JFrame {
   private Connection koneksi = koneksiDB.condb();
   private sekuel Sequel = new sekuel();
   private String requestJson;
   private String URL = "";
   private String utc = "";
   private String link = "";
   private String datajam = "";
   private String nol_jam = "";
   private String nol_menit = "";
   private String nol_detik = "";
   private String jam = "";
   private String menit = "";
   private String detik = "";
   private String hari = "";
   private String noresep = "";
   private String task3 = "";
   private String task4 = "";
   private String task5 = "";
   private String task6 = "";
   private String task7 = "";
   private String task99 = "";
   private String kodepoli = "";
   private String kodedokter = "";
   private String kodebpjs;
   private ApiMobileJKN api;
   private HttpHeaders headers;
   private HttpEntity requestEntity;
   private ObjectMapper mapper;
   private JsonNode root;
   private JsonNode nameNode;
   private PreparedStatement ps;
   private PreparedStatement ps2;
   private PreparedStatement ps3;
   private ResultSet rs;
   private ResultSet rs2;
   private ResultSet rs3;
   private Calendar cal;
   private int day;
   private SimpleDateFormat dateFormat;
   private SimpleDateFormat tanggalFormat;
   private Date parsedDate;
   private Date date;
   private JTextField Tanggal1;
   private JTextField Tanggal2;
   private JTextArea TeksArea;
   private JButton jButton1;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JLabel jLabel3;
   private JPanel jPanel1;
   private JScrollPane jScrollPane1;

   public frmUtama() {
      this.kodebpjs = this.Sequel.cariIsi("select password_asuransi.kd_pj from password_asuransi");
      this.api = new ApiMobileJKN();
      this.mapper = new ObjectMapper();
      this.cal = Calendar.getInstance();
      this.day = this.cal.get(7);
      this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      this.tanggalFormat = new SimpleDateFormat("yyyy-MM-dd");
      this.date = new Date();
      this.initComponents();

      try {
         this.link = koneksiDB.URLAPIMOBILEJKN();
      } catch (Exception var2) {
         System.out.println("E : " + var2);
      }

      this.setSize(390, 340);
      this.date = new Date();
      this.Tanggal1.setText(this.tanggalFormat.format(this.date));
      this.Tanggal2.setText(this.tanggalFormat.format(this.date));
      this.jam();
   }

   private void initComponents() {
      this.jScrollPane1 = new JScrollPane();
      this.TeksArea = new JTextArea();
      this.jPanel1 = new JPanel();
      this.jLabel1 = new JLabel();
      this.Tanggal1 = new JTextField();
      this.jLabel3 = new JLabel();
      this.Tanggal2 = new JTextField();
      this.jLabel2 = new JLabel();
      this.jButton1 = new JButton();
      this.setDefaultCloseOperation(3);
      this.setTitle("SIMKES Khanza Service Mobile JKN");
      this.TeksArea.setColumns(20);
      this.TeksArea.setRows(5);
      this.jScrollPane1.setViewportView(this.TeksArea);
      this.getContentPane().add(this.jScrollPane1, "Center");
      this.jLabel1.setHorizontalAlignment(4);
      this.jLabel1.setText("Tanggal :");
      this.jLabel1.setPreferredSize(new Dimension(70, 23));
      this.jPanel1.add(this.jLabel1);
      this.Tanggal1.setPreferredSize(new Dimension(100, 23));
      this.jPanel1.add(this.Tanggal1);
      this.jLabel3.setText("s.d.");
      this.jLabel3.setPreferredSize(new Dimension(28, 23));
      this.jPanel1.add(this.jLabel3);
      this.Tanggal2.setPreferredSize(new Dimension(100, 23));
      this.jPanel1.add(this.Tanggal2);
      this.jLabel2.setPreferredSize(new Dimension(30, 23));
      this.jPanel1.add(this.jLabel2);
      this.jButton1.setText("Keluar");
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            frmUtama.this.jButton1ActionPerformed(evt);
         }
      });
      this.jPanel1.add(this.jButton1);
      this.getContentPane().add(this.jPanel1, "Last");
      this.pack();
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      System.exit(0);
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
            frmUtama.this.nol_jam = "";
            frmUtama.this.nol_menit = "";
            frmUtama.this.nol_detik = "";
            Date now = Calendar.getInstance().getTime();
            this.nilai_jam = now.getHours();
            this.nilai_menit = now.getMinutes();
            this.nilai_detik = now.getSeconds();
            if (this.nilai_jam <= 9) {
               frmUtama.this.nol_jam = "0";
            }

            if (this.nilai_menit <= 9) {
               frmUtama.this.nol_menit = "0";
            }

            if (this.nilai_detik <= 9) {
               frmUtama.this.nol_detik = "0";
            }

            frmUtama.this.jam = frmUtama.this.nol_jam + Integer.toString(this.nilai_jam);
            frmUtama.this.menit = frmUtama.this.nol_menit + Integer.toString(this.nilai_menit);
            frmUtama.this.detik = frmUtama.this.nol_detik + Integer.toString(this.nilai_detik);
            if (frmUtama.this.jam.equals("01") && frmUtama.this.menit.equals("01") && frmUtama.this.detik.equals("01")) {
               frmUtama.this.TeksArea.setText("");
               frmUtama.this.date = new Date();
               frmUtama.this.Tanggal1.setText(frmUtama.this.tanggalFormat.format(frmUtama.this.date));
               frmUtama.this.Tanggal2.setText(frmUtama.this.tanggalFormat.format(frmUtama.this.date));
            }

            if (frmUtama.this.detik.equals("01") && this.nilai_menit % 0.3 == 0) {
               frmUtama.this.day = frmUtama.this.cal.get(7);
               switch(frmUtama.this.day) {
               case 1:
                  frmUtama.this.hari = "AKHAD";
                  break;
               case 2:
                  frmUtama.this.hari = "SENIN";
                  break;
               case 3:
                  frmUtama.this.hari = "SELASA";
                  break;
               case 4:
                  frmUtama.this.hari = "RABU";
                  break;
               case 5:
                  frmUtama.this.hari = "KAMIS";
                  break;
               case 6:
                  frmUtama.this.hari = "JUMAT";
                  break;
               case 7:
                  frmUtama.this.hari = "SABTU";
               }

               try {
                  frmUtama.this.koneksi = koneksiDB.condb();
                  frmUtama.this.TeksArea.append("Menjalankan WS tambah antrian Mobile JKN Pasien BPJS\n");
                  frmUtama.this.ps = frmUtama.this.koneksi.prepareStatement("SELECT referensi_mobilejkn_bpjs.nobooking,referensi_mobilejkn_bpjs.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,referensi_mobilejkn_bpjs.nohp,referensi_mobilejkn_bpjs.nomorkartu,referensi_mobilejkn_bpjs.nik,referensi_mobilejkn_bpjs.tanggalperiksa,poliklinik.nm_poli,dokter.nm_dokter,referensi_mobilejkn_bpjs.jampraktek,referensi_mobilejkn_bpjs.jeniskunjungan,referensi_mobilejkn_bpjs.nomorreferensi,referensi_mobilejkn_bpjs.status,referensi_mobilejkn_bpjs.validasi,referensi_mobilejkn_bpjs.kodepoli,referensi_mobilejkn_bpjs.pasienbaru,referensi_mobilejkn_bpjs.kodedokter,referensi_mobilejkn_bpjs.jampraktek,referensi_mobilejkn_bpjs.nomorantrean,referensi_mobilejkn_bpjs.angkaantrean,referensi_mobilejkn_bpjs.estimasidilayani,referensi_mobilejkn_bpjs.sisakuotajkn,referensi_mobilejkn_bpjs.kuotajkn,referensi_mobilejkn_bpjs.sisakuotanonjkn,referensi_mobilejkn_bpjs.kuotanonjkn FROM referensi_mobilejkn_bpjs INNER JOIN reg_periksa ON referensi_mobilejkn_bpjs.no_rawat=reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis INNER JOIN poliklinik ON reg_periksa.kd_poli=poliklinik.kd_poli INNER JOIN dokter ON reg_periksa.kd_dokter=dokter.kd_dokter WHERE referensi_mobilejkn_bpjs.statuskirim='Belum' and referensi_mobilejkn_bpjs.tanggalperiksa between " + (frmUtama.this.Tanggal1.getText().equals(frmUtama.this.Tanggal2.getText()) ? "SUBDATE('" + frmUtama.this.Tanggal2.getText() + "',INTERVAL 6 DAY) and '" + frmUtama.this.Tanggal2.getText() + "'" : "'" + frmUtama.this.Tanggal1.getText() + "' and '" + frmUtama.this.Tanggal2.getText() + "'") + "order by referensi_mobilejkn_bpjs.tanggalperiksa");

                  try {
                     frmUtama.this.rs = frmUtama.this.ps.executeQuery();

                     while(frmUtama.this.rs.next()) {
                        try {
                           frmUtama.this.headers = new HttpHeaders();
                           frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                           frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                           frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                           frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                           frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                           frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                           frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"jenispasien\": \"JKN\",\"nomorkartu\": \"" + frmUtama.this.rs.getString("nomorkartu") + "\",\"nik\": \"" + frmUtama.this.rs.getString("nik") + "\",\"nohp\": \"" + frmUtama.this.rs.getString("nohp") + "\",\"kodepoli\": \"" + frmUtama.this.rs.getString("kodepoli") + "\",\"namapoli\": \"" + frmUtama.this.rs.getString("nm_poli") + "\",\"pasienbaru\": " + frmUtama.this.rs.getString("pasienbaru") + ",\"norm\": \"" + frmUtama.this.rs.getString("no_rkm_medis") + "\",\"tanggalperiksa\": \"" + frmUtama.this.rs.getString("tanggalperiksa") + "\",\"kodedokter\": " + frmUtama.this.rs.getString("kodedokter") + ",\"namadokter\": \"" + frmUtama.this.rs.getString("nm_dokter") + "\",\"jampraktek\": \"" + frmUtama.this.rs.getString("jampraktek") + "\",\"jeniskunjungan\": " + frmUtama.this.rs.getString("jeniskunjungan").substring(0, 1) + ",\"nomorreferensi\": \"" + frmUtama.this.rs.getString("nomorreferensi") + "\",\"nomorantrean\": \"" + frmUtama.this.rs.getString("nomorantrean") + "\",\"angkaantrean\": " + Integer.parseInt(frmUtama.this.rs.getString("angkaantrean")) + ",\"estimasidilayani\": " + frmUtama.this.rs.getString("estimasidilayani") + ",\"sisakuotajkn\": " + frmUtama.this.rs.getString("sisakuotajkn") + ",\"kuotajkn\": " + frmUtama.this.rs.getString("kuotajkn") + ",\"sisakuotanonjkn\": " + frmUtama.this.rs.getString("sisakuotanonjkn") + ",\"kuotanonjkn\": " + frmUtama.this.rs.getString("kuotanonjkn") + ",\"keterangan\": \"Peserta harap 30 menit lebih awal guna pencatatan administrasi.\"}";
                           frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                           frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                           frmUtama.this.URL = frmUtama.this.link + "/antrean/add";
                           System.out.println("URL : " + frmUtama.this.URL);
                           frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                           frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                           if (frmUtama.this.nameNode.path("code").asText().equals("200") || frmUtama.this.nameNode.path("code").asText().equals("208") || frmUtama.this.nameNode.path("message").asText().equals("Ok")) {
                              frmUtama.this.Sequel.queryu2("update referensi_mobilejkn_bpjs set statuskirim='Sudah' where nobooking='" + frmUtama.this.rs.getString("nobooking") + "'");
                           }

                           frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                        } catch (Exception var271) {
                           System.out.println("Notifikasi Bridging : " + var271);
                        }
                     }
                  } catch (Exception var272) {
                     System.out.println("Notif Ketersediaan : " + var272);
                  } finally {
                     if (frmUtama.this.rs != null) {
                        frmUtama.this.rs.close();
                     }

                     if (frmUtama.this.ps != null) {
                        frmUtama.this.ps.close();
                     }

                  }

                  frmUtama.this.TeksArea.append("Menjalankan WS batal antrian Mobile JKN Pasien BPJS\n");
                  frmUtama.this.ps = frmUtama.this.koneksi.prepareStatement("SELECT * FROM referensi_mobilejkn_bpjs_batal where referensi_mobilejkn_bpjs_batal.statuskirim='Belum' and referensi_mobilejkn_bpjs_batal.tanggalbatal between " + (frmUtama.this.Tanggal1.getText().equals(frmUtama.this.Tanggal2.getText()) ? "SUBDATE('" + frmUtama.this.Tanggal2.getText() + "',INTERVAL 6 DAY) and '" + frmUtama.this.Tanggal2.getText() + "'" : "'" + frmUtama.this.Tanggal1.getText() + "' and '" + frmUtama.this.Tanggal2.getText() + "'"));

                  try {
                     frmUtama.this.rs = frmUtama.this.ps.executeQuery();

                     while(frmUtama.this.rs.next()) {
                        try {
                           frmUtama.this.headers = new HttpHeaders();
                           frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                           frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                           frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                           frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                           frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                           frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                           frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"keterangan\": \"" + frmUtama.this.rs.getString("keterangan") + "\"}";
                           frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                           frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                           frmUtama.this.URL = frmUtama.this.link + "/antrean/batal";
                           System.out.println("URL : " + frmUtama.this.URL);
                           frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                           frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                           if (frmUtama.this.nameNode.path("code").asText().equals("200")) {
                              frmUtama.this.Sequel.queryu2("update referensi_mobilejkn_bpjs_batal set statuskirim='Sudah' where nomorreferensi='" + frmUtama.this.rs.getString("nomorreferensi") + "'");
                              frmUtama.this.datajam = frmUtama.this.rs.getString("tanggalbatal");
                              if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat_batal"), "99", frmUtama.this.datajam})) {
                                 frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                                 try {
                                    frmUtama.this.TeksArea.append("Menjalankan WS taskid batal pelayanan poli Mobile JKN Pasien BPJS\n");
                                    frmUtama.this.headers = new HttpHeaders();
                                    frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                    frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                    frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                    frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                    frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                    frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                    frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"taskid\": \"99\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                    frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                    frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                    frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                    System.out.println("URL : " + frmUtama.this.URL);
                                    frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                    frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                    if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                       frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='99' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                    }

                                    frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                 } catch (Exception var257) {
                                    System.out.println("Notifikasi Bridging : " + var257);
                                 }
                              }
                           }

                           frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                        } catch (Exception var258) {
                           System.out.println("Notifikasi Bridging : " + var258);
                        }
                     }
                  } catch (Exception var269) {
                     System.out.println("Notif Ketersediaan : " + var269);
                  } finally {
                     if (frmUtama.this.rs != null) {
                        frmUtama.this.rs.close();
                     }

                     if (frmUtama.this.ps != null) {
                        frmUtama.this.ps.close();
                     }

                  }

                  frmUtama.this.ps = frmUtama.this.koneksi.prepareStatement("SELECT referensi_mobilejkn_bpjs.nobooking,referensi_mobilejkn_bpjs.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,referensi_mobilejkn_bpjs.nohp,referensi_mobilejkn_bpjs.nomorkartu,referensi_mobilejkn_bpjs.nik,referensi_mobilejkn_bpjs.tanggalperiksa,poliklinik.nm_poli,dokter.nm_dokter,referensi_mobilejkn_bpjs.jampraktek,referensi_mobilejkn_bpjs.jeniskunjungan,referensi_mobilejkn_bpjs.nomorreferensi,referensi_mobilejkn_bpjs.status,referensi_mobilejkn_bpjs.validasi,referensi_mobilejkn_bpjs.kodepoli,referensi_mobilejkn_bpjs.pasienbaru,referensi_mobilejkn_bpjs.kodedokter,referensi_mobilejkn_bpjs.jampraktek,referensi_mobilejkn_bpjs.nomorantrean,referensi_mobilejkn_bpjs.angkaantrean,referensi_mobilejkn_bpjs.estimasidilayani,referensi_mobilejkn_bpjs.sisakuotajkn,referensi_mobilejkn_bpjs.kuotajkn,referensi_mobilejkn_bpjs.sisakuotanonjkn,referensi_mobilejkn_bpjs.kuotanonjkn FROM referensi_mobilejkn_bpjs INNER JOIN reg_periksa ON referensi_mobilejkn_bpjs.no_rawat=reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis INNER JOIN poliklinik ON reg_periksa.kd_poli=poliklinik.kd_poli INNER JOIN dokter ON reg_periksa.kd_dokter=dokter.kd_dokter WHERE referensi_mobilejkn_bpjs.statuskirim='Sudah' and referensi_mobilejkn_bpjs.tanggalperiksa between '" + frmUtama.this.Tanggal1.getText() + "' and '" + frmUtama.this.Tanggal2.getText() + "' order by referensi_mobilejkn_bpjs.tanggalperiksa");

                  try {
                     frmUtama.this.rs = frmUtama.this.ps.executeQuery();

                     while(frmUtama.this.rs.next()) {
                        frmUtama.this.task3 = "";
                        frmUtama.this.task4 = "";
                        frmUtama.this.task5 = "";
                        frmUtama.this.task6 = "";
                        frmUtama.this.task7 = "";
                        frmUtama.this.task99 = "";
                        frmUtama.this.ps2 = frmUtama.this.koneksi.prepareStatement("select referensi_mobilejkn_bpjs_taskid.taskid from referensi_mobilejkn_bpjs_taskid where referensi_mobilejkn_bpjs_taskid.no_rawat=?");

                        try {
                           frmUtama.this.ps2.setString(1, frmUtama.this.rs.getString("no_rawat"));
                           frmUtama.this.rs2 = frmUtama.this.ps2.executeQuery();

                           while(frmUtama.this.rs2.next()) {
                              if (frmUtama.this.rs2.getString("taskid").equals("3")) {
                                 frmUtama.this.task3 = "Sudah";
                              }

                              if (frmUtama.this.rs2.getString("taskid").equals("4")) {
                                 frmUtama.this.task4 = "Sudah";
                              }

                              if (frmUtama.this.rs2.getString("taskid").equals("5")) {
                                 frmUtama.this.task5 = "Sudah";
                              }

                              if (frmUtama.this.rs2.getString("taskid").equals("6")) {
                                 frmUtama.this.task6 = "Sudah";
                              }

                              if (frmUtama.this.rs2.getString("taskid").equals("7")) {
                                 frmUtama.this.task7 = "Sudah";
                              }

                              if (frmUtama.this.rs2.getString("taskid").equals("99")) {
                                 frmUtama.this.task99 = "Sudah";
                              }
                           }
                        } catch (Exception var265) {
                           System.out.println("Notif : " + var265);
                        } finally {
                           if (frmUtama.this.rs2 != null) {
                              frmUtama.this.rs2.close();
                           }

                           if (frmUtama.this.ps2 != null) {
                              frmUtama.this.ps2.close();
                           }

                        }

                        if (frmUtama.this.task3.equals("")) {
                           frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select referensi_mobilejkn_bpjs.validasi from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                           if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "3", frmUtama.this.datajam})) {
                              frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                              try {
                                 frmUtama.this.TeksArea.append("Menjalankan WS taskid mulai tunggu poli Mobile JKN Pasien BPJS\n");
                                 frmUtama.this.headers = new HttpHeaders();
                                 frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                 frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                 frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                 frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                 frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                 frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                 frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"taskid\": \"3\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                 frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                 frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                 frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                 System.out.println("URL : " + frmUtama.this.URL);
                                 frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                 frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                 if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                    frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='3' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                 }

                                 frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                              } catch (Exception var256) {
                                 System.out.println("Notifikasi Bridging : " + var256);
                              }
                           }
                        }

                        if (frmUtama.this.task4.equals("")) {
                           frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select concat(pemeriksaan_ralan.tgl_perawatan,' ',pemeriksaan_ralan.jam_rawat) from pemeriksaan_ralan where pemeriksaan_ralan.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                           if (frmUtama.this.datajam.equals("")) {
                              frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select if(diterima='0000-00-00 00:00:00','',diterima) from mutasi_berkas where mutasi_berkas.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                           }

                           if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "4", frmUtama.this.datajam})) {
                              frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                              try {
                                 frmUtama.this.TeksArea.append("Menjalankan WS taskid mulai pelayanan poli Mobile JKN Pasien BPJS\n");
                                 frmUtama.this.headers = new HttpHeaders();
                                 frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                 frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                 frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                 frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                 frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                 frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                 frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"taskid\": \"4\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                 frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                 frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                 frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                 System.out.println("URL : " + frmUtama.this.URL);
                                 frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                 frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                 if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                    frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='4' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                 }

                                 frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                              } catch (Exception var255) {
                                 System.out.println("Notifikasi Bridging : " + var255);
                              }
                           }
                        }

                        if (frmUtama.this.task5.equals("")) {
                           frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select if(kembali='0000-00-00 00:00:00','',kembali) from mutasi_berkas where mutasi_berkas.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                           if (frmUtama.this.datajam.equals("")) {
                              frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select now() from reg_periksa where reg_periksa.stts='Sudah' and reg_periksa.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                           }

                           if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "5", frmUtama.this.datajam})) {
                              frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                              try {
                                 frmUtama.this.TeksArea.append("Menjalankan WS taskid selesai pelayanan poli Mobile JKN Pasien BPJS\n");
                                 frmUtama.this.headers = new HttpHeaders();
                                 frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                 frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                 frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                 frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                 frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                 frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                 frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"taskid\": \"5\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                 frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                 frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                 frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                 System.out.println("URL : " + frmUtama.this.URL);
                                 frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                 frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                 if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                    frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='5' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                 }

                                 frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                              } catch (Exception var254) {
                                 System.out.println("Notifikasi Bridging : " + var254);
                              }
                           }
                        }

                        if (frmUtama.this.task6.equals("")) {
                           frmUtama.this.noresep = frmUtama.this.Sequel.cariIsi("select resep_obat.no_resep from resep_obat where resep_obat.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                           if (!frmUtama.this.noresep.equals("")) {
                              try {
                                 frmUtama.this.TeksArea.append("Menjalankan WS tambah antrian farmasi Mobile JKN Pasien BPJS\n");
                                 frmUtama.this.headers = new HttpHeaders();
                                 frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                 frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                 frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                 frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                 frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                 frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                 frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"jenisresep\": \"" + (frmUtama.this.Sequel.cariInteger("select count(resep_dokter_racikan.no_resep) from resep_dokter_racikan where resep_dokter_racikan.no_resep=?", frmUtama.this.noresep) > 0 ? "Racikan" : "Non Racikan") + "\",\"nomorantrean\": " + Integer.parseInt(StringUtils.right(frmUtama.this.noresep, 4)) + ",\"keterangan\": \"Resep dibuat secara elektronik di poli\"}";
                                 frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                 frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                 frmUtama.this.URL = frmUtama.this.link + "/antrean/farmasi/add";
                                 System.out.println("URL : " + frmUtama.this.URL);
                                 frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                 frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                 frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                              } catch (Exception var253) {
                                 System.out.println("Notifikasi Bridging : " + var253);
                              }
                           }

                           frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select concat(resep_obat.tgl_perawatan,' ',resep_obat.jam) from resep_obat where resep_obat.tgl_perawatan<>'0000-00-00' and resep_obat.status='ralan' and resep_obat.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                           if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "6", frmUtama.this.datajam})) {
                              frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                              try {
                                 frmUtama.this.TeksArea.append("Menjalankan WS taskid permintaan resep poli Mobile JKN Pasien BPJS\n");
                                 frmUtama.this.headers = new HttpHeaders();
                                 frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                 frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                 frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                 frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                 frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                 frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                 frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"taskid\": \"6\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                 frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                 frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                 frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                 System.out.println("URL : " + frmUtama.this.URL);
                                 frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                 frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                 if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                    frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='6' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                 }

                                 frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                              } catch (Exception var252) {
                                 System.out.println("Notifikasi Bridging : " + var252);
                              }
                           }
                        }

                        if (frmUtama.this.task7.equals("")) {
                           frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select concat(resep_obat.tgl_penyerahan,' ',resep_obat.jam_penyerahan) from resep_obat where resep_obat.status='ralan' and resep_obat.no_rawat=? and concat(resep_obat.tgl_penyerahan,' ',resep_obat.jam_penyerahan)<>'0000-00-00 00:00:00'", frmUtama.this.rs.getString("no_rawat"));
                           if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "7", frmUtama.this.datajam})) {
                              frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                              try {
                                 frmUtama.this.TeksArea.append("Menjalankan WS taskid validasi resep poli Mobile JKN Pasien BPJS\n");
                                 frmUtama.this.headers = new HttpHeaders();
                                 frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                 frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                 frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                 frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                 frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                 frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                 frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"taskid\": \"7\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                 frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                 frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                 frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                 System.out.println("URL : " + frmUtama.this.URL);
                                 frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                 frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                 if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                    frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='7' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                 }

                                 frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                              } catch (Exception var251) {
                                 System.out.println("Notifikasi Bridging : " + var251);
                              }
                           }
                        }

                        if (frmUtama.this.task99.equals("")) {
                           frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select now() from reg_periksa where reg_periksa.stts='Batal' and reg_periksa.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                           if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "99", frmUtama.this.datajam})) {
                              frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                              try {
                                 frmUtama.this.TeksArea.append("Menjalankan WS taskid batal pelayanan poli Mobile JKN Pasien BPJS\n");
                                 frmUtama.this.headers = new HttpHeaders();
                                 frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                 frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                 frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                 frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                 frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                 frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                 frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("nobooking") + "\",\"taskid\": \"99\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                 frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                 frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                 frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                 System.out.println("URL : " + frmUtama.this.URL);
                                 frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                 frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                 if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                    frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='99' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                 }

                                 frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                              } catch (Exception var250) {
                                 System.out.println("Notifikasi Bridging : " + var250);
                              }
                           }
                        }
                     }
                  } catch (Exception var267) {
                     System.out.println("Notif : " + var267);
                  } finally {
                     if (frmUtama.this.rs != null) {
                        frmUtama.this.rs.close();
                     }

                     if (frmUtama.this.ps != null) {
                        frmUtama.this.ps.close();
                     }

                  }

                  frmUtama.this.TeksArea.append("Menjalankan WS tambah antrian Mobile JKN Pasien Non BPJS/BJS Onsite\n");
                  frmUtama.this.ps = frmUtama.this.koneksi.prepareStatement("select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_poli,poliklinik.nm_poli,reg_periksa.stts_daftar,reg_periksa.no_rkm_medis,reg_periksa.kd_pj from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.tgl_registrasi between '" + frmUtama.this.Tanggal1.getText() + "' and '" + frmUtama.this.Tanggal2.getText() + "' and reg_periksa.no_rawat not in (select referensi_mobilejkn_bpjs.no_rawat from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.tanggalperiksa between '" + frmUtama.this.Tanggal1.getText() + "' and '" + frmUtama.this.Tanggal2.getText() + "') order by concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg)");

                  try {
                     frmUtama.this.rs = frmUtama.this.ps.executeQuery();

                     while(frmUtama.this.rs.next()) {
                        frmUtama.this.ps2 = frmUtama.this.koneksi.prepareStatement("select * from jadwal where jadwal.hari_kerja=? and jadwal.kd_dokter=? and jadwal.kd_poli=?");

                        try {
                           frmUtama.this.ps2.setString(1, frmUtama.this.hari);
                           frmUtama.this.ps2.setString(2, frmUtama.this.rs.getString("kd_dokter"));
                           frmUtama.this.ps2.setString(3, frmUtama.this.rs.getString("kd_poli"));
                           frmUtama.this.rs2 = frmUtama.this.ps2.executeQuery();
                           if (frmUtama.this.rs2.next()) {
                              frmUtama.this.kodedokter = frmUtama.this.Sequel.cariIsi("select maping_dokter_dpjpvclaim.kd_dokter_bpjs from maping_dokter_dpjpvclaim where maping_dokter_dpjpvclaim.kd_dokter=?", frmUtama.this.rs.getString("kd_dokter"));
                              frmUtama.this.kodepoli = frmUtama.this.Sequel.cariIsi("select maping_poli_bpjs.kd_poli_bpjs from maping_poli_bpjs where maping_poli_bpjs.kd_poli_rs=?", frmUtama.this.rs.getString("kd_poli"));
                              if (!frmUtama.this.kodedokter.equals("") && !frmUtama.this.kodepoli.equals("")) {
                                 frmUtama.this.task3 = "";
                                 frmUtama.this.task4 = "";
                                 frmUtama.this.task5 = "";
                                 frmUtama.this.task6 = "";
                                 frmUtama.this.task7 = "";
                                 frmUtama.this.task99 = "";
                                 frmUtama.this.ps3 = frmUtama.this.koneksi.prepareStatement("select referensi_mobilejkn_bpjs_taskid.taskid from referensi_mobilejkn_bpjs_taskid where referensi_mobilejkn_bpjs_taskid.no_rawat=?");

                                 try {
                                    frmUtama.this.ps3.setString(1, frmUtama.this.rs.getString("no_rawat"));
                                    frmUtama.this.rs3 = frmUtama.this.ps3.executeQuery();

                                    while(frmUtama.this.rs3.next()) {
                                       if (frmUtama.this.rs3.getString("taskid").equals("3")) {
                                          frmUtama.this.task3 = "Sudah";
                                       }

                                       if (frmUtama.this.rs3.getString("taskid").equals("4")) {
                                          frmUtama.this.task4 = "Sudah";
                                       }

                                       if (frmUtama.this.rs3.getString("taskid").equals("5")) {
                                          frmUtama.this.task5 = "Sudah";
                                       }

                                       if (frmUtama.this.rs3.getString("taskid").equals("6")) {
                                          frmUtama.this.task6 = "Sudah";
                                       }

                                       if (frmUtama.this.rs3.getString("taskid").equals("7")) {
                                          frmUtama.this.task7 = "Sudah";
                                       }

                                       if (frmUtama.this.rs3.getString("taskid").equals("99")) {
                                          frmUtama.this.task99 = "Sudah";
                                       }
                                    }
                                 } catch (Exception var259) {
                                    System.out.println("Notif : " + var259);
                                 } finally {
                                    if (frmUtama.this.rs3 != null) {
                                       frmUtama.this.rs3.close();
                                    }

                                    if (frmUtama.this.ps3 != null) {
                                       frmUtama.this.ps3.close();
                                    }

                                 }

                                 if (frmUtama.this.task3.equals("")) {
                                    try {
                                       frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select DATE_ADD(concat('" + frmUtama.this.rs.getString("tgl_registrasi") + "',' ','" + frmUtama.this.rs2.getString("jam_mulai") + "'),INTERVAL " + Integer.parseInt(frmUtama.this.rs.getString("no_reg")) * 10 + " MINUTE) ");
                                       frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);
                                       if (!frmUtama.this.rs.getString("kd_pj").equals(frmUtama.this.kodebpjs)) {
                                          frmUtama.this.headers = new HttpHeaders();
                                          frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                          frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                          frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                          frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                          frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                          frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                          frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("no_rawat") + "\",\"jenispasien\": \"NON JKN\",\"nomorkartu\": \"-\",\"nik\": \"-\",\"nohp\": \"-\",\"kodepoli\": \"" + frmUtama.this.Sequel.cariIsi("select maping_poli_bpjs.kd_poli_bpjs from maping_poli_bpjs where maping_poli_bpjs.kd_poli_rs=?", frmUtama.this.rs.getString("kd_poli")) + "\",\"namapoli\": \"" + frmUtama.this.rs.getString("nm_poli") + "\",\"pasienbaru\": " + frmUtama.this.rs.getString("stts_daftar").replaceAll("Baru", "1").replaceAll("Lama", "0").replaceAll("-", "0") + ",\"norm\": \"" + frmUtama.this.rs.getString("no_rkm_medis") + "\",\"tanggalperiksa\": \"" + frmUtama.this.rs.getString("tgl_registrasi") + "\",\"kodedokter\": " + frmUtama.this.Sequel.cariIsi("select maping_dokter_dpjpvclaim.kd_dokter_bpjs from maping_dokter_dpjpvclaim where maping_dokter_dpjpvclaim.kd_dokter=?", frmUtama.this.rs.getString("kd_dokter")) + ",\"namadokter\": \"" + frmUtama.this.rs.getString("nm_dokter") + "\",\"jampraktek\": \"" + frmUtama.this.rs2.getString("jam_mulai").substring(0, 5) + "-" + frmUtama.this.rs2.getString("jam_selesai").substring(0, 5) + "\",\"jeniskunjungan\": 3,\"nomorreferensi\": \"-\",\"nomorantrean\": \"" + frmUtama.this.rs.getString("no_reg") + "\",\"angkaantrean\": " + Integer.parseInt(frmUtama.this.rs.getString("no_reg")) + ",\"estimasidilayani\": " + frmUtama.this.parsedDate.getTime() + ",\"sisakuotajkn\": " + (frmUtama.this.rs2.getInt("kuota") - Integer.parseInt(frmUtama.this.rs.getString("no_reg"))) + ",\"kuotajkn\": " + frmUtama.this.rs2.getString("kuota") + ",\"sisakuotanonjkn\": " + (frmUtama.this.rs2.getInt("kuota") - Integer.parseInt(frmUtama.this.rs.getString("no_reg"))) + ",\"kuotanonjkn\": " + frmUtama.this.rs2.getString("kuota") + ",\"keterangan\": \"Peserta harap 30 menit lebih awal guna pencatatan administrasi.\"}";
                                          frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                          frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                          frmUtama.this.URL = frmUtama.this.link + "/antrean/add";
                                          System.out.println("URL : " + frmUtama.this.URL);
                                          frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                          frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                          frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                       }
                                    } catch (Exception var249) {
                                       System.out.println("Notifikasi Bridging : " + var249);
                                    }

                                    frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select if(concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg)>concat('" + frmUtama.this.rs.getString("tgl_registrasi") + "',' ','" + frmUtama.this.rs2.getString("jam_mulai") + "'),concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg),concat('" + frmUtama.this.rs.getString("tgl_registrasi") + "',' ','" + frmUtama.this.rs2.getString("jam_mulai") + "')) as tanggal from reg_periksa where reg_periksa.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                                    if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "3", frmUtama.this.datajam})) {
                                       frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                                       try {
                                          frmUtama.this.TeksArea.append("Menjalankan WS taskid mulai tunggu poli Mobile JKN Pasien Non BPJS/BPS Onsite\n");
                                          frmUtama.this.headers = new HttpHeaders();
                                          frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                          frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                          frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                          frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                          frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                          frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                          frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("no_rawat") + "\",\"taskid\": \"3\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                          frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                          frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                          frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                          System.out.println("URL : " + frmUtama.this.URL);
                                          frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                          frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                          if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                             frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='3' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                          }

                                          frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                       } catch (Exception var248) {
                                          System.out.println("Notifikasi Bridging : " + var248);
                                       }
                                    }
                                 }

                                 if (frmUtama.this.task4.equals("")) {
                                    frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select concat(pemeriksaan_ralan.tgl_perawatan,' ',pemeriksaan_ralan.jam_rawat) from pemeriksaan_ralan where pemeriksaan_ralan.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                                    if (frmUtama.this.datajam.equals("")) {
                                       frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select if(diterima='0000-00-00 00:00:00','',diterima) from mutasi_berkas where mutasi_berkas.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                                    }

                                    if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "4", frmUtama.this.datajam})) {
                                       frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                                       try {
                                          frmUtama.this.TeksArea.append("Menjalankan WS taskid mulai pelayanan poli Mobile JKN Pasien Non BPJS/BPS Onsite\n");
                                          frmUtama.this.headers = new HttpHeaders();
                                          frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                          frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                          frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                          frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                          frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                          frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                          frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("no_rawat") + "\",\"taskid\": \"4\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                          frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                          frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                          frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                          System.out.println("URL : " + frmUtama.this.URL);
                                          frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                          frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                          if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                             frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='4' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                          }

                                          frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                       } catch (Exception var247) {
                                          System.out.println("Notifikasi Bridging : " + var247);
                                       }
                                    }
                                 }

                                 if (frmUtama.this.task5.equals("")) {
                                    frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select if(kembali='0000-00-00 00:00:00','',kembali) from mutasi_berkas where mutasi_berkas.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                                    if (frmUtama.this.datajam.equals("")) {
                                       frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select now() from reg_periksa where reg_periksa.stts='Sudah' and reg_periksa.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                                    }

                                    if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "5", frmUtama.this.datajam})) {
                                       frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                                       try {
                                          frmUtama.this.TeksArea.append("Menjalankan WS taskid selesai pelayanan poli Mobile JKN Pasien Non BPJS/BPS Onsite\n");
                                          frmUtama.this.headers = new HttpHeaders();
                                          frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                          frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                          frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                          frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                          frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                          frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                          frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("no_rawat") + "\",\"taskid\": \"5\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                          frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                          frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                          frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                          System.out.println("URL : " + frmUtama.this.URL);
                                          frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                          frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                          if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                             frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='5' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                          }

                                          frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                       } catch (Exception var246) {
                                          System.out.println("Notifikasi Bridging : " + var246);
                                       }
                                    }
                                 }

                                 if (frmUtama.this.task6.equals("")) {
                                    frmUtama.this.noresep = frmUtama.this.Sequel.cariIsi("select resep_obat.no_resep from resep_obat where resep_obat.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                                    if (!frmUtama.this.noresep.equals("")) {
                                       try {
                                          frmUtama.this.TeksArea.append("Menjalankan WS tambah antrian farmasi Mobile JKN Pasien Non BPJS/BPS Onsite\n");
                                          frmUtama.this.headers = new HttpHeaders();
                                          frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                          frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                          frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                          frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                          frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                          frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                          frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("no_rawat") + "\",\"jenisresep\": \"" + (frmUtama.this.Sequel.cariInteger("select count(resep_dokter_racikan.no_resep) from resep_dokter_racikan where resep_dokter_racikan.no_resep=?", frmUtama.this.noresep) > 0 ? "Racikan" : "Non Racikan") + "\",\"nomorantrean\": " + Integer.parseInt(StringUtils.right(frmUtama.this.noresep, 4)) + ",\"keterangan\": \"Resep dibuat secara elektronik di poli\"}";
                                          frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                          frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                          frmUtama.this.URL = frmUtama.this.link + "/antrean/farmasi/add";
                                          System.out.println("URL : " + frmUtama.this.URL);
                                          frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                          frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                          frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                       } catch (Exception var245) {
                                          System.out.println("Notifikasi Bridging : " + var245);
                                       }
                                    }

                                    frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select concat(resep_obat.tgl_perawatan,' ',resep_obat.jam) from resep_obat where resep_obat.tgl_perawatan<>'0000-00-00' and resep_obat.status='ralan' and resep_obat.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                                    if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "6", frmUtama.this.datajam})) {
                                       frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                                       try {
                                          frmUtama.this.TeksArea.append("Menjalankan WS taskid permintaan resep poli Mobile JKN Pasien Non BPJS/BPS Onsite\n");
                                          frmUtama.this.headers = new HttpHeaders();
                                          frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                          frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                          frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                          frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                          frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                          frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                          frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("no_rawat") + "\",\"taskid\": \"6\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                          frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                          frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                          frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                          System.out.println("URL : " + frmUtama.this.URL);
                                          frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                          frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                          if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                             frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='6' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                          }

                                          frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                       } catch (Exception var244) {
                                          System.out.println("Notifikasi Bridging : " + var244);
                                       }
                                    }
                                 }

                                 if (frmUtama.this.task7.equals("")) {
                                    frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select concat(resep_obat.tgl_penyerahan,' ',resep_obat.jam_penyerahan) from resep_obat where resep_obat.status='ralan' and resep_obat.no_rawat=? and concat(resep_obat.tgl_penyerahan,' ',resep_obat.jam_penyerahan)<>'0000-00-00 00:00:00'", frmUtama.this.rs.getString("no_rawat"));
                                    if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "7", frmUtama.this.datajam})) {
                                       frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                                       try {
                                          frmUtama.this.TeksArea.append("Menjalankan WS taskid validasi resep poli Mobile JKN Pasien Non BPJS/BPS Onsite\n");
                                          frmUtama.this.headers = new HttpHeaders();
                                          frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                          frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                          frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                          frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                          frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                          frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                          frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("no_rawat") + "\",\"taskid\": \"7\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                          frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                          frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                          frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                          System.out.println("URL : " + frmUtama.this.URL);
                                          frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                          frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                          if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                             frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='7' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                          }

                                          frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                       } catch (Exception var243) {
                                          System.out.println("Notifikasi Bridging : " + var243);
                                       }
                                    }
                                 }

                                 if (frmUtama.this.task99.equals("")) {
                                    frmUtama.this.datajam = frmUtama.this.Sequel.cariIsi("select now() from reg_periksa where reg_periksa.stts='Batal' and reg_periksa.no_rawat=?", frmUtama.this.rs.getString("no_rawat"));
                                    if (!frmUtama.this.datajam.equals("") && frmUtama.this.Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid", "?,?,?", "task id", 3, new String[]{frmUtama.this.rs.getString("no_rawat"), "99", frmUtama.this.datajam})) {
                                       frmUtama.this.parsedDate = frmUtama.this.dateFormat.parse(frmUtama.this.datajam);

                                       try {
                                          frmUtama.this.TeksArea.append("Menjalankan WS taskid batal pelayanan poli Mobile JKN Pasien Non BPJS/BPS Onsite\n");
                                          frmUtama.this.headers = new HttpHeaders();
                                          frmUtama.this.headers.setContentType(MediaType.APPLICATION_JSON);
                                          frmUtama.this.headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                                          frmUtama.this.utc = String.valueOf(frmUtama.this.api.GetUTCdatetimeAsString());
                                          frmUtama.this.headers.add("x-timestamp", frmUtama.this.utc);
                                          frmUtama.this.headers.add("x-signature", frmUtama.this.api.getHmac(frmUtama.this.utc));
                                          frmUtama.this.headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                                          frmUtama.this.requestJson = "{\"kodebooking\": \"" + frmUtama.this.rs.getString("no_rawat") + "\",\"taskid\": \"99\",\"waktu\": \"" + frmUtama.this.parsedDate.getTime() + "\"}";
                                          frmUtama.this.TeksArea.append("JSON : " + frmUtama.this.requestJson + "\n");
                                          frmUtama.this.requestEntity = new HttpEntity(frmUtama.this.requestJson, frmUtama.this.headers);
                                          frmUtama.this.URL = frmUtama.this.link + "/antrean/updatewaktu";
                                          System.out.println("URL : " + frmUtama.this.URL);
                                          frmUtama.this.root = frmUtama.this.mapper.readTree((String)frmUtama.this.api.getRest().exchange(frmUtama.this.URL, HttpMethod.POST, frmUtama.this.requestEntity, String.class, new Object[0]).getBody());
                                          frmUtama.this.nameNode = frmUtama.this.root.path("metadata");
                                          if (!frmUtama.this.nameNode.path("code").asText().equals("200")) {
                                             frmUtama.this.Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='99' and no_rawat='" + frmUtama.this.rs.getString("no_rawat") + "'");
                                          }

                                          frmUtama.this.TeksArea.append("respon WS BPJS : " + frmUtama.this.nameNode.path("code").asText() + " " + frmUtama.this.nameNode.path("message").asText() + "\n");
                                       } catch (Exception var242) {
                                          System.out.println("Notifikasi Bridging : " + var242);
                                       }
                                    }
                                 }
                              }
                           }
                        } catch (Exception var261) {
                           System.out.println("Notif : " + var261);
                        } finally {
                           if (frmUtama.this.rs2 != null) {
                              frmUtama.this.rs2.close();
                           }

                           if (frmUtama.this.ps2 != null) {
                              frmUtama.this.ps2.close();
                           }

                        }
                     }
                  } catch (Exception var263) {
                     System.out.println("Notif Ketersediaan : " + var263);
                  } finally {
                     if (frmUtama.this.rs != null) {
                        frmUtama.this.rs.close();
                     }

                     if (frmUtama.this.ps != null) {
                        frmUtama.this.ps.close();
                     }

                  }

                  frmUtama.this.TeksArea.append("Proses update selesai\n");
               } catch (Exception var274) {
                  System.out.println("Notif : " + var274);
               }
            }

         }
      };
      (new Timer(1000, taskPerformer)).start();
   }
}
