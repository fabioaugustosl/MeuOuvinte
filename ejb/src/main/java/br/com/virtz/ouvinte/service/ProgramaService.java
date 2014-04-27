package br.com.virtz.ouvinte.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.meuouvinte.dao.IProgramaDAO;
import br.com.meuouvinte.modelos.Programa;

public class ProgramaService implements IProgramaService, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private IProgramaDAO programaDAO;

	
	public ProgramaService() {
		super();
		
	}

	public String salvar(Programa programa){
		programaDAO.salvar(programa);
		return null;
	}


	public String remover(Programa programa){
		programaDAO.remover(programa);
		return null;
	}

	@Override
	public List<Programa> recuperarProgramas() {
		return programaDAO.recuperarTodos();
	}


}
