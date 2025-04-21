package com.fiap.hackathon_fiap_sus.profissionais.application.controller;

import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.input.ProfissionalInput;
import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output.ProfissionalOutput;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.*;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfissionalControllerTest {

  @InjectMocks
  private ProfissionalController controller;

  @Mock
  private CadastrarProfissionalUseCasePorts cadastrarUseCase;

  @Mock
  private AtualizarProfissionalUseCasePorts atualizarUseCase;

  @Mock
  private BuscarPorIDProfissionalUseCasePorts buscarUseCase;

  @Mock
  private ListarProfissionaisPorEspecialidadeUseCasePorts listarUseCase;

  @Mock
  private DeleteProfissionalUseCasePorts deleteUseCase;

  private ProfissionalDTO mockDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockDTO = ProfissionalDTO.builder()
        .id(1L)
        .nome("Dra. Ana")
        .cpf("12345678900")
        .email("ana@medico.com")
        .telefone("11999999999")
        .especialidade("CARDIOLOGIA")
        .build();
  }

  @Test
  void testCadastrar() {
    ProfissionalInput input = new ProfissionalInput("Dra. Ana", "12345678900", "11999999999", "ana@medico.com", "CARDIOLOGIA");

    when(cadastrarUseCase.cadastrar(any())).thenReturn(mockDTO);

    ResponseEntity<ProfissionalOutput> response = controller.cadastrar(input);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals("Dra. Ana", response.getBody().getNome());
  }

  @Test
  void testAtualizar() {
    ProfissionalInput input = new ProfissionalInput("Dra. Ana", "12345678900", "11999999999", "ana@medico.com", "CARDIOLOGIA");

    when(atualizarUseCase.atualizar(eq(1L), any())).thenReturn(mockDTO);

    ResponseEntity<ProfissionalOutput> response = controller.atualizar(1L, input);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("12345678900", response.getBody().getCpf());
  }

  @Test
  void testBuscarPorId() {
    when(buscarUseCase.buscarPorId(1L)).thenReturn(mockDTO);

    ResponseEntity<ProfissionalOutput> response = controller.buscarPorId(1L);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("ana@medico.com", response.getBody().getEmail());
  }

  @Test
  void testListarPorEspecialidade() {
    when(listarUseCase.listarProfissionaisPorEspecialidade("CARDIOLOGIA"))
        .thenReturn(List.of(mockDTO.toOutput()));

    ResponseEntity<List<ProfissionalOutput>> response = controller.listarProfissionaisPorEspecialidade("CARDIOLOGIA");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().size());
    assertEquals("CARDIOLOGIA", response.getBody().get(0).getEspecialidade());
  }

  @Test
  void testExcluirProfissional() {
    ResponseEntity<Void> response = controller.excluirProfissional(1L);

    assertEquals(204, response.getStatusCodeValue());
    verify(deleteUseCase).excluir(1L);
  }
}
