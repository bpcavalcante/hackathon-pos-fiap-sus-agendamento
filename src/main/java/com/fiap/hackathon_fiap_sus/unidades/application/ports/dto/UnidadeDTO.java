package com.fiap.hackathon_fiap_sus.unidades.application.ports.dto;

import com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.output.UnidadeOutput;
import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UnidadeDTO {

  private Long id;
  private String unidade;
  private String endereco;
  private String cep;
  private String numero;

  public UnidadeOutput toOutput() {
    return UnidadeOutput.builder()
        .id(this.id)
        .unidade(this.unidade)
        .endereco(this.endereco)
        .cep(this.cep)
        .numero(this.numero)
        .build();
  }

  public Unidade toDomain() {
    return Unidade.builder()
        .id(this.id)
        .unidade(this.unidade)
        .endereco(this.endereco)
        .cep(this.cep)
        .numero(this.numero)
        .build();
  }
}
