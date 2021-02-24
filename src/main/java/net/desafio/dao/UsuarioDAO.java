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
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getAll() {
		return entityManager.createQuery("FROM " + Usuario.class.getName()).getResultList();
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
			entityManager.getTransaction().begin();
			usuario = entityManager.find(Usuario.class, usuario.getId());
			entityManager.remove(usuario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Usuario cliente = getById(id);
			remove(cliente);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}