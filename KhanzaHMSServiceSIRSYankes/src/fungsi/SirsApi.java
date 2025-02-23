package fungsi;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class SirsApi {
   private static final Properties prop = new Properties();
   private String Key;
   private String pass;
   private long millis;

   public SirsApi() {
      try {
         prop.loadFromXML(new FileInputStream("setting/database.xml"));
         this.pass = koneksiDB.PASSSIRS();
      } catch (Exception var2) {
         System.out.println("Notifikasi : " + var2);
      }

   }

   public String getHmac() {
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         byte[] hashInBytes = md.digest(this.pass.getBytes(StandardCharsets.UTF_8));
         StringBuilder sb = new StringBuilder();
         byte[] var4 = hashInBytes;
         int var5 = hashInBytes.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            byte b = var4[var6];
            sb.append(String.format("%02x", b));
         }

         this.Key = sb.toString();
      } catch (Exception var8) {
         System.out.println("Notifikasi : " + var8);
      }

      return this.Key;
   }

   public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
      SSLContext sslContext = SSLContext.getInstance("SSL");
      TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
         public X509Certificate[] getAcceptedIssuers() {
            return null;
         }

         public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
         }

         public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
         }
      }};
      sslContext.init((KeyManager[])null, trustManagers, new SecureRandom());
      SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      Scheme scheme = new Scheme("https", 443, sslFactory);
      HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
      factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
      return new RestTemplate(factory);
   }

   public long GetUTCdatetimeAsString() {
      this.millis = System.currentTimeMillis();
      return this.millis / 1000L;
   }
}
