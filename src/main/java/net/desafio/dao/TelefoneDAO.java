package net.desafio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.desafio.model.Telefone;
import net.desafio.model.Usuario;

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
	
	public Telefone getById(final String id) {
		return getById(Integer.parseInt(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> getAll() {
		return entityManager.createQuery("FROM " + Telefone.class.getName()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Telefone> getTelefonesUsuario(final int usuario_id) {
		return entityManager.createQuery("FROM " + Telefone.class.getName() + " WHERE usuario_id = :usuario_id")
				.setParameter("usuario_id", usuario_id)
				.getResultList();		
	}

	public int qtdTelefone() {
		List<Telefone> telefoneList = TelefoneDAO.getInstance().getAll();

		int qtd = telefoneList.size();

		// Caso não tenha nenhum telefone, resetar o auto incremento
		if (qtd == 0) {
			try {
				entityManager.getTransaction().begin();
				entityManager.createNativeQuery("ALTER TABLE telefone AUTO_INCREMENT=1")
						.executeUpdate();
				entityManager.getTransaction().commit();
			} catch (Exception ex) {
				ex.printStackTrace();
				entityManager.getTransaction().rollback();
			}
		}

		return qtd;
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
			
			qtdTelefone();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void removeById(final int id) {
		Telefone telefone = getById(id);

		remove(telefone);
	}	
}