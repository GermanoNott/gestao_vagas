package br.com.germanott.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.germanott.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.germanott.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.germanott.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class AuthCandidateUseCase {
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Candidate not found");
                });

        var passwordMatches = this.passwordEncoder
                .matches(authCandidateDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        // Create the token
        var token = JWT.create()
                .withIssuer(secretKey)
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);
        // Return the token
        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCandidateResponse;
    }

}
