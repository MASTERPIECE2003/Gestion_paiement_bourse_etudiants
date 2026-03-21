package com.gestionbourse.dto.amount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountResponseDTO {
    private Long idniv;
    private String niveau;
    private int montant;
}
