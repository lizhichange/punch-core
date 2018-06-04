/*
 * Copyright (c) 2017. Dawn Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fulihui.punch.web.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author lizhi
 */
public class AESCoder {
    private static final String KEY_ALGORITHM            = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final int    DEFAULT_KEY_SIZE         = 128;

    private String cipherAlgorithm;

    private AESCoder(String cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
    }

    public static AESCoder getInstance() {
        return getInstance(DEFAULT_CIPHER_ALGORITHM);
    }

    public static AESCoder getInstance(String cipherAlgorithm) {
        return new AESCoder(cipherAlgorithm);
    }

    public byte[] initKey() {
        return initKey(DEFAULT_KEY_SIZE);
    }

    public byte[] initKey(int keySize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            keyGenerator.init(keySize);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException ignore) {
            return null;
        }
    }

    public String initBase64Key() {
        return initBase64Key(DEFAULT_KEY_SIZE);
    }

    public String initBase64Key(int keySize) {
        return Base64.encodeBase64String(initKey(keySize));
    }

    public Key toKey(String base64String) {
        return toKey(Base64.decodeBase64(base64String));
    }

    public Key toKey(byte[] key) {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    public String encryptBase64(String data, String base64Key) {
        return Base64.encodeBase64URLSafeString(encrypt(data, base64Key));
    }

    public String encryptBase64(String data, byte[] key) {
        return Base64.encodeBase64URLSafeString(encrypt(data, key));
    }

    public String encryptBase64(byte[] data, byte[] key) {
        return Base64.encodeBase64URLSafeString(encrypt(data, key));
    }

    public byte[] encrypt(String data, String base64Key) {
        return encrypt(data, Base64.decodeBase64(base64Key));
    }

    public byte[] encrypt(String data, byte[] key) {
        return encrypt(data.getBytes(), key);
    }

    public byte[] encrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, toKey(key));
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decryptString(String encryptedBase64String, String base64Key) {
        return new String(decrypt(encryptedBase64String, base64Key));
    }

    public String decryptString(String encryptedBase64String, byte[] key) {
        return new String(decrypt(encryptedBase64String, key));
    }

    public String decryptString(byte[] encrypted, byte[] key) {
        return new String(decrypt(encrypted, key));
    }

    public byte[] decrypt(String encryptedBase64String, String base64Key) {
        return decrypt(encryptedBase64String, Base64.decodeBase64(base64Key));
    }

    public byte[] decrypt(String encryptedBase64String, byte[] key) {
        return decrypt(Base64.decodeBase64(encryptedBase64String), key);
    }

    public byte[] decrypt(byte[] encrypted, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, toKey(key));
            return cipher.doFinal(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
