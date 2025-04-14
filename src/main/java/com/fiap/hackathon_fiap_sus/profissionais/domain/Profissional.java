package com.fiap.hackathon_fiap_sus.profissionais.domain;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Profissional {
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

  public ProfissionalDTO toDTO() {
    return ProfissionalDTO.builder()
        .id(this.id)
        .nome(this.nome)
        .cpf(this.cpf)
        .telefone(this.telefone)
        .email(this.email)
        .especialidade(this.especialidade)
        .build();
  }

}
