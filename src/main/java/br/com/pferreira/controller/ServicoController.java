package br.com.pferreira.controller;

import br.com.pferreira.domain.Cliente;
import br.com.pferreira.domain.Servico;
import br.com.pferreira.services.IServicoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Pedro Ferreira
 */

@Named
@ViewScoped
public class ServicoController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private IServicoService servicoService;

  private Servico servico;
  private Collection<Servico> servicos;
  private Boolean update;

  @PostConstruct
  public void init(){
    try{
      this.update = false;
      this.servico = new Servico();
      this.servicos = servicoService.buscarTodos();
    }catch (Exception e){
      addMensagemErro("Erro ao carregar serviços!");
    }
  }

  public void salvar(){
    System.out.println("ENTREI SALVAR");
    try {
      if (update){
        servicoService.alterar(servico);
        addMensagemSucesso("Serviço atualizado com sucesso!");
      }else {
        servicoService.cadastrar(servico);
        addMensagemSucesso("Serviço cadastrado com sucesso!");
      }
      this.servicos = servicoService.buscarTodos();
      this.servico = new Servico();
      this.update = false;
    }catch (IllegalArgumentException e){
      addMensagemErro(e.getMessage());
    }catch (Exception e){
      addMensagemErro("Erro ao salvar serviço!");
    }
  }

  public void editar(Servico servico){
    this.update = true;
    this.servico = servico;
  }

  public void excluir(Servico servico){
    try {
      servicoService.excluir(servico);
      servicos.remove(servico);
      addMensagemSucesso("Serviço excluído com sucesso!");
    }catch (Exception e){
      addMensagemErro("Erro ao excluir o serviço!");
    }
  }

  public void cancelar(){
    this.update = false;
    this.servico = new Servico();
  }

  private void addMensagemErro(String msg){
    FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
  }

  private void addMensagemSucesso(String msg){
    FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
  }

  public Servico getServico() {
    return servico;
  }

  public void setServico(Servico servico) {
    this.servico = servico;
  }

  public Collection<Servico> getServicos() {
    return servicos;
  }

  public void setServicos(Collection<Servico> servicos) {
    this.servicos = servicos;
  }

  public Boolean getUpdate() {
    return update;
  }

  public void setUpdate(Boolean update) {
    this.update = update;
  }
}
