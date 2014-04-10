package br.com.meuouvinte.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.meuouvinte.modelos.Ouvinte;

@Stateless
public class OuvinteDAO extends GeralDAO<Ouvinte> implements IOuvinteDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Ouvinte> recuperarOuvintes(Integer idPromocao) {
		Query qry = getDatastore().createNamedQuery("Ouvinte.recuperarPorIdPromocao");
		qry.setParameter("idPromocao", idPromocao);
		return qry.getResultList();
	}

	@Override
	public List<Ouvinte> carregarOuvintes(List<Integer> idsOuvintes) {
		Query qry = getDatastore().createNamedQuery("Ouvinte.ouvintesPorId");
		qry.setParameter("ids", idsOuvintes);
		return qry.getResultList();
	}

	@Override
	public List<Ouvinte> pesquisar(String padrao) {
		Query qry = getDatastore().createNamedQuery("Ouvinte.pesquisar");
		qry.setParameter("padrao", padrao);
		return qry.getResultList();
	}

}
