package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.DeleteProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProfissionalUseCase implements DeleteProfissionalUseCasePorts {

  private final ProfissionalRepositoryPort profissionalRepositoryPort;

  @Override
  public void excluir(Long id) {
    if (!profissionalRepositoryPort.findById(id).isPresent()) {
      throw new RuntimeException("Profissional com ID " + id + " não encontrado");
    }
    profissionalRepositoryPort.excluir(id);
  }
}
