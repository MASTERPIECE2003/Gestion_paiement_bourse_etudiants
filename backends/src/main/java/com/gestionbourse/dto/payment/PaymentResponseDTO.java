package com.gestionbourse.dto.payment;

import com.gestionbourse.dto.student.StudentResponseDTO;
import java.time.LocalDate;

public record PaymentResponseDTO(
        Long idpaye,
        StudentResponseDTO etudiant,
        LocalDate date,
        int nbrMois,
        String annee_univ
) {}