package com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.output.AgendamentoOutput;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoDTOTest {

  @Test
  void deveConverterParaAgendamentoOutputCorretamente() {
    LocalDateTime data = LocalDateTime.of(2025, 4, 25, 14, 0);

    AgendamentoDTO dto = AgendamentoDTO.builder()
        .id(1L)
        .protocolo("AGD-555")
        .status("AGENDADO")
        .dataAgendamento(data)
        .build();

    AgendamentoOutput output = dto.toOutput();

    assertNotNull(output);
    assertEquals(dto.getId(), output.getId());
    assertEquals(dto.getProtocolo(), output.getProtocolo());
    assertEquals(dto.getStatus(), output.getStatus());
    assertEquals(dto.getDataAgendamento(), output.getDataAgendamento());
  }
}
