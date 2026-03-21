package com.gestionbourse.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestionbourse.models.Student;
import com.gestionbourse.models.Payment;
import com.gestionbourse.repository.StudentRepository;
import com.gestionbourse.service.PaymentService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/${version.path}/payers")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .orElseThrow(() -> new RuntimeException("Payer non trouvé avec id: " + id));
    }

    @PostMapping
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    @GetMapping("/retardataires/{start}/{end}")
    public List<Payment> getLatePaymentsForMonth(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        return paymentService.getLatePaymentsForMonth(start, end);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }

    @GetMapping("/recu/{matricule}")
    public ResponseEntity<byte[]> generateReceipt(@PathVariable String matricule) {
        Student student = studentRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec matricule: " + matricule));
        double totalAmount = paymentService.calculateTotalAmount(matricule);
        List<Payment> payments = paymentService.getPaymentsByStudentRegistrationNumber(matricule);
        byte[] pdfBytes = generatePdf(student, payments, totalAmount);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "recu_paiement.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            paymentService.sendEmail(emailRequest.getRecipientEmail(), "Notification de retard de paiement", "Cher étudiant,\n\nVous avez un retard de paiement. Veuillez régulariser votre situation dans les plus brefs délais.\n\nMerci.");
            return ResponseEntity.ok("Email envoyé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    static class EmailRequest {
        private String matricule;
        private String recipientEmail;

        // Getters et setters
        @JsonProperty("matricule")
        public String getRegistrationNumber() {
            return matricule;
        }

        @JsonProperty("matricule")
        public void setRegistrationNumber(String matricule) {
            this.matricule = matricule;
        }

        public String getRecipientEmail() {
            return recipientEmail;
        }

        public void setRecipientEmail(String recipientEmail) {
            this.recipientEmail = recipientEmail;
        }
    }

    private byte[] generatePdf(Student student, List<Payment> payments, double totalAmount) {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Aujourd'hui le " + LocalDate.now().format(dateFormatter)));
            document.add(new Paragraph("Matricule : " + student.getRegistrationNumber()));
            document.add(new Paragraph("Nom: " + student.getName()));

            LocalDate dateNais = student.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            document.add(new Paragraph("Née le : " + dateNais.format(dateFormatter)));

            document.add(new Paragraph("Sexe: " + student.getGender()));
            document.add(new Paragraph("Institution : " + student.getInstitution() + " / Niveau : " + student.getLevel()));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table header
            PdfPCell cell1 = new PdfPCell(new Paragraph("Mois"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Montant"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2);

            // Add fixed equipment row
            PdfPCell cellEquipement = new PdfPCell(new Paragraph("Equipement"));
            cellEquipement.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellEquipement);

            PdfPCell cellEquipementAmount = new PdfPCell(new Paragraph("66,000"));
            cellEquipementAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellEquipementAmount);

            // Add payment rows
            for (Payment payment : payments) {
                PdfPCell cellMonth = new PdfPCell(new Paragraph(payment.getDate().getMonth() + ""));
                cellMonth.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellMonth);

                PdfPCell cellAmount = new PdfPCell(new Paragraph(payment.getNumberOfMonths() + ""));
                cellAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellAmount);
            }

            // Calculate the grand total
            double grandTotal = totalAmount + 66000;

            // Add total row
            PdfPCell cellTotalLabel = new PdfPCell(new Paragraph("Total"));
            cellTotalLabel.setColspan(1);
            cellTotalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cellTotalLabel);

            PdfPCell cellTotalAmount = new PdfPCell(new Paragraph(String.valueOf(grandTotal) + " Ariary"));
            cellTotalAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellTotalAmount);

            document.add(table);

            // Add total paid amount at the bottom
            document.add(new Paragraph("Total Payé : " + grandTotal + " Ariary"));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

}

