package net.desafio.util;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class FuncoesTest extends TestCase {

	@Test
	public void test() throws NoSuchAlgorithmException {
		String retornoEsperado = "698DC19D489C4E4DB73E28A713EAB07B";
		String retornoFeito = Funcoes.crypto("teste");
		
		assertEquals(retornoEsperado, retornoFeito);
	}

}