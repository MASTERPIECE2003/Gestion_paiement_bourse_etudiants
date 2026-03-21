package com.gestionbourse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Montant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisation de l'auto-incrémentation pour idniv
    private Long idniv; // Utilisation de Long pour l'auto-incrémentation en PostgreSQL
    private String niveau;
    private int montant;

    // Getters and Setters
    public Long getIdniv() {
        return idniv;
    }

    public void setIdniv(Long idniv) {
        this.idniv = idniv;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
}
