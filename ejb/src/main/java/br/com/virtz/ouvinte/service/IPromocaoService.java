package br.com.virtz.ouvinte.service;

import java.util.List;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Promocao;

@Local
public interface IPromocaoService {
	public String salvar(Promocao promocao);
	public List<Promocao> recuperarPromocoes();
	public String remover (Promocao promocao);
	public List<Promocao> getPromocoesOuvinte(Ouvinte ouvinte);
	public List<Promocao> recuperarPromocoesAtivas();
	public List<Ouvinte> recuperarOuvintesDePromocao(Promocao promocao);
}
