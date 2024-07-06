package com.gestionbourse.controllers;

import com.gestionbourse.models.Etudiant;
import com.gestionbourse.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Remplacez par votre URL frontend
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }
    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Etudiant> getEtudiantByMatricule(@PathVariable String matricule) {
        Optional<Etudiant> etudiantOptional = etudiantService.getEtudiantByMatricule(matricule);
        if (etudiantOptional.isPresent()) {
            return ResponseEntity.ok(etudiantOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public Etudiant saveEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.saveEtudiant(etudiant);
    }

    @PutMapping("/{id}")
    public Etudiant updateEtudiant(@PathVariable String id, @RequestBody Etudiant etudiant) {
        return etudiantService.updateEtudiant(id, etudiant);
    }

    @GetMapping("/nom/{nom}")
    public List<Etudiant> getEtudiantByNom(@PathVariable String nom) {
        return etudiantService.getEtudiantByNom(nom);
    }

    @GetMapping("/niveau/{niveau}/institution/{institution}")
    public List<Etudiant> getEtudiantsByNiveauAndInstitution(@PathVariable String niveau, @PathVariable String institution) {
        return etudiantService.getEtudiantsByNiveauAndInstitution(niveau, institution);
    }

    @GetMapping("/mineurs")
    public List<Etudiant> getEtudiantsMineurs() {
        return etudiantService.getEtudiantsMineurs();
    }

    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable String id) {
        etudiantService.deleteEtudiant(id);
    }

    @GetMapping("/matricules")
    public List<String> getAllMatricules() {
        return etudiantService.getAllMatricules();
    }
}
