package br.com.meuouvinte.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Promocao;
import br.com.meuouvinte.modelos.Sorteio;

@Local
public interface ISorteioDAO extends OperacacoesCrud<Sorteio> {
	public void salvar(Promocao promocao, Ouvinte ouvinte, String emailUsuarioResponsavel);
	public abstract List<Sorteio> recuperarPorOuvinte(Integer idOuvinte);
}
