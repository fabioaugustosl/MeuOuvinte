package br.com.meuouvinte.models;

import java.util.List;

import br.com.meuouvinte.bean.Ouvinte;
import br.com.meuouvinte.bean.Promocao;

import com.google.inject.ImplementedBy;

@ImplementedBy(PromocaoModel.class)
public interface IPromocaoModel {
	public String salvar(Promocao promocao);
	public List<Promocao> recuperarPromocoes();
	public String remover (Promocao promocao);
	public List<Promocao> getPromocoesOuvinte(Ouvinte ouvinte);
	public void carregarPromocoesAtivas();
}
