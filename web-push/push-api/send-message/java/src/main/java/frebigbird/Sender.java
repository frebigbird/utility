package frebigbird;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

/**
 * Hello world!
 */
public class Sender {
    private static String publicVapidKey = "BLlTN2XJ_ajKuYxFHLAGo4LwTHdQ0IVHsvjMM_iZ9i_6gZxn6ucv4klQZMgP7QE2RML_ghfmM75DjCvJQa9T0LM";
    private static String privateVapidKey = "tD5-DlFVgxP9y8VuRxh5Hc_6Ntg39m6T3hfzxuCnJ1U";

    public static void main(String[] args) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
        Security.addProvider(new BouncyCastleProvider());
        PushService pushService = new PushService()
            .setPublicKey(publicVapidKey)
            .setPrivateKey(privateVapidKey)
            .setSubject("mailto:jhkim@spectra.co.kr");

        Gson gson = new Gson();
        Subscription subscription = gson.fromJson("{'endpoint':'https://fcm.googleapis.com/fcm/send/elaDONlXo-E:APA91bGSGsG_YSDNpUEJNHEGxs4mFwW7dXWAQowPsXXQ57wvo0YY_4oqj0Sj9CkJayk-nB79FPVeLsqS6mOeoPsWhwgQRYka5EKu5XQ2Tg1wDOYL4ybCNSPCU2Y2ja_8HuGneipY7dZJ','expirationTime':null,'keys':{'p256dh':'BN1ypzhRrJY811r9b978yYccMl9sAV0Bg3_L6Z5rzxZ6PLmTxp2A6HZO0A0ySDNcrMZUC3mke-YK_l3bZbB0PlU','auth':'EB7VcLyrghL9T8n-AT3ucQ'}}", Subscription.class);
        Notification notification = new Notification(subscription, "hi");
        pushService.send(notification);
    }
}
