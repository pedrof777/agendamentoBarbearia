package br.com.pferreira.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Pedro Ferreira
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_AGENDAMENTO")
public class Agendamento {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agendamento_sq")
  @SequenceGenerator(name = "agendamento_sq", sequenceName = "sq_agendamento", initialValue = 1, allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ID_CLIENTE", nullable = false)
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "ID_BARBEIRO", nullable = false)
  private Barbeiro barbeiro;

  @ManyToOne
  @JoinColumn(name = "ID_SERVICO", nullable = false)
  private Servico servico;

  @Column(name = "DATA_HORA", nullable = false)
  private LocalDateTime dataHora;

  @Column(name = "STATUS", nullable = false, length = 20)
  private String status;
}
