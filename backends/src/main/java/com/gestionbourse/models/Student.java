package com.gestionbourse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricule", unique = true, updatable = false, nullable = false)
    private String matricule;
    private String nom;
    private String sexe;
    private Date datenais;
    private String institution;
    private String niveau;
    private String mail;
    private String annee_univ;

    // Getters and Setters
    @JsonProperty("matricule")
    public String getRegistrationNumber() {
        return matricule;
    }

    @JsonProperty("matricule")
    public void setRegistrationNumber(String matricule) {
        this.matricule = matricule;
    }

    @JsonProperty("nom")
    public String getName() {
        return nom;
    }

    @JsonProperty("nom")
    public void setName(String nom) {
        this.nom = nom;
    }

    @JsonProperty("sexe")
    public String getGender() {
        return sexe;
    }

    @JsonProperty("sexe")
    public void setGender(String sexe) {
        this.sexe = sexe;
    }

    @JsonProperty("datenais")
    public Date getBirthDate() {
        return datenais;
    }

    @JsonProperty("datenais")
    public void setBirthDate(Date datenais) {
        this.datenais = datenais;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @JsonProperty("niveau")
    public String getLevel() {
        return niveau;
    }

    @JsonProperty("niveau")
    public void setLevel(String niveau) {
        this.niveau = niveau;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @JsonProperty("annee_univ")
    public String getAcademicYear() {
        return annee_univ;
    }

    @JsonProperty("annee_univ")
    public void setAcademicYear(String annee_univ) {
        this.annee_univ = annee_univ;
    }
}

