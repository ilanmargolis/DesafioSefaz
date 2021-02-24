package net.desafio.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.desafio.dao.UsuarioDAO;
import net.desafio.model.Usuario;

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
		
		String action = request.getParameter("action");
		try {
			if (action == null) {
				doGet_index(request, response);
			} else if (action.equalsIgnoreCase("listagem")) {
				doGet_listagem(request, response);
			} else if (action.equalsIgnoreCase("novo")) {
				doGet_novo(request, response);
			} else if (action.equalsIgnoreCase("editar")) {
				doGet_editar(request, response);
			} else if (action.equalsIgnoreCase("deletar")) {
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

//		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/cliente-list.jsp");
//		request.setAttribute("usuarios", usuarioList);
//		dispatcher.forward(request, response);
	}

	// SELEÇÃO DE ITENS DE MENU
	protected void doGet_novo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/cliente-form.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet_editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuario = UsuarioDAO.getInstance().getById(id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/cliente-form.jsp");
		request.setAttribute("usuario", usuario);
		dispatcher.forward(request, response);
	}

	protected void doGet_deletar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		UsuarioDAO.getInstance().removeById(id);

		response.sendRedirect(request.getContextPath());
	}

	/**
	 * Os métodos doPostsão responsáveis pela persistência dos dados
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
