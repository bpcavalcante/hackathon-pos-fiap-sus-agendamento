package com.fiap.hackathon_fiap_sus.usuarios.application.controller;

import com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.input.UsuarioInput;
import com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.output.UsuarioOutput;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.AtualizarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.BuscarPorIDUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.CadastrarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final CadastrarUsuarioUseCasePorts cadastrarUsuarioUseCasePorts;
  private final BuscarPorIDUsuarioUseCasePorts buscarPorIDUsuarioUseCasePorts;
  private final AtualizarUsuarioUseCasePorts atualizarUsuarioUseCasePorts;

  @PostMapping
  public ResponseEntity<UsuarioOutput> cadastrarUsuario(@RequestBody UsuarioInput usuarioInput) {
    UsuarioDTO usuarioDTO = cadastrarUsuarioUseCasePorts.cadastrarUsuario(usuarioInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(usuarioDTO.toOutput());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioOutput> buscarUsuarioPorId(@PathVariable Long id) {
    UsuarioDTO usuarioDTO = buscarPorIDUsuarioUseCasePorts.buscarPorId(id);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(usuarioDTO.toOutput());
  }

  @PutMapping("/{id}")
  public ResponseEntity<UsuarioOutput> atualizarUsuario(
      @PathVariable Long id, @RequestBody UsuarioInput usuarioInput) {
    UsuarioDTO usuarioDTO = atualizarUsuarioUseCasePorts.atualizarUsuario(id, usuarioInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(usuarioDTO.toOutput());
  }
}
