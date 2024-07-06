package com.gestionbourse.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Payer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpaye;

    @ManyToOne
    @JoinColumn(name = "matricule", nullable = false)
    private Etudiant etudiant;

    private String annee_univ;
    private LocalDate date;
    private int nbrMois;

    // Getters and Setters
    public Long getIdpaye() {
        return idpaye;
    }

    public void setIdpaye(Long idpaye) {
        this.idpaye = idpaye;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getAnnee_univ() {
        return annee_univ;
    }

    public void setAnnee_univ(String annee_univ) {
        this.annee_univ = annee_univ;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNbrMois() {
        return nbrMois;
    }

    public void setNbrMois(int nbrMois) {
        this.nbrMois = nbrMois;
    }
}
