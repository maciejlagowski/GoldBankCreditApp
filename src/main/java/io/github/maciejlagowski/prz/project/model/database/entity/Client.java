package io.github.maciejlagowski.prz.project.model.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String forename;
    private String surname;
    private String address;
    private Long pesel;
    @OneToMany
    private List<Income> incomes;
    @OneToMany
    private List<Credit> credits;
}
