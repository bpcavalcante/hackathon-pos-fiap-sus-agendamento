package com.fiap.hackathon_fiap_sus.usuarios.domain.usecase;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import com.fiap.hackathon_fiap_sus.usuarios.domain.Usuario;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.UsuarioRepositoryPort;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarUsuarioUseCaseTest {

  private UsuarioRepositoryPort repository;
  private AtualizarUsuarioUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(UsuarioRepositoryPort.class);
    useCase = new AtualizarUsuarioUseCase(repository);
  }

  @Test
  void deveAtualizarUsuarioComSucesso() {
    Long id = 1L;

    Usuario usuarioExistente = Usuario.builder()
        .id(id)
        .nome("Antigo Nome")
        .email("antigo@email.com")
        .cpf("00000000000")
        .telefone("85999999999")
        .dataNascimento(LocalDate.of(1990, 1, 1))
        .build();

    UsuarioDTO novoDTO = UsuarioDTO.builder()
        .nome("Novo Nome")
        .email("novo@email.com")
        .cpf("12345678900")
        .telefone("85988888888")
        .dataNascimento(LocalDate.of(1990, 1, 1)) // não está sendo atualizado, mas pode estar aqui
        .build();

    UsuarioDatabaseDTO atualizado = UsuarioDatabaseDTO.builder()
        .id(id)
        .nome("Novo Nome")
        .email("novo@email.com")
        .cpf("12345678900")
        .telefone("85988888888")
        .dataNascimento(LocalDate.of(1990, 1, 1))
        .build();

    when(repository.findById(id)).thenReturn(Optional.of(usuarioExistente));
    when(repository.save(any())).thenReturn(atualizado);

    UsuarioDTO resultado = useCase.atualizarUsuario(id, novoDTO);

    assertNotNull(resultado);
    assertEquals("Novo Nome", resultado.getNome());
    assertEquals("novo@email.com", resultado.getEmail());
    assertEquals("12345678900", resultado.getCpf());

    verify(repository).findById(id);
    verify(repository).save(any());
  }

  @Test
  void deveLancarExcecaoSeUsuarioNaoEncontrado() {
    when(repository.findById(99L)).thenReturn(Optional.empty());

    RuntimeException ex = assertThrows(RuntimeException.class, () -> {
      useCase.atualizarUsuario(99L, UsuarioDTO.builder().build());
    });

    assertEquals("Usuario não encontrado", ex.getMessage());
    verify(repository).findById(99L);
    verify(repository, never()).save(any());
  }
}
