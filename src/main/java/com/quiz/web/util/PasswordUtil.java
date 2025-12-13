
package com.quiz.web.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

public final class PasswordUtil {
	private static final String ALGO = "PBKDF2WithHmacSHA256";
	private static final int ITER = 65536;
	private static final int KEYLEN = 256;

	public static byte[] generateSalt(int size) {
		SecureRandom r = new SecureRandom();
		byte[] s = new byte[size];
		r.nextBytes(s);
		return s;
	}

	public static byte[] hash(String password, byte[] salt) {
		try {
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITER, KEYLEN);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGO);
			return skf.generateSecret(spec).getEncoded();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean verify(String password, byte[] salt, byte[] expected) {
		byte[] h = hash(password, salt);
		if (h.length != expected.length)
			return false;
		int r = 0;
		for (int i = 0; i < h.length; i++)
			r |= h[i] ^ expected[i];
		return r == 0;
	}
}
