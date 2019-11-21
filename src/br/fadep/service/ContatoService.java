package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.ContatoDAO;
import br.fadep.model.domain.Contato;


public class ContatoService {

	private ContatoDAO dao = new ContatoDAO();
	
	public List<Contato> getContatos() {
		return dao.getAll();
	}
	
	public Contato getContato(Long id) {
		return dao.getById(id);
	}
	
	public Contato saveContato(Contato Contato) {
		return dao.save(Contato);
	}
	
	public Contato deleteContato(Long id) {
		return dao.delete(id);
	}

}

