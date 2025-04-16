package com.fiap.hackathon_fiap_sus.usuarios.domain.ports;

import com.fiap.hackathon_fiap_sus.usuarios.domain.Usuario;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import java.util.Optional;

public interface UsuarioRepositoryPort {

  UsuarioDatabaseDTO save(UsuarioDatabaseDTO usuarioDatabaseDTO);
  Optional<Usuario> findById(Long id);

}
