package com.gestionbourse.dto.payment;

import com.gestionbourse.dto.student.StudentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private Long idpaye;
    private StudentResponseDTO etudiant;
    private LocalDate date;
    private int nbrMois;
    private String annee_univ;
}
