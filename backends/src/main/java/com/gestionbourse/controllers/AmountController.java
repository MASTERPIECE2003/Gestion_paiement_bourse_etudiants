package com.gestionbourse.controllers;

import com.gestionbourse.models.Amount;
import com.gestionbourse.service.AmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/${version.path}/montants")
public class AmountController {

    @Autowired
    private AmountService amountService;

    @GetMapping
    public List<Amount> getAllAmounts() {
        return amountService.getAllAmounts();
    }

    @GetMapping("/{id}")
    public Amount getAmountById(@PathVariable Long id) {
        return amountService.getAmountById(id)
                .orElseThrow(() -> new RuntimeException("Montant non trouvé avec id: " + id));
    }

    @PostMapping
    public Amount saveAmount(@RequestBody Amount amount) {
        return amountService.saveAmount(amount);
    }

    @PutMapping("/{id}")
    public Amount updateAmount(@PathVariable Long id, @RequestBody Amount amount) {
        return amountService.updateAmount(id, amount);
    }

    @DeleteMapping("/{id}")
    public void deleteAmount(@PathVariable Long id) {
        amountService.deleteAmount(id);
    }
}

