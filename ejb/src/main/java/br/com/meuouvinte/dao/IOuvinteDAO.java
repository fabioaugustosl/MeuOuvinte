package br.com.meuouvinte.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Ouvinte;

@Local
public interface IOuvinteDAO extends OperacacoesCrud<Ouvinte> {
	public List<Ouvinte> recuperarOuvintes(Integer idPromocao);
	public List<Ouvinte> carregarOuvintes(List<Integer> idsOuvintes);
	public List<Ouvinte> pesquisar(String padrao);
}
