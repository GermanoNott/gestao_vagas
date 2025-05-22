package br.com.germanott.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.germanott.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.germanott.gestao_vagas.modules.company.entities.CompanyEntity;

import br.com.germanott.gestao_vagas.modules.company.usecases.CreateCompanyUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    CompanyController(CreateCompanyUseCase createCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        System.out.println("CompanyController initialized");
        try {
            var result = this.createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
