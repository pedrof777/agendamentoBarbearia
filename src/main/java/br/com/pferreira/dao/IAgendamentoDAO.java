package br.com.pferreira.dao;

import br.com.pferreira.dao.generic.IGenericDAO;
import br.com.pferreira.domain.Agendamento;
import br.com.pferreira.domain.Barbeiro;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Pedro Ferreira
 */

public interface IAgendamentoDAO extends IGenericDAO<Agendamento, Long> {

  List<Agendamento> buscarPorBarbeiroEData(Barbeiro barbeiro, LocalDateTime inicio, LocalDateTime fim) throws Exception;
}
