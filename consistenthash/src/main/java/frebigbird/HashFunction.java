package frebigbird;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jhkim on 2017-10-24.
 */
public class HashFunction {
    public Integer hash(String s) {
        String MD5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            MD5 = null;
        }

        return MD5.hashCode();
    }
}
