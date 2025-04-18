package com.fiap.hackathon_fiap_sus.unidades.domain;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
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
public class Unidade {

  private Long id;
  private String unidade;
  private String endereco;
  private String cep;
  private String numero;

  public UnidadeDatabaseDTO toDatabaseDTO() {
    return UnidadeDatabaseDTO.builder()
        .id(this.id)
        .unidade(this.unidade)
        .endereco(this.endereco)
        .cep(this.cep)
        .numero(this.numero)
        .build();
  }

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
