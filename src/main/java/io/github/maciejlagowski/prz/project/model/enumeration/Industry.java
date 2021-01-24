package io.github.maciejlagowski.prz.project.model.enumeration;

import static io.github.maciejlagowski.prz.project.model.enumeration.Risk.*;

public enum Industry {
    AEROSPACE (LOW),
    TRANSPORT (MEDIUM),
    COMPUTER (LOW),
    TELECOMMUNICATION (MEDIUM),
    CULTURE (MEDIUM),
    CONSTRUCTION (MEDIUM),
    EDUCATION (MEDIUM),
    PHARMACEUTICAL (LOW),
    FOOD (HIGH),
    HEALTH_CARE (LOW),
    ENTERTAINMENT (MEDIUM),
    NEWS_MEDIA (LOW),
    ENERGY (LOW),
    MANUFACTURING (MEDIUM),
    MUSIC (HIGH),
    MINING (HIGH),
    ELECTRONICS (LOW),
    POLITICS (MEDIUM),
    BANKING (LOW),
    SPORT (MEDIUM),
    STATE_OWNED (LOW),
    OLD_AGE_OR_INVALIDITY_PENSION (HIGH);

    private final Risk risk;

    Industry(Risk risk) {
        this.risk = risk;
    }

    public Risk getIndustryRisk() {
        return this.risk;
    }
}
