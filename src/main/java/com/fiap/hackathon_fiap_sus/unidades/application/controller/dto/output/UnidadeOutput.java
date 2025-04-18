package com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.output;

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
public class UnidadeOutput {

  private Long id;
  private String unidade;
  private String endereco;
  private String cep;
  private String numero;


}
