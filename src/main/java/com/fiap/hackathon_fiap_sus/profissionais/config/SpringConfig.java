package com.fiap.hackathon_fiap_sus.profissionais.config;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.DeleteProfissionalUseCasePorts;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.ProfissionalRepositoryPort;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.AtualizarProfissionalUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.BuscarPorIDProfissionalUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.CadastrarProfissionalUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.DeleteProfissionalUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.domain.usecase.ListarProfissionaisPorEspecialidadeUseCase;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.ProfissionalJpaRepository;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.implementations.ProfissionalSqlRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

  @Bean
  public CadastrarProfissionalUseCase cadastrarProfissionalUseCase(
      ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new CadastrarProfissionalUseCase(profissionalRepositoryPort);
  }

  @Bean
  public AtualizarProfissionalUseCase atualizarProfissionalUseCase(ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new AtualizarProfissionalUseCase(profissionalRepositoryPort);
  }

  @Bean
  public BuscarPorIDProfissionalUseCase buscarPorIDProfissionalUseCase(ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new BuscarPorIDProfissionalUseCase(profissionalRepositoryPort);
  }

  @Bean
  public ListarProfissionaisPorEspecialidadeUseCase listarProfissionaisPorEspecialidadeUseCase(ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new ListarProfissionaisPorEspecialidadeUseCase(profissionalRepositoryPort);
  }

  @Bean
  public DeleteProfissionalUseCasePorts deleteProfissionalUseCasePorts(ProfissionalRepositoryPort profissionalRepositoryPort) {
    return new DeleteProfissionalUseCase(profissionalRepositoryPort);
  }

  @Bean
  public ProfissionalSqlRepositoryImpl profissionalSqlRepository(
      ProfissionalJpaRepository profissionalJpaRepository) {
    return new ProfissionalSqlRepositoryImpl(profissionalJpaRepository);
  }

}
