package com.fiap.hackathon_fiap_sus.usuarios.domain.ports;

import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;

public interface UsuarioRepositoryPort {

  UsuarioDatabaseDTO save(UsuarioDatabaseDTO usuarioDatabaseDTO);
}
