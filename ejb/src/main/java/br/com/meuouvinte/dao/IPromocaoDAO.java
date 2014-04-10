package br.com.meuouvinte.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Promocao;

@Local
public interface IPromocaoDAO extends OperacacoesCrud<Promocao>{
	public List<Promocao> recuperarPromocoesAtivas();
	public List<Promocao> recuperarPromocoes(List<Integer> idsPromocoes);
}
