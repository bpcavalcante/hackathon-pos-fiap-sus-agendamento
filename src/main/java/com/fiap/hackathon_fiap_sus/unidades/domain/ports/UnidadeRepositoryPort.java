package com.fiap.hackathon_fiap_sus.unidades.domain.ports;

import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import java.util.Optional;

public interface UnidadeRepositoryPort {

  UnidadeDatabaseDTO save(UnidadeDatabaseDTO unidadeDatabaseDTO);

  Optional<Unidade> findById(Long id);

  void excluir(Long id);
}
