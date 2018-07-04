package org.hybridskill.resources;



import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;


@Path("/{algorithm}/{key}/{message}")
public class Cryptography {

	private int keyLength;
	private String cypherAlgo;
	private String initVector;

	public Cryptography(int  keyLength, String cypherAlgo, String initVector) 
	{
		this.keyLength = keyLength;
		this.cypherAlgo = cypherAlgo;
		this.initVector =initVector;
	}
	
	
	public static String encrypt(String value,String key,String algo,String salt) throws Exception{	
        try {
            IvParameterSpec iv = new IvParameterSpec(salt.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), algo);

            Cipher cipher = Cipher.getInstance(algo+"/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
          return  ex.getMessage();
        }
		
	}
	
	
	public static String decrypt(String value,String key, String algo,String salt) throws Exception{
	    try {
            IvParameterSpec iv = new IvParameterSpec(salt.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), algo);

            Cipher cipher = Cipher.getInstance(algo+"/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(value));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;


	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getEncryptedCode(@PathParam("algorithm") String algorithm, @PathParam("key") String key, @PathParam("message") String message)
	{
		try {
				if (algorithm.equals("encrypt")) 
				{
					if (key.length() == keyLength)
					{
						return encrypt(message,key,cypherAlgo,initVector);
					}
					else
					{
						return "Length of key must be :" +Integer.toString(keyLength)+" characters";
					}
				
				}
				else if (algorithm.equals("decrypt"))
				{
					if (key.length() == keyLength)
					{
						return decrypt(message,key,cypherAlgo,initVector);
					}
					else
					{
						return "Length of key must be :" +Integer.toString(keyLength)+" characters";
					}
				}
				else 
				{
				return algorithm +" is not supported,Please select encrypt or decrypt";
				}
			
		}catch(Exception e)
		{return e.getMessage();}
	}
	
}
