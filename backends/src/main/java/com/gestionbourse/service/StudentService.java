package com.gestionbourse.service;

import com.gestionbourse.models.Student;
import com.gestionbourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository etudiantRepository;

    public List<Student> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Student> getEtudiantById(String id) {
        return etudiantRepository.findById(id);
    }

    public Student saveEtudiant(Student etudiant) {
        if (etudiantRepository.findByMatricule(etudiant.getMatricule()).isPresent()) {
            throw new RuntimeException("Un étudiant avec ce matricule existe déjà : " + etudiant.getMatricule());
        }
        return etudiantRepository.save(etudiant);
    }

    public Student updateEtudiant(String id, Student etudiant) {
        Student existingEtudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec id: " + id));
        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setSexe(etudiant.getSexe());
        existingEtudiant.setDatenais(etudiant.getDatenais());
        existingEtudiant.setInstitution(etudiant.getInstitution());
        existingEtudiant.setNiveau(etudiant.getNiveau());
        existingEtudiant.setMail(etudiant.getMail());
        existingEtudiant.setAnnee_univ(etudiant.getAnnee_univ());

        return etudiantRepository.save(existingEtudiant);
    }

    public Optional<Student> getEtudiantByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    public List<Student> getEtudiantByNom(String nom) {
        return etudiantRepository.findByNomContaining(nom);
    }

    public List<Student> getEtudiantsByNiveauAndInstitution(String niveau, String institution) {
        return etudiantRepository.findByNiveauAndInstitution(niveau, institution);
    }

    public List<Student> getEtudiantsMineurs() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        Date date = calendar.getTime();
        return etudiantRepository.findByDatenaisAfter(date);
    }

    public void deleteEtudiant(String id) {
        etudiantRepository.deleteById(id);
    }

    public List<String> getAllMatricules() {
        return etudiantRepository.findAll().stream()
                .map(Student::getMatricule)
                .collect(Collectors.toList());
    }
}


