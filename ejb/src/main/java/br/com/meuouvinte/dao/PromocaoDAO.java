package br.com.meuouvinte.dao;

import java.io.Serializable;
import java.util.List;

import br.com.meuouvinte.modelos.Promocao;

public class PromocaoDAO extends GeralDAO<Promocao> implements IPromocaoDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Promocao> recuperarPromocoesAtivas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promocao> recuperarPromocoes(List<Integer> idsPromocoes) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
