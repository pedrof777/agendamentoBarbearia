package br.com.pferreira.services.generic;

import br.com.pferreira.dao.generic.IGenericDAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Pedro Ferreira
 */

public abstract class GenericService<T, E extends Serializable>
        implements IGenericService<T,E>  {

  protected IGenericDAO<T, E> genericDAO;

  public GenericService(){}

  public GenericService(IGenericDAO<T,E> genericDAO){
    this.genericDAO = genericDAO;
  }

  @Override
  public T cadastrar(T entity) throws Exception {
    return genericDAO.cadastrar(entity);
  }

  @Override
  public T alterar(T entity) throws Exception {
    return genericDAO.alterar(entity);
  }

  @Override
  public void excluir(T entity) throws Exception {
    genericDAO.excluir(entity);
  }

  @Override
  public T consultar(E id) throws Exception {
    return genericDAO.consultar(id);
  }

  @Override
  public Collection<T> buscarTodos() throws Exception {
    return genericDAO.buscarTodos();
  }
}
