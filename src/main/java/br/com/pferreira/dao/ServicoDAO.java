package br.com.pferreira.dao;

import br.com.pferreira.dao.generic.GenericDAO;
import br.com.pferreira.domain.Servico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 * @author Pedro Ferreira
 */

@ApplicationScoped
public class ServicoDAO extends GenericDAO<Servico, Long> implements IServicoDAO {

  public ServicoDAO(){
    super(Servico.class);
  }
}
