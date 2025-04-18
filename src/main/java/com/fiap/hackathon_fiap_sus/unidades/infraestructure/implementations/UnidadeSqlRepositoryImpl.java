package com.fiap.hackathon_fiap_sus.unidades.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.UnidadeJpaRepository;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities.UnidadeEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

@RequiredArgsConstructor
public class UnidadeSqlRepositoryImpl implements UnidadeRepositoryPort {

  private final UnidadeJpaRepository unidadeJpaRepository;

  @Override
  public UnidadeDatabaseDTO save(UnidadeDatabaseDTO unidadeDatabaseDTO) {

    UnidadeEntity unidadeEntity = new UnidadeEntity();

    unidadeEntity.setId(unidadeDatabaseDTO.getId());
    unidadeEntity.setUnidade(unidadeDatabaseDTO.getUnidade());
    unidadeEntity.setEndereco(unidadeDatabaseDTO.getEndereco());
    unidadeEntity.setCep(unidadeDatabaseDTO.getCep());
    unidadeEntity.setNumero(unidadeDatabaseDTO.getNumero());

    try {
      return unidadeJpaRepository.save(unidadeEntity).toDatabaseDTO();
    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao salvar unidade", e);
    }
  }

  @Override
  public Optional<Unidade> findById(Long id) {
    return unidadeJpaRepository.findById(id).map(UnidadeEntity::toDomain);
  }

  @Override
  public void excluir(Long id) {
    try {
      if (!unidadeJpaRepository.existsById(id)) {
        throw new RuntimeException("Unidade com ID " + id + "não encontrado");
      }
      unidadeJpaRepository.deleteById(id);
    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao excluir unidade", e);
    }
  }
}
