package br.com.pferreira.services;

import br.com.pferreira.dao.IAgendamentoDAO;
import br.com.pferreira.domain.Agendamento;
import br.com.pferreira.services.generic.GenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Pedro Ferreira
 */

@ApplicationScoped
public class AgendamentoService extends GenericService<Agendamento, Long>
        implements IAgendamentoService {

  private transient IAgendamentoDAO agendamentoDAO;

  @Inject
  public void setAgendamentoDAO(IAgendamentoDAO agendamentoDAO){

    this.genericDAO = agendamentoDAO;
    this.agendamentoDAO = agendamentoDAO;
  }

  public AgendamentoService(){super();}

  @Override
  public Agendamento cadastrar(Agendamento entity) throws Exception {
    validar(entity);
    verificarDisponibilidade(entity);
    entity.setStatus("AGENDADO");
    return super.cadastrar(entity);
  }

  @Override
  public Agendamento alterar(Agendamento entity) throws Exception {
    validar(entity);
    verificarDisponibilidade(entity);
    entity.setStatus("AGENDADO");
    return super.alterar(entity);
  }

  private void verificarDisponibilidade(Agendamento agendamento) throws Exception {
    int duracao = agendamento.getServico().getDuracaoMinutos();

    LocalDateTime inicioNovo = agendamento.getDataHora();
    LocalDateTime fimNovo = inicioNovo.plusMinutes(duracao);

    LocalDateTime inicioDia = inicioNovo.toLocalDate().atStartOfDay();
    LocalDateTime fimDia = inicioDia.plusDays(1);

    List<Agendamento> agendamentosExistente = agendamentoDAO
            .buscarPorBarbeiroEData(
                    agendamento.getBarbeiro(),
                    inicioDia,
                    fimDia
            );

    for (Agendamento existente : agendamentosExistente){

      if (agendamento.getId() != null && existente.getId().equals(agendamento.getId())) {
        continue;
      }


      LocalDateTime inicioExistente = existente.getDataHora();
      LocalDateTime fimExistente = inicioExistente
              .plusMinutes(existente.getServico().getDuracaoMinutos());

      Boolean conflita = inicioNovo.isBefore(fimExistente)
              && fimNovo.isAfter(inicioExistente);

      if (conflita){
        throw new IllegalArgumentException(
                "Horário indisponível! " + System.lineSeparator() +
                agendamento.getBarbeiro().getNome() +
                " já tem agendamento das " +
                inicioExistente.getHour() + ":" +
                String.format("%02d", inicioExistente.getMinute()) +
                " ás " +
                fimExistente.getHour() + ":" +
                String.format("%02d", fimExistente.getMinute()));
      }
    }
  }

  private void validar(Agendamento agendamento){
    if (agendamento.getCliente() == null){
      throw new IllegalArgumentException("Cliente é obrigatório!");
    }
    if (agendamento.getBarbeiro() == null){
      throw new IllegalArgumentException("Barbeiro é obrigatório!");
    }
    if (agendamento.getServico() == null){
      throw new IllegalArgumentException("Serviço é obrigatório!");
    }
    if (agendamento.getDataHora() == null){
      throw new IllegalArgumentException("Data e hora é obrigatório!");
    }
    if (agendamento.getDataHora().isBefore(LocalDateTime.now())){
      throw new IllegalArgumentException("Data e hora não pode ser no passado!");
    };

  }
}
