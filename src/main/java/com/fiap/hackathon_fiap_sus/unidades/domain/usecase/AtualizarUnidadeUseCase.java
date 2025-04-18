package com.fiap.hackathon_fiap_sus.unidades.domain.usecase;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.AtualizarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarUnidadeUseCase implements AtualizarUnidadeUseCasePorts {

  private final UnidadeRepositoryPort unidadeRepositoryPort;

  @Override
  public UnidadeDTO atualizarUnidade(Long id, UnidadeDTO unidadeDTO) {

    Unidade unidade =
        unidadeRepositoryPort
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

    unidade.setUnidade(unidadeDTO.getUnidade());
    unidade.setEndereco(unidadeDTO.getEndereco());
    unidade.setCep(unidadeDTO.getCep());
    unidade.setNumero(unidadeDTO.getNumero());

    UnidadeDatabaseDTO updatedUnidadeDatabaseDTO =
        unidadeRepositoryPort.save(unidade.toDatabaseDTO());

    return updatedUnidadeDatabaseDTO.toDTO();
  }
}
