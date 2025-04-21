package com.fiap.hackathon_fiap_sus.profissionais.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.ProfissionalJpaRepository;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfissionalSqlRepositoryImplTest {

  private ProfissionalJpaRepository jpaRepository;
  private ProfissionalSqlRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    jpaRepository = mock(ProfissionalJpaRepository.class);
    repository = new ProfissionalSqlRepositoryImpl(jpaRepository);
  }

  private ProfissionalEntity mockEntity() {
    return ProfissionalEntity.builder()
        .id(1L)
        .nome("Dra. Fernanda")
        .cpf("12345678900")
        .email("fernanda@med.com")
        .telefone("11999999999")
        .especialidade("GINECOLOGIA")
        .build();
  }

  private ProfissionalDatabaseDTO mockDTO() {
    return ProfissionalDatabaseDTO.builder()
        .id(1L)
        .nome("Dra. Fernanda")
        .cpf("12345678900")
        .email("fernanda@med.com")
        .telefone("11999999999")
        .especialidade("GINECOLOGIA")
        .build();
  }

  @Test
  void deveSalvarProfissionalComSucesso() {
    ProfissionalEntity entity = mockEntity();
    ProfissionalDatabaseDTO dto = mockDTO();

    when(jpaRepository.save(any())).thenReturn(entity);

    ProfissionalDatabaseDTO saved = repository.save(dto);

    assertEquals("Dra. Fernanda", saved.getNome());
    verify(jpaRepository).save(any());
  }

  @Test
  void deveLancarExcecaoAoSalvarQuandoErroDeBanco() {
    ProfissionalDatabaseDTO dto = mockDTO();

    when(jpaRepository.save(any())).thenThrow(new DataAccessException("Erro no banco") {});

    RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.save(dto));
    assertTrue(ex.getMessage().contains("Erro ao tentar salvar"));
  }

  @Test
  void deveBuscarProfissionalPorId() {
    ProfissionalEntity entity = mockEntity();
    when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));

    Optional<Profissional> resultado = repository.findById(1L);

    assertTrue(resultado.isPresent());
    assertEquals("Dra. Fernanda", resultado.get().getNome());
  }

  @Test
  void deveListarPorEspecialidade() {
    when(jpaRepository.findByEspecialidade("GINECOLOGIA")).thenReturn(List.of(mockEntity()));

    List<ProfissionalDatabaseDTO> resultado = repository.listarProfissionaisPorEspecialidade("GINECOLOGIA");

    assertEquals(1, resultado.size());
    assertEquals("GINECOLOGIA", resultado.get(0).getEspecialidade());
  }

  @Test
  void deveListarTodosQuandoEspecialidadeVazia() {
    when(jpaRepository.findAll()).thenReturn(Arrays.asList(mockEntity(), mockEntity()));

    List<ProfissionalDatabaseDTO> resultado = repository.listarProfissionaisPorEspecialidade("");

    assertEquals(2, resultado.size());
  }

  @Test
  void deveExcluirComSucesso() {
    when(jpaRepository.existsById(1L)).thenReturn(true);

    repository.excluir(1L);

    verify(jpaRepository).deleteById(1L);
  }

  @Test
  void deveLancarExcecaoSeProfissionalNaoExisteAoExcluir() {
    when(jpaRepository.existsById(1L)).thenReturn(false);

    RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.excluir(1L));
    assertTrue(ex.getMessage().contains("não encontrado"));
  }

  @Test
  void deveLancarExcecaoSeErroAoExcluir() {
    when(jpaRepository.existsById(1L)).thenReturn(true);
    doThrow(new DataAccessException("Erro") {}).when(jpaRepository).deleteById(1L);

    RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.excluir(1L));
    assertTrue(ex.getMessage().contains("Erro ao excluir"));
  }
}
