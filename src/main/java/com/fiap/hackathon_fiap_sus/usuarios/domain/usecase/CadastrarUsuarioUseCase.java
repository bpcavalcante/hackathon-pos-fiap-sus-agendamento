package com.fiap.hackathon_fiap_sus.usuarios.domain.usecase;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.CadastrarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import com.fiap.hackathon_fiap_sus.usuarios.domain.Usuario;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.UsuarioRepositoryPort;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarUsuarioUseCase implements CadastrarUsuarioUseCasePorts {

  private final UsuarioRepositoryPort usuarioRepositoryPort;

  @Override
  public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {
    Usuario usuario = usuarioDTO.toDomain();
    UsuarioDatabaseDTO usuarioDatabaseDTO = usuarioRepositoryPort.save(usuario.toDatabaseDTO());
    return usuarioDatabaseDTO.toDTO();
  }
}
