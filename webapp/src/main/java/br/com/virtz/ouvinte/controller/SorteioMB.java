package br.com.virtz.ouvinte.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Promocao;
import br.com.meuouvinte.modelos.Sorteio;
import br.com.virtz.ouvinte.service.IOuvinteService;
import br.com.virtz.ouvinte.service.IPromocaoService;
import br.com.virtz.ouvinte.service.ISorteioService;
import br.com.virtz.ouvinte.service.UsuarioService;


@ManagedBean(name = "sorteioMB")
@SessionScoped
public class SorteioMB  {

	private static final long serialVersionUID = 1L;

	@EJB
	private IOuvinteService ouvinteService;

	@EJB
	private IPromocaoService promocaoService;

	@EJB
	private ISorteioService sorteioService;
	
	@EJB
	private UsuarioService usuarioModel;

	private Promocao promocao;
	private Long idPromocao;
	private List<Ouvinte> ouvintesSorteados;
	private Sorteio sorteio;
	private Ouvinte ouvinteSelecionado;


	public SorteioMB() {
		super();
	}

	public List<Ouvinte> recuperarOuvintes(){
		if(promocao != null){
			return ouvinteService.recuperarOuvintes(promocao.getId());
		}
		return null;
	}
	
	public void recuperarOuvintesSorteados() {
		if(promocao != null){
			ouvintesSorteados = sorteioService.recuperarOuvintesSorteados(getPromocao());
		}
	}
	
	
	public String sortear(ActionEvent event)  {
		
		List<Ouvinte> ouvintesAptosAoSorteio = sorteioService.getOuvintesAptosParaSorteio(getPromocao());

		if(ouvintesAptosAoSorteio == null || ouvintesAptosAoSorteio.isEmpty()){
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("N�o existem mais ouvintes cadastrados nesta promo��o aptos a serem sorteados."));
			return null;
		}
		
		Ouvinte ouvinteSorteado = sorteioService.sortear(promocao, ouvintesAptosAoSorteio);
		
//		String emailUsuarioLogado = usuarioModel.getUsuarioLogado() != null ? usuarioModel.getUsuarioLogado().getEmail() : "-";
		

		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Sorteio para promoção "+promocao.getNome()+" realizado com sucesso!"));
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Ouvinte sorteado: "+ouvinteSorteado.getNome()));
		
//		ouvinteService.carregarOuvintesPorPromocao();
		return null;
	}

	
	public void carregarOuvintesDaPromocao(){
		if(getPromocao() != null){
			promocaoService.recuperarOuvintesDePromocao(getPromocao());
		}
	}

	
	public String limparTela(){
		promocao = null;
		ouvinteSelecionado = null;
		return null;
	}

	public void carregarSorteioOuvinte(){
		//sorteioService.();
	}

	public void limpar(){
		promocao = null;
		ouvinteSelecionado = null;
	}

	
	
	/* gets */
	
	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public List<Ouvinte> getOuvintesSorteados() {
		return ouvintesSorteados;
	}

	public void setOuvintesSorteados(List<Ouvinte> ouvintesSorteados) {
		this.ouvintesSorteados = ouvintesSorteados;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}

	public Ouvinte getOuvinteSelecionado() {
		return ouvinteSelecionado;
	}

	public void setOuvinteSelecionado(Ouvinte ouvinteSelecionado) {
		this.ouvinteSelecionado = ouvinteSelecionado;
	}

}
