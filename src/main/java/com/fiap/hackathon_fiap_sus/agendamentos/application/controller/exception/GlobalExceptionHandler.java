package com.fiap.hackathon_fiap_sus.agendamentos.application.controller.exception;

import com.fiap.hackathon_fiap_sus.agendamentos.application.controller.exception.dto.ErroResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @Hidden
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErroResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
    ErroResponse erro = new ErroResponse("Requisição inválida", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
  }
}
