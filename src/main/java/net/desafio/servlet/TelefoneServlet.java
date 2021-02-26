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
 * Servlet implementation class TelefoneServlet
 */
@WebServlet("/telefone")
public class TelefoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TelefoneServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		String usuario_id = request.getParameter("usuario_id");
		Usuario usuario = UsuarioDAO.getInstance().getById(usuario_id);
		List<Telefone> telefoneList = TelefoneDAO.getInstance().getTelefonesUsuario(Integer.parseInt(usuario_id));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/telefone-list.jsp");
		request.setAttribute("telefoneList", telefoneList);
		request.setAttribute("usuario", usuario); // por algum motivo não consigo usar usuario.telefoneList (ainda vou verificar)
		dispatcher.forward(request, response);
	}

	// SELEÇÃO DE ITENS DE MENU
	protected void doGet_novo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario_id = request.getParameter("usuario_id");
		Usuario usuario = UsuarioDAO.getInstance().getById(usuario_id);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/telefone-form.jsp");
		request.setAttribute("usuario", usuario);
		request.setAttribute("operacao", "nova");
		dispatcher.forward(request, response);
	}

	protected void doGet_editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int telefone_id = Integer.parseInt(request.getParameter("telefone_id"));
		Telefone telefone = TelefoneDAO.getInstance().getById(telefone_id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/telefone-form.jsp");
		request.setAttribute("telefone", telefone);
		dispatcher.forward(request, response);
	}

	protected void doGet_deletar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int telefone_id = Integer.parseInt(request.getParameter("telefone_id"));
		Telefone telefone = TelefoneDAO.getInstance().getById(telefone_id);
		int usuario_id = telefone.getUsuario().getId();
		TelefoneDAO.getInstance().removeById(telefone_id);

		response.sendRedirect(request.getContextPath() + "/telefone?usuario_id=" + usuario_id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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

		int ddd = Integer.parseInt(request.getParameter("ddd"));
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

		String usuario_id = request.getParameter("usuario_id");
		Usuario usuario = UsuarioDAO.getInstance().getById(usuario_id);

		Telefone telefone = new Telefone(ddd, numero, tipo, usuario);

		TelefoneDAO.getInstance().persist(telefone);

		doGet_listagem(request, response);
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/telefone-lista.jsp");
//		request.setAttribute("cliente", telefone.getUsuario());
//		dispatcher.forward(request, response);
	}

	protected void doPost_alterar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int telefone_id = Integer.parseInt(request.getParameter("telefone_id"));
		int ddd = Integer.parseInt(request.getParameter("ddd"));
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

		String usuario_id = request.getParameter("usuario_id");
		Usuario usuario = UsuarioDAO.getInstance().getById(usuario_id);

		Telefone telefone = new Telefone(telefone_id, ddd, numero, tipo, usuario);

		TelefoneDAO.getInstance().merge(telefone);

		response.sendRedirect(request.getContextPath() + "/telefone?usuario_id=" + usuario_id);
	}	
}
