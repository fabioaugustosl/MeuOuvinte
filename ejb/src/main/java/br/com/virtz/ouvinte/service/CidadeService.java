package br.com.virtz.ouvinte.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.meuouvinte.modelos.enumerados.EnumCidades;

@Stateless
public class CidadeService implements ICidadeService{

	private static final long serialVersionUID = 1L;

	private EnumCidades enumCidades;

	public CidadeService() {
		super();
	}

	public List<String> autoCompleteCidade(String padrao) {
		List<String> ret = new ArrayList<String>();
		if(padrao== null){
			return ret;
		}
		for(EnumCidades c :enumCidades.values()){
			if(c.getNomeCidade().toUpperCase().startsWith(padrao.toUpperCase())){
				ret.add(c.getNomeCidade());
			}
		}
		return ret;
	}


}
