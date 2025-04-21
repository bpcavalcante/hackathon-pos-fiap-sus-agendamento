package com.fiap.hackathon_fiap_sus.agendamentos.application.ports;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;

public interface BuscarAgendamentoPorIdUseCasePorts {

  AgendamentoDTO buscarPorId(Long id);

}
