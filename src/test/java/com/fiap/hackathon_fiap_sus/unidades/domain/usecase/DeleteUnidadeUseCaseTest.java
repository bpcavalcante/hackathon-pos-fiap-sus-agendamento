package com.fiap.hackathon_fiap_sus.unidades.domain.usecase;

import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteUnidadeUseCaseTest {

  private UnidadeRepositoryPort repository;
  private DeleteUnidadeUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(UnidadeRepositoryPort.class);
    useCase = new DeleteUnidadeUseCase(repository);
  }

  @Test
  void deveExcluirUnidadeQuandoEncontrada() {
    Long id = 1L;
    Unidade unidade = Unidade.builder()
        .id(id)
        .unidade("UBS Central")
        .endereco("Rua A")
        .cep("00000000")
        .numero("10")
        .build();

    when(repository.findById(id)).thenReturn(Optional.of(unidade));

    useCase.excluir(id);

    verify(repository).excluir(id);
  }

  @Test
  void deveLancarExcecaoQuandoUnidadeNaoEncontrada() {
    Long id = 99L;
    when(repository.findById(id)).thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(RuntimeException.class, () -> useCase.excluir(id));
    assertEquals("Unidade com ID 99 não encontrada", exception.getMessage());

    verify(repository, never()).excluir(anyLong());
  }
}
