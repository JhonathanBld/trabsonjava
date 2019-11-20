package br.fadep.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cidade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;	
	@OneToOne
	private Estado estado;
	private Long codigoIBGE;
	private Long populacao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Long getCodigoIBGE() {
		return codigoIBGE;
	}
	
	public void setCodigoIBGE(Long codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}
	
	public Long getPopulacao() {
		return populacao;
	}
	
	public void setPopulacao(Long populacao) {
		this.populacao = populacao;
	}	
}
