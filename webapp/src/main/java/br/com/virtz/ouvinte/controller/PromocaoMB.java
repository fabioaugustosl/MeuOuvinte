package br.com.virtz.ouvinte.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Promocao;
import br.com.meuouvinte.modelos.Sorteio;
import br.com.virtz.ouvinte.service.PromocaoService;


@ManagedBean
@ViewScoped
public class PromocaoMB {

	private static final long serialVersionUID = 1L;

	@EJB
	private PromocaoService promocaoService;

	private Promocao promocao;

	private List<Promocao> promocoes;

	private List<Promocao> promocoesAtivas;

	private List<Ouvinte> ouvintesPromocao;

	private Promocao promocaoSelecionada;

	public PromocaoMB() {
		super();
		promocao = new Promocao();
	}

	public String salvar() {
		if(promocao.getDataFim().before(promocao.getDataInicio())){
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("A data de início deve ser antes da data final da promoão."));
			return null;
		}
		promocaoService.salvar(promocao);
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Promoão salva com sucesso!"));
		
		carregarPromocoes();
		return null;
	}

	public String salvarAjax(ActionEvent event){
		salvar();
		return null;
	}

	public void novo(){
		this.promocao = new Promocao();
	}

	public void remover(ActionEvent event){
		// TODO : importar o sorteio service e recuperar se a promoção já foi utilizada.
		
		/*List<Sorteio> sorteios = sorteioDAO.recuperar(promocao.getId().getId());
		
		if(sorteios != null && !sorteios.isEmpty()){
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("A promo��o "+promocao.getNome()+" n�o pode ser exclu�da porque j� foi realizado seu sorteio."));
			return null;
		}*/

		promocaoService.remover(promocao);

		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Promoção removida com sucesso!"));
		novo();
		carregarPromocoes();
	}

	public void remover(){
		promocaoService.remover(promocao);
		novo();
		carregarPromocoes();
	}

	public void carregarPromocoes(){
		promocoes = promocaoService.recuperarPromocoes();
	}

	public void carregarPromocoesAtivas() {
		promocoesAtivas = promocaoService.recuperarPromocoesAtivas();
	}

	
	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}

	public void carregarOuvintesDePromocao(){
		ouvintesPromocao = promocaoService.recuperarOuvintesDePromocao(promocaoSelecionada);
	}

	public List<Ouvinte> getOuvintesPromocao(){
		return ouvintesPromocao;
	}
	
	public Promocao getPromocaoSelecionada() {
		return promocaoSelecionada;
	}

	public void setPromocaoSelecionada(Promocao promocaoSelecionada) {
		this.promocaoSelecionada = promocaoSelecionada;
	}

	public PromocaoService getPromocaoService() {
		return promocaoService;
	}

	public void setPromocaoService(PromocaoService promocaoService) {
		this.promocaoService = promocaoService;
	}

	public List<Promocao> getPromocoesAtivas() {
		return promocoesAtivas;
	}

	public void setPromocoesAtivas(List<Promocao> promocoesAtivas) {
		this.promocoesAtivas = promocoesAtivas;
	}

	public void setOuvintesPromocao(List<Ouvinte> ouvintesPromocao) {
		this.ouvintesPromocao = ouvintesPromocao;
	}
	

}
