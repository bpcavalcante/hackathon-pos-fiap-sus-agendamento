package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.AtualizarProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarProfissionalUseCase implements AtualizarProfissionalUseCasePorts {

  private final ProfissionalRepositoryPort profissionalRepositoryPort;

  @Override
  public ProfissionalDTO atualizar(Long id, ProfissionalDTO profissionalDTO) {

    Profissional profissionalExistente =
        profissionalRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

    profissionalExistente.setNome(profissionalDTO.getNome());
    profissionalExistente.setEmail(profissionalDTO.getEmail());
    profissionalExistente.setCpf(profissionalDTO.getCpf());
    profissionalExistente.setEspecialidade(profissionalDTO.getEspecialidade());
    profissionalExistente.setTelefone(profissionalDTO.getTelefone());

    ProfissionalDatabaseDTO updatedProfissionalDatabaseDTO = profissionalRepositoryPort.save(profissionalExistente.toDatabaseDTO());

    return updatedProfissionalDatabaseDTO.toDTO();
  }
}
