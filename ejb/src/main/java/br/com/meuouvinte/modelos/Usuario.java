package br.com.meuouvinte.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "USUARIO")
@NamedQueries({
	@NamedQuery(name="Usuario.usuarioPorEmail",
				query="SELECT u FROM Usuario u WHERE u.email = :email")
})
public class Usuario implements Serializable, EntidadePersistencia {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Integer id;

	@NotNull
    @Column(name = "nome", length=100, nullable=false)
	private String nome;

	@NotNull
	@Email
    @Column(name = "email", length=100, nullable=false)
	private String email;

	@NotNull
    @Column(name = "senha", length=100, nullable=false)
	private String senha;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
