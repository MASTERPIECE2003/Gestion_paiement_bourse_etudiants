package com.gestionbourse.service;

import com.gestionbourse.models.Etudiant;
import com.gestionbourse.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(String id) {
        return etudiantRepository.findById(id);
    }

    public Etudiant saveEtudiant(Etudiant etudiant) {
        if (etudiantRepository.findByMatricule(etudiant.getMatricule()).isPresent()) {
            throw new RuntimeException("Un étudiant avec ce matricule existe déjà : " + etudiant.getMatricule());
        }
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(String id, Etudiant etudiant) {
        Etudiant existingEtudiant = etudiantRepository.findById(id)
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

    public Optional<Etudiant> getEtudiantByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    public List<Etudiant> getEtudiantByNom(String nom) {
        return etudiantRepository.findByNomContaining(nom);
    }

    public List<Etudiant> getEtudiantsByNiveauAndInstitution(String niveau, String institution) {
        return etudiantRepository.findByNiveauAndInstitution(niveau, institution);
    }

    public List<Etudiant> getEtudiantsMineurs() {
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
                .map(Etudiant::getMatricule)
                .collect(Collectors.toList());
    }
}

