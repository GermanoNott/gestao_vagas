package br.com.germanott.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    @Schema(example = "Vaga para Desenvolvedor Java Junior")
    private String description;
    @Schema(example = "maria")
    private String username;
    @Schema(example = "maria@email.com")
    private String email;
    private UUID id;
    @Schema(example = "Maria da Silva")
    private String name;
}
