package com.gestionbourse.dto.student;

import java.util.Date;

public record StudentResponseDTO(
        String matricule,
        String nom,
        String sexe,
        Date datenais,
        String institution,
        String niveau,
        String mail,
        String annee_univ
) {}