package io.github.maciejlagowski.prz.project.model.bik;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter(AccessLevel.PACKAGE)
@ToString
public class BikLiability {

    private Long id;
    private Double amount;
    private Double amountRepaid;
    private Date takeDate;
    private Boolean isPaidOff;
    private Double installment;
    private Boolean isDefault;

    BikLiability() {
    }
}
