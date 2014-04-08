package br.com.meuouvinte.dao;

import java.util.List;

import br.com.meuouvinte.modelos.Promocao;

public interface IPromocaoDAO extends OperacacoesCrud<Promocao>{
	public List<Promocao> recuperarPromocoesAtivas();
	public List<Promocao> recuperarPromocoes(List<Integer> idsPromocoes);
}
