package com.gestionbourse.mapper;

import com.gestionbourse.dto.payment.PaymentRequestDTO;
import com.gestionbourse.dto.payment.PaymentResponseDTO;
import com.gestionbourse.models.Payment;
import com.gestionbourse.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    private final StudentMapper studentMapper;

    public Payment toEntity(PaymentRequestDTO dto, Student student) {
        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setDate(dto.getDate());
        payment.setNumberOfMonths(dto.getNbrMois());
        payment.setAcademicYear(dto.getAnnee_univ());
        return payment;
    }

    public PaymentResponseDTO toDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getPaymentId(),
                studentMapper.toDTO(payment.getStudent()),
                payment.getDate(),
                payment.getNumberOfMonths(),
                payment.getAcademicYear()
        );
    }
}