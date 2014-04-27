package br.com.virtz.ouvinte.service;

import java.util.List;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Programa;
import br.com.meuouvinte.modelos.Promocao;

@Local
public interface IProgramaService {
	public String salvar(Programa programa);
	public List<Programa> recuperarProgramas();
	public String remover (Programa programa);
}
