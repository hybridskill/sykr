package org.hybridskill.resources;

import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;

public class RestorRedis {

public static void main(String[] arg) 
{
	String key ="asdfd123";
	String value ="my name is joker";
    try {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "DES");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(value.getBytes());
        System.out.println("encrypted string: "
                + Base64.encodeBase64String(encrypted));

    } catch (Exception ex) {
        ex.printStackTrace();
    }

   

}
}
