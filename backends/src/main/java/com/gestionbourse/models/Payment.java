package com.gestionbourse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpaye;

    @ManyToOne
    @JoinColumn(name = "matricule", nullable = false)
    private Student etudiant;

    private String annee_univ;
    private LocalDate date;
    private int nbrMois;

    // Getters and Setters
    @JsonProperty("idpaye")
    public Long getPaymentId() {
        return idpaye;
    }

    @JsonProperty("idpaye")
    public void setPaymentId(Long idpaye) {
        this.idpaye = idpaye;
    }

    @JsonProperty("etudiant")
    public Student getStudent() {
        return etudiant;
    }

    @JsonProperty("etudiant")
    public void setStudent(Student etudiant) {
        this.etudiant = etudiant;
    }

    @JsonProperty("annee_univ")
    public String getAcademicYear() {
        return annee_univ;
    }

    @JsonProperty("annee_univ")
    public void setAcademicYear(String annee_univ) {
        this.annee_univ = annee_univ;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonProperty("nbrMois")
    public int getNumberOfMonths() {
        return nbrMois;
    }

    @JsonProperty("nbrMois")
    public void setNumberOfMonths(int nbrMois) {
        this.nbrMois = nbrMois;
    }
}

