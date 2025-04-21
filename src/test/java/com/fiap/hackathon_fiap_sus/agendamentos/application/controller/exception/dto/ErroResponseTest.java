package com.fiap.hackathon_fiap_sus.agendamentos.application.controller.exception.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErroResponseTest {

  @Test
  void deveCriarErroResponseComTituloEDetalhe() {
    String titulo = "Erro de Validação";
    String detalhe = "O campo 'email' é obrigatório";

    ErroResponse response = new ErroResponse(titulo, detalhe);

    assertEquals(titulo, response.getTitulo());
    assertEquals(detalhe, response.getDetalhe());
  }
}
