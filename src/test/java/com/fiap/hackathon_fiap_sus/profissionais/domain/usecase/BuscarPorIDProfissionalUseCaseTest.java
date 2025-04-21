package com.fiap.hackathon_fiap_sus.profissionais.domain.usecase;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPorIDProfissionalUseCaseTest {

  private ProfissionalRepositoryPort repository;
  private BuscarPorIDProfissionalUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(ProfissionalRepositoryPort.class);
    useCase = new BuscarPorIDProfissionalUseCase(repository);
  }

  private Profissional profissionalMock() {
    return Profissional.builder()
        .id(1L)
        .nome("Dra. Carla")
        .cpf("12345678900")
        .email("carla@medico.com")
        .telefone("11999999999")
        .especialidade("NEUROLOGIA")
        .build();
  }

  @Test
  void deveRetornarProfissionalQuandoEncontrado() {
    when(repository.findById(1L)).thenReturn(Optional.of(profissionalMock()));

    ProfissionalDTO resultado = useCase.buscarPorId(1L);

    assertNotNull(resultado);
    assertEquals("Dra. Carla", resultado.getNome());
    assertEquals("NEUROLOGIA", resultado.getEspecialidade());
    verify(repository).findById(1L);
  }

  @Test
  void deveLancarExcecaoQuandoNaoEncontrarProfissional() {
    when(repository.findById(2L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> useCase.buscarPorId(2L));
    verify(repository).findById(2L);
  }
}
