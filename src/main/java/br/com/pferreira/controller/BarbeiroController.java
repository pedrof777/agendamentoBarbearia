package br.com.pferreira.controller;

import br.com.pferreira.domain.Barbeiro;
import br.com.pferreira.domain.Cliente;
import br.com.pferreira.services.IBarbeiroService;
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
public class BarbeiroController implements Serializable {

  private static final Long serialVersionUID = 1L;

  @Inject
  private  IBarbeiroService barbeiroService;

  private Barbeiro barbeiro;
  private Collection<Barbeiro> barbeiros;
  private Boolean update;

  @PostConstruct
  public void init(){
    try {
      this.update = false;
      this.barbeiro = new Barbeiro();
      this.barbeiros = barbeiroService.buscarTodos();
    }catch (Exception e){
      addMensagemErro("Erro ao carregar barbeiros");
    }
  }

  public void salvar(){
    try {
      if (update){
        barbeiroService.alterar(barbeiro);
        addMensagemSucesso("Barbeiro atualizado com sucesso!");
      }else {
        barbeiroService.cadastrar(barbeiro);
        addMensagemSucesso("Barbeiro cadastrado com sucesso!");
      }
      this.barbeiros = barbeiroService.buscarTodos();
      this.barbeiro = new Barbeiro();
      this.update = false;
    }catch (IllegalArgumentException e){
      addMensagemErro(e.getMessage());
    }catch (Exception e){
      addMensagemErro("Erro ao salvar barbeiro!");
    }
  }

  public void editar(Barbeiro barbeiro){
    this.update = true;
    this.barbeiro = barbeiro;
  }

  public void excluir(Barbeiro barbeiro){
    try {
      barbeiroService.excluir(barbeiro);
      barbeiros.remove(barbeiro);
      addMensagemSucesso("Barbeiro excluído com sucesso!");
    }catch (Exception e){
      addMensagemErro("Erro ao excluir barbeiro!");

    }
  }

  public void cancelar(){
    this.update = false;
    this.barbeiro = new Barbeiro();
  }

  private void addMensagemErro(String msg){
    FacesContext.getCurrentInstance().addMessage("growl",
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
  }

  private void addMensagemSucesso(String msg){
    FacesContext.getCurrentInstance().addMessage("growl",
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
  }

  public Boolean getUpdate() {
    return update;
  }

  public void setUpdate(Boolean update) {
    this.update = update;
  }

  public Collection<Barbeiro> getBarbeiros() {
    return barbeiros;
  }

  public void setBarbeiros(Collection<Barbeiro> barbeiros) {
    this.barbeiros = barbeiros;
  }

  public Barbeiro getBarbeiro() {
    return barbeiro;
  }

  public void setBarbeiro(Barbeiro barbeiro) {
    this.barbeiro = barbeiro;
  }
}
