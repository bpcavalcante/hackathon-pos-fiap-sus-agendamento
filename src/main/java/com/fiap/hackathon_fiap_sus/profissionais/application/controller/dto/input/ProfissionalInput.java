package com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.input;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalInput {
  private String nome;
  private String cpf;
  private String telefone;
  private String email;
  private String especialidade;

  public ProfissionalDTO toDTO() {
    return ProfissionalDTO.builder()
        .nome(this.nome)
        .cpf(this.cpf)
        .telefone(this.telefone)
        .email(this.email)
        .especialidade(this.especialidade)
        .build();
  }
}
