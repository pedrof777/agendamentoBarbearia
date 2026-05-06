package br.com.pferreira.services;

import br.com.pferreira.dao.IServicoDAO;
import br.com.pferreira.domain.Servico;
import br.com.pferreira.services.generic.GenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * @author Pedro Ferreira
 */

@ApplicationScoped
public class ServicoService extends GenericService<Servico, Long>
  implements IServicoService{

  @Inject
  public void setService(IServicoDAO servicoDAO){
    this.genericDAO = servicoDAO;
  }

  public ServicoService(){super();}

  @Override
  public Servico cadastrar(Servico entity) throws Exception {
    validar(entity);
    return super.cadastrar(entity);
  }

  @Override
  public Servico alterar(Servico entity) throws Exception {
    validar(entity);
    return super.alterar(entity);
  }

  private void validar(Servico servico){
    if(servico.getNome() == null || servico.getNome().isBlank()){
      throw new IllegalArgumentException("Nome do serviço é obrigatório!");
    }
    if (servico.getPreco() == null || servico.getPreco() <= 0.0){
      throw new IllegalArgumentException("Preço do serviço é obrigatório!");
    }
    if (servico.getDuracaoMinutos() == null || servico.getDuracaoMinutos() <= 0){
      throw new IllegalArgumentException("Duração do serviço é obrigatório!");
    }
  }
}
