package br.com.virtz.ouvinte.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Promocao;
import br.com.virtz.ouvinte.service.IOuvinteService;

@ManagedBean
@ViewScoped
public class OuvinteMB {

	private static final long serialVersionUID = 1L;


	@Inject
	private IOuvinteService ouvinteService;

	private Ouvinte ouvinte;
	private Long idPromocaoSelecionada;
	private List<Ouvinte> ouvintes;
	// private OuvinteList ouvinteList;
	private Ouvinte[] ouvintesSelecionados;
	private Ouvinte ouvinteSelecionado;
	private String nomePesquisa;
	private Promocao promocaoSelecionadaOuvinte;

	private List<Ouvinte> ouvintesPorPromocao;

	private Long idPromocao;

	private Long value;

	private Boolean exibirPanelParticipar = Boolean.FALSE;

	public OuvinteMB() {
		super();
		ouvinte = new Ouvinte();
	}

	public String pesquisar(ActionEvent event) {
		exibirPanelParticipar = Boolean.FALSE;
		ouvintes = ouvinteService.pesquisar(nomePesquisa);
		return null;
	}

	public String salvar() {
		ouvinteService.salvar(ouvinte);
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Ouvinte salvo com sucesso!"));
		return null;
	}

	public String salvarAjax(ActionEvent event) {
		return salvar();
	}

	public void carregarPromocoesAtivas() {
		// promocaoModel.carregarPromocoesAtivas();
	}

	public List<Promocao> getPromocoesAtivas() {
		// return promocaoModel.getPromocoesAtivas();
		return null;
	}

	public void novo() {
		this.ouvinte = new Ouvinte();
		this.ouvinteSelecionado = null;
	}

	public void remover() {
		for (Ouvinte o : ouvintesSelecionados) {
			// TODO : fazer a validação se o cara já foi sorteado
			/*
			 * List<Sorteio> sorteios =
			 * sorteioDAO.recuperarPorOuvinte(o.getId().getId()); if(sorteios !=
			 * null && !sorteios.isEmpty()){
			 * FacesContext.getCurrentInstance().addMessage("", new
			 * FacesMessage("Ouvinte "+o.getNome()+
			 * " não pode ser excluído porque ele já foi sorteado em alguma promoção."
			 * )); continue; }
			 */
			ouvinteService.remover(o);
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage("Ouvinte " + o.getNome()
							+ " excluído com sucesso!"));
		}
		this.ouvintes = ouvinteService.recuperarOuvintes();
	}

	@PostConstruct
	public void prepararListagem() {
		this.ouvintes = ouvinteService.recuperarOuvintes();
	}

	public String atualizar() {
		nomePesquisa = "";
		this.ouvintes = ouvinteService.recuperarOuvintes();
		return null;
	}

	public List<Promocao> getPromocaoOuvinte() {
		/*
		 * if(ouvinte != null && ouvinte.getId() != null){ return
		 * promocaoModel.getPromocoesOuvinte(ouvinteModel.getOuvinte()); }
		 */
		return null;
	}

	public String adicionarPromocao() {
		/*
		 * try { ouvintePromocaoDAO.salvar(ouvinte.getId().getId(), idPromocao);
		 * FacesContext.getCurrentInstance().addMessage("", new
		 * FacesMessage("Ouvinte aicionado � promo��o com sucesso!")); } catch
		 * (EntityNotFoundException e) { e.printStackTrace();
		 * FacesContext.getCurrentInstance().addMessage("", new
		 * FacesMessage("Ocorreu uma falha ao adicionar o ouvinte � promo��o.!"
		 * )); }
		 */
		return null;
	}

	public String participarPromocao() throws EntityNotFoundException {
		/*
		 * ocultarPainelParticipar(); return ouvinteModel.particiarPromocao();
		 */
		return null;
	}

	public String limparPesquisa() {
		nomePesquisa = null;
		// setOuvinteList(null);
		return null;
	}

	public List<String> autoCompleteCidade(String padrao) {
		// TODO :
		// return cidadeModel.autoCompleteCidade(padrao);
		return null;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(ValueChangeEvent e) {
		this.value = (Long) e.getNewValue();
	}

	public Boolean getExibirPanelParticipar() {
		return exibirPanelParticipar;
	}

	public void mostrarPainelParticipar() {
		setExibirPanelParticipar(Boolean.TRUE);
	}

	public void ocultarPainelParticipar() {
		setExibirPanelParticipar(Boolean.FALSE);
	}

	private void setExibirPanelParticipar(Boolean exibirPanelParticipar) {
		this.exibirPanelParticipar = exibirPanelParticipar;
	}
}
