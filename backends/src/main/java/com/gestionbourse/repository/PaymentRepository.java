package com.gestionbourse.repository;

import com.gestionbourse.models.Student;
import com.gestionbourse.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByEtudiant(Student etudiant);
    List<Payment> findByDateBeforeAndNbrMoisLessThan(LocalDate date, int nbrMois);
}

