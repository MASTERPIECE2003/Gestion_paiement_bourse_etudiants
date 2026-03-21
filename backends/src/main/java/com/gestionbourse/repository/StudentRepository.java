package com.gestionbourse.repository;

import com.gestionbourse.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s WHERE s.registrationNumber = :registrationNumber")
    Optional<Student> findByRegistrationNumber(@Param("registrationNumber") String registrationNumber);
    @Query("SELECT s FROM Student s WHERE s.name LIKE CONCAT('%', :name, '%')")
    List<Student> findByNameContaining(@Param("name") String name);
    @Query("SELECT s FROM Student s WHERE s.level = :level AND s.institution = :institution")
    List<Student> findByLevelAndInstitution(@Param("level") String level, @Param("institution") String institution);

    @Query("SELECT s FROM Student s WHERE s.birthDate > :birthDate")
    List<Student> findByBirthDateAfter(@Param("birthDate") Date birthDate);
}

