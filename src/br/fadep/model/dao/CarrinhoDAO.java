package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Carrinho;

public class CarrinhoDAO {
	public List<Carrinho> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Carrinho> carrinho = null;

		try {
			carrinho = em.createQuery("select c from Carrinho c", Carrinho.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos as cidades do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return carrinho;
	}

	public Carrinho getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Carrinho carrinho = null;

		try {
			carrinho = em.find(Carrinho.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar o carrinho por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (carrinho == null) {
			throw new DAOException("Carrinho de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return carrinho;
	}

	public Carrinho save(Carrinho carrinho) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(carrinho);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar o carrinho no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return carrinho;
	}

	public Carrinho update(Carrinho carrinho) {
		EntityManager em = JPAUtil.getEntityManager();
		Carrinho carrinhoManaged = null;
		if (carrinho.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}
		try {

			em.getTransaction().begin();
			carrinhoManaged = em.find(Carrinho.class, carrinho.getId());
			carrinhoManaged.setData(carrinho.getData());
			carrinhoManaged.setJogos(carrinho.getJogos());
			em.getTransaction().commit();

		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Carrinho informado para atualização não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getCode());

		} finally {
			em.close();
		}
		return carrinhoManaged;
	}

	public Carrinho delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Carrinho carrinho = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			carrinho = em.find(Carrinho.class, id);
			em.remove(carrinho);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover o carrinho do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return carrinho;
	}
}
