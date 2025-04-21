package com.fiap.hackathon_fiap_sus.agendamentos.application.controller;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.input.AgendamentoInput;
import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.output.AgendamentoOutput;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.BuscarAgendamentoPorIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.CadastrarAgendamentoUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.CancelarAgendamentoIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.ListarAgendamentosPorUsuarioIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

  private final CadastrarAgendamentoUseCasePorts cadastrarAgendamentoUseCasePorts;
  private final ListarAgendamentosPorUsuarioIdUseCasePorts listarAgendamentosPorUsuarioIdUseCasePorts;
  private final BuscarAgendamentoPorIdUseCasePorts buscarAgendamentoPorIdUseCasePorts;
  private final CancelarAgendamentoIdUseCasePorts cancelarAgendamentoIdUseCasePorts;

  @PostMapping
  public ResponseEntity<AgendamentoOutput> cadastrar(
      @RequestBody @Valid AgendamentoInput agendamentoInput) {
    AgendamentoDTO agendamentoDTO =
        cadastrarAgendamentoUseCasePorts.cadastrar(agendamentoInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(agendamentoDTO.toOutput());
  }

  @GetMapping(value = "/usuarios/{usuarioId}")
  public ResponseEntity<List<AgendamentoOutput>> listarAgendamentosPorUsuarioId(@PathVariable Long usuarioId) {
    List<AgendamentoOutput> agendamentos = listarAgendamentosPorUsuarioIdUseCasePorts.listarAgendamentosPorUsuarioId(usuarioId);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(agendamentos);
  }

  @GetMapping(value = "/{agendamentoId}")
  public ResponseEntity<AgendamentoOutput> buscarAgendamentoPorId(@PathVariable Long agendamentoId) {
    AgendamentoDTO agendamentoDTO = buscarAgendamentoPorIdUseCasePorts.buscarPorId(agendamentoId);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(agendamentoDTO.toOutput());
  }

  @PutMapping(value = "/cancelar/{agendamentoId}")
  public ResponseEntity<AgendamentoOutput> cancelar(@PathVariable Long agendamentoId) {
    AgendamentoDTO agendamentoDTO = cancelarAgendamentoIdUseCasePorts.cancelar(agendamentoId);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(agendamentoDTO.toOutput());
  }

}
