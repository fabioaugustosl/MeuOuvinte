package br.com.meuouvinte.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.meuouvinte.models.ProgramaModel;
import br.com.meuouvinte.servlet.BasePageBean;

import com.google.inject.Inject;


@ManagedBean(name = "programaMB")
@SessionScoped
public class ProgramaMB extends BasePageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramaModel programaModel;

	public ProgramaMB() {
		super();
	}

	public String salvar() {
		return programaModel.salvar();
	}


	public String salvarAjax(ActionEvent event) {
		return programaModel.salvarAjax(event);
	}


	public void novo(){
		programaModel.novo();
	}

	public void remover(){
		programaModel.remover();
	}

	public ProgramaModel getProgramaModel() {
		return programaModel;
	}

	public void prepararListagem(){
		programaModel.carregarProgramas();
	}

	public String atualizar(){
		return programaModel.atualizar();
	}


}
