package br.fadep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.fadep.exceptions.DAOException;
import br.fadep.exceptions.ErrorCode;
import br.fadep.model.domain.Cliente;

public class ClienteDAO {

	public List<Cliente> getAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Cliente> clientes = null;

		try {
			clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
		} catch (RuntimeException ex) {

			throw new DAOException("Erro ao recuperar todos os clientes do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return clientes;
	}

	public Cliente getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Cliente cliente = null;

		try {
			cliente = em.find(Cliente.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar cliente por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());
		} finally {
			em.close();
		}

		if (cliente == null) {
			throw new DAOException("Cliente de id " + id + " não existe.", ErrorCode.NOT_FOUND.getCode());
		}

		return cliente;
	}

	public Cliente save(Cliente cliente) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {

			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar cliente no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		EntityManager em = JPAUtil.getEntityManager();
		Cliente clienteManaged = null;
		if (cliente.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}
		try {
			em.getTransaction().begin();
			clienteManaged = em.find(Cliente.class, cliente.getId());
			clienteManaged.setDataNascimento(cliente.getDataNascimento());
			clienteManaged.setCpf(cliente.getCpf());
			clienteManaged.setCidade(cliente.getCidade());
			clienteManaged.setNome(cliente.getNome());
			clienteManaged.setContatos(cliente.getContatos());
			clienteManaged.setRg(cliente.getRg());
			clienteManaged.setSexo(cliente.getSexo());
			em.getTransaction().commit();

		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Cliente informado para atualização não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getCode());

		} finally {
			em.close();
		}
		return clienteManaged;
	}

	public Cliente delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Cliente cliente = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
		}

		try {
			em.getTransaction().begin();
			cliente = em.find(Cliente.class, id);
			em.remove(cliente);
			em.getTransaction().commit();

		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover cliente do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getCode());

		} finally {
			em.close();
		}

		return cliente;
	}
}
