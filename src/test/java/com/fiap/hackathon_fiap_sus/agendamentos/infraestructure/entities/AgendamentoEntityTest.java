package com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.entities;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities.UnidadeEntity;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.entities.UsuarioEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoEntityTest {

  @Test
  void deveSetarStatusDefaultComoAgendadoSeNaoInformado() {
    AgendamentoEntity entity = AgendamentoEntity.builder()
        .protocolo("AGD-001")
        .dataAgendamento(LocalDateTime.now())
        .usuario(new UsuarioEntity())
        .profissional(new ProfissionalEntity())
        .unidadeSaude(new UnidadeEntity())
        .build();

    entity.prePersist();

    assertEquals("AGENDADO", entity.getStatus());
  }

  @Test
  void deveManterStatusExistenteSeInformado() {
    AgendamentoEntity entity = AgendamentoEntity.builder()
        .protocolo("AGD-002")
        .status("CANCELADO")
        .dataAgendamento(LocalDateTime.now())
        .usuario(new UsuarioEntity())
        .profissional(new ProfissionalEntity())
        .unidadeSaude(new UnidadeEntity())
        .build();

    entity.prePersist();

    assertEquals("CANCELADO", entity.getStatus());
  }

  @Test
  void deveConverterParaAgendamentoDTO() {
    LocalDateTime agendamentoData = LocalDateTime.of(2025, 4, 20, 10, 30);

    AgendamentoEntity entity = AgendamentoEntity.builder()
        .id(1L)
        .protocolo("AGD-123")
        .status("AGENDADO")
        .dataAgendamento(agendamentoData)
        .usuario(new UsuarioEntity())
        .profissional(new ProfissionalEntity())
        .unidadeSaude(new UnidadeEntity())
        .build();

    AgendamentoDTO dto = entity.toDTO();

    assertEquals(1L, dto.getId());
    assertEquals("AGD-123", dto.getProtocolo());
    assertEquals("AGENDADO", dto.getStatus());
    assertEquals(agendamentoData, dto.getDataAgendamento());
  }
}
