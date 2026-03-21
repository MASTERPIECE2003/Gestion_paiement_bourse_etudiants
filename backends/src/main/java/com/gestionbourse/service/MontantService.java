package com.gestionbourse.service;

import com.gestionbourse.models.Montant;
import com.gestionbourse.repository.MontantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MontantService {

    @Autowired
    private MontantRepository montantRepository;

    public List<Montant> getAllMontants() {
        return montantRepository.findAll();
    }

    public Optional<Montant> getMontantById(Long id) {
        return montantRepository.findById(id);
    }

    public Montant saveMontant(Montant montant) {
        return montantRepository.save(montant);
    }

    public Montant updateMontant(Long id, Montant montant) {
        Montant existingMontant = montantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Montant non trouvé avec id: " + id));

        existingMontant.setNiveau(montant.getNiveau());
        existingMontant.setMontant(montant.getMontant());

        return montantRepository.save(existingMontant);
    }

    public void deleteMontant(Long id) {
        montantRepository.deleteById(id);
    }
}
