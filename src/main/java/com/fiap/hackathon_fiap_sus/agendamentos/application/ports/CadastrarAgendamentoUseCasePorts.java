package com.fiap.hackathon_fiap_sus.agendamentos.application.ports;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;

public interface CadastrarAgendamentoUseCasePorts {
  AgendamentoDTO cadastrar(AgendamentoDTO dto);
}
