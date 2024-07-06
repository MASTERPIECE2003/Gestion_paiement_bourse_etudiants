package com.gestionbourse.repository;

import com.gestionbourse.models.Etudiant;
import com.gestionbourse.models.Payer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PayerRepository extends JpaRepository<Payer, Long> {
    List<Payer> findByEtudiant(Etudiant etudiant);
    List<Payer> findByDateBeforeAndNbrMoisLessThan(LocalDate date, int nbrMois);
}
