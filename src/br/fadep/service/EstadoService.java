package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.EstadoDAO;
import br.fadep.model.domain.Estado;


public class EstadoService {

	private EstadoDAO dao = new EstadoDAO();
	
	public List<Estado> getEstados() {
		return dao.getAll();
	}
	
	public Estado getEstado(Long id) {
		return dao.getById(id);
	}
	
	public Estado saveEstado(Estado Estado) {
		return dao.save(Estado);
	}
	
	public Estado deleteEstado(Long id) {
		return dao.delete(id);
	}

}

