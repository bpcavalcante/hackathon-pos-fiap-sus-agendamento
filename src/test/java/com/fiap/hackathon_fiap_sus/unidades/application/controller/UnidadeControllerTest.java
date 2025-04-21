package com.fiap.hackathon_fiap_sus.unidades.application.controller;

import com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.input.UnidadeInput;
import com.fiap.hackathon_fiap_sus.unidades.application.controller.dto.output.UnidadeOutput;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.AtualizarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.BuscarPorIDUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.CadastrarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.DeleteUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.dto.UnidadeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnidadeControllerTest {

  @InjectMocks
  private UnidadeController controller;

  @Mock
  private CadastrarUnidadeUseCasePorts cadastrarUseCase;

  @Mock
  private BuscarPorIDUnidadeUseCasePorts buscarUseCase;

  @Mock
  private AtualizarUnidadeUseCasePorts atualizarUseCase;

  @Mock
  private DeleteUnidadeUseCasePorts deleteUseCase;

  private UnidadeDTO mockDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockDTO = UnidadeDTO.builder()
        .id(1L)
        .unidade("UBS Central")
        .cep("60000000")
        .endereco("Rua Principal, 123")
        .numero("123")
        .build();
  }

  @Test
  void testCadastrar() {
    UnidadeInput input = new UnidadeInput("UBS Central", "Rua Principal, 123", "60000000", "123");

    when(cadastrarUseCase.cadastrar(any())).thenReturn(mockDTO);

    ResponseEntity<UnidadeOutput> response = controller.cadastrar(input);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("UBS Central", response.getBody().getUnidade());
  }

  @Test
  void testBuscarUnidadePorId() {
    when(buscarUseCase.buscarPorId(1L)).thenReturn(mockDTO);

    ResponseEntity<UnidadeOutput> response = controller.buscarUnidadePorId(1L);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Rua Principal, 123", response.getBody().getEndereco());
  }

  @Test
  void testAtualizarUnidade() {
    UnidadeInput input = new UnidadeInput("UBS Atualizada", "Rua Nova, 321", "60111111", "321");

    UnidadeDTO atualizado = UnidadeDTO.builder()
        .id(1L)
        .unidade("UBS Atualizada")
        .endereco("Rua Nova, 321")
        .cep("60111111")
        .numero("321")
        .build();

    when(atualizarUseCase.atualizarUnidade(eq(1L), any())).thenReturn(atualizado);

    ResponseEntity<UnidadeOutput> response = controller.atualizarUnidade(1L, input);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("UBS Atualizada", response.getBody().getUnidade());
  }

  @Test
  void testExcluirUnidade() {
    ResponseEntity<Void> response = controller.excluirUnidade(1L);

    assertEquals(204, response.getStatusCodeValue());
    verify(deleteUseCase).excluir(1L);
  }
}
