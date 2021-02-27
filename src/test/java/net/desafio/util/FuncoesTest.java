package net.desafio.util;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

class FuncoesTest extends Funcoes {

	@Test
	public void test() {
		String retornoEsperado = "698DC19D489C4E4DB73E28A713EAB07B";
		String retornoFeito = "";
		
		try {
			retornoFeito = Funcoes.crypto("teste");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(retornoEsperado, retornoFeito);
	}
	
}
