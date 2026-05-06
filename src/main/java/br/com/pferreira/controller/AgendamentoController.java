package br.com.pferreira.controller;

import br.com.pferreira.domain.Agendamento;
import br.com.pferreira.domain.Barbeiro;
import br.com.pferreira.domain.Cliente;
import br.com.pferreira.domain.Servico;
import br.com.pferreira.services.IAgendamentoService;
import br.com.pferreira.services.IBarbeiroService;
import br.com.pferreira.services.IClienteService;
import br.com.pferreira.services.IServicoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author Pedro Ferreira
 */

@Named
@ViewScoped
public class AgendamentoController implements Serializable {

  private static final Long serialVersionUID = 1L;

  @Inject
  private  IAgendamentoService  agendamentoService;

  @Inject
  private  IClienteService clienteService;

  @Inject
  private  IBarbeiroService barbeiroService;

  @Inject
  private  IServicoService servicoService;

  private Agendamento agendamento;

  private Collection<Barbeiro> barbeiros;

  private Collection<Agendamento> agendamentos;

  private Collection<Cliente> clientes;

  private Collection<Servico> servicos;

  private Boolean update;

  @PostConstruct
  public void init() {
    try {
      this.update = false;
      this.agendamento = new Agendamento();
      this.agendamentos = agendamentoService.buscarTodos();
      this.clientes = clienteService.buscarTodos();
      this.barbeiros = barbeiroService.buscarTodos();
      this.servicos = servicoService.buscarTodos();
    }catch (Exception e){
      e.printStackTrace();
      addMensagemErro("Erro ao carregar dados" + e.getMessage() );
    }
  }

  public void salvar(){
    try {
      if (update){
        agendamentoService.alterar(agendamento);
        addMensagemSucesso("Agendamento atualizado com sucesso!");
      }else {
        agendamentoService.cadastrar(agendamento);
        addMensagemSucesso("Agendamento realizado com sucesso");
      }
      this.clientes = clienteService.buscarTodos();
      this.barbeiros = barbeiroService.buscarTodos();
      this.servicos = servicoService.buscarTodos();
      this.agendamentos = agendamentoService.buscarTodos();
      this.agendamento = new Agendamento();
      this.update = false;
    }catch (IllegalArgumentException e){
      addMensagemErro(e.getMessage());
    }catch (Exception e){
      addMensagemErro("Erro ao salvar agendamento");
    }
  }

  public void editar(Agendamento agendamento){
    this.update = true;

    Agendamento agendamentoSelecionado = new Agendamento();
    agendamentoSelecionado.setId(agendamento.getId());
    agendamentoSelecionado.setCliente(agendamento.getCliente());
    agendamentoSelecionado.setBarbeiro(agendamento.getBarbeiro());
    agendamentoSelecionado.setServico(agendamento.getServico());
    agendamentoSelecionado.setDataHora(agendamento.getDataHora());
    agendamentoSelecionado.setStatus(agendamento.getStatus());

    this.agendamento = agendamentoSelecionado;

  }

  public void excluir(Agendamento agendamento){
    try {
      agendamentoService.excluir(agendamento);
      agendamentos.remove(agendamento);
      addMensagemSucesso("Agendamento excluído com sucesso!");
    }catch (Exception e){
      addMensagemErro("Erro ao excluir agendamento");
    }
  }

  public void cancelar(){
    this.update = false;
    this.agendamento = new Agendamento();

  }

  private void addMensagemSucesso(String msg){
    FacesContext.getCurrentInstance().addMessage("growl",
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));

  }

  private void addMensagemErro(String msg){
    FacesContext.getCurrentInstance().addMessage("growl",
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));

  }

  public LocalDateTime getHoje() {
    return LocalDateTime.now();
  }

  public Agendamento getAgendamento() {
    return agendamento;
  }

  public void setAgendamento(Agendamento agendamento) {
    this.agendamento = agendamento;
  }

  public Collection<Barbeiro> getBarbeiros() {
    return barbeiros;
  }

  public void setBarbeiros(Collection<Barbeiro> barbeiros) {
    this.barbeiros = barbeiros;
  }

  public Collection<Agendamento> getAgendamentos() {
    return agendamentos;
  }

  public void setAgendamentos(Collection<Agendamento> agendamentos) {
    this.agendamentos = agendamentos;
  }

  public Collection<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(Collection<Cliente> clientes) {
    this.clientes = clientes;
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
