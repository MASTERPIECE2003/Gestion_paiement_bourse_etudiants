package com.gestionbourse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpaye")
    @JsonProperty("idpaye")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "matricule", nullable = false)
    @JsonProperty("etudiant")
    private Student student;

    @Column(name = "annee_univ")
    @JsonProperty("annee_univ")
    private String academicYear;

    private LocalDate date;

    @Column(name = "nbrMois")
    @JsonProperty("nbrMois")
    private int numberOfMonths;
}

