package com.fiap.hackathon_fiap_sus.profissionais.application.ports;

import com.fiap.hackathon_fiap_sus.profissionais.application.controller.dto.output.ProfissionalOutput;
import java.util.List;

public interface ListarProfissionaisPorEspecialidadeUseCasePorts {

  List<ProfissionalOutput> listarProfissionaisPorEspecialidade(String especialidade);

}
