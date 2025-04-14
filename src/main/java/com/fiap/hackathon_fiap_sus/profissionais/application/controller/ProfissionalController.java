package com.fiap.hackathon_fiap_sus.profissionais.application.controller;

import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.input.ProfissionalInput;
import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output.ProfissionalOutput;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.AtualizarProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.BuscarPorIDProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.CadastrarProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.DeleteProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.ListarProfissionaisPorEspecialidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

  private final CadastrarProfissionalUseCasePorts cadastrarProfissionalUseCasePorts;
  private final AtualizarProfissionalUseCasePorts atualizarProfissionalUseCasePorts;
  private final BuscarPorIDProfissionalUseCasePorts buscarPorIDProfissionalUseCasePorts;
  private final ListarProfissionaisPorEspecialidadeUseCasePorts listarProfissionaisPorEspecialidadeUseCasePorts;
  private final DeleteProfissionalUseCasePorts deleteProfissionalUseCasePorts;

  @PostMapping
  public ResponseEntity<ProfissionalOutput> cadastrar(
      @RequestBody ProfissionalInput profissionalInput) {
    ProfissionalDTO profissionalDTO =
        cadastrarProfissionalUseCasePorts.cadastrar(profissionalInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(profissionalDTO.toOutput());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProfissionalOutput> atualizar(@PathVariable Long id, @RequestBody ProfissionalInput profissionalInput) {
    ProfissionalDTO profissionalDTO = atualizarProfissionalUseCasePorts.atualizar(id, profissionalInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(profissionalDTO.toOutput());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProfissionalOutput> buscarPorId(@PathVariable Long id) {
    ProfissionalDTO profissionalDTO = buscarPorIDProfissionalUseCasePorts.buscarPorId(id);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(profissionalDTO.toOutput());
  }

  @GetMapping
  public ResponseEntity<List<ProfissionalOutput>> listarProfissionaisPorEspecialidade(
      @RequestParam String especialidade) {
    List<ProfissionalOutput> profissionais = listarProfissionaisPorEspecialidadeUseCasePorts
        .listarProfissionaisPorEspecialidade(especialidade);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(profissionais);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirProfissional(@PathVariable Long id) {
    deleteProfissionalUseCasePorts.excluir(id);
    return ResponseEntity.noContent().build();
  }


}
