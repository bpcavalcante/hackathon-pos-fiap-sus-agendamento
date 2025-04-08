package com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfissionalDatabaseDTO {

  private Long id;
  private String nome;
  private String cpf;
  private String telefone;
  private String email;
  private String especialidade;

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
