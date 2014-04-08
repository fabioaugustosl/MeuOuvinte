package br.com.meuouvinte.dao;

import java.util.List;

public interface OperacacoesCrud<T> {
	public T salvar(T entidade);
	public List<T> recuperarTodos();
	public T recuperarPorId(Integer id) throws Exception;
	public void remover(T entidade);
	public void remover(Integer id) throws Exception;
}
