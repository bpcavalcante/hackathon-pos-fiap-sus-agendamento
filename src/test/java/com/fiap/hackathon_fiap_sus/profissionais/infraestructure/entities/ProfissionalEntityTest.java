package com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities;

import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfissionalEntityTest {

  @Test
  void testToDatabaseDTO() {
    ProfissionalEntity entity = ProfissionalEntity.builder()
        .id(1L)
        .nome("Dr. Ana Clara")
        .cpf("12345678900")
        .telefone("11999999999")
        .email("ana@exemplo.com")
        .especialidade("Pediatria")
        .build();

    ProfissionalDatabaseDTO dto = entity.toDatabaseDTO();

    assertNotNull(dto);
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getNome(), dto.getNome());
    assertEquals(entity.getCpf(), dto.getCpf());
    assertEquals(entity.getTelefone(), dto.getTelefone());
    assertEquals(entity.getEmail(), dto.getEmail());
    assertEquals(entity.getEspecialidade(), dto.getEspecialidade());
  }

  @Test
  void testToDomain() {
    ProfissionalEntity entity = ProfissionalEntity.builder()
        .id(2L)
        .nome("Dr. João Silva")
        .cpf("98765432100")
        .telefone("11888888888")
        .email("joao@medico.com")
        .especialidade("Ortopedia")
        .build();

    Profissional domain = entity.toDomain();

    assertNotNull(domain);
    assertEquals(entity.getId(), domain.getId());
    assertEquals(entity.getNome(), domain.getNome());
    assertEquals(entity.getCpf(), domain.getCpf());
    assertEquals(entity.getTelefone(), domain.getTelefone());
    assertEquals(entity.getEmail(), domain.getEmail());
    assertEquals(entity.getEspecialidade(), domain.getEspecialidade());
  }
}
