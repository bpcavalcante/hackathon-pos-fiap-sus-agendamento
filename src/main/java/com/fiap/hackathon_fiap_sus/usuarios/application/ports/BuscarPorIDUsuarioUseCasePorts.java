package com.fiap.hackathon_fiap_sus.usuarios.application.ports;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;

public interface BuscarPorIDUsuarioUseCasePorts {

  UsuarioDTO buscarPorId(Long idUsuario);

}
