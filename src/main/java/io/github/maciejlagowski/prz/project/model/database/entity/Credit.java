package io.github.maciejlagowski.prz.project.model.database.entity;

import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Account account;
    private Double amountTaken;
    private Double amountRepaid;
    private Double markup;
    private Date takeDate;
    private Date plannedRepaymentDate;
    private Date actualRepaymentDate;
    private Boolean isPaidOff;
    private CreditType type;
}
