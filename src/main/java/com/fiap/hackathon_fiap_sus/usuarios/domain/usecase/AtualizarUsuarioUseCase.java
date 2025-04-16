package com.fiap.hackathon_fiap_sus.usuarios.domain.usecase;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.AtualizarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import com.fiap.hackathon_fiap_sus.usuarios.domain.Usuario;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.UsuarioRepositoryPort;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarUsuarioUseCase implements AtualizarUsuarioUseCasePorts {

  private final UsuarioRepositoryPort usuarioRepositoryPort;

  @Override
  public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {

    Usuario usuario = usuarioRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    usuario.setNome(usuarioDTO.getNome());
    usuario.setEmail(usuarioDTO.getEmail());
    usuario.setCpf(usuarioDTO.getCpf());
    usuario.setTelefone(usuarioDTO.getTelefone());
    usuario.setDataNascimento(usuario.getDataNascimento());

    UsuarioDatabaseDTO updatedUsuarioDatabaseDTO = usuarioRepositoryPort.save(usuario.toDatabaseDTO());

    return updatedUsuarioDatabaseDTO.toDTO();
  }
}
