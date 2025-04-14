package com.fiap.hackathon_fiap_sus.profissionais.domain.ports;

import com.fiap.hackathon_fiap_sus.profissionais.domain.Profissional;
import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;
import java.util.List;
import java.util.Optional;

public interface ProfissionalRepositoryPort {
  ProfissionalDatabaseDTO save(ProfissionalDatabaseDTO profissionalDatabaseDTO);
  Optional<Profissional> findById(Long id);
  List<ProfissionalDatabaseDTO> listarProfissionaisPorEspecialidade(String especialidade);
  void excluir(Long id);
}
