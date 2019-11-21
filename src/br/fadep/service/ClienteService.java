package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.ClienteDAO;
import br.fadep.model.domain.Cliente;


public class ClienteService {

	private ClienteDAO dao = new ClienteDAO();
	
	public List<Cliente> getClientes() {
		return dao.getAll();
	}
	
	public Cliente getCliente(Long id) {
		return dao.getById(id);
	}
	
	public Cliente saveCliente(Cliente Cliente) {
		return dao.save(Cliente);
	}
	
	public Cliente updateCliente(Cliente Cliente) {
		return dao.update(Cliente);
	}
	
	public Cliente deleteCliente(Long id) {
		return dao.delete(id);
	}

}

