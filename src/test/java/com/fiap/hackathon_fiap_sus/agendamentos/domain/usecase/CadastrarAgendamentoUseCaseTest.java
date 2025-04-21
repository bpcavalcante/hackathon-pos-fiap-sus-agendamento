package com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarAgendamentoUseCaseTest {

  private AgendamentoRepositoryPort repository;
  private CadastrarAgendamentoUseCase useCase;

  @BeforeEach
  void setup() {
    repository = Mockito.mock(AgendamentoRepositoryPort.class);
    useCase = new CadastrarAgendamentoUseCase(repository);
  }

  private AgendamentoDTO buildAgendamento() {
    return AgendamentoDTO.builder()
        .id(1L)
        .protocolo("AGD-0001")
        .dataAgendamento(LocalDateTime.of(2025, 4, 20, 10, 0))
        .usuarioId(100L)
        .profissionalId(200L)
        .unidadeSaudeId(300L)
        .build();
  }

  @Test
  void deveCadastrarAgendamentoComSucesso() {
    AgendamentoDTO dto = buildAgendamento();

    when(repository.existsByProfissionalAndHorario(dto.getProfissionalId(), dto.getDataAgendamento()))
        .thenReturn(false);
    when(repository.existsByUsuarioAndHorario(dto.getUsuarioId(), dto.getDataAgendamento()))
        .thenReturn(false);
    when(repository.save(dto)).thenReturn(dto);

    AgendamentoDTO resultado = useCase.cadastrar(dto);

    assertEquals(dto, resultado);
    verify(repository).save(dto);
  }

  @Test
  void deveLancarExcecao_SeHorarioOcupadoPorProfissional() {
    AgendamentoDTO dto = buildAgendamento();

    when(repository.existsByProfissionalAndHorario(dto.getProfissionalId(), dto.getDataAgendamento()))
        .thenReturn(true);

    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> useCase.cadastrar(dto));

    assertEquals("Profissional já possui agendamento nesse horário.", ex.getMessage());
    verify(repository, never()).save(any());
  }

  @Test
  void deveLancarExcecao_SeHorarioOcupadoPorUsuario() {
    AgendamentoDTO dto = buildAgendamento();

    when(repository.existsByProfissionalAndHorario(dto.getProfissionalId(), dto.getDataAgendamento()))
        .thenReturn(false);
    when(repository.existsByUsuarioAndHorario(dto.getUsuarioId(), dto.getDataAgendamento()))
        .thenReturn(true);

    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> useCase.cadastrar(dto));

    assertEquals("Usuário já possui agendamento nesse horário.", ex.getMessage());
    verify(repository, never()).save(any());
  }
}
