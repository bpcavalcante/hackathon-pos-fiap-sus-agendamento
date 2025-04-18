package com.fiap.hackathon_fiap_sus.unidades.domain.usecase;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.BuscarPorIDUnidadeUseCasePorts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPorIDUnidadeUseCase implements BuscarPorIDUnidadeUseCasePorts {

  private final UnidadeRepositoryPort unidadeRepositoryPort;

  @Override
  public UnidadeDTO buscarPorId(Long idUnidade) {
    Unidade unidade = unidadeRepositoryPort.findById(idUnidade).orElseThrow(RuntimeException::new);
    return unidade.toDTO();
  }
}
