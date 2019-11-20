package br.fadep.model.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Jogo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	@OneToOne
	private Empresa desenvolvedora;
	@OneToOne
	private Empresa publicadora;
	@OneToMany
	private List<Categoria> categorias;
	private double valor;
	private Date data;	
	private String versao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresa getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(Empresa desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

	public Empresa getPublicadora() {
		return publicadora;
	}

	public void setPublicadora(Empresa publicadora) {
		this.publicadora = publicadora;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}	
}
