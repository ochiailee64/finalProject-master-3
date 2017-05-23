package com.example.csaper6.finalp;

/**
 * Created by csaper6 on 4/25/17.
 */
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;


/**
 * Created by andrewlee on 4/23/17.
 */



public class rsa {
    public KeyPair MakeKeys() {
        //this makes the public and the private key
        //these keys will be used for encrypting and decrypting the messsage
        //returns a KeyPair, which consists of the public and private key
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        kpg.initialize(2048);
        KeyPair kp = kpg.genKeyPair();
        return kp;

    }

    public SealedObject enCrypt(PublicKey key, String message){
        //this takes a message that is going to be encrypted, and the key that is going to be used
        //for the encryption
        //It returns a sealed object, which holds the encrypted message.
        Cipher c = null;
        try {
            c = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
// Initiate the Cipher, telling it that it is going to Encrypt, giving it the public key
        try {
            c.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        String myMessage = message;
        SealedObject sealedObject= null;
        try {
            sealedObject = new SealedObject(myMessage, c);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return sealedObject;
    }

    public String decipher(SealedObject Sealedobject, PrivateKey key){
        //this takes the sealed object, which is going to be deciphered
        //It also takes a private key as a parameter, which is the key that is going to be used for the
        //decipherment

        Cipher dec = null;
        try {
            dec = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
// Initiate the Cipher, telling it that it is going to Decrypt, giving it the private key
        try {
            dec.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        String message = null;
        try {
            message = (String) Sealedobject.getObject(dec);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return message;
    }





}




















//region Store the key in a file
//        Key publicKey = kp.getPublic();
//        Key privateKey = kp.getPrivate();

//        KeyFactory fact = null;
//        try {
//            fact = KeyFactory.getInstance("RSA");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        RSAPublicKeySpec pub = null;
//        try {
//            pub = fact.getKeySpec(publicKey, RSAPublicKeySpec.class);
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//
//        RSAPrivateKeySpec priv = null;
//        try {
//            priv = fact.getKeySpec(privateKey, RSAPrivateKeySpec.class);
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//endregion
