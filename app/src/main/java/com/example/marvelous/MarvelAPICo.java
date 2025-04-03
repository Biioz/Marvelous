package com.example.marvelous;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MarvelAPICo {
    private static final String PUBLIC_KEY = "5e3dbae37e5e8ad1d192644aa97cd631";
    private static final String PRIVATE_KEY = "ba3a7bee20a1876af8baa05e14e6c24dcfb22223";

    /*
    Quelques information sur l'authentification à l'api ( cf doc marvel).
    Public & Private Key
    ts - a timestamp
    hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
     */
    public static String genHash(String timestamp){
        try {
            String clef = timestamp+PRIVATE_KEY+PUBLIC_KEY ;

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(clef.getBytes());
            BigInteger bigInt = new BigInteger(1, hashBytes);
            String hashText = bigInt.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;


        } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException("MD5 non supporté", e);
        }
    }

    public static String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static String getPublicKey() {
        return PUBLIC_KEY;
    }

    public static String getPrivateKey() {
        return PRIVATE_KEY;
    }
}

// La requête pour se connecter
// http://gateway.marvel.com/v1/public/comics?
//ts=1
// &apikey=1234
// &hash=générer avec la fonction genHASH