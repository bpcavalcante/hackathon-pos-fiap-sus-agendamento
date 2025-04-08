package com.fiap.hackathon_fiap_sus.profissionais.domain.ports;

import com.fiap.hackathon_fiap_sus.profissionais.domain.ports.dto.ProfissionalDatabaseDTO;

public interface ProfissionalRepositoryPort {
  ProfissionalDatabaseDTO save(ProfissionalDatabaseDTO profissionalDatabaseDTO);
}
