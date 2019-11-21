package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.JogoDAO;
import br.fadep.model.domain.Jogo;


public class JogoService {

	private JogoDAO dao = new JogoDAO();
	
	public List<Jogo> getJogos() {
		return dao.getAll();
	}
	
	public Jogo getJogo(Long id) {
		return dao.getById(id);
	}
	
	public Jogo saveJogo(Jogo Jogo) {
		return dao.save(Jogo);
	}
	
	public Jogo updateJogo(Jogo Jogo) {
		return dao.update(Jogo);
	}
	
	public Jogo deleteJogo(Long id) {
		return dao.delete(id);
	}

}

