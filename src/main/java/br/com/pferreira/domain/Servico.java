package br.com.pferreira.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pedro Ferreira
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_SERVICO")
public class Servico {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servico_sq")
  @SequenceGenerator(name = "servico_sq", sequenceName = "sq_servico", initialValue = 1, allocationSize = 1)
  private Long id;

  @Column(name = "NOME", nullable = false, length = 100)
  private String nome;

  @Column(name = "PRECO", nullable = false, length = 100)
  private Double preco;

  @Column(name = "DURACAO_MINUTOS", nullable = false)
  private Integer duracaoMinutos;

}
