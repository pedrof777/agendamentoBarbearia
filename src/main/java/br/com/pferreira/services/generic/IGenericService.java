package br.com.pferreira.services.generic;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Pedro Ferreira
 */

public interface IGenericService <T, E extends Serializable> {

  T cadastrar(T entity) throws Exception;

  T alterar(T entity) throws Exception;

  void excluir(T entity) throws Exception;

  T consultar(E id) throws Exception;

  Collection<T> buscarTodos() throws Exception;
}
