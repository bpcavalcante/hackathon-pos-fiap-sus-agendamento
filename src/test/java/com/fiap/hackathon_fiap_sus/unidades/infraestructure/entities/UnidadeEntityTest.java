package com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities;

import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnidadeEntityTest {

  @Test
  void testToDatabaseDTO() {
    UnidadeEntity entity = UnidadeEntity.builder()
        .id(1L)
        .unidade("UBS Central")
        .endereco("Rua A, 123")
        .cep("12345678")
        .numero("100")
        .build();

    UnidadeDatabaseDTO dto = entity.toDatabaseDTO();

    assertNotNull(dto);
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getUnidade(), dto.getUnidade());
    assertEquals(entity.getEndereco(), dto.getEndereco());
    assertEquals(entity.getCep(), dto.getCep());
    assertEquals(entity.getNumero(), dto.getNumero());
  }

  @Test
  void testToDomain() {
    UnidadeEntity entity = UnidadeEntity.builder()
        .id(2L)
        .unidade("UBS Leste")
        .endereco("Av. Brasil, 200")
        .cep("87654321")
        .numero("200")
        .build();

    Unidade domain = entity.toDomain();

    assertNotNull(domain);
    assertEquals(entity.getId(), domain.getId());
    assertEquals(entity.getUnidade(), domain.getUnidade());
    assertEquals(entity.getEndereco(), domain.getEndereco());
    assertEquals(entity.getCep(), domain.getCep());
    assertEquals(entity.getNumero(), domain.getNumero());
  }
}
