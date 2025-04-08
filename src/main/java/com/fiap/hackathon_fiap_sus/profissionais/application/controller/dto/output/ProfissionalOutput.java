package com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output;

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
public class ProfissionalOutput {

  private Long id;
  private String nome;
  private String cpf;
  private String telefone;
  private String email;
  private String especialidade;
}
