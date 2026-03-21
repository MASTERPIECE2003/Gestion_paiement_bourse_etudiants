package com.gestionbourse.repository;

import com.gestionbourse.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByMatricule(String matricule);
    List<Student> findByNomContaining(String nom);
    List<Student> findByNiveauAndInstitution(String niveau, String institution);
    List<Student> findByDatenaisAfter(Date date);
}

