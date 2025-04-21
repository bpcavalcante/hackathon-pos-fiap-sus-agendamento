package com.fiap.hackathon_fiap_sus.agendamentos.application.ports;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.output.AgendamentoOutput;
import java.util.List;

public interface ListarAgendamentosPorUsuarioIdUseCasePorts {

  List<AgendamentoOutput> listarAgendamentosPorUsuarioId(Long usuarioId);

}
