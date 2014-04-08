package br.com.meuouvinte.dao;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.meuouvinte.modelos.Usuario;

public class UsuarioDAO extends GeralDAO<Usuario> implements IUsuarioDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Usuario recuperar(String email) {
		Query qry = getDatastore().createNamedQuery("Usuario.usuarioPorEmail");
		qry.setParameter("email", email);
		return (Usuario) qry.getSingleResult();
	}
	
}
