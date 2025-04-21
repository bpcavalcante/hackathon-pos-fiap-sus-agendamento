package com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.CancelarAgendamentoIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelarAgendamentoUseCase implements CancelarAgendamentoIdUseCasePorts {

  private final AgendamentoRepositoryPort repository;

  @Override
  public AgendamentoDTO cancelar(Long agendamentoId) {
    AgendamentoDTO dto = repository.findById(agendamentoId)
        .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com ID: " + agendamentoId));

    dto.setStatus("CANCELADO");

    return repository.updateStatus(dto);
  }
}
