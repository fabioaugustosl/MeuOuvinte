package br.com.virtz.ouvinte.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Ouvinte;
import br.com.meuouvinte.modelos.Promocao;
import br.com.meuouvinte.modelos.Sorteio;

@Local
public interface ISorteioService extends Serializable {
	public List<Ouvinte> getOuvintesAptosParaSorteio(Promocao promocao);
	public Ouvinte sortear(Promocao promocao, List<Ouvinte> ouvintesAptosAoSorteio);
	public List<Sorteio> recuperarSorteioOuvinte(Ouvinte ouvinte);
	public List<Ouvinte> recuperarOuvintesSorteados(Promocao promocao);
}
