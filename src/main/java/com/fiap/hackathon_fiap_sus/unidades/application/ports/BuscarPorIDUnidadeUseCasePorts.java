package com.fiap.hackathon_fiap_sus.unidades.application.ports;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;

public interface BuscarPorIDUnidadeUseCasePorts {

  UnidadeDTO buscarPorId(Long id);

}
