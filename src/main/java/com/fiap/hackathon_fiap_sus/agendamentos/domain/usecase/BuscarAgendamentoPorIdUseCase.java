package com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.BuscarAgendamentoPorIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarAgendamentoPorIdUseCase implements BuscarAgendamentoPorIdUseCasePorts {

  private final AgendamentoRepositoryPort agendamentoRepositoryPort;

  @Override
  public AgendamentoDTO buscarPorId(Long id) {
    return agendamentoRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException());
  }

}
