package com.fiap.hackathon_fiap_sus.profissionais.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.ProfissionalJpaRepository;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ProfissionalSqlRepositoryImpl implements ProfissionalRepositoryPort {

  private final ProfissionalJpaRepository profissionalJpaRepository;

  @Override
  @Transactional
  public ProfissionalDatabaseDTO save(ProfissionalDatabaseDTO profissionalDatabaseDTO) {

    ProfissionalEntity newProfissionalEntity =
        ProfissionalEntity.builder()
            .nome(profissionalDatabaseDTO.getNome())
            .cpf(profissionalDatabaseDTO.getCpf())
            .telefone(profissionalDatabaseDTO.getTelefone())
            .email(profissionalDatabaseDTO.getEmail())
            .especialidade(profissionalDatabaseDTO.getEspecialidade())
            .build();

    try {
      return profissionalJpaRepository.save(newProfissionalEntity).toDatabaseDTO();
    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao tentar salvar o profissional", e);
    }
  }
}
