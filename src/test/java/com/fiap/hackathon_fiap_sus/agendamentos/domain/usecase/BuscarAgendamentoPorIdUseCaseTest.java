package com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarAgendamentoPorIdUseCaseTest {

  private AgendamentoRepositoryPort repository;
  private BuscarAgendamentoPorIdUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(AgendamentoRepositoryPort.class);
    useCase = new BuscarAgendamentoPorIdUseCase(repository);
  }

  @Test
  void deveRetornarAgendamento_QuandoEncontrado() {
    Long id = 1L;

    AgendamentoDTO dto = AgendamentoDTO.builder()
        .id(id)
        .protocolo("AGD-0001")
        .status("AGENDADO")
        .dataAgendamento(LocalDateTime.of(2025, 4, 20, 10, 0))
        .usuarioId(10L)
        .profissionalId(20L)
        .unidadeSaudeId(30L)
        .build();

    when(repository.findById(id)).thenReturn(Optional.of(dto));

    AgendamentoDTO resultado = useCase.buscarPorId(id);

    assertNotNull(resultado);
    assertEquals("AGD-0001", resultado.getProtocolo());
    verify(repository).findById(id);
  }

  @Test
  void deveLancarExcecao_QuandoAgendamentoNaoForEncontrado() {
    Long id = 999L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> useCase.buscarPorId(id));
    verify(repository).findById(id);
  }
}
