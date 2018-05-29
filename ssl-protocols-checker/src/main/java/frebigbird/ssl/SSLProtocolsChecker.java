package frebigbird.ssl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLProtocolsChecker {
   private final static TrustManager[] TRUST_ALL_CERTS = new X509TrustManager[]{new X509TrustManager()
    {
        public java.security.cert.X509Certificate[] getAcceptedIssuers()
        {
            return new java.security.cert.X509Certificate[]{};
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
        {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
        {
        }
    }};
   
   private void method() throws NoSuchAlgorithmException, KeyManagementException, IOException {
      SSLContext _context = SSLContext.getInstance("TLS");
        _context.init(null, TRUST_ALL_CERTS, SecureRandom.getInstance("SHA1PRNG"));

      SSLServerSocketFactory factory = _context.getServerSocketFactory();
      SSLServerSocket socket = (SSLServerSocket) factory.createServerSocket(19090, 50);
      //socket.setEnabledProtocols(new String[] {"TLSv1"});

      System.out.println(">>> Supported Protocols");
      System.out.println("-------------------");
      for (String protocol: socket.getSupportedProtocols()) {
         System.out.println(protocol);
      }
      
      System.out.println("\n");
      System.out.println(">>> Enabled Protocols");
      System.out.println("-------------------");
      for (String protocol: socket.getEnabledProtocols()) {
         System.out.println(protocol);
      }
   }

   public static void main(String ... args) throws KeyManagementException, NoSuchAlgorithmException, IOException {
      new SSLProtocolsChecker().method();      
   }
}