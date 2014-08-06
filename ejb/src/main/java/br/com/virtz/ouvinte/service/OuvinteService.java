package br.com.virtz.ouvinte.service;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;

import br.com.meuouvinte.dao.IOuvinteDAO;
import br.com.meuouvinte.dao.IOuvintePromocaoDAO;
import br.com.meuouvinte.dao.ISorteioDAO;
import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Sorteio;

@Stateless
public class OuvinteService implements IOuvinteService{

	private static final long serialVersionUID = 1L;

	@EJB
	private IOuvinteDAO ouvinteDAO;

	@EJB
	private IOuvintePromocaoDAO ouvintePromocaoDAO;

	@EJB
	private ISorteioDAO sorteioDAO;

	
	public String participarPromocao() throws EntityNotFoundException  {
//		ouvintePromocaoDAO.salvar(getOuvinte().getId().getId(), getIdPromocaoSelecionada());
//		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Ouvinte cadastrado na promo��o com sucesso!"));
		return null;
	}

	public void remover(Ouvinte ouvinte){
		ouvinteDAO.remover(ouvinte);
	}

	public List<Ouvinte> recuperarOuvintes(){
		return ouvinteDAO.recuperarTodos();
	}

	public List<Ouvinte> recuperarOuvintes(Integer idPromocao){
		List<Ouvinte> ouvintes = ouvinteDAO.recuperarOuvintes(idPromocao);
		return ouvintes;
	}

	
	public List<Ouvinte>  carregarOuvintesPorPromocao(Integer idPromocao){

		if(idPromocao == null){
			return null;
		}
		
		List<Ouvinte> ouvintesPorPromocao = ouvinteDAO.recuperarOuvintes(idPromocao);

		/*List<OuvintePromocao> ouvintesPromocao = ouvintePromocaoDAO.recuperarPorPromocao(idPromocao);

		if(ouvintesPromocao != null && !ouvintesPromocao.isEmpty()){

			 List<Long> idsOuvintes = new ArrayList<Long>();
			 for(OuvintePromocao op : ouvintesPromocao){
				 idsOuvintes.add(op.getIdOuvinte());
			 }

			 ouvintesPorPromocao = ouvinteDAO.carregarOuvintes(idsOuvintes);

			 List<Sorteio> sorteios = sorteioDAO.recuperar(idPromocao);

			 for(Ouvinte o : ouvintesPorPromocao){
				 o.setSorteado(jaFoiSorteadoNestaPromocao(o, sorteios));
			 }

			 Collections.sort(ouvintesPorPromocao);
		}*/
		 Collections.sort(ouvintesPorPromocao);
		return ouvintesPorPromocao;

	}

	private Sorteio jaFoiSorteadoNestaPromocao(Ouvinte ouvinte, List<Sorteio> sorteios){
		for(Sorteio s : sorteios){
			if(s.getSorteado().equals(ouvinte)){
				return s;
			}
		}
		return null;
	}

	public List<Ouvinte> pesquisar(String padrao){
		List<Ouvinte> ouvintes = ouvinteDAO.pesquisar(padrao);
		Collections.sort(ouvintes);
		return ouvintes;
	}

	@Override
	public void salvar(Ouvinte ouvinte) {
		ouvinteDAO.salvar(ouvinte);		
	}


}
