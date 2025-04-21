package com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CancelarAgendamentoUseCaseTest {

  private AgendamentoRepositoryPort repository;
  private CancelarAgendamentoUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(AgendamentoRepositoryPort.class);
    useCase = new CancelarAgendamentoUseCase(repository);
  }

  private AgendamentoDTO agendamento() {
    return AgendamentoDTO.builder()
        .id(1L)
        .protocolo("AGD-001")
        .status("AGENDADO")
        .dataAgendamento(LocalDateTime.of(2025, 4, 21, 10, 0))
        .usuarioId(10L)
        .profissionalId(20L)
        .unidadeSaudeId(30L)
        .build();
  }

  @Test
  void deveCancelarAgendamentoComSucesso() {
    AgendamentoDTO dto = agendamento();

    when(repository.findById(1L)).thenReturn(Optional.of(dto));
    when(repository.updateStatus(any())).thenAnswer(inv -> inv.getArgument(0)); // retorna o DTO modificado

    AgendamentoDTO resultado = useCase.cancelar(1L);

    assertNotNull(resultado);
    assertEquals("CANCELADO", resultado.getStatus());
    verify(repository).updateStatus(dto);
  }

  @Test
  void deveLancarExcecao_QuandoAgendamentoNaoExiste() {
    when(repository.findById(99L)).thenReturn(Optional.empty());

    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> useCase.cancelar(99L));

    assertEquals("Agendamento não encontrado com ID: 99", ex.getMessage());
    verify(repository, never()).updateStatus(any());
  }
}
