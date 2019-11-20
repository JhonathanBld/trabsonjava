package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Endereco;

public class EnderecoDAO {

	public List<Endereco> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Endereco> enderecos = null;

		try {
			enderecos = em.createQuery("select e from Endereco e", Endereco.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos os endere�os do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return enderecos;
	}

	public Endereco getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Endereco endereco = null;

		try {
			endereco = em.find(Endereco.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar endere�o por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (endereco == null) {
			throw new DAOException("Endere�o de id " + id + " n�o existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return endereco;
	}

	public Endereco save(Endereco endereco) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(endereco);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar endere�o no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return endereco;
	}

	public Endereco update(Endereco endereco) {
		EntityManager em = JPAUtil.getEntityManager();
		Endereco enderecoManaged = null;
		if (endereco.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}
		try {
			em.getTransaction().begin();
			enderecoManaged = em.find(Endereco.class, endereco.getId());
			enderecoManaged.setBairro(endereco.getBairro());
			enderecoManaged.setCep(endereco.getCep());
			enderecoManaged.setCidade(endereco.getCidade());
			enderecoManaged.setComplemento(endereco.getComplemento());
			enderecoManaged.setNumero(endereco.getNumero());
			enderecoManaged.setRua(endereco.getRua());
			em.getTransaction().commit();

		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Endere�o informado para atualiza��o n�o existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getCode());

		} finally {
			em.close();
		}
		return enderecoManaged;
	}

	public Endereco delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Endereco endereco = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			endereco = em.find(Endereco.class, id);
			em.remove(endereco);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover endere�o do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return endereco;
	}
}
