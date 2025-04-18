package com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UnidadeDatabaseDTO {

  private Long id;
  private String unidade;
  private String endereco;
  private String cep;
  private String numero;

  public UnidadeDTO toDTO() {
    return UnidadeDTO.builder()
        .id(this.id)
        .unidade(this.unidade)
        .endereco(this.endereco)
        .cep(this.cep)
        .numero(this.numero)
        .build();
  }
}
