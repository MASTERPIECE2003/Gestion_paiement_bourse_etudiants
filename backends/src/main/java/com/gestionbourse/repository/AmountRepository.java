package com.gestionbourse.repository;

import com.gestionbourse.models.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmountRepository extends JpaRepository<Amount, Long> {
    List<Amount> findByNiveau(String niveau);
}

