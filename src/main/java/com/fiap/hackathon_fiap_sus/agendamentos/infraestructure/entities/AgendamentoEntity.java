package com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.entities;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities.UnidadeEntity;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.entities.UsuarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_AGENDAMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AgendamentoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String protocolo;

  private String status;

  @Column(name = "data_agendamento")
  private LocalDateTime dataAgendamento;

  @ManyToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private UsuarioEntity usuario;

  @ManyToOne
  @JoinColumn(name = "profissional_id", nullable = false)
  private ProfissionalEntity profissional;

  @ManyToOne
  @JoinColumn(name = "unidade_saude_id", nullable = false)
  private UnidadeEntity unidadeSaude;

  @PrePersist
  public void prePersist() {
    if (this.status == null || this.status.isBlank()) {
      this.status = "AGENDADO";
    }
  }

  public AgendamentoDTO toDTO() {
    return AgendamentoDTO.builder()
        .id(this.getId())
        .protocolo(this.getProtocolo())
        .status(this.getStatus())
        .dataAgendamento(this.getDataAgendamento())
        .build();
  }
}
