package security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public String hash(String text) {
		try {
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] digested = md.digest(text.getBytes("UTF-8"));
            BigInteger no = new BigInteger(1, digested); 
            String hash = no.toString(16); 
            while (hash.length() < 32) { 
            	hash = "0" + hash; 
            } 
            return hash;
        }  
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        	return null;
        }
	}
	
}
