package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.PaisDAO;
import br.fadep.model.domain.Pais;


public class PaisService {

	private PaisDAO dao = new PaisDAO();
	
	public List<Pais> getPaiss() {
		return dao.getAll();
	}
	
	public Pais getPais(Long id) {
		return dao.getById(id);
	}
	
	public Pais savePais(Pais Pais) {
		return dao.save(Pais);
	}
	
	public Pais deletePais(Long id) {
		return dao.delete(id);
	}

}

