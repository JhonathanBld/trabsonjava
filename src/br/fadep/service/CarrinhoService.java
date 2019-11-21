package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.CarrinhoDAO;
import br.fadep.model.domain.Carrinho;


public class CarrinhoService {

	private CarrinhoDAO dao = new CarrinhoDAO();
	
	public List<Carrinho> getCarrinhos() {
		return dao.getAll();
	}
	
	public Carrinho getCarrinho(Long id) {
		return dao.getById(id);
	}
	
	public Carrinho saveCarrinho(Carrinho Carrinho) {
		return dao.save(Carrinho);
	}
	
	public Carrinho updateCarrinho(Carrinho Carrinho) {
		return dao.update(Carrinho);
	}
	
	public Carrinho deleteCarrinho(Long id) {
		return dao.delete(id);
	}

}

