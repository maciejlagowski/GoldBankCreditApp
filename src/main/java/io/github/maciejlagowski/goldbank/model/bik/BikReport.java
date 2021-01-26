package io.github.maciejlagowski.goldbank.model.bik;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BikReport {
    private Long pesel;
    private List<BikLiability> liabilities = new LinkedList<>();
}
