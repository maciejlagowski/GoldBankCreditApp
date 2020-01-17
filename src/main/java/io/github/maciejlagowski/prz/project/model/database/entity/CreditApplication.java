package io.github.maciejlagowski.prz.project.model.database.entity;

import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import io.github.maciejlagowski.prz.project.model.enums.Risk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date applicationDate;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Client> clients;
    private Risk risk;
    @OneToOne
    private Credit credit;
    private CreditType type;
}
