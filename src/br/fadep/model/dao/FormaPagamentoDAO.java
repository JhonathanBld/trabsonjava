package br.fadep.model.dao;
import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.FormaPagamento;


public class FormaPagamentoDAO {
	public List<FormaPagamento> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<FormaPagamento> formaPagamentoes = null;

		try {
			formaPagamentoes = em.createQuery("select f from FormaPagamento f", FormaPagamento.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos as formas de pagamentos do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return formaPagamentoes;
	}
	
	public FormaPagamento getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		FormaPagamento formaPagamento = null;

		try {
			formaPagamento = em.find(FormaPagamento.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar forma de pagamento por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (formaPagamento == null) {
			throw new DAOException("País de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return formaPagamento;
	}
	
	public FormaPagamento save(FormaPagamento formaPagamento) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(formaPagamento);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar o forma de pagamento no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return formaPagamento;
	}
	
	public FormaPagamento delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		FormaPagamento formaPagamento = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			formaPagamento = em.find(FormaPagamento.class, id);
			em.remove(formaPagamento);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover a forma de pagamento do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return formaPagamento;
	}
}