package com.gestionbourse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricule", unique = true, updatable = false, nullable = false)
    @JsonProperty("matricule")
    private String registrationNumber;

    @Column(name = "nom")
    @JsonProperty("nom")
    private String name;

    @Column(name = "sexe")
    @JsonProperty("sexe")
    private String gender;

    @Column(name = "datenais")
    @JsonProperty("datenais")
    private Date birthDate;

    private String institution;

    @Column(name = "niveau")
    @JsonProperty("niveau")
    private String level;

    private String mail;

    @Column(name = "annee_univ")
    @JsonProperty("annee_univ")
    private String academicYear;
}

