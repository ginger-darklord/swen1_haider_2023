package org.example.application;

public enum CardType {
    MONSTER("monster"),
    SPELL("spell");

    public String type;
    CardType(String type) {
        this.type = type;
    }

}
