package net.desafio.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.desafio.dao.TelefoneDAO;
import net.desafio.dao.UsuarioDAO;
import net.desafio.model.Telefone;
import net.desafio.model.Usuario;
import net.desafio.util.Funcoes;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
    }

	/**
	 * Os métodos doGet são responsáveis pela apresentação dos dados e formulários de inclusão, alteração e exclusão
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String acao = request.getParameter("acao");
		try {
			if (acao == null) {
				doGet_index(request, response);
			} else if (acao.equalsIgnoreCase("listagem")) {
				doGet_listagem(request, response);
			} else if (acao.equalsIgnoreCase("novo")) {
				doGet_novo(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				doGet_editar(request, response);
			} else if (acao.equalsIgnoreCase("deletar")) {
				doGet_deletar(request, response);
			}
		} catch (Exception e) {
			throw new ServletException();
		}
		
	}

	protected void doGet_index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet_listagem(request, response);
	}

	protected void doGet_listagem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Usuario> usuarioList = UsuarioDAO.getInstance().getAll();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/usuario-list.jsp");
		request.setAttribute("usuarios", usuarioList);
		dispatcher.forward(request, response);
	}

	// SELEÇÃO DE ITENS DE MENU
	protected void doGet_novo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/usuario-form.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet_editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int usuario_id = Integer.parseInt(request.getParameter("usuario_id"));
		Usuario usuario = UsuarioDAO.getInstance().getById(usuario_id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/usuario-form.jsp");
		request.setAttribute("usuario", usuario);
		dispatcher.forward(request, response);
	}

	protected void doGet_deletar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int usuario_id = Integer.parseInt(request.getParameter("usuario_id"));
		UsuarioDAO.getInstance().removeById(usuario_id);

		// caso não exista nenhum usuário, abre a tela de cadastramento 
		if (UsuarioDAO.getInstance().qtdUsuario() == 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario?acao=novo");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/usuario");
		}
	}

	/**
	 * Os métodos doPost são responsáveis pela persistência dos dados
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String acao = request.getParameter("acao");

		if (acao == null || acao.equalsIgnoreCase("logar")) {
			doGet_index(request, response);
		} else if (acao.equalsIgnoreCase("inserir")) {
			try {
				doPost_inserir(request, response);
			} catch (NoSuchAlgorithmException | ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (acao.equalsIgnoreCase("alterar")) {
			doPost_alterar(request, response);
		}
	}
	
	protected void doPost_inserir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = Funcoes.crypto(request.getParameter("senha"));
		Usuario usuario = new Usuario(nome, email, senha);

		UsuarioDAO.getInstance().persist(usuario);

		// Se for o primeiro cadastro, volta para a página de login
		if (UsuarioDAO.getInstance().qtdUsuario() == 1) {
			response.sendRedirect(request.getContextPath());
		} else {
			response.sendRedirect(request.getContextPath() + "/usuario");
		}
	}

	protected void doPost_alterar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int usuario_id = Integer.parseInt(request.getParameter("usuario_id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		List<Telefone> telefoneList = TelefoneDAO.getInstance().getTelefonesUsuario(usuario_id);
		Usuario usuario = new Usuario(usuario_id, nome, email, senha, telefoneList);

		UsuarioDAO.getInstance().merge(usuario);

		response.sendRedirect(request.getContextPath() + "/usuario");
	}
}
