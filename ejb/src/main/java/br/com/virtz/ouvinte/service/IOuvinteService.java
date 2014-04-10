package br.com.virtz.ouvinte.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Ouvinte;

@Local
public interface IOuvinteService extends Serializable {
	public void salvar(Ouvinte ouvinte);
	public List<Ouvinte> recuperarOuvintes();
	public List<Ouvinte> recuperarOuvintes(Integer idPromocao);
	public void remover (Ouvinte ouvinte);
	public List<Ouvinte> pesquisar(String padrao);
}
