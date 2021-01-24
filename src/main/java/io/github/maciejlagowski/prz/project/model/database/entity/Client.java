package io.github.maciejlagowski.prz.project.model.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String forename;
    @NonNull
    private String surname;
    @NonNull
    private String address;
    @NonNull
    private Long pesel;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Income> incomes;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Credit> credits;
}
