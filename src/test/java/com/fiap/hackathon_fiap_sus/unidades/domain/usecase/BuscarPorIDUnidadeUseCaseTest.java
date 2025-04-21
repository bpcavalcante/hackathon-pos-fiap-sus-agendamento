package com.fiap.hackathon_fiap_sus.unidades.domain.usecase;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPorIDUnidadeUseCaseTest {

  private UnidadeRepositoryPort repository;
  private BuscarPorIDUnidadeUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(UnidadeRepositoryPort.class);
    useCase = new BuscarPorIDUnidadeUseCase(repository);
  }

  private Unidade buildUnidade() {
    return Unidade.builder()
        .id(1L)
        .unidade("UBS Modelo")
        .endereco("Rua das Unidades, 123")
        .cep("60000000")
        .numero("123")
        .build();
  }

  @Test
  void deveRetornarUnidadeQuandoEncontrada() {
    when(repository.findById(1L)).thenReturn(Optional.of(buildUnidade()));

    UnidadeDTO result = useCase.buscarPorId(1L);

    assertNotNull(result);
    assertEquals("UBS Modelo", result.getUnidade());
    assertEquals("60000000", result.getCep());
    verify(repository).findById(1L);
  }

  @Test
  void deveLancarExcecaoQuandoUnidadeNaoEncontrada() {
    when(repository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> useCase.buscarPorId(99L));
    verify(repository).findById(99L);
  }
}
