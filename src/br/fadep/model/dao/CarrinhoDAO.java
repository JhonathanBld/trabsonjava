package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Carrinho;

public class CarrinhoDAO {
	public List<Carrinho> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Carrinho> cidades = null;

		try {
			cidades = em.createQuery("select c from Carrinho c", Carrinho.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos as cidades do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return cidades;
	}

	public Carrinho getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Carrinho cidade = null;

		try {
			cidade = em.find(Carrinho.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar cidade por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (cidade == null) {
			throw new DAOException("Cidade de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return cidade;
	}

	public Carrinho save(Carrinho cidade) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(cidade);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar cidade no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return cidade;
	}

	public Carrinho update(Carrinho cidade) {
		EntityManager em = JPAUtil.getEntityManager();
		Carrinho cidadeManaged = null;
		if (cidade.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}
		try {
			em.getTransaction().begin();
			em.getTransaction().begin();
			cidadeManaged = em.find(Carrinho.class, cidade.getId());
			cidadeManaged.setEstado(cidade.getEstado());
			cidadeManaged.setCodigoIBGE(cidade.getCodigoIBGE());		
			cidadeManaged.setNome(cidade.getNome());
			cidadeManaged.setPopulacao(cidade.getPopulacao());
			em.getTransaction().commit();

		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Cidade informada para atualização não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getCode());

		} finally {
			em.close();
		}
		return cidadeManaged;
	}

	public Carrinho delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Carrinho cidade = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			cidade = em.find(Carrinho.class, id);
			em.remove(cidade);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover a cidade do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return cidade;
	}
}
