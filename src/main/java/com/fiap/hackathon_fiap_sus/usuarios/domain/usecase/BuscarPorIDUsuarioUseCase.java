package com.fiap.hackathon_fiap_sus.usuarios.domain.usecase;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.BuscarPorIDUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import com.fiap.hackathon_fiap_sus.usuarios.domain.Usuario;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPorIDUsuarioUseCase implements BuscarPorIDUsuarioUseCasePorts {

  private final UsuarioRepositoryPort usuarioRepositoryPort;

  @Override
  public UsuarioDTO buscarPorId(Long idUsuario) {
    Usuario usuario = usuarioRepositoryPort.findById(idUsuario).orElseThrow(RuntimeException::new);
    return usuario.toDTO();
  }
}
