package br.com.meuouvinte.dao;

import java.io.Serializable;

import br.com.meuouvinte.modelos.Usuario;

public class UsuarioDAO extends GeralDAO<Usuario> implements IUsuarioDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Usuario recuperar(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
