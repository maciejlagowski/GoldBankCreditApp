package io.github.maciejlagowski.prz.project.model.bik;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter(AccessLevel.PACKAGE)
public class BikReport {
    private Long pesel;
    private List<BikLiability> liabilities = new LinkedList<>();

    BikReport() {
    }
}
