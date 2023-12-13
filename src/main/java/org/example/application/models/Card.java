package org.example.application.models;

public class Card {
    private String Id;
    private String Damage;
    private String Name;

    public String getDamage() {
        return Damage;
    }

    public String getName() {
        return Name;
    }

    public String getId() {
        return this.Id;
    }
}
