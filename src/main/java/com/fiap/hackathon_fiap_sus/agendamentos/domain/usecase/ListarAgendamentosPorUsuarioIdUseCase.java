package com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.output.AgendamentoOutput;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.ListarAgendamentosPorUsuarioIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarAgendamentosPorUsuarioIdUseCase
    implements ListarAgendamentosPorUsuarioIdUseCasePorts {

  private final AgendamentoRepositoryPort agendamentoRepositoryPort;

  @Override
  public List<AgendamentoOutput> listarAgendamentosPorUsuarioId(Long usuarioId) {
    return agendamentoRepositoryPort.findByUsuarioId(usuarioId).stream()
        .map(AgendamentoOutput::fromDTO)
        .collect(Collectors.toList());
  }
}
