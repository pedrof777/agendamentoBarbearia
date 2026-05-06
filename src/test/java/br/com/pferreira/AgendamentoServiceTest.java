package br.com.pferreira;

import br.com.pferreira.dao.IAgendamentoDAO;
import br.com.pferreira.domain.Agendamento;
import br.com.pferreira.domain.Barbeiro;
import br.com.pferreira.domain.Cliente;
import br.com.pferreira.domain.Servico;
import br.com.pferreira.services.AgendamentoService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Pedro Ferreira
 */


@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

  private AgendamentoService agendamentoService;

  @Mock
  private IAgendamentoDAO agendamentoDAO;

  private Agendamento agendamento;
  private Barbeiro barbeiro;
  private Cliente cliente;
  private Servico servico;

  @BeforeEach
  public void setUp(){
    agendamentoService = new AgendamentoService();
    agendamentoService.setAgendamentoDAO(agendamentoDAO);

    cliente = new Cliente();
    cliente.setId(1L);
    cliente.setEmail("jona@gmail.com");
    cliente.setNome("Jona");
    cliente.setTelefone("11999999999");

    barbeiro = new Barbeiro();
    barbeiro.setId(1L);
    barbeiro.setNome("Barbeiro 1");

    servico = new Servico();
    servico.setId(1L);
    servico.setNome("Corte de Cabelo");
    servico.setPreco(40.0);
    servico.setDuracaoMinutos(30);

    agendamento = new Agendamento();
    agendamento.setCliente(cliente);
    agendamento.setBarbeiro(barbeiro);
    agendamento.setServico(servico);
    agendamento.setDataHora(LocalDateTime.now().plusDays(1));
  }

  @Test
  public void deveCadastrarAgendamentoComSucesso() throws Exception {
    when(agendamentoDAO.buscarPorBarbeiroEData(any(), any(), any()))
            .thenReturn(List.of());
    when(agendamentoDAO.cadastrar(agendamento)).thenReturn(agendamento);

    Agendamento agendamentoCadastrado = agendamentoService.cadastrar(agendamento);
    assertNotNull(agendamentoCadastrado);
    assertEquals("AGENDADO", agendamentoCadastrado.getStatus());
    verify(agendamentoDAO, times(1)).cadastrar(agendamento);

  }

  @Test
  public void deveLancarExcecaoQuandoClienteNulo(){
    agendamento.setCliente(null);

    assertThrows(IllegalArgumentException.class,
            () -> agendamentoService.cadastrar(agendamento));
  }

  @Test
  public void deveLancarExcecaoQuandoBarbeiroNulo(){
    agendamento.setBarbeiro(null);

    assertThrows(IllegalArgumentException.class,
            () -> agendamentoService.cadastrar(agendamento));
  }

  @Test
  public void deveLancarExcecaoQuandoServicoNulo(){
    agendamento.setServico(null);

    assertThrows(IllegalArgumentException.class,
            () -> agendamentoService.cadastrar(agendamento));
  }

  @Test
  public void deveLancarExcecaoQuandoDataNula(){
    agendamento.setDataHora(null);

    assertThrows(IllegalArgumentException.class,
            () -> agendamentoService.cadastrar(agendamento));
  }

  @Test
  public void deveLancarExcecaoQuandoDataNoPassado(){
    agendamento.setDataHora(LocalDateTime.now().minusDays(1));

    assertThrows(IllegalArgumentException.class,
            () -> agendamentoService.cadastrar(agendamento));
  }

  @Test
  public void deveLancarExcecaoQuandoHorarioConflita() throws Exception {
    Agendamento agendamentoExistente = new Agendamento();
    agendamentoExistente.setBarbeiro(barbeiro);
    agendamentoExistente.setServico(servico);
    agendamentoExistente.setCliente(cliente);
    agendamentoExistente.setDataHora(agendamento.getDataHora());
    agendamentoExistente.setStatus("AGENDADO");

    when(agendamentoDAO.buscarPorBarbeiroEData(any(),any(), any()))
            .thenReturn(List.of(agendamentoExistente));

    assertThrows(IllegalArgumentException.class,
            () -> agendamentoService.cadastrar(agendamento));
  }
}
