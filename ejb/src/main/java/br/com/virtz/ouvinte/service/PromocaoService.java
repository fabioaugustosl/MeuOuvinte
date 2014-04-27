package br.com.virtz.ouvinte.service;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.meuouvinte.dao.IOuvinteDAO;
import br.com.meuouvinte.dao.IOuvintePromocaoDAO;
import br.com.meuouvinte.dao.ISorteioDAO;
import br.com.meuouvinte.dao.PromocaoDAO;
import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.OuvintePromocao;
import br.com.meuouvinte.modelos.Promocao;

public class PromocaoService implements IPromocaoService , Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private PromocaoDAO promocaoDAO;

	@Inject
	private IOuvinteDAO ouvinteDAO;

	@Inject
	private ISorteioDAO sorteioDAO;

	@Inject
	private IOuvintePromocaoDAO ouvintePromocaoDAO;

	

	public String salvar(Promocao promocao){
		
		promocaoDAO.salvar(promocao);
		
		return null;
	}

	public String salvarAjax(ActionEvent event, Promocao promocao) {
		return salvar(promocao);
	}

	public List<Promocao> recuperarPromocoes(){
		return promocaoDAO.recuperarTodos();
	}

	public String remover(Promocao promocao){
		promocaoDAO.remover(promocao);
		return null;
	}

	public PromocaoDAO getPromocaoDAO() {
		return promocaoDAO;
	}

	public void setPromocaoDAO(PromocaoDAO promocaoDAO) {
		this.promocaoDAO = promocaoDAO;
	}

	public List<Promocao> recuperarPromocoesAtivas() {
		return promocaoDAO.recuperarPromocoesAtivas();
	}
	
	public List<Promocao> getPromocoesOuvinte(Ouvinte ouvinte) {
		List<OuvintePromocao> ouvintesPromocoes = ouvintePromocaoDAO.recuperarPorOuvinte(ouvinte.getId());
		Set<Promocao> promocoes = new HashSet<Promocao>();
		for(OuvintePromocao op : ouvintesPromocoes){
			promocoes.add(op.getPromocao());
		}
		return new ArrayList<Promocao>(promocoes);
	}

	public List<Ouvinte> recuperarOuvintesDePromocao(Promocao promocao){
		Set<Ouvinte> ouvintes = new HashSet<Ouvinte>();
		if(promocao != null && promocao.getId() != null){
			List<OuvintePromocao> ouvintesPromocoes = ouvintePromocaoDAO.recuperarPorPromocao(promocao.getId());
			for(OuvintePromocao op : ouvintesPromocoes){
				ouvintes.add(op.getOuvinte());
			}
		} 
		
		return new ArrayList<Ouvinte>(ouvintes);
	}

	
}
