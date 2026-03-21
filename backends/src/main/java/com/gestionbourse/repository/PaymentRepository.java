package com.gestionbourse.repository;

import com.gestionbourse.models.Student;
import com.gestionbourse.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.student = :student")
    List<Payment> findByStudent(@Param("student") Student student);

    @Query("SELECT p FROM Payment p WHERE p.date < :date AND p.numberOfMonths < :numberOfMonths")
    List<Payment> findByDateBeforeAndNumberOfMonthsLessThan(
            @Param("date") LocalDate date,
            @Param("numberOfMonths") int numberOfMonths
    );
}

