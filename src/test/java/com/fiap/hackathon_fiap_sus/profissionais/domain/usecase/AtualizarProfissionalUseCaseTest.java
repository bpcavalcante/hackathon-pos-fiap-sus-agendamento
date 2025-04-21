package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarProfissionalUseCaseTest {

  private ProfissionalRepositoryPort repository;
  private AtualizarProfissionalUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(ProfissionalRepositoryPort.class);
    useCase = new AtualizarProfissionalUseCase(repository);
  }

  private ProfissionalDTO novoProfissionalDTO() {
    return ProfissionalDTO.builder()
        .nome("Dr. João")
        .cpf("99988877766")
        .telefone("11987654321")
        .email("joao@medico.com")
        .especialidade("ORTOPEDIA")
        .build();
  }

  private Profissional profissionalExistente() {
    return Profissional.builder()
        .id(1L)
        .nome("Antigo Nome")
        .cpf("00000000000")
        .telefone("000000000")
        .email("antigo@email.com")
        .especialidade("GERAL")
        .build();
  }

  private ProfissionalDatabaseDTO databaseDTOAtualizado() {
    return ProfissionalDatabaseDTO.builder()
        .id(1L)
        .nome("Dr. João")
        .cpf("99988877766")
        .telefone("11987654321")
        .email("joao@medico.com")
        .especialidade("ORTOPEDIA")
        .build();
  }

  @Test
  void deveAtualizarProfissionalComSucesso() {
    Long id = 1L;
    ProfissionalDTO input = novoProfissionalDTO();

    when(repository.findById(id)).thenReturn(Optional.of(profissionalExistente()));
    when(repository.save(any())).thenReturn(databaseDTOAtualizado());

    ProfissionalDTO result = useCase.atualizar(id, input);

    assertNotNull(result);
    assertEquals("Dr. João", result.getNome());
    assertEquals("ORTOPEDIA", result.getEspecialidade());
    verify(repository).findById(id);
    verify(repository).save(any());
  }

  @Test
  void deveLancarExcecao_QuandoProfissionalNaoForEncontrado() {
    when(repository.findById(999L)).thenReturn(Optional.empty());

    RuntimeException ex = assertThrows(RuntimeException.class, () -> {
      useCase.atualizar(999L, novoProfissionalDTO());
    });

    assertEquals("Profissional não encontrado", ex.getMessage());
    verify(repository, never()).save(any());
  }
}
