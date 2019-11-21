package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.CidadeDAO;
import br.fadep.model.domain.Cidade;


public class CidadeService {

	private CidadeDAO dao = new CidadeDAO();
	
	public List<Cidade> getCidades() {
		return dao.getAll();
	}
	
	public Cidade getCidade(Long id) {
		return dao.getById(id);
	}
	
	public Cidade saveCidade(Cidade Cidade) {
		return dao.save(Cidade);
	}
	
	public Cidade updateCidade(Cidade Cidade) {
		return dao.update(Cidade);
	}
	
	public Cidade deleteCidade(Long id) {
		return dao.delete(id);
	}

}