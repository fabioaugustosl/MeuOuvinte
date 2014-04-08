package br.com.meuouvinte.dao;

import java.util.List;

import br.com.meuouvinte.modelos.Sorteio;

public interface ISorteioDAO extends OperacacoesCrud<Sorteio> {
	public void salvar(Integer idPromocao, Integer idOuvinte, String emailUsuarioResponsavel);
	public abstract List<Sorteio> recuperarPorOuvinte(Integer idOuvinte);
}
