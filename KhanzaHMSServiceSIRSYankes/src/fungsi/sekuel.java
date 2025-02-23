package fungsi;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import uz.ncipro.calendar.JDateTimePicker;

public final class sekuel {
   private ImageIcon icon = null;
   private ImageIcon iconThumbnail = null;
   private String folder;
   private final Connection connect = koneksiDB.condb();
   private PreparedStatement ps;
   private ResultSet rs;
   private int angka = 0;
   private double angka2 = 0.0D;
   private String dicari = "";
   private Date tanggal = new Date();
   private boolean bool = false;
   private DecimalFormat df2 = new DecimalFormat("####");

   public void menyimpan(String table, String value, String sama) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         try {
            this.ps.executeUpdate();
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
            JOptionPane.showMessageDialog((Component)null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public void menyimpan2(String table, String value, String sama) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         try {
            this.ps.executeUpdate();
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public boolean menyimpantf(String table, String value, String sama) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");
         this.ps.executeUpdate();
         if (this.ps != null) {
            this.ps.close();
         }

         return true;
      } catch (Exception var5) {
         System.out.println("Notifikasi : " + var5);
         JOptionPane.showMessageDialog((Component)null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
         return false;
      }
   }

   public boolean menyimpantf2(String table, String value, String sama) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");
         this.ps.executeUpdate();
         if (this.ps != null) {
            this.ps.close();
         }

         return true;
      } catch (Exception var5) {
         System.out.println("Notifikasi : " + var5);
         return false;
      }
   }

   public boolean menyimpantf(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
      this.bool = false;

      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         for(this.angka = 1; this.angka <= i; ++this.angka) {
            this.ps.setString(this.angka, a[this.angka - 1]);
         }

         this.ps.executeUpdate();
         if (this.ps != null) {
            this.ps.close();
         }

         this.bool = true;
      } catch (Exception var12) {
         try {
            this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

            for(this.angka = 1; this.angka <= j; ++this.angka) {
               this.ps.setString(this.angka, b[this.angka - 1]);
            }

            this.ps.executeUpdate();
            if (this.ps != null) {
               this.ps.close();
            }

            this.bool = true;
         } catch (Exception var11) {
            this.bool = false;
            System.out.println("Notifikasi : " + var11);
         }
      }

      return this.bool;
   }

   public void menyimpan(String table, String value, String sama, int i, String[] a) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var11) {
            System.out.println("Notifikasi : " + var11);
            JOptionPane.showMessageDialog((Component)null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var13) {
         System.out.println("Notifikasi : " + var13);
      }

   }

   public void menyimpan2(String table, String value, String sama, int i, String[] a) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var11) {
            System.out.println("Notifikasi : " + var11);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var13) {
         System.out.println("Notifikasi : " + var13);
      }

   }

   public boolean menyimpantf(String table, String value, String sama, int i, String[] a) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         for(this.angka = 1; this.angka <= i; ++this.angka) {
            this.ps.setString(this.angka, a[this.angka - 1]);
         }

         this.ps.executeUpdate();
         if (this.ps != null) {
            this.ps.close();
         }

         return true;
      } catch (Exception var7) {
         System.out.println("Notifikasi : " + var7);
         if (var7.toString().contains("Duplicate")) {
            JOptionPane.showMessageDialog((Component)null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
         } else {
            JOptionPane.showMessageDialog((Component)null, "Maaf, gagal menyimpan data. Ada kesalahan Query...!");
         }

         return false;
      }
   }

   public boolean menyimpantf2(String table, String value, String sama, int i, String[] a) {
      this.bool = true;

      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
            this.bool = true;
         } catch (Exception var11) {
            this.bool = false;
            System.out.println("Notifikasi : " + var11);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var13) {
         this.bool = false;
         System.out.println("Notifikasi : " + var13);
      }

      return this.bool;
   }

   public void menyimpan(String table, String value, int i, String[] a) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var10) {
            System.out.println("Notifikasi : " + var10);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var12) {
         System.out.println("Notifikasi : " + var12);
      }

   }

   public void menyimpan2(String table, String value, int i, String[] a) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var10) {
            System.out.println("Notifikasi " + table + " : " + var10);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var12) {
      }

   }

   public void menyimpan(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         for(this.angka = 1; this.angka <= i; ++this.angka) {
            this.ps.setString(this.angka, a[this.angka - 1]);
         }

         this.ps.executeUpdate();
         if (this.ps != null) {
            this.ps.close();
         }
      } catch (Exception var12) {
         try {
            this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

            for(this.angka = 1; this.angka <= j; ++this.angka) {
               this.ps.setString(this.angka, b[this.angka - 1]);
            }

            this.ps.executeUpdate();
            if (this.ps != null) {
               this.ps.close();
            }
         } catch (Exception var11) {
            System.out.println("Notifikasi : " + var11);
         }
      }

   }

   public void menyimpan3(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         for(this.angka = 1; this.angka <= i; ++this.angka) {
            this.ps.setString(this.angka, a[this.angka - 1]);
         }

         this.ps.executeUpdate();
         JOptionPane.showMessageDialog((Component)null, "Proses simpan berhasil..!!");
         if (this.ps != null) {
            this.ps.close();
         }
      } catch (Exception var12) {
         try {
            this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

            for(this.angka = 1; this.angka <= j; ++this.angka) {
               this.ps.setString(this.angka, b[this.angka - 1]);
            }

            this.ps.executeUpdate();
            JOptionPane.showMessageDialog((Component)null, "Proses simpan berhasil..!!");
            if (this.ps != null) {
               this.ps.close();
            }
         } catch (Exception var11) {
            System.out.println("Notifikasi : " + var11);
         }
      }

   }

   public void menyimpan(String table, String value) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ")");

         try {
            this.ps.executeUpdate();
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

   }

   public void menyimpan(String table, String isisimpan, String isiedit, String acuan_field) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + isisimpan + ")");
         this.ps.executeUpdate();
         if (this.ps != null) {
            this.ps.close();
         }
      } catch (Exception var8) {
         try {
            this.ps = this.connect.prepareStatement("update " + table + " set " + isiedit + " where " + acuan_field);
            this.ps.executeUpdate();
            if (this.ps != null) {
               this.ps.close();
            }
         } catch (Exception var7) {
            System.out.println("Notifikasi Edit : " + var7);
         }
      }

   }

   public void menyimpan(String table, String value, String sama, JTextField AlmGb) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ",?)");

         try {
            this.ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), (new File(AlmGb.getText())).length());
            this.ps.executeUpdate();
         } catch (Exception var10) {
            System.out.println("Notifikasi : " + var10);
            JOptionPane.showMessageDialog((Component)null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var12) {
         System.out.println("Notifikasi : " + var12);
      }

   }

   public void menyimpan(String table, String value, String sama, JTextField AlmGb, JTextField AlmPhoto) {
      try {
         this.ps = this.connect.prepareStatement("insert into " + table + " values(" + value + ",?,?)");

         try {
            this.ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), (new File(AlmGb.getText())).length());
            this.ps.setBinaryStream(2, new FileInputStream(AlmPhoto.getText()), (new File(AlmPhoto.getText())).length());
            this.ps.executeUpdate();
         } catch (Exception var11) {
            System.out.println("Notifikasi : " + var11);
            JOptionPane.showMessageDialog((Component)null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var13) {
         System.out.println("Notifikasi : " + var13);
      }

   }

   public void meghapus(String table, String field, String nilai_field) {
      try {
         this.ps = this.connect.prepareStatement("delete from " + table + " where " + field + "=?");

         try {
            this.ps.setString(1, nilai_field);
            this.ps.executeUpdate();
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
            JOptionPane.showMessageDialog((Component)null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public void meghapus2(String table, String field, String nilai_field) {
      try {
         this.ps = this.connect.prepareStatement("delete from " + table + " where " + field + "=?");

         try {
            this.ps.setString(1, nilai_field);
            this.ps.executeUpdate();
            JOptionPane.showMessageDialog((Component)null, "Proses hapus berhasil...!!!!");
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
            JOptionPane.showMessageDialog((Component)null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public void meghapus3(String table, String field, String nilai_field) {
      try {
         this.ps = this.connect.prepareStatement("delete from " + table + " where " + field + "=?");

         try {
            this.ps.setString(1, nilai_field);
            this.ps.executeUpdate();
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public void mengedit(String table, String acuan_field, String update) {
      try {
         this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

         try {
            this.ps.executeUpdate();
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Gagal Mengedit. Mungkin kode sudah digunakan sebelumnya...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public boolean mengedittf(String table, String acuan_field, String update) {
      this.bool = true;

      try {
         this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

         try {
            this.ps.executeUpdate();
            this.bool = true;
         } catch (Exception var9) {
            this.bool = false;
            System.out.println("Notifikasi : " + var9);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Gagal Mengedit. Mungkin kode sudah digunakan sebelumnya...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         this.bool = false;
         System.out.println("Notifikasi : " + var11);
      }

      return this.bool;
   }

   public void mengedit(String table, String acuan_field, String update, int i, String[] a) {
      try {
         this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var11) {
            System.out.println("Notifikasi : " + var11);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Gagal Mengedit. Periksa kembali data...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var13) {
         System.out.println("Notifikasi : " + var13);
      }

   }

   public void mengedit2(String table, String acuan_field, String update, int i, String[] a) {
      try {
         this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
            JOptionPane.showMessageDialog((Component)null, "Proses edit berhasil...!!!!");
         } catch (Exception var11) {
            System.out.println("Notifikasi : " + var11);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Gagal mengedit. Periksa kembali data...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var13) {
         System.out.println("Notifikasi : " + var13);
      }

   }

   public void mengedit3(String table, String acuan_field, String update, int i, String[] a) {
      try {
         this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var11) {
            System.out.println("Notifikasi : " + var11);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var13) {
         System.out.println("Notifikasi : " + var13);
      }

   }

   public boolean mengedittf(String table, String acuan_field, String update, int i, String[] a) {
      this.bool = true;

      try {
         this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

         try {
            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
            this.bool = true;
         } catch (Exception var11) {
            this.bool = false;
            System.out.println("Notifikasi : " + var11);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Gagal Mengedit. Periksa kembali data...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var13) {
         this.bool = false;
         System.out.println("Notifikasi : " + var13);
      }

      return this.bool;
   }

   public void mengedit(String table, String acuan_field, String update, JTextField AlmGb) {
      try {
         this.ps = this.connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);

         try {
            this.ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), (new File(AlmGb.getText())).length());
            this.ps.executeUpdate();
         } catch (Exception var10) {
            System.out.println("Notifikasi : " + var10);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Pilih dulu data yang mau anda edit...\n Klik data pada table untuk memilih...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var12) {
         System.out.println("Notifikasi : " + var12);
      }

   }

   public void query(String qry) {
      try {
         this.ps = this.connect.prepareStatement(qry);

         try {
            this.ps.executeQuery();
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Query tidak bisa dijalankan...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

   }

   public void queryu(String qry) {
      try {
         this.ps = this.connect.prepareStatement(qry);

         try {
            this.ps.executeUpdate();
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Query tidak bisa dijalankan...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

   }

   public boolean queryutf(String qry) {
      this.bool = false;

      try {
         this.ps = this.connect.prepareStatement(qry);

         try {
            this.ps.executeUpdate();
            this.bool = true;
         } catch (Exception var7) {
            this.bool = false;
            System.out.println("Notifikasi : " + var7);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Query tidak bisa dijalankan...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         this.bool = false;
         System.out.println("Notifikasi : " + var9);
      }

      return this.bool;
   }

   public void queryu(String qry, String parameter) {
      try {
         this.ps = this.connect.prepareStatement(qry);

         try {
            this.ps.setString(1, parameter);
            this.ps.executeUpdate();
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
            JOptionPane.showMessageDialog((Component)null, "Maaf, Query tidak bisa dijalankan...!!!!");
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

   }

   public void queryu2(String qry) {
      try {
         this.ps = this.connect.prepareStatement(qry);

         try {
            this.ps.executeUpdate();
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

   }

   public void queryu2(String qry, int i, String[] a) {
      try {
         try {
            this.ps = this.connect.prepareStatement(qry);

            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public boolean queryu2tf(String qry, int i, String[] a) {
      this.bool = false;

      try {
         try {
            this.ps = this.connect.prepareStatement(qry);

            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
            this.bool = true;
         } catch (Exception var9) {
            this.bool = false;
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

      return this.bool;
   }

   public void queryu3(String qry, int i, String[] a) {
      try {
         try {
            this.ps = this.connect.prepareStatement(qry);

            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public void queryu4(String qry, int i, String[] a) {
      try {
         try {
            this.ps = this.connect.prepareStatement(qry);

            for(this.angka = 1; this.angka <= i; ++this.angka) {
               this.ps.setString(this.angka, a[this.angka - 1]);
            }

            this.ps.executeUpdate();
         } catch (Exception var9) {
         } finally {
            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
      }

   }

   public void AutoComitFalse() {
      try {
         this.connect.setAutoCommit(false);
      } catch (Exception var2) {
      }

   }

   public void AutoComitTrue() {
      try {
         this.connect.setAutoCommit(true);
      } catch (Exception var2) {
      }

   }

   public void cariIsi(String sql, JComboBox cmb) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               String dicari = this.rs.getString(1);
               cmb.setSelectedItem(dicari);
            } else {
               cmb.setSelectedItem("");
            }
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

   }

   public void cariIsi(String sql, JDateTimePicker dtp) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               try {
                  dtp.setDisplayFormat("yyyy-MM-dd");
                  dtp.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(this.rs.getString(1)));
                  dtp.setDisplayFormat("dd-MM-yyyy");
               } catch (Exception var9) {
                  System.out.println(var9);
               }
            }
         } catch (Exception var10) {
            System.out.println("Notifikasi : " + var10);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var12) {
         System.out.println("Notifikasi : " + var12);
      }

   }

   public void cariIsi(String sql, JTextField txt) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               txt.setText(this.rs.getString(1));
            } else {
               txt.setText("");
            }
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

   }

   public int cariRegistrasi(String norawat) {
      this.angka = 0;

      try {
         this.ps = this.connect.prepareStatement("select count(billing.no_rawat) from billing where billing.no_rawat=?");

         try {
            this.ps.setString(1, norawat);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.angka = this.rs.getInt(1);
            }
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println(var9);
      }

      return this.angka;
   }

   public void cariIsi(String sql, JTextField txt, String kunci) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.ps.setString(1, kunci);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               txt.setText(this.rs.getString(1));
            } else {
               txt.setText("");
            }
         } catch (SQLException var9) {
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public void cariIsi(String sql, JTextArea txt, String kunci) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.ps.setString(1, kunci);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               txt.setText(this.rs.getString(1));
            } else {
               txt.setText("");
            }
         } catch (SQLException var9) {
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

   }

   public void cariIsi(String sql, JLabel txt) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               txt.setText(this.rs.getString(1));
            } else {
               txt.setText("");
            }
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

   }

   public String cariIsi(String sql) {
      this.dicari = "";

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.dicari = this.rs.getString(1);
            } else {
               this.dicari = "";
            }
         } catch (Exception var7) {
            this.dicari = "";
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

      return this.dicari;
   }

   public ByteArrayInputStream cariGambar(String sql) {
      ByteArrayInputStream inputStream = null;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               inputStream = new ByteArrayInputStream(this.rs.getBytes(1));
            }
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

      return inputStream;
   }

   public String cariIsi(String sql, String data) {
      this.dicari = "";

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.ps.setString(1, data);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.dicari = this.rs.getString(1);
            } else {
               this.dicari = "";
            }
         } catch (Exception var8) {
            this.dicari = "";
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

      return this.dicari;
   }

   public Date cariIsi2(String sql) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.tanggal = this.rs.getDate(1);
            } else {
               this.tanggal = new Date();
            }
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

      return this.tanggal;
   }

   public Integer cariInteger(String sql) {
      this.angka = 0;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.angka = this.rs.getInt(1);
            } else {
               this.angka = 0;
            }
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

      return this.angka;
   }

   public Integer cariIntegerCount(String sql) {
      this.angka = 0;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            for(this.rs = this.ps.executeQuery(); this.rs.next(); this.angka += this.rs.getInt(1)) {
            }
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

      return this.angka;
   }

   public Integer cariInteger(String sql, String data) {
      this.angka = 0;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.ps.setString(1, data);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.angka = this.rs.getInt(1);
            } else {
               this.angka = 0;
            }
         } catch (Exception var8) {
            this.angka = 0;
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

      return this.angka;
   }

   public Integer cariInteger(String sql, String data, String data2) {
      this.angka = 0;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.ps.setString(1, data);
            this.ps.setString(2, data2);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.angka = this.rs.getInt(1);
            } else {
               this.angka = 0;
            }
         } catch (Exception var9) {
            this.angka = 0;
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

      return this.angka;
   }

   public Integer cariInteger(String sql, String data, String data2, String data3) {
      this.angka = 0;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.ps.setString(1, data);
            this.ps.setString(2, data2);
            this.ps.setString(3, data3);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.angka = this.rs.getInt(1);
            } else {
               this.angka = 0;
            }
         } catch (Exception var10) {
            this.angka = 0;
            System.out.println("Notifikasi : " + var10);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var12) {
         System.out.println("Notifikasi : " + var12);
      }

      return this.angka;
   }

   public Integer cariInteger2(String sql) {
      this.angka = 0;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            this.rs.last();
            this.angka = this.rs.getRow();
            if (this.angka < 1) {
               this.angka = 0;
            }
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

      return this.angka;
   }

   public void cariIsiAngka(String sql, JTextField txt) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               txt.setText(this.df2.format(this.rs.getDouble(1)));
            } else {
               txt.setText("0");
            }
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

   }

   public void cariIsiAngka(String sql, JLabel txt) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               txt.setText(this.df2.format(this.rs.getDouble(1)));
            } else {
               txt.setText("0");
            }
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

   }

   public double cariIsiAngka(String sql) {
      this.angka2 = 0.0D;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.angka2 = this.rs.getDouble(1);
            } else {
               this.angka2 = 0.0D;
            }
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

      return this.angka2;
   }

   public double cariIsiAngka(String sql, String data) {
      this.angka2 = 0.0D;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.ps.setString(1, data);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.angka2 = this.rs.getDouble(1);
            } else {
               this.angka2 = 0.0D;
            }
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

      return this.angka2;
   }

   public double cariIsiAngka2(String sql, String data, String data2) {
      this.angka2 = 0.0D;

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.ps.setString(1, data);
            this.ps.setString(2, data2);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.angka2 = this.rs.getDouble(1);
            } else {
               this.angka2 = 0.0D;
            }
         } catch (Exception var9) {
            System.out.println("Notifikasi : " + var9);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var11) {
         System.out.println("Notifikasi : " + var11);
      }

      return this.angka2;
   }

   public void cariGambar(String sql, JLabel txt) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.icon = new ImageIcon(this.rs.getBlob(1).getBytes(1L, (int)this.rs.getBlob(1).length()));
               this.createThumbnail();
               txt.setIcon(this.icon);
            } else {
               txt.setText((String)null);
            }
         } catch (Exception var8) {
            System.out.println("Notifikasi : " + var8);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var10) {
         System.out.println("Notifikasi : " + var10);
      }

   }

   public void cariGambar(String sql, Canvas txt, String text) {
      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();

            for(int var4 = 0; this.rs.next(); ++var4) {
               ((sekuel.Painter)txt).setImage(this.gambar(text));
               Blob blob = this.rs.getBlob(5);
               ((sekuel.Painter)txt).setImageIcon(new ImageIcon(blob.getBytes(1L, (int)blob.length())));
            }
         } catch (Exception var10) {
            this.cetak(var10.toString());
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var12) {
         System.out.println("Notifikasi : " + var12);
      }

   }

   public String cariString(String sql) {
      this.dicari = "";

      try {
         this.ps = this.connect.prepareStatement(sql);

         try {
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
               this.dicari = this.rs.getString(1);
            } else {
               this.dicari = "";
            }
         } catch (Exception var7) {
            System.out.println("Notifikasi : " + var7);
         } finally {
            if (this.rs != null) {
               this.rs.close();
            }

            if (this.ps != null) {
               this.ps.close();
            }

         }
      } catch (Exception var9) {
         System.out.println("Notifikasi : " + var9);
      }

      return this.dicari;
   }

   private String gambar(String id) {
      return this.folder + File.separator + id.trim() + ".jpg";
   }

   public void Tabel(JTable tb, int[] lebar) {
      tb.setAutoResizeMode(0);
      this.angka = tb.getColumnCount();

      for(int i = 0; i < this.angka; ++i) {
         TableColumn tbc = tb.getColumnModel().getColumn(i);
         tbc.setPreferredWidth(lebar[i]);
      }

   }

   private void createThumbnail() {
      short maxDim = 150;

      try {
         Image inImage = this.icon.getImage();
         double scale = (double)maxDim / (double)inImage.getHeight((ImageObserver)null);
         if (inImage.getWidth((ImageObserver)null) > inImage.getHeight((ImageObserver)null)) {
            scale = (double)maxDim / (double)inImage.getWidth((ImageObserver)null);
         }

         int scaledW = (int)(scale * (double)inImage.getWidth((ImageObserver)null));
         int scaledH = (int)(scale * (double)inImage.getHeight((ImageObserver)null));
         BufferedImage outImage = new BufferedImage(scaledW, scaledH, 1);
         AffineTransform tx = new AffineTransform();
         if (scale < 1.0D) {
            tx.scale(scale, scale);
         }

         Graphics2D g2d = outImage.createGraphics();
         g2d.drawImage(inImage, tx, (ImageObserver)null);
         g2d.dispose();
         this.iconThumbnail = new ImageIcon(outImage);
      } catch (Exception var10) {
      }

   }

   private void cetak(String str) {
      System.out.println(str);
   }

   public class Painter extends Canvas {
      Image image;

      private void setImage(String file) {
         URL url = null;

         try {
            url = (new File(file)).toURI().toURL();
         } catch (MalformedURLException var4) {
            this.cetak(var4.toString());
         }

         this.image = this.getToolkit().getImage(url);
         this.repaint();
      }

      private void setImageIcon(ImageIcon file) {
         this.image = file.getImage();
         this.repaint();
      }

      public void paint(Graphics g) {
         double d = (double)(this.image.getHeight(this) / this.getHeight());
         double w = (double)this.image.getWidth(this) / d;
         double x = (double)(this.getWidth() / 2) - w / 2.0D;
         g.drawImage(this.image, (int)x, 0, (int)w, this.getHeight(), this);
      }

      private void cetak(String str) {
         System.out.println(str);
      }
   }

   public class NIOCopier {
      public NIOCopier(String asal, String tujuan) throws IOException {
         FileInputStream inFile = new FileInputStream(asal);

         FileOutputStream outFile;
         try {
            outFile = new FileOutputStream(tujuan);
            FileChannel inChannel = inFile.getChannel();

            FileChannel outChannel;
            try {
               outChannel = outFile.getChannel();
               ByteBuffer buffer = ByteBuffer.allocate(1048576);

               while(inChannel.read(buffer) != -1) {
                  buffer.flip();

                  while(buffer.hasRemaining()) {
                     outChannel.write(buffer);
                  }

                  buffer.clear();
               }
            } catch (Throwable var12) {
               if (inChannel != null) {
                  try {
                     inChannel.close();
                  } catch (Throwable var11) {
                     var12.addSuppressed(var11);
                  }
               }

               throw var12;
            }

            if (inChannel != null) {
               inChannel.close();
            }

            outChannel.close();
         } catch (Throwable var13) {
            try {
               inFile.close();
            } catch (Throwable var10) {
               var13.addSuppressed(var10);
            }

            throw var13;
         }

         inFile.close();
         outFile.close();
      }
   }
}
