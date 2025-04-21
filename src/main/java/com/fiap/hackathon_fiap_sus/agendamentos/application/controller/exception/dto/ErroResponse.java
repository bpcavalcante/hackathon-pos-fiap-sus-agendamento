package com.fiap.hackathon_fiap_sus.agendamentos.application.controller.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroResponse {
  private String titulo;
  private String detalhe;
}
