package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.VendaDAO;
import br.fadep.model.domain.Venda;


public class VendaService {

	private VendaDAO dao = new VendaDAO();
	
	public List<Venda> getVendas() {
		return dao.getAll();
	}
	
	public Venda getVenda(Long id) {
		return dao.getById(id);
	}
	
	public Venda saveVenda(Venda Venda) {
		return dao.save(Venda);
	}
	
	public Venda updateVenda(Venda Venda) {
		return dao.update(Venda);
	}
	
	public Venda deleteVenda(Long id) {
		return dao.delete(id);
	}

}

