package net.desafio.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Funcoes {

	public static String crypto(String senha) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(senha.getBytes(), 0, senha.length()); 
		String sCrypto = new BigInteger(1, digest.digest()).toString(16);
		
		return sCrypto.toUpperCase();
	}
}