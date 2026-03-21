package com.gestionbourse.dto.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    @NotBlank(message = "matricule étudiant obligatoire")
    private String matricule;

    @NotNull(message = "date obligatoire")
    private LocalDate date;

    @Min(value = 1, message = "nombre de mois doit être au moins 1")
    private int nbrMois;

    private String annee_univ;
}
