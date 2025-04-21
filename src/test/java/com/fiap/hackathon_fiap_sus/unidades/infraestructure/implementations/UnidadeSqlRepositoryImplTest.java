package com.fiap.hackathon_fiap_sus.unidades.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.UnidadeJpaRepository;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities.UnidadeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnidadeSqlRepositoryImplTest {

  private UnidadeJpaRepository jpaRepository;
  private UnidadeSqlRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    jpaRepository = mock(UnidadeJpaRepository.class);
    repository = new UnidadeSqlRepositoryImpl(jpaRepository);
  }

  private UnidadeEntity mockEntity() {
    return UnidadeEntity.builder()
        .id(1L)
        .unidade("UBS Modelo")
        .endereco("Rua A")
        .cep("60000000")
        .numero("123")
        .build();
  }

  private UnidadeDatabaseDTO mockDatabaseDTO() {
    return UnidadeDatabaseDTO.builder()
        .id(1L)
        .unidade("UBS Modelo")
        .endereco("Rua A")
        .cep("60000000")
        .numero("123")
        .build();
  }

  @Test
  void deveSalvarComSucesso() {
    UnidadeEntity entity = mockEntity();
    when(jpaRepository.save(any())).thenReturn(entity);

    UnidadeDatabaseDTO result = repository.save(mockDatabaseDTO());

    assertEquals("UBS Modelo", result.getUnidade());
    verify(jpaRepository).save(any());
  }

  @Test
  void deveLancarExcecaoAoSalvar() {
    when(jpaRepository.save(any())).thenThrow(new DataAccessException("Erro de banco") {});

    RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.save(mockDatabaseDTO()));
    assertTrue(ex.getMessage().contains("Erro ao salvar"));
  }

  @Test
  void deveRetornarUnidadeQuandoEncontrada() {
    UnidadeEntity entity = mockEntity();
    when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));

    Optional<Unidade> result = repository.findById(1L);

    assertTrue(result.isPresent());
    assertEquals("UBS Modelo", result.get().getUnidade());
  }

  @Test
  void deveRetornarVazioQuandoNaoEncontrada() {
    when(jpaRepository.findById(99L)).thenReturn(Optional.empty());

    Optional<Unidade> result = repository.findById(99L);

    assertTrue(result.isEmpty());
  }

  @Test
  void deveExcluirComSucesso() {
    when(jpaRepository.existsById(1L)).thenReturn(true);

    repository.excluir(1L);

    verify(jpaRepository).deleteById(1L);
  }

  @Test
  void deveLancarExcecaoSeUnidadeNaoExisteAoExcluir() {
    when(jpaRepository.existsById(2L)).thenReturn(false);

    RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.excluir(2L));
    assertTrue(ex.getMessage().contains("não encontrado"));
    verify(jpaRepository, never()).deleteById(anyLong());
  }

  @Test
  void deveLancarErroAoExcluirPorProblemaDeBanco() {
    when(jpaRepository.existsById(1L)).thenReturn(true);
    doThrow(new DataAccessException("Erro") {}).when(jpaRepository).deleteById(1L);

    RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.excluir(1L));
    assertTrue(ex.getMessage().contains("Erro ao excluir"));
  }
}
