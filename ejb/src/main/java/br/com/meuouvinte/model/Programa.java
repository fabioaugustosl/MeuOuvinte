package br.com.meuouvinte.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PROGRAMA")
public class Programa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Integer id;

	@NotNull
    @Column(name = "nome", length=100, nullable=false)
	private String nome;

    @Column(name = "locutor", length=100)
	private String locutor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocutor() {
		return locutor;
	}

	public void setLocutor(String locutor) {
		this.locutor = locutor;
	}

}
