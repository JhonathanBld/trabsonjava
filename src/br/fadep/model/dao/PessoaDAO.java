package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Pessoa;

public class PessoaDAO {

	public List<Pessoa> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Pessoa> pessoas = null;

		try {
			pessoas = em.createQuery("select p from Pessoa p", Pessoa.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos os países do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return pessoas;
	}
	
	public Pessoa getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Pessoa pessoa = null;

		try {
			pessoa = em.find(Pessoa.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar país por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (pessoa == null) {
			throw new DAOException("País de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return pessoa;
	}
	
	public Pessoa save(Pessoa pessoa) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pessoa);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar o país no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return pessoa;
	}
	
	public Pessoa delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Pessoa pessoa = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			pessoa = em.find(Pessoa.class, id);
			em.remove(pessoa);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover país do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return pessoa;
	}
	
}
