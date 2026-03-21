package com.gestionbourse.controllers;

import com.gestionbourse.models.Amount;
import com.gestionbourse.service.AmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/${version.path}/montants")
public class AmountController {

    @Autowired
    private AmountService montantService;

    @GetMapping
    public List<Amount> getAllMontants() {
        return montantService.getAllMontants();
    }

    @GetMapping("/{id}")
    public Amount getMontantById(@PathVariable Long id) {
        return montantService.getMontantById(id)
                .orElseThrow(() -> new RuntimeException("Montant non trouvé avec id: " + id));
    }

    @PostMapping
    public Amount saveMontant(@RequestBody Amount montant) {
        return montantService.saveMontant(montant);
    }

    @PutMapping("/{id}")
    public Amount updateMontant(@PathVariable Long id, @RequestBody Amount montant) {
        return montantService.updateMontant(id, montant);
    }

    @DeleteMapping("/{id}")
    public void deleteMontant(@PathVariable Long id) {
        montantService.deleteMontant(id);
    }
}

