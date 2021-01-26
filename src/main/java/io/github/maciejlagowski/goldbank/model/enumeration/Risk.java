package io.github.maciejlagowski.goldbank.model.enumeration;

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

    public int getNumericRisk() {
        return this.risk;
    }
}
