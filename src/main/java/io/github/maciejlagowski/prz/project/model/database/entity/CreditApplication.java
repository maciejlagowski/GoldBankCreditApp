package io.github.maciejlagowski.prz.project.model.database.entity;

import io.github.maciejlagowski.prz.project.model.enumeration.CreditType;
import io.github.maciejlagowski.prz.project.model.enumeration.Risk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate applicationDate;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Client> clients;
    private Risk risk;
    @OneToOne
    private Credit credit;
    private CreditType type;
}
