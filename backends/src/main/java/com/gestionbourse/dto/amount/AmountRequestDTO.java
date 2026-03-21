package com.gestionbourse.dto.amount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountRequestDTO {
    @NotBlank(message = "niveau obligatoire")
    private String niveau;

    @Min(value = 0, message = "montant doit être positif")
    private int montant;
}
