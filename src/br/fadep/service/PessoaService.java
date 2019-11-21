package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.PessoaDAO;
import br.fadep.model.domain.Pessoa;


public class PessoaService {

	private PessoaDAO dao = new PessoaDAO();
	
	public List<Pessoa> getPessoas() {
		return dao.getAll();
	}
	
	public Pessoa getPessoa(Long id) {
		return dao.getById(id);
	}
	
	public Pessoa savePessoa(Pessoa Pessoa) {
		return dao.save(Pessoa);
	}
	
	public Pessoa deletePessoa(Long id) {
		return dao.delete(id);
	}

}

