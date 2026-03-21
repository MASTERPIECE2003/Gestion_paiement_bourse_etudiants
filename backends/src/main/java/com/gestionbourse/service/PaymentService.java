package com.gestionbourse.service;

import com.gestionbourse.models.Student;
import com.gestionbourse.models.Payment;
import com.gestionbourse.models.Amount;
import com.gestionbourse.repository.PaymentRepository;
import com.gestionbourse.repository.StudentRepository;
import com.gestionbourse.repository.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AmountRepository amountRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment savePayment(Payment payment) {
        if (payment.getStudent() == null || payment.getStudent().getRegistrationNumber() == null) {
            throw new RuntimeException("L'étudiant ou le matricule ne peut pas être null");
        }
        Student student = studentRepository.findById(payment.getStudent().getRegistrationNumber())
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec matricule: " + payment.getStudent().getRegistrationNumber()));
        payment.setStudent(student);
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment payment) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payer non trouvé avec id: " + id));

        existingPayment.setStudent(payment.getStudent());
        existingPayment.setAcademicYear(payment.getAcademicYear());
        existingPayment.setDate(payment.getDate());
        existingPayment.setNumberOfMonths(payment.getNumberOfMonths());

        return paymentRepository.save(existingPayment);
    }

    public List<Payment> getLatePaymentsForMonth(LocalDate start, LocalDate end) {
        return paymentRepository.findByDateBeforeAndNumberOfMonthsLessThan(end, 1);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> getPaymentsByStudentRegistrationNumber(String registrationNumber) {
        Student student = studentRepository.findById(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec matricule: " + registrationNumber));
        return paymentRepository.findByStudent(student);
    }

    public double calculateTotalAmount(String matricule) {
        Student student = studentRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec matricule: " + matricule));
        String level = student.getLevel();

        List<Amount> amounts = amountRepository.findByLevel(level);
        if (amounts.isEmpty()) {
            throw new RuntimeException("Montant non trouvé pour niveau: " + level);
        }

        Amount amount = amounts.get(0);

        List<Payment> payments = paymentRepository.findByStudent(student);
        return payments.stream().mapToDouble(payment -> payment.getNumberOfMonths() * amount.getAmount()).sum();
    }

    public List<Payment> findLatePayments() {
        LocalDate threeWeeksAgo = LocalDate.now().minusWeeks(3);
        return paymentRepository.findByDateBeforeAndNumberOfMonthsLessThan(threeWeeksAgo, 1);
    }

    // Méthode pour envoyer des notifications par email
    public void sendLatePaymentNotifications() {
        List<Payment> latePayments = findLatePayments();
        for (Payment payment : latePayments) {
            Student student = payment.getStudent();
            if (student.getMail() != null) {
                sendEmail(student.getMail(), "Notification de retard de paiement", "Cher " + student.getName() + ",\n\nVous avez un retard de paiement. Veuillez régulariser votre situation dans les plus brefs délais.\n\nMerci.");
            }
        }
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void scheduledLatePaymentNotifications() {
        sendLatePaymentNotifications();
    }
}

