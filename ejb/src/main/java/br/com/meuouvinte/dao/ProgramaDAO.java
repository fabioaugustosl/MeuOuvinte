package br.com.meuouvinte.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.meuouvinte.modelos.Programa;

@Stateless
public class ProgramaDAO extends GeralDAO<Programa> implements IProgramaDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Programa recuperar(String nome) {
		Query qry = getDatastore().createNamedQuery("Programa.recuperarPorNome");
		qry.setParameter("nome", nome);
		return (Programa) qry.getSingleResult();
	}
	
}
