package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.BuscarPorIDProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPorIDProfissionalUseCase implements BuscarPorIDProfissionalUseCasePorts {

  private final ProfissionalRepositoryPort profissionalRepositoryPort;

  @Override
  public ProfissionalDTO buscarPorId(Long id) {
    Profissional profissional = profissionalRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException());
    return profissional.toDTO();
  }
}
