package com.fiap.hackathon_fiap_sus.config;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.BuscarAgendamentoPorIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.CadastrarAgendamentoUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.CancelarAgendamentoIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.ListarAgendamentosPorUsuarioIdUseCasePorts;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase.BuscarAgendamentoPorIdUseCase;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase.CadastrarAgendamentoUseCase;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase.CancelarAgendamentoUseCase;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.usecase.ListarAgendamentosPorUsuarioIdUseCase;
import com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.AgendamentoJpaRepository;
import com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.implementations.AgendamentoSqlRepositoryImpl;
import com.fiap.hackathon_fiap_sus.profissionais.application.ports.DeleteProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.AtualizarProfissionalUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.BuscarPorIDProfissionalUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.CadastrarProfissionalUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.DeleteProfissionalUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.ListarProfissionaisPorEspecialidadeUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.ProfissionalJpaRepository;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.implementations.ProfissionalSqlRepositoryImpl;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.AtualizarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.BuscarPorIDUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.CadastrarUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.application.ports.DeleteUnidadeUseCasePorts;
import com.fiap.hackathon_fiap_sus.unidades.domain.ports.UnidadeRepositoryPort;
import com.fiap.hackathon_fiap_sus.unidades.domain.usecase.AtualizarUnidadeUseCase;
import com.fiap.hackathon_fiap_sus.unidades.domain.usecase.BuscarPorIDUnidadeUseCase;
import com.fiap.hackathon_fiap_sus.unidades.domain.usecase.CadastrarUnidadeUseCase;
import com.fiap.hackathon_fiap_sus.unidades.domain.usecase.DeleteUnidadeUseCase;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.UnidadeJpaRepository;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.implementations.UnidadeSqlRepositoryImpl;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.AtualizarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.BuscarPorIDUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.CadastrarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.UsuarioRepositoryPort;
import com.fiap.hackathon_fiap_sus.usuarios.domain.usecase.AtualizarUsuarioUseCase;
import com.fiap.hackathon_fiap_sus.usuarios.domain.usecase.BuscarPorIDUsuarioUseCase;
import com.fiap.hackathon_fiap_sus.usuarios.domain.usecase.CadastrarUsuarioUseCase;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.UsuarioJpaRepository;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.implementations.UsuarioSqlRepositoryImpl;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

  // Configurações Profissional

  @Bean
  public CadastrarProfissionalUseCase cadastrarProfissionalUseCase(
      ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new CadastrarProfissionalUseCase(profissionalRepositoryPort);
  }

  @Bean
  public AtualizarProfissionalUseCase atualizarProfissionalUseCase(
      ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new AtualizarProfissionalUseCase(profissionalRepositoryPort);
  }

  @Bean
  public BuscarPorIDProfissionalUseCase buscarPorIDProfissionalUseCase(
      ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new BuscarPorIDProfissionalUseCase(profissionalRepositoryPort);
  }

  @Bean
  public ListarProfissionaisPorEspecialidadeUseCase listarProfissionaisPorEspecialidadeUseCase(
      ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new ListarProfissionaisPorEspecialidadeUseCase(profissionalRepositoryPort);
  }

  @Bean
  public DeleteProfissionalUseCasePorts deleteProfissionalUseCasePorts(
      ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new DeleteProfissionalUseCase(profissionalRepositoryPort);
  }

  @Bean
  public ProfissionalSqlRepositoryImpl profissionalSqlRepository(
      ProfissionalJpaRepository profissionalJpaRepository) {
    return new ProfissionalSqlRepositoryImpl(profissionalJpaRepository);
  }

  // Configurações Usuario

  @Bean
  public CadastrarUsuarioUseCasePorts cadastrarUsuarioUseCasePorts(
      UsuarioRepositoryPort usuarioRepositoryPort) {
    return new CadastrarUsuarioUseCase(usuarioRepositoryPort);
  }

  @Bean
  public BuscarPorIDUsuarioUseCasePorts buscarPorIDUsuarioUseCasePorts(
      UsuarioRepositoryPort usuarioRepositoryPort) {
    return new BuscarPorIDUsuarioUseCase(usuarioRepositoryPort);
  }

  @Bean
  public AtualizarUsuarioUseCasePorts atualizarUsuarioUseCasePorts(
      UsuarioRepositoryPort usuarioRepositoryPort) {
    return new AtualizarUsuarioUseCase(usuarioRepositoryPort);
  }

  @Bean
  public UsuarioSqlRepositoryImpl usuarioSqlRepository(UsuarioJpaRepository usuarioJpaRepository) {
    return new UsuarioSqlRepositoryImpl(usuarioJpaRepository);
  }

  // Configurações Unidade

  @Bean
  public CadastrarUnidadeUseCasePorts cadastrarUnidadeUseCasePorts(
      UnidadeRepositoryPort unidadeRepositoryPort) {
    return new CadastrarUnidadeUseCase(unidadeRepositoryPort);
  }

  @Bean
  public BuscarPorIDUnidadeUseCasePorts buscarPorIDUnidadeUseCasePorts(
      UnidadeRepositoryPort unidadeRepositoryPort) {
    return new BuscarPorIDUnidadeUseCase(unidadeRepositoryPort);
  }

  @Bean
  public AtualizarUnidadeUseCasePorts atualizarUnidadeUseCasePorts(
      UnidadeRepositoryPort unidadeRepositoryPort) {
    return new AtualizarUnidadeUseCase(unidadeRepositoryPort);
  }

  @Bean
  public DeleteUnidadeUseCasePorts deleteUnidadeUseCasePorts(
      UnidadeRepositoryPort unidadeRepositoryPort) {
    return new DeleteUnidadeUseCase(unidadeRepositoryPort);
  }

  @Bean
  public UnidadeSqlRepositoryImpl unidadeSqlRepository(UnidadeJpaRepository unidadeJpaRepository) {
    return new UnidadeSqlRepositoryImpl(unidadeJpaRepository);
  }

  // Configurações Agendamento

  @Bean
  public CadastrarAgendamentoUseCasePorts cadastrarAgendamentoUseCasePorts(
      AgendamentoRepositoryPort agendamentoRepositoryPort) {
    return new CadastrarAgendamentoUseCase(agendamentoRepositoryPort);
  }

  @Bean
  public ListarAgendamentosPorUsuarioIdUseCasePorts listarAgendamentosPorUsuarioIdUseCasePorts(AgendamentoRepositoryPort agendamentoRepositoryPort) {
    return new ListarAgendamentosPorUsuarioIdUseCase(agendamentoRepositoryPort);
  }

  @Bean
  public BuscarAgendamentoPorIdUseCasePorts buscarAgendamentoPorIdUseCasePorts(AgendamentoRepositoryPort agendamentoRepositoryPort) {
    return new BuscarAgendamentoPorIdUseCase(agendamentoRepositoryPort);
  }

  @Bean
  public CancelarAgendamentoIdUseCasePorts cancelarAgendamentoIdUseCasePorts(AgendamentoRepositoryPort agendamentoRepositoryPort) {
    return new CancelarAgendamentoUseCase(agendamentoRepositoryPort);
  }

  @Bean
  public AgendamentoSqlRepositoryImpl agendamentoSqlRepository(
      AgendamentoJpaRepository agendamentoJpaRepository,
      ProfissionalJpaRepository profissionalJpaRepository,
      UsuarioJpaRepository usuarioJpaRepository,
      UnidadeJpaRepository unidadeJpaRepository) {
    return new AgendamentoSqlRepositoryImpl(
        agendamentoJpaRepository,
        profissionalJpaRepository,
        usuarioJpaRepository,
        unidadeJpaRepository);
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Hackathon SUS API")
            .version("1.0")
            .description("Documentação da API do SUS"));
  }

}
