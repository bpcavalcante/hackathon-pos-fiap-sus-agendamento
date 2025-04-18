package com.fiap.hackathon_fiap_sus.unidades.application.controller;

import com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.input.UnidadeInput;
import com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.output.UnidadeOutput;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.BuscarPorIDUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.CadastrarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/unidades")
@RequiredArgsConstructor
public class UnidadeController {

  private final CadastrarUnidadeUseCasePorts cadastrarUnidadeUseCasePorts;
  private final BuscarPorIDUnidadeUseCasePorts buscarPorIDUnidadeUseCasePorts;

  @PostMapping
  public ResponseEntity<UnidadeOutput> cadastrar(@RequestBody UnidadeInput unidadeInput) {
    UnidadeDTO unidadeDTO = cadastrarUnidadeUseCasePorts.cadastrar(unidadeInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(unidadeDTO.toOutput());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UnidadeOutput> buscarUnidadePorId(@PathVariable Long id) {

    UnidadeDTO unidadeDTO = buscarPorIDUnidadeUseCasePorts.buscarPorId(id);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(unidadeDTO.toOutput());
  }
}
