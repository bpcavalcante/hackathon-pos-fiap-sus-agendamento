package com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto;

import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output.ProfissionalOutput;
import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfissionalDTO {

  private Long id;
  private String nome;
  private String cpf;
  private String telefone;
  private String email;
  private String especialidade;

  public ProfissionalOutput toOutput() {
    return ProfissionalOutput.builder()
        .id(this.id)
        .nome(this.nome)
        .cpf(this.cpf)
        .telefone(this.telefone)
        .email(this.email)
        .especialidade(this.especialidade)
        .build();
  }

  public Profissional toDomain() {
    return Profissional.builder()
        .id(this.id)
        .nome(this.nome)
        .cpf(this.cpf)
        .telefone(this.telefone)
        .email(this.email)
        .especialidade(this.especialidade)
        .build();
  }

}
