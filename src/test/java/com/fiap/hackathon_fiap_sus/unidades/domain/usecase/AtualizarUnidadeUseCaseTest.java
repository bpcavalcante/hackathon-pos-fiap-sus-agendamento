package com.fiap.hackathon_fiap_sus.unidades.domain.usecase;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import com.fiap.hackathon_fiap_sus.unidades.domain.Unidade;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarUnidadeUseCaseTest {

  private UnidadeRepositoryPort repository;
  private AtualizarUnidadeUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(UnidadeRepositoryPort.class);
    useCase = new AtualizarUnidadeUseCase(repository);
  }

  private Unidade buildUnidadeExistente() {
    return Unidade.builder()
        .id(1L)
        .unidade("UBS Antiga")
        .endereco("Rua A")
        .cep("00000000")
        .numero("10")
        .build();
  }

  private UnidadeDTO buildDTOAtualizado() {
    return UnidadeDTO.builder()
        .unidade("UBS Nova")
        .endereco("Rua Nova")
        .cep("11111111")
        .numero("123")
        .build();
  }

  private UnidadeDatabaseDTO buildDatabaseDTOAtualizado() {
    return UnidadeDatabaseDTO.builder()
        .id(1L)
        .unidade("UBS Nova")
        .endereco("Rua Nova")
        .cep("11111111")
        .numero("123")
        .build();
  }

  @Test
  void deveAtualizarUnidadeComSucesso() {
    Unidade unidade = buildUnidadeExistente();
    UnidadeDTO dto = buildDTOAtualizado();
    UnidadeDatabaseDTO atualizado = buildDatabaseDTOAtualizado();

    when(repository.findById(1L)).thenReturn(Optional.of(unidade));
    when(repository.save(any())).thenReturn(atualizado);

    UnidadeDTO result = useCase.atualizarUnidade(1L, dto);

    assertNotNull(result);
    assertEquals("UBS Nova", result.getUnidade());
    assertEquals("Rua Nova", result.getEndereco());
    assertEquals("11111111", result.getCep());
    verify(repository).findById(1L);
    verify(repository).save(any());
  }

  @Test
  void deveLancarExcecaoQuandoUnidadeNaoEncontrada() {
    when(repository.findById(99L)).thenReturn(Optional.empty());

    UnidadeDTO dto = buildDTOAtualizado();

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      useCase.atualizarUnidade(99L, dto);
    });

    assertEquals("Unidade não encontrada", exception.getMessage());
    verify(repository, never()).save(any());
  }
}
