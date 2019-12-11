package io.github.maciejlagowski.prz.project.model.enums;

import java.util.List;

public enum Risk {
    LOW(1), MEDIUM(2), HIGH(3), DEFAULT(4);

    private final int risk;

    Risk(int risk) {
        this.risk = risk;
    }

    public static Risk getRisk(int risk) {
        switch (risk) {
            case 1:
                return LOW;
            case 2:
                return MEDIUM;
            case 3:
                return HIGH;
            case 4:
                return DEFAULT;
        }
        throw new IllegalArgumentException("Risk number is not in enum");
    }

    public static Risk calculateAverageRisk(List<Risk> riskList) {
        double avg = 0.0;
        for (Risk risk : riskList) {
            if (risk.equals(DEFAULT)) {
                return DEFAULT;
            }
            avg += risk.getNumericRisk();
        }
        avg /= riskList.size();
        return getRisk((int) Math.round(avg));
    }

    public static Risk getBestRisk(List<Risk> riskList) {
        Risk risk = HIGH;
        for (Risk riskFromList : riskList) {
            if (riskFromList.equals(DEFAULT)) {
                return DEFAULT;
            }
            if (riskFromList.risk < risk.risk) {
                risk = riskFromList;
            }
        }
        return risk;
    }

    public int getNumericRisk() {
        return this.risk;
    }
}
