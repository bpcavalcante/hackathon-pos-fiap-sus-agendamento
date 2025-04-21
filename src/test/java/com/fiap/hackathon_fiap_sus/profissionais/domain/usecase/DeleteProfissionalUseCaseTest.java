package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteProfissionalUseCaseTest {

  private ProfissionalRepositoryPort repository;
  private DeleteProfissionalUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(ProfissionalRepositoryPort.class);
    useCase = new DeleteProfissionalUseCase(repository);
  }

  @Test
  void deveExcluirProfissionalQuandoEncontrado() {
    Long id = 1L;
    Profissional profissional = Profissional.builder()
        .id(id)
        .nome("Dra. Paula")
        .cpf("12345678900")
        .telefone("11999999999")
        .email("paula@clinic.com")
        .especialidade("DERMATOLOGIA")
        .build();

    when(repository.findById(id)).thenReturn(Optional.of(profissional));

    useCase.excluir(id);

    verify(repository).excluir(id);
  }

  @Test
  void deveLancarExcecaoQuandoProfissionalNaoForEncontrado() {
    Long id = 99L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.excluir(id));

    assertEquals("Profissional com ID 99 não encontrado", ex.getMessage());
    verify(repository, never()).excluir(anyLong());
  }
}
