package br.com.meuouvinte.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.OuvintePromocao;
import br.com.meuouvinte.modelos.Promocao;

@Stateless
public class OuvintePromocaoDAO extends GeralDAO<OuvintePromocao> implements IOuvintePromocaoDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Ouvinte ouvinte, Promocao promocao) throws Exception {
		OuvintePromocao op = new OuvintePromocao();
		op.setOuvinte(ouvinte);
		op.setPromocao(promocao);
		op.setDataAdicao(new Date());
		
		this.salvar(op);
	}

	@Override
	public List<OuvintePromocao> recuperarPorPromocao(Integer idPromocao) {
		Query qry = getDatastore().createNamedQuery("OuvintePromocao.recuperarPorPromocao");
		qry.setParameter("idPromocao", idPromocao);
		return qry.getResultList();
	}

	@Override
	public List<OuvintePromocao> recuperarPorOuvinte(Integer idOuvinte) {
		Query qry = getDatastore().createNamedQuery("OuvintePromocao.recuperarPorOuvinte");
		qry.setParameter("idOuvinte", idOuvinte);
		return qry.getResultList();
	}

	@Override
	public OuvintePromocao find(Integer idOuvinte, Integer idPromocao) throws Exception {
		Query qry = getDatastore().createNamedQuery("OuvintePromocao.recuperarPorOuvinteEPromocao");
		qry.setParameter("idOuvinte", idOuvinte);
		qry.setParameter("idPromocao", idPromocao);
		return (OuvintePromocao) qry.getSingleResult();
	}

	@Override
	public void removerPorPromocao(Integer idPromocao) {
		List<OuvintePromocao> ops = recuperarPorPromocao(idPromocao);
		for(OuvintePromocao op : ops){
			remover(op);
		}
	}

	@Override
	public void removerPorOuvinte(Integer idOuvinte) {
		List<OuvintePromocao> ops = recuperarPorOuvinte(idOuvinte);
		for(OuvintePromocao op : ops){
			remover(op);
		}		
	}

	

}
