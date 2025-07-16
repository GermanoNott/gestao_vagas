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
    @Schema(example = "Desenvolvedor Java Full Stack")
    private String description;
    @Schema(example = "username123")
    private String username;
    @Schema(example = "email@email.com")
    private String email;
    private UUID id;
    @Schema(example = "Usuário de Teste")
    private String name;
}
