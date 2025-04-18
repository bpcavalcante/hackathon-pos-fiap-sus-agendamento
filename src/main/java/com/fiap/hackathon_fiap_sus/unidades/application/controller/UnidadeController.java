package com.fiap.hackathon_fiap_sus.unidades.application.controller;

import com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.input.UnidadeInput;
import com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.output.UnidadeOutput;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.AtualizarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.BuscarPorIDUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.CadastrarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.DeleteUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/unidades")
@RequiredArgsConstructor
public class UnidadeController {

  private final CadastrarUnidadeUseCasePorts cadastrarUnidadeUseCasePorts;
  private final BuscarPorIDUnidadeUseCasePorts buscarPorIDUnidadeUseCasePorts;
  private final AtualizarUnidadeUseCasePorts atualizarUnidadeUseCasePorts;
  private final DeleteUnidadeUseCasePorts deleteUnidadeUseCasePorts;

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

  @PutMapping("/{id}")
  public ResponseEntity<UnidadeOutput> atualizarUnidade(
      @PathVariable Long id, @RequestBody UnidadeInput unidadeInput) {

    UnidadeDTO unidadeDTO = atualizarUnidadeUseCasePorts.atualizarUnidade(id, unidadeInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(unidadeDTO.toOutput());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirUnidade(@PathVariable Long id) {
    deleteUnidadeUseCasePorts.excluir(id);
    return ResponseEntity.noContent().build();
  }

}
