package com.fiap.hackathon_fiap_sus.usuarios.domain.usecase;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import com.fiap.hackathon_fiap_sus.usuarios.domain.Usuario;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.UsuarioRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPorIDUsuarioUseCaseTest {

  private UsuarioRepositoryPort repository;
  private BuscarPorIDUsuarioUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(UsuarioRepositoryPort.class);
    useCase = new BuscarPorIDUsuarioUseCase(repository);
  }

  @Test
  void deveBuscarUsuarioPorIdComSucesso() {
    Usuario usuario = Usuario.builder()
        .id(1L)
        .nome("Joana Silva")
        .email("joana@email.com")
        .cpf("12345678900")
        .telefone("85999999999")
        .dataNascimento(LocalDate.of(1990, 5, 15))
        .build();

    when(repository.findById(1L)).thenReturn(Optional.of(usuario));

    UsuarioDTO result = useCase.buscarPorId(1L);

    assertNotNull(result);
    assertEquals("Joana Silva", result.getNome());
    assertEquals("85999999999", result.getTelefone());
    verify(repository).findById(1L);
  }

  @Test
  void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
    when(repository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> useCase.buscarPorId(99L));
    verify(repository).findById(99L);
  }
}
