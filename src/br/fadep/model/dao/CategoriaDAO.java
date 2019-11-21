package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Categoria;

public class CategoriaDAO {

	public List<Categoria> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Categoria> categoria = null;

		try {
			categoria = em.createQuery("select c from Categoria c", Categoria.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos as categorias do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return categoria;
	}

	public Categoria getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Categoria categoria = null;

		try {
			categoria = em.find(Categoria.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar a categoria por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (categoria == null) {
			throw new DAOException("Categoria de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return categoria;
	}

	public Categoria save(Categoria categoria) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar a categoria no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return categoria;
	}

	public Categoria delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Categoria categoria = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			categoria = em.find(Categoria.class, id);
			em.remove(categoria);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover a carrinho do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return categoria;
	}
}