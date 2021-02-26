package net.desafio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.desafio.model.Usuario;

@Stateless
public class UsuarioDAO {

	protected EntityManager entityManager;
	private static UsuarioDAO instance;

	// padrão de projeto Singleton
	public static UsuarioDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioDAO();
		}

		return instance;
	}

	private UsuarioDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		// pega as configurações do arquivo persistence.xml
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("desafioSefazPU");
		if (entityManager == null) {
			// responsável por realizar as operações de CRUD no banco de dados
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	// ***** CRUD *****
	public Usuario getById(final int id) {
		return entityManager.find(Usuario.class, id);
	}

	// Sobrecarga do m�todo, pois no ContaServlet recebo um par�metro String
	public Usuario getById(final String id) {
		return getById(Integer.parseInt(id));
	}

	public Usuario getByEmail(final String email) {
		try {
			return (Usuario) entityManager.createQuery("FROM " + Usuario.class.getName() + " WHERE email = :email")
					.setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getAll() {
		return entityManager.createQuery("FROM " + Usuario.class.getName()).getResultList();
	}

	public int qtdUsuario() {
		List<Usuario> usuarioList = UsuarioDAO.getInstance().getAll();

		int qtd = usuarioList.size();

		// Caso não tenha nenhum usuário, resetar o auto incremento
		if (qtd == 0) {
			try {
				entityManager.getTransaction().begin();
				entityManager.createNativeQuery("ALTER TABLE usuario AUTO_INCREMENT=1")
						.executeUpdate();
				entityManager.getTransaction().commit();
			} catch (Exception ex) {
				ex.printStackTrace();
				entityManager.getTransaction().rollback();
			}
		}

		return qtd;
	}

	public void persist(Usuario usuario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(usuario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Usuario usuario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(usuario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Usuario usuario) {
		try {
			usuario = entityManager.find(Usuario.class, usuario.getId());

			entityManager.getTransaction().begin();
			entityManager.remove(usuario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		Usuario usuario = getById(id);

		remove(usuario);
	}
}