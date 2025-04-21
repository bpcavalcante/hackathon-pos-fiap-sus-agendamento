package com.fiap.hackathon_fiap_sus.agendamentos.application.controller.exception;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.exception.dto.ErroResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  @Test
  void deveRetornarBadRequestParaIllegalArgumentException() {
    String mensagem = "Usuário não encontrado";
    IllegalArgumentException ex = new IllegalArgumentException(mensagem);

    ResponseEntity<ErroResponse> response = handler.handleIllegalArgumentException(ex);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Requisição inválida", response.getBody().getTitulo());
    assertEquals(mensagem, response.getBody().getDetalhe());
  }
}
