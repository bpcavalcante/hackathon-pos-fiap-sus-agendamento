package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.CadastrarProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarProfissionalUseCase implements CadastrarProfissionalUseCasePorts {

  private final ProfissionalRepositoryPort profissionalRepositoryPort;

  @Override
  public ProfissionalDTO cadastrar(ProfissionalDTO profissionalDTO) {
    Profissional profissional = profissionalDTO.toDomain();
    ProfissionalDatabaseDTO profissionalDatabaseDTO = profissionalRepositoryPort.save(profissional.toDTO());
    return profissionalDatabaseDTO.toDTO();
  }

}
