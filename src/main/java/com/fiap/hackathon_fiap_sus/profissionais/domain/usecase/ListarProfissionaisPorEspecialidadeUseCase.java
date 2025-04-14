package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output.ProfissionalOutput;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.ListarProfissionaisPorEspecialidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarProfissionaisPorEspecialidadeUseCase implements ListarProfissionaisPorEspecialidadeUseCasePorts {

  private final ProfissionalRepositoryPort profissionalRepositoryPort;

  @Override
  public List<ProfissionalOutput> listarProfissionaisPorEspecialidade(String especialidade) {

    List<ProfissionalDatabaseDTO> profissionais = profissionalRepositoryPort
        .listarProfissionaisPorEspecialidade(especialidade);

    return profissionais.stream().map(profissional -> ProfissionalOutput
        .builder().id(profissional.getId()).nome(profissional.getNome())
        .email(profissional.getEmail()).telefone(profissional.getTelefone())
        .cpf(profissional.getCpf()).especialidade(profissional.getEspecialidade()).build())
        .collect(Collectors.toList());
  }
}
