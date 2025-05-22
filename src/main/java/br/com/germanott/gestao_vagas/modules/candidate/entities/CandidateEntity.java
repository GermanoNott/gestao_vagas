package br.com.germanott.gestao_vagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // Getters and Setters for all fields
// Lombok will generate the getters, setters, equals, hashCode, and toString
// methods
// for this class at compile time, reducing boilerplate code.
@Entity(name = "candidate") // Table name in the database
public class CandidateEntity {

    @Id // Primary key
        // The @Id annotation indicates that this field is the primary key of the
        // entity.
    @GeneratedValue(strategy = GenerationType.UUID) // Auto-generate UUID
    private UUID id;

    @Schema(example = "Maria da Silva", requiredMode = RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @NotBlank // Ensure username is not blank
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$", message = "Username must be at least 3 characters long and can only contain letters, numbers, dots, underscores, and hyphens")
    @Schema(example = "maria", requiredMode = RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;

    @Email(message = "Invalid email format")
    @Schema(example = "maria@email.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do candidato")
    private String email;

    @Length(min = 10, max = 200, message = "Password must be between 10 and 200 characters")
    @Schema(example = "Password@123", minLength = 10, maxLength = 200, requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;
    @Schema(example = "Desenvolvedor Java Junior", requiredMode = RequiredMode.REQUIRED, description = "Breve descrição do candidato")
    private String description;
    private String curriculum;

    @CreationTimestamp // Automatically set the creation timestamp
    private LocalDateTime createdAt;

}
