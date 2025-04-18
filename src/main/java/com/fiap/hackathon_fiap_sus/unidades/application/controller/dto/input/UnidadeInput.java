package com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.input;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import java.time.LocalDate;
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
public class UnidadeInput {

  private String unidade;
  private String endereco;
  private String cep;
  private String numero;

  public UnidadeDTO toDTO() {
    return UnidadeDTO.builder()
        .unidade(this.unidade)
        .endereco(this.endereco)
        .cep(this.cep)
        .numero(this.numero)
        .build();
  }
}
