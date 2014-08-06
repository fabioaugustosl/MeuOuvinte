package br.com.virtz.ouvinte.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import br.com.meuouvinte.modelos.Programa;
import br.com.virtz.ouvinte.service.IProgramaService;


@ManagedBean(name = "programaMB")
@SessionScoped
public class ProgramaMB {

	private static final long serialVersionUID = 1L;

	@EJB
	private IProgramaService programaService;
	
	private Programa programa;
	private List<Programa> programas;
	private Programa[] programasSelecionados;
	private Programa programaSelecionado;

	public ProgramaMB() {
		super();
		programa = new Programa();
	}

	public String salvar() {
		programaService.salvar(programaSelecionado);
		carregarProgramas();
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Programa salvo com sucesso!"));
		return null;	
	}


	public String salvarAjax(ActionEvent event) {
		return salvar();
	}


	public void novo(){
		this.programa = new Programa();
		this.programaSelecionado = null;
	}

	public void remover(){
		for(Programa o : programasSelecionados){
			programaService.remover(programaSelecionado);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Programa "+o.getNome()+" excluído com sucesso!"));
		}
		this.programas = null;

		carregarProgramas();
	}
	
	public void carregarProgramas(){
		programas = programaService.recuperarProgramas();
	}
	
	// GEtters e setters

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public List<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}

	public Programa[] getProgramasSelecionados() {
		return programasSelecionados;
	}

	public void setProgramasSelecionados(Programa[] programasSelecionados) {
		this.programasSelecionados = programasSelecionados;
	}

	public Programa getProgramaSelecionado() {
		return programaSelecionado;
	}

	public void setProgramaSelecionado(Programa programaSelecionado) {
		this.programaSelecionado = programaSelecionado;
	}
	

}
