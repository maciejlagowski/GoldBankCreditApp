package io.github.maciejlagowski.prz.project.model.bik;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikLiability {

    private Long id;
    private Double amount;
    private Double amountRepaid;
    private Date takeDate;
    private Boolean isPaidOff;
    private Double installment;
    private Boolean isDefault;
}
