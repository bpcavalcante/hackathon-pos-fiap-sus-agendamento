package com.fiap.hackathon_fiap_sus.unidades.domain.usecase;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.DeleteUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUnidadeUseCase implements DeleteUnidadeUseCasePorts {

  private final UnidadeRepositoryPort unidadeRepositoryPort;

  @Override
  public void excluir(Long id) {

    if (!unidadeRepositoryPort.findById(id).isPresent()) {
      throw new RuntimeException("Unidade com ID " + id + " não encontrada");
    }

    unidadeRepositoryPort.excluir(id);
  }
}
