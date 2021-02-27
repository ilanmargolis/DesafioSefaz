package net.desafio.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class UsuarioTest {

	@Test
	void test() {
		// Testando os construtores (nem sei se isso é necessário)
		assertNotNull(new Usuario());
		assertNotNull(new Usuario("Nome", "Email", "Senha"));
		
		List<Telefone> telefoneList = null;
		assertNotNull(new Usuario(1, "Nome", "Email", "Senha", telefoneList));
	}

}
