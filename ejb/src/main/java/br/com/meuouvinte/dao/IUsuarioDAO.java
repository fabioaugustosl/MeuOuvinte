package br.com.meuouvinte.dao;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Usuario;

@Local
public interface IUsuarioDAO extends OperacacoesCrud<Usuario> {
	public Usuario recuperar(String email);
}
