package br.com.meuouvinte.dao;

import br.com.meuouvinte.modelos.Usuario;

public interface IUsuarioDAO extends OperacacoesCrud<Usuario> {
	public Usuario recuperar(String email);
}
