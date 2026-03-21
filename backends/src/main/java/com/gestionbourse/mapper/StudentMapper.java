package com.gestionbourse.mapper;

import com.gestionbourse.dto.student.StudentRequestDTO;
import com.gestionbourse.dto.student.StudentResponseDTO;
import com.gestionbourse.models.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setRegistrationNumber(dto.getMatricule());
        student.setName(dto.getNom());
        student.setGender(dto.getSexe());
        student.setBirthDate(dto.getDatenais());
        student.setInstitution(dto.getInstitution());
        student.setLevel(dto.getNiveau());
        student.setMail(dto.getMail());
        student.setAcademicYear(dto.getAnnee_univ());
        return student;
    }

    public StudentResponseDTO toDTO(Student student) {
        return new StudentResponseDTO(
                student.getRegistrationNumber(),
                student.getName(),
                student.getGender(),
                student.getBirthDate(),
                student.getInstitution(),
                student.getLevel(),
                student.getMail(),
                student.getAcademicYear()
        );
    }
}