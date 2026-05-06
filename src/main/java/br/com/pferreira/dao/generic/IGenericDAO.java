package br.com.pferreira.dao.generic;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Pedro Ferreira
 */

public interface IGenericDAO<T, E extends Serializable >{

  T cadastrar(T entity) throws Exception;

  T alterar(T entity) throws Exception;

  void excluir(T entity) throws Exception;

  T consultar(E id) throws Exception;

  Collection<T> buscarTodos() throws Exception;
}
