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
@Table(name = "TB_CLIENTE")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sq")
  @SequenceGenerator(name = "cliente_sq", sequenceName = "sq_cliente", initialValue = 1, allocationSize = 1)
  private Long id;

  @Column(name = "NOME", nullable = false, length = 100)
  private String nome;

  @Column(name = "TELEFONE", nullable = false, length = 20)
  private String telefone;

  @Column(name = "EMAIL", nullable = true, length = 100)
  private String email;
}
