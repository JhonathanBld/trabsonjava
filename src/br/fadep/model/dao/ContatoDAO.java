package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Contato;

public class ContatoDAO {


	public List<Contato> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Contato> contatos = null;

		try {
			contatos = em.createQuery("select e from Contato e", Contato.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos os contatos do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return contatos;
	}

	public Contato getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Contato contato = null;

		try {
			contato = em.find(Contato.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar contato por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (contato == null) {
			throw new DAOException("Contato de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return contato;
	}

	public Contato save(Contato contato) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(contato);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar contato no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return contato;
	}

	public Contato delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Contato contato = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			contato = em.find(Contato.class, id);
			em.remove(contato);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover o contato do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return contato;
	}

}