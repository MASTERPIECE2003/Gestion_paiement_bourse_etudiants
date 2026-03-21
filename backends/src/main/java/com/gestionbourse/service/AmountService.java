package com.gestionbourse.service;

import com.gestionbourse.models.Amount;
import com.gestionbourse.repository.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmountService {

    @Autowired
    private AmountRepository montantRepository;

    public List<Amount> getAllMontants() {
        return montantRepository.findAll();
    }

    public Optional<Amount> getMontantById(Long id) {
        return montantRepository.findById(id);
    }

    public Amount saveMontant(Amount montant) {
        return montantRepository.save(montant);
    }

    public Amount updateMontant(Long id, Amount montant) {
        Amount existingMontant = montantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Montant non trouvé avec id: " + id));

        existingMontant.setNiveau(montant.getNiveau());
        existingMontant.setMontant(montant.getMontant());

        return montantRepository.save(existingMontant);
    }

    public void deleteMontant(Long id) {
        montantRepository.deleteById(id);
    }
}

