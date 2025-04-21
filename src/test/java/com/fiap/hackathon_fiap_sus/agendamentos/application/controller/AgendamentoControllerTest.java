package com.fiap.hackathon_fiap_sus.agendamentos.application.controller;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.input.AgendamentoInput;
import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.output.AgendamentoOutput;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.BuscarAgendamentoPorIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.CadastrarAgendamentoUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.CancelarAgendamentoIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.ListarAgendamentosPorUsuarioIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgendamentoControllerTest {

  @InjectMocks
  private AgendamentoController controller;

  @Mock
  private CadastrarAgendamentoUseCasePorts cadastrarUseCase;

  @Mock
  private ListarAgendamentosPorUsuarioIdUseCasePorts listarUseCase;

  @Mock
  private BuscarAgendamentoPorIdUseCasePorts buscarUseCase;

  @Mock
  private CancelarAgendamentoIdUseCasePorts cancelarUseCase;

  private AgendamentoDTO mockDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockDTO = AgendamentoDTO.builder()
        .id(1L)
        .protocolo("AGD-123")
        .dataAgendamento(LocalDateTime.now())
        .status("AGENDADO")
        .usuarioId(10L)
        .profissionalId(20L)
        .unidadeSaudeId(30L)
        .build();
  }

  @Test
  void testCadastrar() {
    AgendamentoInput input = new AgendamentoInput();
    input.setProtocolo("AGD-123");
    input.setDataAgendamento(LocalDateTime.now());
    input.setUsuarioId(10L);
    input.setProfissionalId(20L);
    input.setUnidadeSaudeId(30L);

    when(cadastrarUseCase.cadastrar(any())).thenReturn(mockDTO);

    ResponseEntity<AgendamentoOutput> response = controller.cadastrar(input);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals("AGD-123", response.getBody().getProtocolo());
  }

  @Test
  void testListarAgendamentosPorUsuarioId() {
    when(listarUseCase.listarAgendamentosPorUsuarioId(10L))
        .thenReturn(List.of(mockDTO.toOutput()));

    ResponseEntity<List<AgendamentoOutput>> response = controller.listarAgendamentosPorUsuarioId(10L);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().size());
    assertEquals("AGD-123", response.getBody().get(0).getProtocolo());
  }

  @Test
  void testBuscarAgendamentoPorId() {
    when(buscarUseCase.buscarPorId(1L)).thenReturn(mockDTO);

    ResponseEntity<AgendamentoOutput> response = controller.buscarAgendamentoPorId(1L);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("AGD-123", response.getBody().getProtocolo());
  }

  @Test
  void testCancelarAgendamento() {
    AgendamentoDTO canceladoDTO = mockDTO;
    canceladoDTO.setStatus("CANCELADO");

    when(cancelarUseCase.cancelar(1L)).thenReturn(canceladoDTO);

    ResponseEntity<AgendamentoOutput> response = controller.cancelar(1L);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("CANCELADO", response.getBody().getStatus());
  }
}
