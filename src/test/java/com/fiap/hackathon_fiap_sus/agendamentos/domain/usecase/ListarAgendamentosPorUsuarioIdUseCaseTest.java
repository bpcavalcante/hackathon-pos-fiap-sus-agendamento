package com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.output.AgendamentoOutput;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarAgendamentosPorUsuarioIdUseCaseTest {

  private AgendamentoRepositoryPort repository;
  private ListarAgendamentosPorUsuarioIdUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(AgendamentoRepositoryPort.class);
    useCase = new ListarAgendamentosPorUsuarioIdUseCase(repository);
  }

  @Test
  void deveListarAgendamentosDoUsuario() {
    Long usuarioId = 100L;

    AgendamentoDTO dto = AgendamentoDTO.builder()
        .id(1L)
        .protocolo("AGD-0001")
        .status("AGENDADO")
        .dataAgendamento(LocalDateTime.of(2025, 4, 21, 10, 0))
        .usuarioId(usuarioId)
        .profissionalId(200L)
        .unidadeSaudeId(300L)
        .build();

    when(repository.findByUsuarioId(usuarioId)).thenReturn(List.of(dto));

    List<AgendamentoOutput> resultado = useCase.listarAgendamentosPorUsuarioId(usuarioId);

    assertEquals(1, resultado.size());
    assertEquals("AGD-0001", resultado.get(0).getProtocolo());
    assertEquals("AGENDADO", resultado.get(0).getStatus());
    verify(repository, times(1)).findByUsuarioId(usuarioId);
  }

  @Test
  void deveRetornarListaVazia_QuandoUsuarioNaoPossuiAgendamentos() {
    Long usuarioId = 999L;

    when(repository.findByUsuarioId(usuarioId)).thenReturn(List.of());

    List<AgendamentoOutput> resultado = useCase.listarAgendamentosPorUsuarioId(usuarioId);

    assertTrue(resultado.isEmpty());
    verify(repository).findByUsuarioId(usuarioId);
  }
}
