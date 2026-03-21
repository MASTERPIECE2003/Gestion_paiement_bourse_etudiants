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
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        if (studentRepository.findByRegistrationNumber(student.getRegistrationNumber()).isPresent()) {
            throw new RuntimeException("Un étudiant avec ce matricule existe déjà : " + student.getRegistrationNumber());
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(String id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec id: " + id));
        existingStudent.setName(student.getName());
        existingStudent.setGender(student.getGender());
        existingStudent.setBirthDate(student.getBirthDate());
        existingStudent.setInstitution(student.getInstitution());
        existingStudent.setLevel(student.getLevel());
        existingStudent.setMail(student.getMail());
        existingStudent.setAcademicYear(student.getAcademicYear());

        return studentRepository.save(existingStudent);
    }

    public Optional<Student> getStudentByRegistrationNumber(String registrationNumber) {
        return studentRepository.findByRegistrationNumber(registrationNumber);
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByNameContaining(name);
    }

    public List<Student> getStudentsByLevelAndInstitution(String level, String institution) {
        return studentRepository.findByLevelAndInstitution(level, institution);
    }

    public List<Student> getMinorStudents() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        Date date = calendar.getTime();
        return studentRepository.findByBirthDateAfter(date);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public List<String> getAllRegistrationNumbers() {
        return studentRepository.findAll().stream()
                .map(Student::getRegistrationNumber)
                .collect(Collectors.toList());
    }
}


