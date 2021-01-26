package io.github.maciejlagowski.goldbank.model.enumeration;

public enum Industry {
    AEROSPACE(Risk.LOW),
    TRANSPORT(Risk.MEDIUM),
    COMPUTER(Risk.LOW),
    TELECOMMUNICATION(Risk.MEDIUM),
    CULTURE(Risk.MEDIUM),
    CONSTRUCTION(Risk.MEDIUM),
    EDUCATION(Risk.MEDIUM),
    PHARMACEUTICAL(Risk.LOW),
    FOOD(Risk.HIGH),
    HEALTH_CARE(Risk.LOW),
    ENTERTAINMENT(Risk.MEDIUM),
    NEWS_MEDIA(Risk.LOW),
    ENERGY(Risk.LOW),
    MANUFACTURING(Risk.MEDIUM),
    MUSIC(Risk.HIGH),
    MINING(Risk.HIGH),
    ELECTRONICS(Risk.LOW),
    POLITICS(Risk.MEDIUM),
    BANKING(Risk.LOW),
    SPORT(Risk.MEDIUM),
    STATE_OWNED(Risk.LOW),
    OLD_AGE_OR_INVALIDITY_PENSION(Risk.HIGH);

    private final Risk risk;

    Industry(Risk risk) {
        this.risk = risk;
    }

    public Risk getIndustryRisk() {
        return this.risk;
    }
}
