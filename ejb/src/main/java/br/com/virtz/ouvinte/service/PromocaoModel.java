package br.com.meuouvinte.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.meuouvinte.bean.Ouvinte;
import br.com.meuouvinte.bean.OuvintePromocao;
import br.com.meuouvinte.bean.Promocao;
import br.com.meuouvinte.bean.Sorteio;
import br.com.meuouvinte.dao.IOuvinteDAO;
import br.com.meuouvinte.dao.IOuvintePromocaoDAO;
import br.com.meuouvinte.dao.ISorteioDAO;
import br.com.meuouvinte.dao.PromocaoDAO;

import com.google.inject.Inject;

public class PromocaoModel implements IPromocaoModel , Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private PromocaoDAO promocaoDAO;

	@Inject
	private IOuvinteDAO ouvinteDAO;

	@Inject
	private ISorteioDAO sorteioDAO;

	@Inject
	private IOuvintePromocaoDAO ouvintePromocaoDAO;

	private List<Promocao> promocoesAtivas;

	private List<Promocao> promocoes;

	private List<Ouvinte> ouvintesPromocao;

	private Promocao promocaoSelecionada;

	public String salvar(Promocao promocao){
		if(promocao.getDataFim().before(promocao.getDataInicio())){
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("A data de in�cio deve ser antes da data final da promo��o."));
			return null;
		}
		promocaoDAO.salvar(promocao);
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Promo��o salva com sucesso!"));
		return null;
	}

	public String salvarAjax(ActionEvent event, Promocao promocao) {
		return salvar(promocao);
	}

	public List<Promocao> recuperarPromocoes(){
		return promocaoDAO.recuperarPromocoes();
	}

	public String remover(Promocao promocao){
		List<Sorteio> sorteios = sorteioDAO.recuperar(promocao.getId().getId());
		if(sorteios != null && !sorteios.isEmpty()){
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("A promo��o "+promocao.getNome()+" n�o pode ser exclu�da porque j� foi realizado seu sorteio."));
			return null;
		}

		promocaoDAO.remover(promocao);
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Promo��o removida com sucesso!"));
		return null;
	}

	public PromocaoDAO getPromocaoDAO() {
		return promocaoDAO;
	}

	public void setPromocaoDAO(PromocaoDAO promocaoDAO) {
		this.promocaoDAO = promocaoDAO;
	}

	public void carregarPromocoesAtivas() {
		promocoesAtivas = promocaoDAO.recuperarPromocoesAtivas();
	}

	public void carregarPromocoes() {
		promocoes = promocaoDAO.recuperarPromocoes();
	}

	public List<Promocao> getPromocoesAtivas() {
		return promocoesAtivas;
	}

	public void setPromocoesAtivas(List<Promocao> promocoesAtivas) {
		this.promocoesAtivas = promocoesAtivas;
	}

	public List<Promocao> getPromocoesOuvinte(Ouvinte ouvinte) {
		List<OuvintePromocao> ouvintesPromocoes = ouvintePromocaoDAO.recuperarPorOuvinte(ouvinte.getId().getId());
		List<Long> idsPromocoes = new ArrayList<Long>();
		for(OuvintePromocao op : ouvintesPromocoes){
			idsPromocoes.add(op.getIdPromocao());
		}
		return idsPromocoes.isEmpty() ? null : promocaoDAO.recuperarPromocoes(idsPromocoes);
	}

	public void carregarOuvintesDePromocao(){
		if(getPromocaoSelecionada() != null && getPromocaoSelecionada().getId() != null){
			List<OuvintePromocao> ouvintesPromocoes = ouvintePromocaoDAO.recuperarPorPromocao(promocaoSelecionada.getId().getId());
			List<Long> idsOuvintes = new ArrayList<Long>();
			for(OuvintePromocao op : ouvintesPromocoes){
				idsOuvintes.add(op.getIdOuvinte());
			}
			ouvintesPromocao = idsOuvintes.isEmpty() ? new ArrayList<Ouvinte>() : ouvinteDAO.carregarOuvintes(idsOuvintes);
		} else {
			ouvintesPromocao = new ArrayList<Ouvinte>();
		}
	}

	public List<Ouvinte> getOuvintesPromocao() {
		return ouvintesPromocao;
	}

	public void setOuvintesPromocao(List<Ouvinte> ouvintesPromocao) {
		this.ouvintesPromocao = ouvintesPromocao;
	}

	public Promocao getPromocaoSelecionada() {
		return promocaoSelecionada;
	}

	public void setPromocaoSelecionada(Promocao promocaoSelecionada) {
		this.promocaoSelecionada = promocaoSelecionada;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}

}
