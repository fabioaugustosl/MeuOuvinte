package br.com.meuouvinte.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.meuouvinte.modelos.EntidadePersistencia;


public abstract class GeralDAO<T> implements OperacacoesCrud<T> {

    @PersistenceContext(unitName="OuvinteDS")
    private EntityManager em;
	
	public EntityManager getDatastore() {
		return em;
	}
	
	protected Class getClazz(){
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        return (Class) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public T salvar(T entidade) {
		if(entidade instanceof EntidadePersistencia){
			if(((EntidadePersistencia) entidade).getId() == null){
				em.persist(entidade);
				return entidade;
			} else {
				return em.merge(entidade);
			}
		}
		return null;
	}
	
	@Override
	public T recuperarPorId(Integer id) throws Exception {
		Class clazz = getClazz();
		return (T) em.find(clazz, id);
	}
	
	@Override
	public List<T> recuperarTodos() {
		Class clazz = getClazz();
		Query qry = em.createQuery("SELECT e FROM "+clazz.getSimpleName()+" e ");
		return qry.getResultList();
	}
	
	@Override
	public void remover(T entidade) {
		em.remove(entidade);
	}
	
	@Override
	public void remover(Integer id) throws Exception {
		T e = recuperarPorId(id);
		em.remove(e);
	}

}
