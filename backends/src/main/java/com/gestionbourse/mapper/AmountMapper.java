package com.gestionbourse.mapper;

import com.gestionbourse.dto.amount.AmountRequestDTO;
import com.gestionbourse.dto.amount.AmountResponseDTO;
import com.gestionbourse.models.Amount;
import org.springframework.stereotype.Component;

@Component
public class AmountMapper {

    public Amount toEntity(AmountRequestDTO dto) {
        Amount amount = new Amount();
        amount.setLevel(dto.getNiveau());
        amount.setAmount(dto.getMontant());
        return amount;
    }

    public AmountResponseDTO toDTO(Amount amount) {
        return new AmountResponseDTO(
                amount.getLevelId(),
                amount.getLevel(),
                amount.getAmount()
        );
    }
}