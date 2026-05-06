package br.com.pferreira.dao;

import br.com.pferreira.dao.generic.GenericDAO;
import br.com.pferreira.domain.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 * @author Pedro Ferreira
 */

@ApplicationScoped
public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO{

  public ClienteDAO(){
    super(Cliente.class);
  }
}
