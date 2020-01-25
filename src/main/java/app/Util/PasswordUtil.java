package app.Util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordUtil {



    public static String getHashedPassword(String password){


        String salt = "saltedPassword";
        int iterations = 10000;
        int keyLength = 512;
        byte[] res = null;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password.toCharArray(), salt.getBytes(), iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            res = key.getEncoded( );
            return  Hex.encodeHexString(res);
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }


    }
}
