
package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Venda;

public class VendaDAO {

	public List<Venda> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Venda> venda = null;

		try {
			venda = em.createQuery("select p from Venda p", Venda.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos os produtos do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return venda;
	}

	public Venda getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Venda produto = null;

		try {
			produto = em.find(Venda.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar produto por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (produto == null) {
			throw new DAOException("Produto de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return produto;
	}

	public Venda save(Venda venda) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(venda);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar a venda no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return venda;
	}

	public Venda update(Venda venda) {
		EntityManager em = JPAUtil.getEntityManager();
		Venda vendaManaged = null;
		if (venda.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}
		try {
			em.getTransaction().begin();
			vendaManaged = em.find(Venda.class, venda.getId());
			vendaManaged.setCarrinho(venda.getCarrinho());
			vendaManaged.setCliente(venda.getCliente());
			vendaManaged.setFormaPagamento(venda.getFormaPagamento());
			em.getTransaction().commit();

		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Produto informado para atualização não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getCode());

		} finally {
			em.close();
		}
		return vendaManaged;
	}

	public Venda delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Venda venda = null;
		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			venda = em.find(Venda.class, id);
			em.remove(venda);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover produto do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return venda;
	}

}
