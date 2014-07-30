package br.com.virtz.ouvinte.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import br.com.meuouvinte.dao.IOuvinteDAO;
import br.com.meuouvinte.dao.IOuvintePromocaoDAO;
import br.com.meuouvinte.dao.IPromocaoDAO;
import br.com.meuouvinte.dao.ISorteioDAO;
import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.OuvintePromocao;
import br.com.meuouvinte.modelos.Promocao;
import br.com.meuouvinte.modelos.Sorteio;

public class SorteioService implements ISorteioService,  Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private IOuvinteDAO ouvinteDAO;

	@Inject
	private IPromocaoDAO promocaoDAO;

	@Inject
	private ISorteioDAO sorteioDAO;

	@Inject
	private IOuvintePromocaoDAO ouvintePromocaoDAO;

	
	public SorteioService() {
		super();
	}

	public String salvar(Sorteio sorteio){
		sorteioDAO.salvar(sorteio);
		return null;
	}

	public Ouvinte sortear(Promocao promocao, List<Ouvinte> ouvintesAptosAoSorteio) {
		if(promocao != null){

			Random random = new Random();
			Integer numSorteado = random.nextInt(ouvintesAptosAoSorteio.size());
			Ouvinte ouvinte = (Ouvinte) ouvintesAptosAoSorteio.get(numSorteado);


			sorteioDAO.salvar(promocao, ouvinte, null);
			
			return ouvinte;

		}
		return null;
	}

	public List<OuvintePromocao> getOuvintesDaPromocao(Promocao promocao) {
		List<OuvintePromocao> ouvintesDaPromocao = ouvintePromocaoDAO.recuperarPorPromocao(promocao.getId());
		return ouvintesDaPromocao;
	}

	public List<Ouvinte> getOuvintesAptosParaSorteio(Promocao promocao) {
		List<OuvintePromocao> ouvintesDaPromocao = getOuvintesDaPromocao(promocao);

		if(ouvintesDaPromocao == null || ouvintesDaPromocao.isEmpty()){
			return null;
		}
		
		List<Sorteio> sorteiosParaPromocao = sorteioDAO.recuperarPorPromocao(promocao.getId());
		List<Ouvinte> ouvintesAptosAoSorteio = new ArrayList<Ouvinte>();

		for(OuvintePromocao op : ouvintesDaPromocao){
			if(!ouvinteFoiSorteado(sorteiosParaPromocao, op.getOuvinte())){
				ouvintesAptosAoSorteio.add(op.getOuvinte());
			}
		}
		return ouvintesAptosAoSorteio;
	}

	private Boolean ouvinteFoiSorteado(List<Sorteio> sorteiosParaPromocao, Ouvinte ouvinte){
		for(Sorteio s : sorteiosParaPromocao){
			if(ouvinte.equals(s.getSorteado())){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	public List<Ouvinte> recuperarOuvintesSorteados(Promocao promocao) {
			List<OuvintePromocao> ops = ouvintePromocaoDAO.recuperarPorPromocao(promocao.getId());
			
			List<Ouvinte> ouvintes = new ArrayList<Ouvinte>();
			
			if(ouvintes != null && !ouvintes.isEmpty()){
				for(OuvintePromocao op : ops){
					ouvintes.add(op.getOuvinte());
				}
			}
			
			return ouvintes;
	}


	public List<Sorteio> recuperarSorteioOuvinte(Ouvinte ouvinte){
		return  sorteioDAO.recuperarPorOuvinte(ouvinte.getId());
	}



}
