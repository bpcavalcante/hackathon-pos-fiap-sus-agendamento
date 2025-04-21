package com.fiap.hackathon_fiap_sus.agendamentos.application.controller.dto.input;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoInput {

  @NotNull
  private String protocolo;

  @NotNull
  private LocalDateTime dataAgendamento;

  @NotNull
  private Long usuarioId;

  @NotNull
  private Long profissionalId;

  @NotNull
  private Long unidadeSaudeId;

  public AgendamentoDTO toDTO() {
    return AgendamentoDTO.builder()
        .protocolo(protocolo)
        .dataAgendamento(dataAgendamento)
        .usuarioId(usuarioId)
        .profissionalId(profissionalId)
        .unidadeSaudeId(unidadeSaudeId)
        .build();
  }
}
