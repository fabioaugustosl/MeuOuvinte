package br.com.meuouvinte.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.meuouvinte.bean.Ouvinte;
import br.com.meuouvinte.bean.Promocao;
import br.com.meuouvinte.models.OuvinteModel;
import br.com.meuouvinte.models.PromocaoModel;
import br.com.meuouvinte.models.SorteioModel;
import br.com.meuouvinte.servlet.BasePageBean;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Inject;


@ManagedBean(name = "sorteioMB")
@SessionScoped
public class SorteioMB extends BasePageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private OuvinteModel ouvinteModel;

	@Inject
	private PromocaoModel promocaoModel;

	@Inject
	private SorteioModel sorteioModel;

	//@ManagedProperty("#{usuarioMB}")
	@Inject
	private UsuarioMB usuarioMB;

	public SorteioMB() {
		super();
	}

	public List<Ouvinte> recuperarOuvintes(){
		if(sorteioModel.getPromocao() != null){
			return ouvinteModel.recuperarOuvintes(sorteioModel.getPromocao().getId().getId());
		}
		return null;
	}

	public String sortear(ActionEvent event) throws EntityNotFoundException {
		sorteioModel.setUsuarioModel(usuarioMB.getUsuarioModel());
		sorteioModel.sortear(event);
		ouvinteModel.carregarOuvintesPorPromocao();
		return null;
	}

	public void carregarPromocoesAtivas(){
		 promocaoModel.carregarPromocoesAtivas();
	}

	public void carregarPromocoes(){
		 promocaoModel.carregarPromocoes();
	}

	public List<Promocao> getPromocoes(){
		return promocaoModel.getPromocoes();
	}

	public List<Promocao> getPromocoesAtivas(){
		return promocaoModel.getPromocoesAtivas();
	}

	public void carregarOuvintesDaPromocao(){
		ouvinteModel.setIdPromocao(sorteioModel.getIdPromocao());
		ouvinteModel.carregarOuvintesPorPromocao();
	}

	public List<Ouvinte> getOuvintesDaPromocao(){
		return ouvinteModel.getOuvintesPorPromocao();
	}

	public String limparTela(){
		sorteioModel.setIdPromocao(null);
		sorteioModel.setOuvintesSorteados(null);
		return null;
	}

	/* gets */
	public OuvinteModel getOuvinteModel() {
		return ouvinteModel;
	}

	public PromocaoModel getPromocaoModel() {
		return promocaoModel;
	}

	public SorteioModel getSorteioModel() {
		return sorteioModel;
	}

	public void limpar(){
		ouvinteModel.setIdPromocao(null);
		ouvinteModel.setOuvintesPorPromocao(null);
	}

	public void carregarSorteioOuvinte(){
		sorteioModel.carregarSorteioOuvinte();
	}

}
