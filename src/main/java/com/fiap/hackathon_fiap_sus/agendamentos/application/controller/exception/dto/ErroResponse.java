package com.fiap.hackathon_fiap_sus.agendamentos.application.controller.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "ErroResponse", description = "Detalhes de erro para requisições inválidas")
public class ErroResponse {

  @Schema(description = "Título do erro", example = "Requisição inválida")
  private String titulo;

  @Schema(description = "Detalhe técnico do erro", example = "ID do profissional não encontrado")
  private String detalhe;
}