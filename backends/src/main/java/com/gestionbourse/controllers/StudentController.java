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
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Student> getStudentByRegistrationNumber(@PathVariable String matricule) {
        Optional<Student> studentOptional = studentService.getStudentByRegistrationNumber(matricule);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @GetMapping("/nom/{nom}")
    public List<Student> getStudentsByName(@PathVariable String nom) {
        return studentService.getStudentsByName(nom);
    }

    @GetMapping("/niveau/{niveau}/institution/{institution}")
    public List<Student> getStudentsByLevelAndInstitution(@PathVariable String niveau, @PathVariable String institution) {
        return studentService.getStudentsByLevelAndInstitution(niveau, institution);
    }

    @GetMapping("/mineurs")
    public List<Student> getMinorStudents() {
        return studentService.getMinorStudents();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/matricules")
    public List<String> getAllRegistrationNumbers() {
        return studentService.getAllRegistrationNumbers();
    }
}

