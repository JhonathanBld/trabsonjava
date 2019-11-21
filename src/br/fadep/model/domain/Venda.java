package br.fadep.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Carrinho carrinho;
	@OneToMany

	private List<FormaPagamento> formasPagamento;
	@OneToOne	
	private Cliente cliente;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Carrinho getCarrinho() {
		return carrinho;
	}
	
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	public List<FormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}
	
	public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
	
}
