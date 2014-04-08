package br.com.meuouvinte.dao;

import java.util.List;

import br.com.meuouvinte.modelos.Ouvinte;

public interface IOuvinteDAO extends OperacacoesCrud<Ouvinte> {
	public List<Ouvinte> recuperarOuvintes(Integer idPromocao);
	public List<Ouvinte> carregarOuvintes(List<Integer> idsOuvintes);
}
