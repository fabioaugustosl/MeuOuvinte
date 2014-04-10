package br.com.meuouvinte.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Promocao;
import br.com.meuouvinte.modelos.Sorteio;

@Stateless
public class SorteioDAO extends GeralDAO<Sorteio> implements ISorteioDAO, Serializable{

	private static final long serialVersionUID = 1L;

	
	@Override
	public List<Sorteio> recuperarPorOuvinte(Integer idOuvinte) {
		Query qry = getDatastore().createNamedQuery("Sorteio.recuperarPorIdOuvinte");
		qry.setParameter("idOuvinte", idOuvinte);
		return qry.getResultList();
	}

	@Override
	public void salvar(Promocao promocao, Ouvinte ouvinte, String emailUsuarioResponsavel) {
		Sorteio sorteio = new Sorteio();
		sorteio.setPromocao(promocao);
		sorteio.setSorteado(ouvinte);
		sorteio.setEmailUsuarioResponsavel(emailUsuarioResponsavel);
		sorteio.setDataSorteio(new Date());
		
		this.salvar(sorteio);
	}

	
}
