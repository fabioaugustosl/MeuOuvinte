package br.com.meuouvinte.dao;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Programa;

@Local
public interface IProgramaDAO extends OperacacoesCrud<Programa> {
	public Programa recuperar(String nome);
}
