package com.gestionbourse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Amount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idniv")
    @JsonProperty("idniv")
    private Long levelId;

    @Column(name = "niveau")
    @JsonProperty("niveau")
    private String level;

    @Column(name = "montant")
    @JsonProperty("montant")
    private int amount;
}

