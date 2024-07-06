package com.gestionbourse.repository;

import com.gestionbourse.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import java.util.Optional;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Optional<Etudiant> findByMatricule(String matricule);
    List<Etudiant> findByNomContaining(String nom);
    List<Etudiant> findByNiveauAndInstitution(String niveau, String institution);
    List<Etudiant> findByDatenaisAfter(Date date);
}
