package br.com.pferreira.dao;

import br.com.pferreira.dao.generic.GenericDAO;
import br.com.pferreira.domain.Barbeiro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 * @author Pedro Ferreira
 */

@ApplicationScoped
public class BarbeiroDAO extends GenericDAO<Barbeiro, Long> implements IBarbeiroDAO {

  public BarbeiroDAO(){
    super(Barbeiro.class);
  }
}
