package com.gestionbourse.service;

import com.gestionbourse.models.Amount;
import com.gestionbourse.repository.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmountService {

    @Autowired
    private AmountRepository amountRepository;

    public List<Amount> getAllAmounts() {
        return amountRepository.findAll();
    }

    public Optional<Amount> getAmountById(Long id) {
        return amountRepository.findById(id);
    }

    public Amount saveAmount(Amount amount) {
        return amountRepository.save(amount);
    }

    public Amount updateAmount(Long id, Amount amount) {
        Amount existingAmount = amountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Montant non trouvé avec id: " + id));

        existingAmount.setLevel(amount.getLevel());
        existingAmount.setAmount(amount.getAmount());

        return amountRepository.save(existingAmount);
    }

    public void deleteAmount(Long id) {
        amountRepository.deleteById(id);
    }
}

