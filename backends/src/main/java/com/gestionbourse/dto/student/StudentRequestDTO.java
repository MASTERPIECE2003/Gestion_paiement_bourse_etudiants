package com.gestionbourse.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    @NotBlank(message = "matricule obligatoire")
    private String matricule;

    @NotBlank(message = "nom obligatoire")
    private String nom;

    @NotBlank(message = "sexe obligatoire")
    private String sexe;

    @NotNull(message = "datenais obligatoire")
    private Date datenais;

    @NotBlank(message = "institution obligatoire")
    private String institution;

    @NotBlank(message = "niveau obligatoire")
    private String niveau;

    @Email(message = "format email invalide")
    private String mail;

    private String annee_univ;
}
