package com.fiap.hackathon_fiap_sus.unidades.domain.ports;

import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;

public interface UnidadeRepositoryPort {

  UnidadeDatabaseDTO save(UnidadeDatabaseDTO unidadeDatabaseDTO);

}
