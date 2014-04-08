package br.com.meuouvinte.dao;

import java.util.List;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.OuvintePromocao;
import br.com.meuouvinte.modelos.Promocao;

public interface IOuvintePromocaoDAO extends OperacacoesCrud<OuvintePromocao> {
	public void salvar(Ouvinte ouvinte, Promocao promocao) throws Exception;
	public List<OuvintePromocao> recuperarPorPromocao(Integer idPromocao);
	public List<OuvintePromocao> recuperarPorOuvinte(Integer idOuvinte);
	public OuvintePromocao find(Integer idOuvinte, Integer idPromocao) throws Exception;
	public abstract void removerPorPromocao(Integer idPromocao);
	public abstract void removerPorOuvinte(Integer idOuvinte);
}
