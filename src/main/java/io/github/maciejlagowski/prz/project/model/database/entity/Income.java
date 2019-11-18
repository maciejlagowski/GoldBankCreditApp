package io.github.maciejlagowski.prz.project.model.database.entity;

import io.github.maciejlagowski.prz.project.model.enums.Industry;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String companyName;
    private Industry industry;
}
