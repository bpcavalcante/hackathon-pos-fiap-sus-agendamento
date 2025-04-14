package com.fiap.hackathon_fiap_sus.profissionais.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.ProfissionalJpaRepository;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ProfissionalSqlRepositoryImpl implements ProfissionalRepositoryPort {

  private final ProfissionalJpaRepository profissionalJpaRepository;

  @Override
  @Transactional
  public ProfissionalDatabaseDTO save(ProfissionalDatabaseDTO profissionalDatabaseDTO) {

    ProfissionalEntity profissionalEntity = new ProfissionalEntity();

    profissionalEntity.setId(profissionalDatabaseDTO.getId());
    profissionalEntity.setNome(profissionalDatabaseDTO.getNome());
    profissionalEntity.setCpf(profissionalDatabaseDTO.getCpf());
    profissionalEntity.setTelefone(profissionalDatabaseDTO.getTelefone());
    profissionalEntity.setEmail(profissionalDatabaseDTO.getEmail());
    profissionalEntity.setEspecialidade(profissionalDatabaseDTO.getEspecialidade());

    try {
      return profissionalJpaRepository.save(profissionalEntity).toDatabaseDTO();
    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao tentar salvar o profissional", e);
    }
  }

  @Override
  public Optional<Profissional> findById(Long id) {
    return profissionalJpaRepository.findById(id).map(ProfissionalEntity::toDomain);
  }

  @Override
  public List<ProfissionalDatabaseDTO> listarProfissionaisPorEspecialidade(String especialidade) {
    List<ProfissionalEntity> profissionais;

    if (especialidade == null || especialidade.trim().isEmpty()) {
      profissionais = profissionalJpaRepository.findAll();
    } else {
      profissionais = profissionalJpaRepository.findByEspecialidade(especialidade);
    }

    return profissionais.stream()
        .map(ProfissionalEntity::toDatabaseDTO)
        .collect(Collectors.toList());
  }

  @Override
  public void excluir(Long id) {
    try {
      if (!profissionalJpaRepository.existsById(id)) {
        throw new RuntimeException("Profissional com ID " + id + " não encontrado.");
      }
      profissionalJpaRepository.deleteById(id);
    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao excluir o profissional " + id, e);
    }
  }
}
