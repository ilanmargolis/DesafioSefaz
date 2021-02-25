package net.desafio.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.desafio.dao.UsuarioDAO;
import net.desafio.model.Usuario;
import net.desafio.util.Funcoes;

/**
 * Implementação da classe LoginServlet
 * Faz as verificações do usuário que pretende se logar no aplicativo
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;

		// caso não exista nenhum usuário, abre a tela de cadastramento 
		if (UsuarioDAO.getInstance().qtdUsuario() == 0) {
			dispatcher = request.getRequestDispatcher("/usuario?acao=novo");
		} else {
			dispatcher = request.getRequestDispatcher("/forms/login.jsp");
		}

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao == null) {
			doGet(request, response);
		} else if (acao.equalsIgnoreCase("logar")) {
			try {
				doPost_logar(request, response);
			} catch (NoSuchAlgorithmException | ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost_logar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {

		String email = request.getParameter("email");
		String senha = Funcoes.crypto(request.getParameter("senha"));

		Usuario usuario = null;

		usuario = UsuarioDAO.getInstance().getByEmail(email);
		if (usuario == null) {
			throw new IOException("E-mail errado");
		}

		// Senha confere
		if (senha.equals(usuario.getSenha())) {
			/*
			 * verifica o tipo de usuario e faz login na área correspondente
			 * caso eu utilize herança estendendo de Usuario
			 */	
			if (usuario instanceof Usuario) {
				response.sendRedirect(request.getContextPath() + "/usuario");
			}
		} else {
			throw new IOException("Senha errada " + senha + " = " + usuario.getSenha());
		}
	}
}
