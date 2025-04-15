package com.fiap.hackathon_fiap_sus.usuarios.application.controller;

import com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.input.UsuarioInput;
import com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.output.UsuarioOutput;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.CadastrarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final CadastrarUsuarioUseCasePorts cadastrarUsuarioUseCasePorts;

  @PostMapping
  public ResponseEntity<UsuarioOutput> cadastrarUsuario(@RequestBody UsuarioInput usuarioInput) {
    UsuarioDTO usuarioDTO = cadastrarUsuarioUseCasePorts.cadastrarUsuario(usuarioInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(usuarioDTO.toOutput());
  }
}
