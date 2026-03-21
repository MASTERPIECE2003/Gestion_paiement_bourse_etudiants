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
    private PaymentRepository payerRepository;

    @Autowired
    private StudentRepository etudiantRepository;

    @Autowired
    private AmountRepository montantRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    public List<Payment> getAllPayers() {
        return payerRepository.findAll();
    }

    public Optional<Payment> getPayerById(Long id) {
        return payerRepository.findById(id);
    }

    public Payment savePayer(Payment payer) {
        if (payer.getEtudiant() == null || payer.getEtudiant().getMatricule() == null) {
            throw new RuntimeException("L'étudiant ou le matricule ne peut pas être null");
        }
        Student etudiant = etudiantRepository.findById(payer.getEtudiant().getMatricule())
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec matricule: " + payer.getEtudiant().getMatricule()));
        payer.setEtudiant(etudiant);
        return payerRepository.save(payer);
    }

    public Payment updatePayer(Long id, Payment payer) {
        Payment existingPayer = payerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payer non trouvé avec id: " + id));

        existingPayer.setEtudiant(payer.getEtudiant());
        existingPayer.setAnnee_univ(payer.getAnnee_univ());
        existingPayer.setDate(payer.getDate());
        existingPayer.setNbrMois(payer.getNbrMois());

        return payerRepository.save(existingPayer);
    }

    public List<Payment> getRetardatairesPourUnMois(LocalDate start, LocalDate end) {
        return payerRepository.findByDateBeforeAndNbrMoisLessThan(end, 1);
    }

    public void deletePayer(Long id) {
        payerRepository.deleteById(id);
    }

    public List<Payment> getPaymentsByEtudiantMatricule(String matricule) {
        Student etudiant = etudiantRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec matricule: " + matricule));
        return payerRepository.findByEtudiant(etudiant);
    }

    public double calculateTotalAmount(String matricule) {
        Student etudiant = etudiantRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec matricule: " + matricule));
        String niveau = etudiant.getNiveau();

        List<Amount> montants = montantRepository.findByNiveau(niveau);
        if (montants.isEmpty()) {
            throw new RuntimeException("Montant non trouvé pour niveau: " + niveau);
        }

        Amount montant = montants.get(0);

        List<Payment> payments = payerRepository.findByEtudiant(etudiant);
        return payments.stream().mapToDouble(payment -> payment.getNbrMois() * montant.getMontant()).sum();
    }

    public List<Payment> findLatePayments() {
        LocalDate threeWeeksAgo = LocalDate.now().minusWeeks(3);
        return payerRepository.findByDateBeforeAndNbrMoisLessThan(threeWeeksAgo, 1);
    }

    // Méthode pour envoyer des notifications par email
    public void sendLatePaymentNotifications() {
        List<Payment> latePayments = findLatePayments();
        for (Payment payer : latePayments) {
            Student etudiant = payer.getEtudiant();
            if (etudiant.getMail() != null) {
                sendEmail(etudiant.getMail(), "Notification de retard de paiement", "Cher " + etudiant.getNom() + ",\n\nVous avez un retard de paiement. Veuillez régulariser votre situation dans les plus brefs délais.\n\nMerci.");
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

