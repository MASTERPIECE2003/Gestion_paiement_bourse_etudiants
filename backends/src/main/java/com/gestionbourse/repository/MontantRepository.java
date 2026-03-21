package com.gestionbourse.repository;

import com.gestionbourse.models.Montant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MontantRepository extends JpaRepository<Montant, Long> {
    List<Montant> findByNiveau(String niveau);
}
