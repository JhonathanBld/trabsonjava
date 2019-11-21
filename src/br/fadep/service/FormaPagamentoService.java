package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.FormaPagamentoDAO;
import br.fadep.model.domain.FormaPagamento;


public class FormaPagamentoService {

	private FormaPagamentoDAO dao = new FormaPagamentoDAO();
	
	public List<FormaPagamento> getFormaPagamentos() {
		return dao.getAll();
	}
	
	public FormaPagamento getFormaPagamento(Long id) {
		return dao.getById(id);
	}
	
	public FormaPagamento saveFormaPagamento(FormaPagamento FormaPagamento) {
		return dao.save(FormaPagamento);
	}
	
	public FormaPagamento deleteFormaPagamento(Long id) {
		return dao.delete(id);
	}

}

