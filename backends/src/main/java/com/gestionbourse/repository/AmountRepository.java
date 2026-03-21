package com.gestionbourse.repository;

import com.gestionbourse.models.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmountRepository extends JpaRepository<Amount, Long> {
    @Query("SELECT a FROM Amount a WHERE a.level = :level")
    List<Amount> findByLevel(@Param("level") String level);
}

