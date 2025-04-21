package com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.output.AgendamentoOutput;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AgendamentoDTO {
  private Long id;
  private String protocolo;
  private String status;
  private LocalDateTime dataAgendamento;
  private Long usuarioId;
  private Long profissionalId;
  private Long unidadeSaudeId;

  public AgendamentoOutput toOutput() {
    return AgendamentoOutput.fromDTO(this);
  }
}
