package com.gestionbourse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Amount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisation de l'auto-incrémentation pour idniv
    private Long idniv; // Utilisation de Long pour l'auto-incrémentation en PostgreSQL
    private String niveau;
    private int montant;

    // Getters and Setters
    @JsonProperty("idniv")
    public Long getLevelId() {
        return idniv;
    }

    @JsonProperty("idniv")
    public void setLevelId(Long idniv) {
        this.idniv = idniv;
    }

    @JsonProperty("niveau")
    public String getLevel() {
        return niveau;
    }

    @JsonProperty("niveau")
    public void setLevel(String niveau) {
        this.niveau = niveau;
    }

    @JsonProperty("montant")
    public int getAmount() {
        return montant;
    }

    @JsonProperty("montant")
    public void setAmount(int montant) {
        this.montant = montant;
    }
}

