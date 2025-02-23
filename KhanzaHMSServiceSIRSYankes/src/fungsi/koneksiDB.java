package fungsi;

import AESsecurity.EnkripsiAES;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.awt.Component;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.swing.JOptionPane;

public class koneksiDB {
   private static Connection connection = null;
   private static final Properties prop = new Properties();
   private static final MysqlDataSource dataSource = new MysqlDataSource();
   private static String var = "";

   public static Connection condb() {
      if (connection == null) {
         try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dataSource.setURL("jdbc:mysql://" + EnkripsiAES.decrypt(prop.getProperty("HOST")) + ":" + EnkripsiAES.decrypt(prop.getProperty("PORT")) + "/" + EnkripsiAES.decrypt(prop.getProperty("DATABASE")) + "?zeroDateTimeBehavior=convertToNull&amp;autoReconnect=true");
            dataSource.setUser(EnkripsiAES.decrypt(prop.getProperty("USER")));
            dataSource.setPassword(EnkripsiAES.decrypt(prop.getProperty("PAS")));
            connection = dataSource.getConnection();
            System.out.println("  Koneksi Berhasil. Sorry bro loading, silahkan baca dulu.... \n\n\tSoftware ini adalah Software Menejemen Rumah Sakit/Klinik/\n  Puskesmas yang  gratis dan boleh digunakan siapa saja tanpa dikenai \n  biaya apapun. Dilarang keras memperjualbelikan/mengambil \n  keuntungan dari Software ini dalam bentuk apapun tanpa seijin pembuat \n  software (Khanza.Soft Media). Bagi yang sengaja memperjualbelikan/\n  mengambil keuntangan dari softaware ini tanpa ijin, kami sumpahi sial \n  1000 turunan, miskin sampai 500 turunan. Selalu mendapat kecelakaan \n  sampai 400 turunan. Anak pertamanya cacat tidak punya kaki sampai 300 \n  turunan. Susah cari jodoh sampai umur 50 tahun sampai 200 turunan.\n  Ya Alloh maafkan kami karena telah berdoa buruk, semua ini kami lakukan\n  karena kami tidak pernah rela karya kami dibajak tanpa ijin.\n\n                                                                           \n  #    ____  ___  __  __  ____   ____    _  __ _                              \n  #   / ___||_ _||  \\/  ||  _ \\ / ___|  | |/ /| |__    __ _  _ __   ____ __ _ \n  #   \\___ \\ | | | |\\/| || |_) |\\___ \\  | ' / | '_ \\  / _` || '_ \\ |_  // _` |\n  #    ___) || | | |  | ||  _ <  ___) | | . \\ | | | || (_| || | | | / /| (_| |\n  #   |____/|___||_|  |_||_| \\_\\|____/  |_|\\_\\|_| |_| \\__,_||_| |_|/___|\\__,_|\n  #                                                                           \n                                                                           \n  Licensi yang dianut di software ini https://en.wikipedia.org/wiki/Aladdin_Free_Public_License \n  Informasi dan panduan bisa dicek di halaman https://github.com/mas-elkhanza/SIMRS-Khanza/wiki \n                                                                           ");
         } catch (Exception var3) {
            System.out.println("Notif : " + var3);

            try {
               if (connection.isClosed()) {
                  prop.loadFromXML(new FileInputStream("setting/database.xml"));
                  dataSource.setURL("jdbc:mysql://" + EnkripsiAES.decrypt(prop.getProperty("HOST")) + ":" + EnkripsiAES.decrypt(prop.getProperty("PORT")) + "/" + EnkripsiAES.decrypt(prop.getProperty("DATABASE")) + "?zeroDateTimeBehavior=convertToNull&amp;autoReconnect=true&amp;cachePrepStmts=true");
                  dataSource.setUser(EnkripsiAES.decrypt(prop.getProperty("USER")));
                  dataSource.setPassword(EnkripsiAES.decrypt(prop.getProperty("PAS")));
                  connection = dataSource.getConnection();
               }
            } catch (Exception var2) {
               JOptionPane.showMessageDialog((Component)null, "Koneksi Putus : " + var3);
            }
         }
      }

      return connection;
   }

   public static String HOST() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("HOST"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String DATABASE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("DATABASE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PORT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("PORT"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String USER() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("USER"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String CARICEPAT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("CARICEPAT");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String HOSTHYBRIDWEB() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("HOSTHYBRIDWEB"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String HYBRIDWEB() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("HYBRIDWEB");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PORTWEB() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("PORTWEB");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String ANTRIAN() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("ANTRIAN");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String ALARMAPOTEK() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("ALARMAPOTEK");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String FORMALARMAPOTEK() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("FORMALARMAPOTEK");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String ALARMLAB() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("ALARMLAB");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String FORMALARMLAB() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("FORMALARMLAB");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String ALARMRADIOLOGI() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("ALARMRADIOLOGI");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String FORMALARMRADIOLOGI() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("FORMALARMRADIOLOGI");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String ALARMRSISRUTE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("ALARMRSISRUTE");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String ALARMBOOKINGPERIKSA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("ALARMBOOKINGPERIKSA");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String ALARMPENGADUANPASIEN() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("ALARMPENGADUANPASIEN");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String MENUTRANSPARAN() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("MENUTRANSPARAN");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLAPIBPJS() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLAPIBPJS");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String SECRETKEYAPIBPJS() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIBPJS"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String CONSIDAPIBPJS() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("CONSIDAPIBPJS"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLAPIAPLICARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLAPIAPLICARE");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String SECRETKEYAPIAPLICARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIAPLICARE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String CONSIDAPIAPLICARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("CONSIDAPIAPLICARE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLAPIPCARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLAPIPCARE");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String SECRETKEYAPIPCARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIPCARE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String CONSIDAPIPCARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("CONSIDAPIPCARE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PASSPCARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("PASSPCARE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String USERPCARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("USERPCARE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String DIVREGPCARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("DIVREGPCARE");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String KACABPCARE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("KACABPCARE");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLAPISISRUTE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLAPISISRUTE");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String IDSISRUTE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("IDSISRUTE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PASSSISRUTE() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("PASSSISRUTE"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLAPISIRS() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLAPISIRS");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String IDSIRS() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("IDSIRS"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PASSSIRS() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("PASSSIRS"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLAPICORONA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLAPICORONA");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String IDCORONA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("IDCORONA"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PASSCORONA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("PASSCORONA"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLAPISITT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLAPISITT");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String IDSITT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("IDSITT"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PASSSITT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("PASSSITT"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String KABUPATENSITT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("KABUPATENSITT");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String KAMARAKTIFRANAP() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("KAMARAKTIFRANAP");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String DOKTERAKTIFKASIRRALAN() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("DOKTERAKTIFKASIRRALAN");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String POLIAKTIFKASIRRALAN() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("POLIAKTIFKASIRRALAN");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String RUANGANAKTIFINVENTARIS() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("RUANGANAKTIFINVENTARIS");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String BASENOREG() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("BASENOREG");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URUTNOREG() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URUTNOREG");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String JADWALDOKTERDIREGISTRASI() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("JADWALDOKTERDIREGISTRASI");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String IPPRINTERTRACER() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("IPPRINTERTRACER");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLAPIINHEALTH() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLAPIINHEALTH");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String TOKENINHEALTH() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("TOKENINHEALTH");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PEMBULATANHARGAOBAT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("PEMBULATANHARGAOBAT");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String AKTIFKANBATCHOBAT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("AKTIFKANBATCHOBAT");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String CETAKRINCIANOBAT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("CETAKRINCIANOBAT");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String AKTIFKANBILLINGPARSIAL() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("AKTIFKANBILLINGPARSIAL");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLDUKCAPILJAKARTA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLDUKCAPILJAKARTA");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String USERDUKCAPILJAKARTA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("USERDUKCAPILJAKARTA"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PASSDUKCAPILJAKARTA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("PASSDUKCAPILJAKARTA"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String VAR1DUKCAPILJAKARTA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("VAR1DUKCAPILJAKARTA");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String VAR2DUKCAPILJAKARTA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("VAR2DUKCAPILJAKARTA");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String URLDUKCAPIL() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("URLDUKCAPIL");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String USERDUKCAPIL() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("USERDUKCAPIL"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String PASSDUKCAPIL() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("PASSDUKCAPIL"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String IPUSERDUKCAPIL() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("IPUSERDUKCAPIL");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String AKTIFKANTRACKSQL() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("AKTIFKANTRACKSQL");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String HOSTWSLICA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("HOSTWSLICA");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String KEYWSLICA() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = EnkripsiAES.decrypt(prop.getProperty("KEYWSLICA"));
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String DEPOAKTIFOBAT() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("DEPOAKTIFOBAT");
      } catch (Exception var1) {
         var = "";
      }

      return var;
   }

   public static String STOKKOSONGRESEP() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         var = prop.getProperty("STOKKOSONGRESEP");
      } catch (Exception var1) {
         var = "no";
      }

      return var;
   }

   public static String HPPFARMASI() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         if (prop.getProperty("HPPFARMASI").equals("h_beli")) {
            var = "h_beli";
         } else {
            var = "dasar";
         }
      } catch (Exception var1) {
         var = "dasar";
      }

      return var;
   }

   public static String HPPTOKO() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         if (prop.getProperty("HPPTOKO").equals("h_beli")) {
            var = "h_beli";
         } else {
            var = "dasar";
         }
      } catch (Exception var1) {
         var = "dasar";
      }

      return var;
   }
}
