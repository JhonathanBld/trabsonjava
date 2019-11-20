package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Cidade;

public class CidadeDAO {
	

	public List<Cidade> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Cidade> cidades = null;

		try {
			cidades = em.createQuery("select c from Cidades c", Cidade.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos as cidades do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return cidades;
	}

	public Cidade getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Cidade cidade = null;

		try {
			cidade = em.find(Cidade.class, id);
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

	public Cidade save(Cidade cidade) {
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

	public Cidade update(Cidade cidade) {
		EntityManager em = JPAUtil.getEntityManager();
		Cidade cidadeManaged = null;
		if (cidade.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}
		try {
			em.getTransaction().begin();
			em.getTransaction().begin();
			cidadeManaged = em.find(Cidade.class, cidade.getId());
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

	public Cidade delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Cidade cidade = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			cidade = em.find(Cidade.class, id);
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
