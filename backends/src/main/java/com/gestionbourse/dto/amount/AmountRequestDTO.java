package com.gestionbourse.dto.amount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AmountRequestDTO(
        @NotBlank(message = "niveau obligatoire")
        String niveau,

        @Min(value = 0, message = "montant doit être positif")
        int montant
) {}