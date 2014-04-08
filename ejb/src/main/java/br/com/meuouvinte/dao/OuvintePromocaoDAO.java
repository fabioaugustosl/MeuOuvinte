package br.com.meuouvinte.dao;

import java.io.Serializable;
import java.util.List;

import br.com.meuouvinte.modelos.OuvintePromocao;


public class OuvintePromocaoDAO extends GeralDAO<OuvintePromocao> implements IOuvintePromocaoDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Integer idOuvinte, Integer idPromocao) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OuvintePromocao> recuperarPorPromocao(Integer idPromocao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OuvintePromocao> recuperarPorOuvinte(Integer idOuvinte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OuvintePromocao find(Integer idOuvinte, Integer idPromocao)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerPorPromocao(Integer idPromocao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerPorOuvinte(Integer idOuvinte) {
		// TODO Auto-generated method stub
		
	}

	

}
