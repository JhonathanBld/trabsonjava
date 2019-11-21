package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Empresa;

public class EmpresaDAO {


	public List<Empresa> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Empresa> empresa = null;

		try {
			empresa = em.createQuery("select c from Empresa c", Empresa.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos as empresas do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return empresa;
	}

	public Empresa getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Empresa empresa = null;

		try {
			empresa = em.find(Empresa.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar a empresa por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (empresa == null) {
			throw new DAOException("Categoria de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return empresa;
	}

	public Empresa save(Empresa empresa) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(empresa);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar a empresa no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return empresa;
	}

	public Empresa delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Empresa empresa = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			empresa = em.find(Empresa.class, id);
			em.remove(empresa);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover a empresa do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return empresa;
	}
}