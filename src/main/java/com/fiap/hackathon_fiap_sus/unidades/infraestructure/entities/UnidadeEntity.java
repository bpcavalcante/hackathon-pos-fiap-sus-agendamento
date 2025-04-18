package com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities;

import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_UNIDADE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UnidadeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
