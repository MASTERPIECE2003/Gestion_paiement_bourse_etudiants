package com.gestionbourse.controllers;

import com.gestionbourse.models.Montant;
import com.gestionbourse.service.MontantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/montants")
public class MontantController {

    @Autowired
    private MontantService montantService;

    @GetMapping
    public List<Montant> getAllMontants() {
        return montantService.getAllMontants();
    }

    @GetMapping("/{id}")
    public Montant getMontantById(@PathVariable Long id) {
        return montantService.getMontantById(id)
                .orElseThrow(() -> new RuntimeException("Montant non trouvé avec id: " + id));
    }

    @PostMapping
    public Montant saveMontant(@RequestBody Montant montant) {
        return montantService.saveMontant(montant);
    }

    @PutMapping("/{id}")
    public Montant updateMontant(@PathVariable Long id, @RequestBody Montant montant) {
        return montantService.updateMontant(id, montant);
    }

    @DeleteMapping("/{id}")
    public void deleteMontant(@PathVariable Long id) {
        montantService.deleteMontant(id);
    }
}
