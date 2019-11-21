package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Estado;

public class EstadoDAO {

	public List<Estado> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Estado> estados = null;

		try {
			estados = em.createQuery("select e from Estado e", Estado.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos os estados do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return estados;
	}

	public Estado getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Estado estado = null;

		try {
			estado = em.find(Estado.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar estado por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (estado == null) {
			throw new DAOException("Estado de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return estado;
	}

	public Estado save(Estado estado) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(estado);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar estado no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return estado;
	}

	public Estado delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Estado estado = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			estado = em.find(Estado.class, id);
			em.remove(estado);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover o estado do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return estado;
	}

}