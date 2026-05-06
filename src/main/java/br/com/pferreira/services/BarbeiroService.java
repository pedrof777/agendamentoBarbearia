package br.com.pferreira.services;

import br.com.pferreira.dao.IBarbeiroDAO;
import br.com.pferreira.domain.Barbeiro;
import br.com.pferreira.services.generic.GenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * @author Pedro Ferreira
 */

@ApplicationScoped
public class BarbeiroService extends GenericService<Barbeiro, Long> implements IBarbeiroService {

  @Inject
  public void setBarbeiroDAO(IBarbeiroDAO barbeiroDAO){
    this.genericDAO = barbeiroDAO;
  }

  public BarbeiroService(){super();}

  @Override
  public Barbeiro cadastrar(Barbeiro entity) throws Exception {
    validar(entity);
    return super.cadastrar(entity);
  }

  @Override
  public Barbeiro alterar(Barbeiro entity) throws Exception {
    validar(entity);
    return super.alterar(entity);
  }

  private void validar(Barbeiro barbeiro){
    if(barbeiro.getNome() == null || barbeiro.getNome().isBlank()){
      throw new IllegalArgumentException("Nome do barbeiro é obrigatório!");
    }
  }
}
