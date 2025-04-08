package com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities;

import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PROFISSIONAL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProfissionalEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String cpf;
  private String telefone;
  private String email;
  private String especialidade;

  public ProfissionalDatabaseDTO toDatabaseDTO() {
    return ProfissionalDatabaseDTO.builder()
        .id(this.id)
        .nome(this.nome)
        .cpf(this.cpf)
        .telefone(this.telefone)
        .email(this.email)
        .especialidade(this.especialidade)
        .build();
  }
}
