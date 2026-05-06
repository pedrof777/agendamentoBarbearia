package br.com.pferreira.controller;

import br.com.pferreira.domain.Cliente;
import br.com.pferreira.services.IClienteService;
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
public class ClienteController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private  IClienteService clienteService;

  private Cliente cliente;
  private Collection<Cliente> clientes;
  private Boolean update;

  @PostConstruct
  public void init() {
    try {
      this.update = false;
      this.cliente = new Cliente();
      this.clientes = clienteService.buscarTodos();
    } catch (Exception e) {
      addMensagemErro("Erro ao carregar clientes");
    }
  }

  public void salvar(){
    try {
      if (update){
        clienteService.alterar(cliente);
        addMensagemSucesso("Cliente atualizado com sucesso!");
      }else {
        clienteService.cadastrar(cliente);

        addMensagemSucesso("Cliente cadastrado com sucesso");
      }
      this.clientes = clienteService.buscarTodos();
      this.cliente = new Cliente();
      this.update = false;
    }catch (IllegalArgumentException  e){
      addMensagemErro(e.getMessage());
    }catch (Exception e){
      e.printStackTrace();
      addMensagemErro("Erro ao salvar cliente!");
    }
  }

  public void editar(Cliente cliente){
    this.update = true;
    this.cliente = cliente;
  }

  public void excluir(Cliente cliente){
    try {
      clienteService.excluir(cliente);
      clientes.remove(cliente);
      addMensagemSucesso("Cliente excluído com sucesso!");
    }catch (Exception e){
      addMensagemErro("Erro ao excluir cliente");
    }
  }

  public void cancelar(){
    this.update = false;
    this.cliente = new Cliente();
  }

  private void addMensagemErro(String msg){
    FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
  }

  private void addMensagemSucesso(String msg){
    FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
  }

  public IClienteService getClienteService() {
    return clienteService;
  }

  public void setClienteService(IClienteService clienteService) {
    this.clienteService = clienteService;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Collection<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(Collection<Cliente> clientes) {
    this.clientes = clientes;
  }

  public Boolean getUpdate() {
    return update;
  }

  public void setUpdate(Boolean update) {
    this.update = update;
  }
}
