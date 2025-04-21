package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output.ProfissionalOutput;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarProfissionaisPorEspecialidadeUseCaseTest {

  private ProfissionalRepositoryPort repository;
  private ListarProfissionaisPorEspecialidadeUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(ProfissionalRepositoryPort.class);
    useCase = new ListarProfissionaisPorEspecialidadeUseCase(repository);
  }

  private ProfissionalDatabaseDTO mockProfissional() {
    return ProfissionalDatabaseDTO.builder()
        .id(1L)
        .nome("Dra. Paula")
        .cpf("12345678900")
        .telefone("11999999999")
        .email("paula@clinic.com")
        .especialidade("CARDIOLOGIA")
        .build();
  }

  @Test
  void deveRetornarListaDeProfissionaisPorEspecialidade() {
    when(repository.listarProfissionaisPorEspecialidade("CARDIOLOGIA"))
        .thenReturn(List.of(mockProfissional()));

    List<ProfissionalOutput> resultado = useCase.listarProfissionaisPorEspecialidade("CARDIOLOGIA");

    assertEquals(1, resultado.size());
    assertEquals("Dra. Paula", resultado.get(0).getNome());
    assertEquals("CARDIOLOGIA", resultado.get(0).getEspecialidade());
    verify(repository).listarProfissionaisPorEspecialidade("CARDIOLOGIA");
  }

  @Test
  void deveRetornarListaVaziaQuandoNenhumProfissionalEncontrado() {
    when(repository.listarProfissionaisPorEspecialidade("NEUROLOGIA"))
        .thenReturn(List.of());

    List<ProfissionalOutput> resultado = useCase.listarProfissionaisPorEspecialidade("NEUROLOGIA");

    assertTrue(resultado.isEmpty());
    verify(repository).listarProfissionaisPorEspecialidade("NEUROLOGIA");
  }
}
