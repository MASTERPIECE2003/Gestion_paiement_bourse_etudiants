package com.gestionbourse.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record StudentRequestDTO(
        @NotBlank(message = "matricule obligatoire")
        String matricule,
        @NotBlank(message = "nom obligatoire")
        String nom,
        @NotBlank(message = "sexe obligatoire")
        String sexe,
        @NotNull(message = "datenais obligatoire")
        Date datenais,
        @NotBlank(message = "institution obligatoire")
        String institution,
        @NotBlank(message = "niveau obligatoire")
        String niveau,
        @Email(message = "format email invalide")
        String mail,
        String annee_univ
) {}