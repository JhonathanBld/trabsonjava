package br.fadep.service;

import java.util.List;

import br.fadep.model.dao.EmpresaDAO;
import br.fadep.model.domain.Empresa;


public class EmpresaService {

	private EmpresaDAO dao = new EmpresaDAO();
	
	public List<Empresa> getEmpresas() {
		return dao.getAll();
	}
	
	public Empresa getEmpresa(Long id) {
		return dao.getById(id);
	}
	
	public Empresa saveEmpresa(Empresa Empresa) {
		return dao.save(Empresa);
	}
	
	public Empresa deleteEmpresa(Long id) {
		return dao.delete(id);
	}

}