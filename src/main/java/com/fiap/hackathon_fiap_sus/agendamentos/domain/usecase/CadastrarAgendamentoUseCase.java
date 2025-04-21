package com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.CadastrarAgendamentoUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarAgendamentoUseCase implements CadastrarAgendamentoUseCasePorts {

  private final AgendamentoRepositoryPort agendamentoRepositoryPort;

  @Override
  public AgendamentoDTO cadastrar(AgendamentoDTO dto) {

    boolean horarioOcupadoPorProfissional = agendamentoRepositoryPort.existsByProfissionalAndHorario(dto.getProfissionalId(), dto.getDataAgendamento());
    if (horarioOcupadoPorProfissional) {
      throw new IllegalArgumentException("Profissional já possui agendamento nesse horário.");
    }

    boolean horarioOcupadoPorUsuario = agendamentoRepositoryPort.existsByUsuarioAndHorario(dto.getUsuarioId(), dto.getDataAgendamento());
    if (horarioOcupadoPorUsuario) {
      throw new IllegalArgumentException("Usuário já possui agendamento nesse horário.");
    }

    return agendamentoRepositoryPort.save(dto);
  }

}
