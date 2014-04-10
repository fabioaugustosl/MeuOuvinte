package br.com.meuouvinte.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.meuouvinte.bean.Ouvinte;
import br.com.meuouvinte.bean.Promocao;
import br.com.meuouvinte.models.PromocaoModel;
import br.com.meuouvinte.servlet.BasePageBean;

import com.google.inject.Inject;


@ManagedBean(name = "promocaoMB")
@SessionScoped
public class PromocaoMB extends BasePageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private PromocaoModel promocaoModel;

	private Promocao promocao;

	private List<Promocao> promocoes;


	public PromocaoMB() {
		super();
		promocao = new Promocao();
	}

	public String salvar() {
		promocaoModel.salvar(promocao);
		carregarPromocoes();
		return null;
	}

	public String salvarAjax(ActionEvent event){
		promocaoModel.salvarAjax(event, promocao);
		carregarPromocoes();
		return null;
	}

	public void novo(){
		this.promocao = new Promocao();
	}

	public void remover(ActionEvent event){
		promocaoModel.remover(promocao);
		novo();
		carregarPromocoes();
	}

	public void remover(){
		promocaoModel.remover(promocao);
		novo();
		carregarPromocoes();
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public void carregarPromocoes(){
		promocoes = promocaoModel.recuperarPromocoes();
	}
	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}

	public Promocao getPromocaoSelecionada() {
		return promocaoModel.getPromocaoSelecionada();
	}

	public void setPromocaoSelecionada(Promocao promocaoSelecionada) {
		promocaoModel.setPromocaoSelecionada(promocaoSelecionada);
	}

	public void carregarOuvintesDePromocao(){
		promocaoModel.carregarOuvintesDePromocao();
	}

	public List<Ouvinte> getOuvintesPromocao(){
		return promocaoModel.getOuvintesPromocao();
	}

	public PromocaoModel getPromocaoModel() {
		return promocaoModel;
	}

	public void setPromocaoModel(PromocaoModel promocaoModel) {
		this.promocaoModel = promocaoModel;
	}

}
