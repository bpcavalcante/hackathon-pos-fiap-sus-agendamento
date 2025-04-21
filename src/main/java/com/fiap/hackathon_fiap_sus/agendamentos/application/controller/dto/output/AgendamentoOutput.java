package com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.output;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgendamentoOutput {

  private Long id;
  private String protocolo;
  private String status;
  private LocalDateTime dataAgendamento;

  public static AgendamentoOutput fromDTO(AgendamentoDTO dto) {
    return AgendamentoOutput.builder()
        .id(dto.getId())
        .protocolo(dto.getProtocolo())
        .status(dto.getStatus())
        .dataAgendamento(dto.getDataAgendamento())
        .build();
  }

}
