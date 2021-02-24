package net.desafio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.desafio.model.Telefone;

@Stateless
public class TelefoneDAO {

	protected EntityManager entityManager;
	private static TelefoneDAO instance;

	// padrão de projeto Singleton
	public static TelefoneDAO getInstance() {
		if (instance == null) {
			instance = new TelefoneDAO();
		}

		return instance;
	}

	private TelefoneDAO() {
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
	public Telefone getById(final int id) {
		return entityManager.find(Telefone.class, id);
	}
	
	// Sobrecarga do m�todo, pois no ContaServlet recebo um par�metro String
	public Telefone getById(final String id) {
		return getById(Integer.parseInt(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> getAll() {
		return entityManager.createQuery("FROM " + Telefone.class.getName()).getResultList();
	}

	public void persist(Telefone telefone) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(telefone);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Telefone telefone) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(telefone);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Telefone telefone) {
		try {
			entityManager.getTransaction().begin();
			telefone = entityManager.find(Telefone.class, telefone.getId());
			entityManager.remove(telefone);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
}