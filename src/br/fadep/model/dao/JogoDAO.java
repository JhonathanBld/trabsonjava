package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Jogo;

public class JogoDAO {
	public List<Jogo> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Jogo> jogo = null;

		try {
			jogo = em.createQuery("select c from Jogo c", Jogo.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos as jogos do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return jogo;
	}

	public Jogo getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Jogo jogo = null;

		try {
			jogo = em.find(Jogo.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar o jogo por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (jogo == null) {
			throw new DAOException("Jogo de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return jogo;
	}

	public Jogo save(Jogo jogo) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(jogo);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar o jogo no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return jogo;
	}

	public Jogo update(Jogo jogo) {
		EntityManager em = JPAUtil.getEntityManager();
		Jogo jogoManaged = null;
		if (jogo.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}
		try {

			em.getTransaction().begin();
			jogoManaged = em.find(Jogo.class, jogo.getId());
			jogoManaged.setData(jogo.getData());
			jogoManaged.setCategorias(jogo.getCategorias());
			jogoManaged.setDescricao(jogo.getDescricao());
			jogoManaged.setDesenvolvedora(jogo.getDesenvolvedora());
			jogoManaged.setNome(jogo.getNome());
			jogoManaged.setValor(jogo.getValor());
			jogoManaged.setVersao(jogo.getVersao());
			em.getTransaction().commit();

		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Jogo informado para atualização não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getCode());

		} finally {
			em.close();
		}
		return jogoManaged;
	}

	public Jogo delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Jogo jogo = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			jogo = em.find(Jogo.class, id);
			em.remove(jogo);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover a jogo do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return jogo;
	}
}
