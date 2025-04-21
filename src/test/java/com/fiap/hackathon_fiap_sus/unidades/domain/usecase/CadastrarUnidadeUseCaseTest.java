package com.fiap.hackathon_fiap_sus.unidades.domain.usecase;

import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.dto.UnidadeDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarUnidadeUseCaseTest {

  private UnidadeRepositoryPort repository;
  private CadastrarUnidadeUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(UnidadeRepositoryPort.class);
    useCase = new CadastrarUnidadeUseCase(repository);
  }

  private UnidadeDTO buildUnidadeDTO() {
    return UnidadeDTO.builder()
        .unidade("UBS Fortaleza")
        .endereco("Av. Santos Dumont, 1000")
        .cep("60150000")
        .numero("1000")
        .build();
  }

  private UnidadeDatabaseDTO buildSavedDatabaseDTO() {
    return UnidadeDatabaseDTO.builder()
        .id(1L)
        .unidade("UBS Fortaleza")
        .endereco("Av. Santos Dumont, 1000")
        .cep("60150000")
        .numero("1000")
        .build();
  }

  @Test
  void deveCadastrarUnidadeComSucesso() {
    UnidadeDTO input = buildUnidadeDTO();
    UnidadeDatabaseDTO saved = buildSavedDatabaseDTO();

    when(repository.save(any())).thenReturn(saved);

    UnidadeDTO result = useCase.cadastrar(input);

    assertNotNull(result);
    assertEquals("UBS Fortaleza", result.getUnidade());
    assertEquals("Av. Santos Dumont, 1000", result.getEndereco());
    assertEquals("60150000", result.getCep());

    verify(repository).save(any());
  }
}
