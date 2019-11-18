package io.github.maciejlagowski.prz.project.model.database.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Account {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne
    @Column(name="client")
    private Long client;    //TODO Client
    @Column(name="balance")
    private Double balance;
    //TODO history?
}
