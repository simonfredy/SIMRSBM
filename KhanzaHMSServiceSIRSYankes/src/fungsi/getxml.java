package fungsi;

import AESsecurity.EnkripsiAES;
import java.io.FileInputStream;
import java.util.Properties;

public class getxml {
   private static final Properties prop = new Properties();
   private static String var = "";

   public getxml() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
      } catch (Exception var2) {
         System.out.println("Notif : " + var2);
      }

   }

   public static String cariCepat() {
      try {
         var = prop.getProperty("CARICEPAT");
      } catch (Exception var1) {
         var = "tidak aktif";
      }

      return var;
   }

   public static String HOST() {
      try {
         var = EnkripsiAES.decrypt(prop.getProperty("HOSTHYBRIDWEB"));
      } catch (Exception var1) {
         var = "localhost";
      }

      return var;
   }

   public static String PORT() {
      try {
         var = EnkripsiAES.decrypt(prop.getProperty("PORT"));
      } catch (Exception var1) {
         var = "3306";
      }

      return var;
   }

   public static String DATABASE() {
      try {
         var = EnkripsiAES.decrypt(prop.getProperty("DATABASE"));
      } catch (Exception var1) {
         var = "sik";
      }

      return var;
   }
}
