package br.com.meuouvinte.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.meuouvinte.bean.Ouvinte;
import br.com.meuouvinte.bean.OuvintePromocao;
import br.com.meuouvinte.bean.Promocao;
import br.com.meuouvinte.bean.Sorteio;
import br.com.meuouvinte.dao.IOuvinteDAO;
import br.com.meuouvinte.dao.IOuvintePromocaoDAO;
import br.com.meuouvinte.dao.IPromocaoDAO;
import br.com.meuouvinte.dao.ISorteioDAO;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Inject;

public class SorteioModel implements  Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private IOuvinteDAO ouvinteDAO;

	@Inject
	private IPromocaoDAO promocaoDAO;

	@Inject
	private ISorteioDAO sorteioDAO;

	@Inject
	private IOuvintePromocaoDAO ouvintePromocaoDAO;

	@Inject
	private UsuarioModel usuarioModel;

	private Promocao promocao;
	private Long idPromocao;
	private List<Ouvinte> ouvintesSorteados;
	private Sorteio sorteio;
	private Ouvinte ouvinteSelecionado;

	public SorteioModel() {
		super();
	}

	private String salvar(Sorteio sorteio){
		sorteioDAO.salvar(sorteio);
		return null;
	}

	public String sortear(ActionEvent event) throws EntityNotFoundException {
		if(idPromocao != null){
			List<Sorteio> sorteiosParaPromocao = sorteioDAO.recuperar(idPromocao);

			List<OuvintePromocao> ouvintesDaPromocao = ouvintePromocaoDAO.recuperarPorPromocao(idPromocao);

			if(ouvintesDaPromocao == null || ouvintesDaPromocao.isEmpty()){
				return null;
			}

			List<Long> ouvintesAptosAoSorteio = new ArrayList<Long>();

			for(OuvintePromocao op : ouvintesDaPromocao){
				if(!ouvinteFoiSorteado(sorteiosParaPromocao, op.getIdOuvinte())){
					ouvintesAptosAoSorteio.add(op.getIdOuvinte());
				}
			}

			if(ouvintesAptosAoSorteio.isEmpty()){
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Não existem mais ouvintes cadastrados nesta promoção aptos a serem sorteados."));
				return null;
			}

			Random random = new Random();
			Integer numSorteado = random.nextInt(ouvintesAptosAoSorteio.size());
			Long idOuvinteSorteado = ouvintesAptosAoSorteio.get(numSorteado);

			String emailUsuarioLogado = usuarioModel.getUsuarioLogado() != null ? usuarioModel.getUsuarioLogado().getEmail() : "-";

			sorteioDAO.salvar(idPromocao, idOuvinteSorteado, emailUsuarioLogado);

			//recuperarOuvintesSorteados();
			String p = "";
			try{
				Promocao promocao = promocaoDAO.findById(idPromocao);
				p =promocao.getNome();
			}catch (Exception e) {
			}
			String o = "";
			try {
				Ouvinte ouvinteSorteado = ouvinteDAO.findById(idOuvinteSorteado);
				o = ouvinteSorteado.getNome();
			}catch (Exception e) {
			}

			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Sorteio para promoção "+p+" realizado com sucesso!"));
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Ouvinte sorteado: "+o));
		}
		return null;
	}

	private Boolean ouvinteFoiSorteado(List<Sorteio> sorteiosParaPromocao, Long idOuvinte){
		for(Sorteio s : sorteiosParaPromocao){
			if(idOuvinte.equals(s.getIdOuvinteSorteado())){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	public void recuperarOuvintesSorteados() {
		if(idPromocao != null){
			List<Sorteio> sorteios = sorteioDAO.recuperar(idPromocao);
			if(sorteios != null && !sorteios.isEmpty()){
				List<Long> idsOuvintes = new ArrayList<Long>();
				for(Sorteio s : sorteios){
					idsOuvintes.add(s.getIdOuvinteSorteado());
				}
				ouvintesSorteados = ouvinteDAO.carregarOuvintes(idsOuvintes);
			}
		}
	}

	public List<Sorteio> recuperarPorOuvinte(Long idOuvinte){
		return sorteioDAO.recuperarPorOuvinte(idOuvinte);
	}

	public void carregarSorteioOuvinte(){
		if(getOuvinteSelecionado() != null){
			List<Sorteio> sorteios = sorteioDAO.recuperar(getOuvinteSelecionado().getId().getId());
			if(sorteios != null){
				for(Sorteio s : sorteios){
					if(s.getIdPromocao().equals(getIdPromocao())){
						getOuvinteSelecionado().setSorteado(sorteio);
						break;
					}
				}
			}
		}
	}


	/* gets e sets*/

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

	public Long getIdPromocao() {
		return idPromocao;
	}

	public void setIdPromocao(Long idPromocao) {
		this.idPromocao = idPromocao;
	}

	public Ouvinte getOuvinteSelecionado() {
		return ouvinteSelecionado;
	}

	public void setOuvinteSelecionado(Ouvinte ouvinteSelecionado) {
		this.ouvinteSelecionado = ouvinteSelecionado;
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}


}
