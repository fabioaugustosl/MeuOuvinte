package br.com.meuouvinte.models;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.meuouvinte.bean.Programa;
import br.com.meuouvinte.dao.IProgramaDAO;

import com.google.inject.Inject;

public class ProgramaModel implements  Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IProgramaDAO programaDAO;

	private Programa programa;
	private List<Programa> programas;
	private Programa[] programasSelecionados;
	private Programa programaSelecionado;

	public ProgramaModel() {
		super();
		programa = new Programa();
	}

	public String salvar(){
		programaDAO.salvar(programa);
		carregarProgramas();
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Programa salvo com sucesso!"));
		return null;
	}

	public String salvarAjax(ActionEvent event) {
		salvar();
		this.programas = null;
		carregarProgramas();
		return null;
	}


	public void remover(){
		for(Programa o : programasSelecionados){
			programaDAO.remover(o);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Programa "+o.getNome()+" excluído com sucesso!"));
		}
		this.programas = null;

		carregarProgramas();
	}

	public void carregarProgramas(){
		programas = programaDAO.recuperarTodos();
	}


	public void novo(){
		this.programa = new Programa();
		this.programaSelecionado = null;
	}

	public String atualizar(){
		carregarProgramas();
		return null;
	}

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
