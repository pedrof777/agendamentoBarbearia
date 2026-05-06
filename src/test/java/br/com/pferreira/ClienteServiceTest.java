package br.com.pferreira;

import br.com.pferreira.dao.IClienteDAO;
import br.com.pferreira.domain.Barbeiro;
import br.com.pferreira.domain.Cliente;
import br.com.pferreira.services.ClienteService;
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
public class ClienteServiceTest {

  private ClienteService clienteService;

  @Mock
  private IClienteDAO clienteDAO;

  private Cliente cliente;

  @BeforeEach
  public void setUp(){
    clienteService = new ClienteService();
    clienteService.setClienteDAO(clienteDAO);

    cliente = new Cliente();
    cliente.setEmail("jona@gmail.com");
    cliente.setNome("Jona");
    cliente.setTelefone("11999999999");
  }

  @Test
  public void deveCadastrarClienteComSucesso() throws Exception {
    when(clienteDAO.cadastrar(cliente)).thenReturn(cliente);

    Cliente clienteCadastrado = clienteService.cadastrar(cliente);

    assertNotNull(clienteCadastrado);
    assertEquals("Jona", clienteCadastrado.getNome());
    verify(clienteDAO, times(1)).cadastrar(cliente);
  }

  @Test
  public void deveLancarExcecaoAoCadastrarClienteComNulo(){
    cliente.setNome(null);
    assertThrows(IllegalArgumentException.class,
            () -> clienteService.cadastrar(cliente));

    cliente.setEmail(null);
    assertThrows(IllegalArgumentException.class,
            () -> clienteService.cadastrar(cliente));

    cliente.setTelefone(null);
    assertThrows(IllegalArgumentException.class,
            () -> clienteService.cadastrar(cliente));
  }

  @Test
  public void deveLancarExcecaoAoCadastrarClienteComVazio(){
    cliente.setNome("");
    assertThrows(IllegalArgumentException.class,
            () -> clienteService.cadastrar(cliente));

    cliente.setEmail("");
    assertThrows(IllegalArgumentException.class,
            () -> clienteService.cadastrar(cliente));

    cliente.setTelefone("");
    assertThrows(IllegalArgumentException.class,
            () -> clienteService.cadastrar(cliente));
  }

  @Test
  public void deveAlterarClienteComSucesso() throws Exception {
    cliente.setId(1L);
    when(clienteDAO.alterar(cliente)).thenReturn(cliente);

    Cliente clienteAlterado = clienteService.alterar(cliente);

    assertNotNull(clienteAlterado);
    assertEquals(1L, clienteAlterado.getId());
    verify(clienteDAO, times(1)).alterar(cliente);
  }

  @Test
  public void deveExcluirClienteComSucesso() throws Exception {
    doNothing().when(clienteDAO).excluir(cliente);

    clienteService.excluir(cliente);
    Cliente clienteConsultado2 = clienteService.consultar(cliente.getId());
    assertNull(clienteConsultado2);

    verify(clienteDAO, times(1)).excluir(cliente);
  }

  @Test
  public void deveBuscarTodosClientesComSucesso() throws Exception {
    Cliente cliente2 = new Cliente();
    cliente2.setNome("Paulao");

    when(clienteDAO.buscarTodos()).thenReturn(List.of(cliente, cliente2));

    var clientes = clienteService.buscarTodos();

    assertNotNull(clientes);
    assertEquals(2, clientes.size());
    verify(clienteDAO, times(1)).buscarTodos();
  }
}
