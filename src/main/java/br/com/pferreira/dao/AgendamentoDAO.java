package br.com.pferreira.dao;

import br.com.pferreira.dao.generic.GenericDAO;
import br.com.pferreira.domain.Agendamento;
import br.com.pferreira.domain.Barbeiro;
import br.com.pferreira.util.JPAutil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Pedro Ferreira
 */

@ApplicationScoped
public class AgendamentoDAO extends GenericDAO<Agendamento, Long> implements IAgendamentoDAO {

  public AgendamentoDAO(){
    super(Agendamento.class);
  }

  @Override
  public List<Agendamento> buscarPorBarbeiroEData(Barbeiro barbeiro, LocalDateTime inicio, LocalDateTime fim) throws Exception {
    EntityManager em = JPAutil.getEntityManager();
    return em.createQuery(
                "SELECT a FROM Agendamento a " +
                    "WHERE a.barbeiro = :barbeiro " +
                    "AND a.dataHora BETWEEN :inicio AND :fim " +
                    "AND a.status = 'AGENDADO'", Agendamento.class)
            .setParameter("barbeiro", barbeiro)
            .setParameter("inicio", inicio)
            .setParameter("fim", fim)
            .getResultList();
  }
}
