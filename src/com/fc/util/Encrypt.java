package com.fc.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class Encrypt {

	public static final int ENCRY_STYLE_MD5 = 1;
	public static final int ENCRY_STYLE_DES = 2;

	public static String encryptString(int paramInt, String paramString)
			throws Exception {
		String str = "";
		if (paramInt == 1) {
			str = _$1(paramString);
			return str;
		}
		if (paramInt == 2) {
			str = _$3(paramString);
			return str;
		}
		return paramString;
	}

	public static String decryptString(int paramInt, String paramString)
			throws Exception {
		String str = "";
		if (paramInt == 1)
			return paramString;
		if (paramInt == 2) {
			str = _$2(paramString);
			return str;
		}
		return paramString;
	}

	private static byte[] _$2(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2, int paramInt) throws Exception {
		byte[] arrayOfByte = new byte[paramInt + 8];
		int i = paramInt / 2;
		int j = 0;
		int k = 0;
		for (j = 0; j < 3; ++j)
			arrayOfByte[j] = paramArrayOfByte2[j];
		for (j = 0; j < i; ++j)
			arrayOfByte[(j + 3)] = paramArrayOfByte1[j];
		for (; k < 2; ++k)
			arrayOfByte[(k + 3 + i)] = paramArrayOfByte2[(k + 3)];
		for (k = 0; k < paramInt - i; ++k)
			arrayOfByte[(k + 5 + i)] = paramArrayOfByte1[(i + k)];
		for (int l = 0; l < 3; ++l)
			arrayOfByte[(l + 5 + paramInt)] = paramArrayOfByte2[(l + 5)];
		return arrayOfByte;
	}

	private static String _$3(String paramString) throws Exception {
		byte[] arrayOfByte1 = paramString.getBytes();
		int i = arrayOfByte1.length;
		int j = i - i % 8 + 8;
		byte[] arrayOfByte2 = new byte[j];
		for (int k = 0; k < j; ++k)
			if (k < i)
				arrayOfByte2[k] = arrayOfByte1[k];
			else
				arrayOfByte2[k] = 0;
		KeyGenerator localKeyGenerator = KeyGenerator.getInstance("DES");
		localKeyGenerator.init(56);
		SecretKey localSecretKey = localKeyGenerator.generateKey();
		Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding", "SunJCE");
		localCipher.init(1, localSecretKey);
		byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte2);
		byte[] arrayOfByte4 = _$2(arrayOfByte3, localSecretKey.getEncoded(),
				arrayOfByte2.length);
		return new BASE64Encoder().encode(arrayOfByte4);
	}

	private static byte[] _$1(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2, int paramInt) throws Exception {
		byte[] arrayOfByte = new byte[paramInt - 8];
		int i = 3;
		int j = 3 + (paramInt - 8) / 2;
		int k = 5 + (paramInt - 8) / 2;
		int l = 5 + paramInt - 8;
		int i1 = paramInt;
		int i2 = 0;
		for (i2 = 0; i2 < i; ++i2)
			paramArrayOfByte2[i2] = paramArrayOfByte1[i2];
		for (i2 = 0; i2 < j - i; ++i2)
			arrayOfByte[i2] = paramArrayOfByte1[(i + i2)];
		for (i2 = 0; i2 < k - j; ++i2)
			paramArrayOfByte2[(i2 + i)] = paramArrayOfByte1[(j + i2)];
		for (i2 = 0; i2 < l - k; ++i2)
			arrayOfByte[(j - i + i2)] = paramArrayOfByte1[(k + i2)];
		for (i2 = 0; i2 < i1 - l; ++i2)
			paramArrayOfByte2[(5 + i2)] = paramArrayOfByte1[(l + i2)];
		return arrayOfByte;
	}

	private static String _$2(String paramString) throws Exception {
		byte[] arrayOfByte1 = new BASE64Decoder().decodeBuffer(paramString);
		byte[] arrayOfByte2 = new byte[8];
		byte[] arrayOfByte3 = _$1(arrayOfByte1, arrayOfByte2,
				arrayOfByte1.length);
		SecretKeyFactory localSecretKeyFactory = SecretKeyFactory
				.getInstance("DES");
		DESKeySpec localDESKeySpec = new DESKeySpec(arrayOfByte2);
		localSecretKeyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey localSecretKey = localSecretKeyFactory
				.generateSecret(localDESKeySpec);
		Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding", "SunJCE");
		localCipher.init(2, localSecretKey);
		byte[] arrayOfByte4 = localCipher.doFinal(arrayOfByte3);
		int i = 0;
		for (; i < arrayOfByte4.length; ++i) {
			if (arrayOfByte4[i] == 0)
				break;
		}
		byte[] arrayOfByte5 = new byte[i];
		for (int j = 0; j < i; ++j)
			arrayOfByte5[j] = arrayOfByte4[j];
		return new String(arrayOfByte5);
	}

	private static String _$1(String paramString) throws Exception {
		MessageDigest localMessageDigest = null;
		localMessageDigest = MessageDigest.getInstance("MD5");
		localMessageDigest.update(paramString.getBytes());
		byte[] arrayOfByte = localMessageDigest.digest();
		// System.out.println(new BASE64Encoder().encode(arrayOfByte));
		// System.out.println(new sun.misc.BASE64Encoder().encode(arrayOfByte));
		return new BASE64Encoder().encode(arrayOfByte); // gnzLDuqKcGxMNKFokfhOew==
		// return new
		// sun.misc.BASE64Encoder().encode(arrayOfByte);//gnzLDuqKcGxMNKFokfhOew==
		// return new String(arrayOfByte,"utf-8");
	}

	public static void main(String[] args) {
		String test = "test";
		try {
			// byte[] bs = "12345".getBytes();
			// for(byte b:bs)
			// System.out.println(b);
			// _$1("12345");
			// _$1("ttt");
			// _$1("鏁呮剰");
			// _$1("鍙戠儳纰燂紬锛?");
			// _$1("闃?鏄惁 脳");
			// _$1("r3");
			// System.out.println(encryptString(Encrypt.ENCRY_STYLE_MD5,"c4ca4238a0b923820dcc509a6f75849b"));
			// String test1 =
			// Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5,test);
			// System.out.println(test1);
			// System.out.println(test1.equals("CY9rzUYh03PK3k6DJie09g=="));
			// String tt = Encrypt.encryptString(Encrypt.ENCRY_STYLE_DES,test);
			// System.out.println(tt);
			// System.out.println(Encrypt.decryptString(Encrypt.ENCRY_STYLE_DES,
			// tt));
//			String password = encryptString(2,"p");		
//			System.out.println(password);
			String password = decryptString(2,"SV5/YBvhz+JwkiPp/hYnIYWFOkA7hc3O");
			System.out.println(password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
