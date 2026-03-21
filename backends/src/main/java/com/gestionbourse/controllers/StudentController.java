package com.gestionbourse.controllers;

import com.gestionbourse.models.Student;
import com.gestionbourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/${version.path}/etudiants")
public class StudentController {

    @Autowired
    private StudentService etudiantService;

    @GetMapping
    public List<Student> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }
    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Student> getEtudiantByMatricule(@PathVariable String matricule) {
        Optional<Student> etudiantOptional = etudiantService.getEtudiantByMatricule(matricule);
        if (etudiantOptional.isPresent()) {
            return ResponseEntity.ok(etudiantOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public Student saveEtudiant(@RequestBody Student etudiant) {
        return etudiantService.saveEtudiant(etudiant);
    }

    @PutMapping("/{id}")
    public Student updateEtudiant(@PathVariable String id, @RequestBody Student etudiant) {
        return etudiantService.updateEtudiant(id, etudiant);
    }

    @GetMapping("/nom/{nom}")
    public List<Student> getEtudiantByNom(@PathVariable String nom) {
        return etudiantService.getEtudiantByNom(nom);
    }

    @GetMapping("/niveau/{niveau}/institution/{institution}")
    public List<Student> getEtudiantsByNiveauAndInstitution(@PathVariable String niveau, @PathVariable String institution) {
        return etudiantService.getEtudiantsByNiveauAndInstitution(niveau, institution);
    }

    @GetMapping("/mineurs")
    public List<Student> getEtudiantsMineurs() {
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

