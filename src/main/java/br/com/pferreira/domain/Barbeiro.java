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
@Table(name = "TB_BARBEIRO")
public class Barbeiro {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "barbeiro_sq")
  @SequenceGenerator(name = "barbeiro_sq", sequenceName = "sq_barbeiro", initialValue = 1, allocationSize = 1)
  private Long id;

  @Column(name = "NOME", nullable = false, length = 100)
  private String nome;
}
