package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Pais;

public class PaisDAO {
	public List<Pais> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Pais> paises = null;

		try {
			paises = em.createQuery("select p from Produto p", Pais.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos os países do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return paises;
	}
	
	public Pais getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Pais pais = null;

		try {
			pais = em.find(Pais.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar país por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (pais == null) {
			throw new DAOException("País de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return pais;
	}
	
	public Pais save(Pais pais) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pais);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar o país no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return pais;
	}
	
	public Pais delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Pais pais = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			pais = em.find(Pais.class, id);
			em.remove(pais);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover país do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return produto;
	}
	
}
