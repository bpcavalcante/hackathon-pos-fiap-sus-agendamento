package com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto;

import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output.ProfissionalOutput;
import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfissionalDTOTest {

  @Test
  void testToOutput() {
    ProfissionalDTO dto = ProfissionalDTO.builder()
        .id(1L)
        .nome("Maria Souza")
        .cpf("12345678900")
        .telefone("11999999999")
        .email("maria@exemplo.com")
        .especialidade("Cardiologia")
        .build();

    ProfissionalOutput output = dto.toOutput();

    assertNotNull(output);
    assertEquals(dto.getId(), output.getId());
    assertEquals(dto.getNome(), output.getNome());
    assertEquals(dto.getCpf(), output.getCpf());
    assertEquals(dto.getTelefone(), output.getTelefone());
    assertEquals(dto.getEmail(), output.getEmail());
    assertEquals(dto.getEspecialidade(), output.getEspecialidade());
  }

  @Test
  void testToDomain() {
    ProfissionalDTO dto = ProfissionalDTO.builder()
        .id(2L)
        .nome("João Lima")
        .cpf("98765432100")
        .telefone("11988888888")
        .email("joao@teste.com")
        .especialidade("Dermatologia")
        .build();

    Profissional domain = dto.toDomain();

    assertNotNull(domain);
    assertEquals(dto.getId(), domain.getId());
    assertEquals(dto.getNome(), domain.getNome());
    assertEquals(dto.getCpf(), domain.getCpf());
    assertEquals(dto.getTelefone(), domain.getTelefone());
    assertEquals(dto.getEmail(), domain.getEmail());
    assertEquals(dto.getEspecialidade(), domain.getEspecialidade());
  }
}
