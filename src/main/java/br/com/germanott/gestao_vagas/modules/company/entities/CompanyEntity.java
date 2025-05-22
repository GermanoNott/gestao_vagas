package br.com.germanott.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "company") // Table name in the database
// The @Entity annotation indicates that this class is a JPA entity and will be
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String cnpj;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$", message = "Username must be at least 3 characters long and can only contain letters, numbers, dots, underscores, and hyphens")
    private String username;
    private String email;

    @Length(min = 10, max = 200, message = "Password must be between 10 and 200 characters")
    private String password;
    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
