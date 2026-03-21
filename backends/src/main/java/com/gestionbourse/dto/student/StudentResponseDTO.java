package com.gestionbourse.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private String matricule;
    private String nom;
    private String sexe;
    private Date datenais;
    private String institution;
    private String niveau;
    private String mail;
    private String annee_univ;
}
