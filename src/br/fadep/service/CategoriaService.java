package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.CategoriaDAO;
import br.fadep.model.domain.Categoria;


public class CategoriaService {

	private CategoriaDAO dao = new CategoriaDAO();
	
	public List<Categoria> getCategorias() {
		return dao.getAll();
	}
	
	public Categoria getCategoria(Long id) {
		return dao.getById(id);
	}
	
	public Categoria saveCategoria(Categoria Categoria) {
		return dao.save(Categoria);
	}
	
	public Categoria deleteCategoria(Long id) {
		return dao.delete(id);
	}

}

