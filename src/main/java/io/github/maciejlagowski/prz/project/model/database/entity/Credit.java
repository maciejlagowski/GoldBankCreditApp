package io.github.maciejlagowski.prz.project.model.database.entity;

import io.github.maciejlagowski.prz.project.model.enumeration.CreditType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amountTaken;
    private Double amountRepaid;
    private Double fullCreditCost;
    private LocalDate takeDate;
    private LocalDate plannedRepaymentDate;
    private LocalDate actualRepaymentDate;
    private Boolean isPaidOff;
    private CreditType type;
    private Double installment;
    private Boolean isDefault;
}
