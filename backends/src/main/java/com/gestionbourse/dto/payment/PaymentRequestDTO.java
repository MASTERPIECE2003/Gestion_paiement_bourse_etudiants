package com.gestionbourse.dto.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PaymentRequestDTO(
        @NotBlank(message = "matricule étudiant obligatoire")
        String matricule,
        @NotNull(message = "date obligatoire")
        LocalDate date,
        @Min(value = 1, message = "nombre de mois doit être au moins 1")
        int nbrMois,
        String annee_univ
) {}