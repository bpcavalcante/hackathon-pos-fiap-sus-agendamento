package com.fiap.hackathon_fiap_sus.config;

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
  public AtualizarUsuarioUseCasePorts atualizarUsuarioUseCasePorts(UsuarioRepositoryPort usuarioRepositoryPort) {
    return new AtualizarUsuarioUseCase(usuarioRepositoryPort);
  }

  @Bean
  public UsuarioSqlRepositoryImpl usuarioSqlRepository(UsuarioJpaRepository usuarioJpaRepository) {
    return new UsuarioSqlRepositoryImpl(usuarioJpaRepository);
  }

  // Configurações Unidade

  @Bean
  public CadastrarUnidadeUseCasePorts cadastrarUnidadeUseCasePorts(UnidadeRepositoryPort unidadeRepositoryPort) {
    return new CadastrarUnidadeUseCase(unidadeRepositoryPort);
  }

  @Bean
  public BuscarPorIDUnidadeUseCasePorts buscarPorIDUnidadeUseCasePorts(UnidadeRepositoryPort unidadeRepositoryPort) {
    return new BuscarPorIDUnidadeUseCase(unidadeRepositoryPort);
  }

  @Bean
  public AtualizarUnidadeUseCasePorts atualizarUnidadeUseCasePorts(UnidadeRepositoryPort unidadeRepositoryPort) {
    return new AtualizarUnidadeUseCase(unidadeRepositoryPort);
  }

  @Bean
  public DeleteUnidadeUseCasePorts deleteUnidadeUseCasePorts(UnidadeRepositoryPort unidadeRepositoryPort) {
    return new DeleteUnidadeUseCase(unidadeRepositoryPort);
  }

  @Bean
  public UnidadeSqlRepositoryImpl unidadeSqlRepository(UnidadeJpaRepository unidadeJpaRepository) {
    return new UnidadeSqlRepositoryImpl(unidadeJpaRepository);
  }

}
