package br.com.pferreira;

import br.com.pferreira.dao.IBarbeiroDAO;
import br.com.pferreira.domain.Barbeiro;
import br.com.pferreira.services.BarbeiroService;
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
public class BarbeiroServiceTest {

  private BarbeiroService barbeiroService;

  @Mock
  private IBarbeiroDAO barbeiroDAO;

  private Barbeiro barbeiro;

  @BeforeEach
  public void setUp(){
    barbeiroService = new BarbeiroService();
    barbeiroService.setBarbeiroDAO(barbeiroDAO);

    barbeiro = new Barbeiro();
    barbeiro.setNome("Tucano da gillete");
  }

  @Test
  public void deveCadastrarBarbeiroComSucesso() throws Exception {
    when(barbeiroDAO.cadastrar(barbeiro)).thenReturn(barbeiro);

    Barbeiro barbeiroCadastrado = barbeiroService.cadastrar(barbeiro);

    assertNotNull(barbeiroCadastrado);
    assertEquals("Tucano da gillete", barbeiroCadastrado.getNome());
    verify(barbeiroDAO, times(1)).cadastrar(barbeiro);
  }

  @Test
  public void deveLancarExcecaoAoCadastrarBarbeiroComNulo(){
    barbeiro.setNome(null);
    assertThrows(IllegalArgumentException.class,
            () -> barbeiroService.cadastrar(barbeiro));
  }

  @Test
  public void deveLancarExcecaoAoCadastrarBarbeiroComVazio(){
    barbeiro.setNome("");
    assertThrows(IllegalArgumentException.class,
            () -> barbeiroService.cadastrar(barbeiro));
  }

  @Test
  public void deveAlterarClienteComSucesso() throws Exception {
    barbeiro.setNome("Tucano");
    when(barbeiroDAO.alterar(barbeiro)).thenReturn(barbeiro);

    Barbeiro barbeiroAlterado = barbeiroService.alterar(barbeiro);
    assertNotNull(barbeiroAlterado);
    assertEquals("Tucano", barbeiroAlterado.getNome());
    verify(barbeiroDAO, times(1)).alterar(barbeiro);
  }

  @Test
  public void deveExcluirBarbeiroComSucesso() throws Exception {
    doNothing().when(barbeiroDAO).excluir(barbeiro);

    barbeiroService.excluir(barbeiro);
    Barbeiro barbeiroPesquisadoAposExclusao = barbeiroService.consultar(barbeiro.getId());
    assertNull(barbeiroPesquisadoAposExclusao);

    verify(barbeiroDAO, times(1)).excluir(barbeiro);
  }

  @Test
  public void deveBuscarTodosBarbeirosComSucesso() throws Exception {

    Barbeiro barbeiro2 = new Barbeiro();
    barbeiro2.setNome("Lucao");

    when(barbeiroDAO.buscarTodos()).thenReturn(List.of(barbeiro, barbeiro2));

    var barbeiros = barbeiroService.buscarTodos();
    assertNotNull(barbeiros);
    assertEquals(2, barbeiros.size());
    verify(barbeiroDAO, times(1)).buscarTodos();
  }
}
