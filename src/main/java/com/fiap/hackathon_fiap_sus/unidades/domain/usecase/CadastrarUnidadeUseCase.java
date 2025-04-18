package com.fiap.hackathon_fiap_sus.unidades.domain.usecase;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.CadastrarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarUnidadeUseCase implements CadastrarUnidadeUseCasePorts {

  private final UnidadeRepositoryPort unidadeRepositoryPort;

  @Override
  public UnidadeDTO cadastrar(UnidadeDTO unidadeDTO) {
    Unidade unidade = unidadeDTO.toDomain();
    UnidadeDatabaseDTO unidadeDatabaseDTO = unidadeRepositoryPort.save(unidade.toDatabaseDTO());
    return unidadeDatabaseDTO.toDTO();
  }
}
