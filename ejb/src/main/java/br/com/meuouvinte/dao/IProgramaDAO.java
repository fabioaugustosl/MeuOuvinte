package br.com.meuouvinte.dao;

import br.com.meuouvinte.modelos.Programa;

public interface IProgramaDAO extends OperacacoesCrud<Programa> {
	public Programa recuperar(String nome);
}
