package br.com.meuouvinte.dao;

import java.io.Serializable;
import java.util.List;

import br.com.meuouvinte.modelos.Sorteio;

public class SorteioDAO extends GeralDAO<Sorteio> implements ISorteioDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Integer idPromocao, Integer idOuvinte,
			String emailUsuarioResponsavel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Sorteio> recuperarPorOuvinte(Integer idOuvinte) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
