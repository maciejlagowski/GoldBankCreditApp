package io.github.maciejlagowski.prz.project.model.database.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NonNull
    private Client client;
    @NonNull
    private Double balance;
    //TODO history?
}
