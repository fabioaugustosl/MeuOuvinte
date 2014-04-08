package br.com.meuouvinte.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import br.com.meuouvinte.modelos.Promocao;


public class PromocaoDAO extends GeralDAO<Promocao> implements IPromocaoDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Promocao> recuperarPromocoesAtivas() {
		Query qry = getDatastore().createNamedQuery("Promocao.recuperarPromocaoAtivas");
		qry.setParameter("data", new Date());
		return qry.getResultList();
	}

	@Override
	public List<Promocao> recuperarPromocoes(List<Integer> idsPromocoes) {
		Query qry = getDatastore().createNamedQuery("Promocao.recuperarPromocoes");
		qry.setParameter("ids", idsPromocoes);
		return qry.getResultList();
	}

	
}
