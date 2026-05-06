package br.com.pferreira;

import br.com.pferreira.dao.IServicoDAO;
import br.com.pferreira.domain.Servico;
import br.com.pferreira.services.ServicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Pedro Ferreira
 */

@ExtendWith(MockitoExtension.class)
public class ServicoServicetest {

  private ServicoService servicoService;

  @Mock
  private IServicoDAO servicoDAO;

  private Servico servico;

  @BeforeEach
  public void setUp(){
    servicoService = new ServicoService();
    servicoService.setService(servicoDAO);

    servico = new Servico();
    servico.setNome("Corte de cabelo");
    servico.setPreco(50.0);
    servico.setDuracaoMinutos(50);
  }

  @Test
  public void deveCadastrarServicoComSucesso() throws Exception {
    when(servicoDAO.cadastrar(servico)).thenReturn(servico);

    Servico servicoCadastrado = servicoService.cadastrar(servico);

    assertNotNull(servicoCadastrado);
    assertEquals("Corte de cabelo", servicoCadastrado.getNome());
    verify(servicoDAO, times(1)).cadastrar(servico);
  }

  @Test
  public void deveLancarExcecaoAoCadastrarServicoComNulo(){
    servico.setNome(null);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.cadastrar(servico));

    servico.setPreco(null);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.cadastrar(servico));

    servico.setDuracaoMinutos(null);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.cadastrar(servico));
  }

  @Test
  public void deveLancarExcecaoAoCadastrarServicoComVazio(){
    servico.setNome("");
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.cadastrar(servico));
  }

  @Test
  public void deveLancarExcecaoAoCadastrarServicoComPrecoInvalido(){
    servico.setPreco(0.0);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.cadastrar(servico));

    servico.setPreco(-10.0);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.cadastrar(servico));
  }

  @Test
  public void deveLancarExcecaoAoCadastrarServicoComDuracaoInvalida() {
    servico.setDuracaoMinutos(0);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.cadastrar(servico));

    servico.setDuracaoMinutos(-10);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.cadastrar(servico));
  }

  @Test
  public void deveAlterarServicoComSucesso() throws Exception {
    servico.setPreco(60.0);
    when(servicoDAO.alterar(servico)).thenReturn(servico);

    Servico servicoAlterado = servicoService.alterar(servico);

    assertNotNull(servicoAlterado);
    assertEquals(60.0, servicoAlterado.getPreco());
    verify(servicoDAO, times(1)).alterar(servico);
  }

  @Test
  public void deveLancarExcecaoAoAlterarServicoComPrecoInvalido(){
    servico.setPreco(0.0);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.alterar(servico));

    servico.setPreco(-10.0);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.alterar(servico));

  }

  @Test
  public void deveLancarExcecaoAoAlterarServicoComDuracaoMinutoInvalido(){
    servico.setDuracaoMinutos(0);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.alterar(servico));

    servico.setDuracaoMinutos(-10);
    assertThrows(IllegalArgumentException.class,
            () -> servicoService.alterar(servico));

  }

  @Test
  public void deveExcuirServicoComSucesso() throws Exception {
    doNothing().when(servicoDAO).excluir(servico);

    servicoService.excluir(servico);
    Servico servicoPesquisado = servicoService.consultar(servico.getId());
    assertNull(servicoPesquisado);

    verify(servicoDAO, times(1)).excluir(servico);
  }

  @Test
  public void deveBuscarTodosServicosComSucesso() throws Exception {
    Servico servico2 = new Servico();
    servico2.setNome("Barba");

    when(servicoDAO.buscarTodos()).thenReturn(List.of(servico, servico2));

    var servicos = servicoService.buscarTodos();
    assertNotNull(servicos);
    assertEquals(2, servicos.size());
    verify(servicoDAO, times(1)).buscarTodos();
  }
}
