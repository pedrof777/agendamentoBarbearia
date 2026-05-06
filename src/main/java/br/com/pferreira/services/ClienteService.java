package br.com.pferreira.services;

import br.com.pferreira.dao.IClienteDAO;
import br.com.pferreira.domain.Cliente;
import br.com.pferreira.services.generic.GenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * @author Pedro Ferreira
 */

@ApplicationScoped
public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

  @Inject
  public void setClienteDAO(IClienteDAO clienteDAO){
    this.genericDAO = clienteDAO;
  }

  public ClienteService(){
    super();
  }

  @Override
  public Cliente cadastrar(Cliente entity) throws Exception {
    validar(entity);
    return super.cadastrar(entity);
  }

  @Override
  public Cliente alterar(Cliente entity) throws Exception {
    validar(entity);
    return super.alterar(entity);
  }

  private void validar(Cliente cliente){
    if (cliente.getNome() == null || cliente.getNome().isBlank()){
      throw new IllegalArgumentException("Nome do cliente é obrigatório!");
    }
    if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()){
      throw new IllegalArgumentException("Telefone do cliente é obrigatório!");
    }

  }
}
