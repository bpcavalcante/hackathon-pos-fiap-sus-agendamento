package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarProfissionalUseCaseTest {

  private ProfissionalRepositoryPort repository;
  private CadastrarProfissionalUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(ProfissionalRepositoryPort.class);
    useCase = new CadastrarProfissionalUseCase(repository);
  }

  private ProfissionalDTO buildDTO() {
    return ProfissionalDTO.builder()
        .id(null)
        .nome("Dra. Ana")
        .cpf("12345678900")
        .email("ana@medico.com")
        .telefone("11999999999")
        .especialidade("DERMATOLOGIA")
        .build();
  }

  private ProfissionalDatabaseDTO buildSavedDatabaseDTO() {
    return ProfissionalDatabaseDTO.builder()
        .id(1L)
        .nome("Dra. Ana")
        .cpf("12345678900")
        .email("ana@medico.com")
        .telefone("11999999999")
        .especialidade("DERMATOLOGIA")
        .build();
  }

  @Test
  void deveCadastrarProfissionalComSucesso() {
    ProfissionalDTO input = buildDTO();
    ProfissionalDatabaseDTO saved = buildSavedDatabaseDTO();

    when(repository.save(any())).thenReturn(saved);

    ProfissionalDTO resultado = useCase.cadastrar(input);

    assertNotNull(resultado);
    assertEquals(1L, resultado.getId());
    assertEquals("Dra. Ana", resultado.getNome());
    assertEquals("DERMATOLOGIA", resultado.getEspecialidade());

    verify(repository).save(any());
  }
}
