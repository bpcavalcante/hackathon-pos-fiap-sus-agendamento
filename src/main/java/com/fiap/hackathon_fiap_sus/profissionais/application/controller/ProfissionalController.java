package com.fiap.hackathon_fiap_sus.profissionais.application.controller;

import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.input.ProfissionalInput;
import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output.ProfissionalOutput;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.CadastrarProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

  private final CadastrarProfissionalUseCasePorts cadastrarProfissionalUseCasePorts;

  @PostMapping
  public ResponseEntity<ProfissionalOutput> cadastrar(
      @RequestBody ProfissionalInput profissionalInput) {
    ProfissionalDTO profissionalDTO =
        cadastrarProfissionalUseCasePorts.cadastrar(profissionalInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(profissionalDTO.toOutput());
  }
}
