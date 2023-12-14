package org.example.application.models;

public class Card {
    private String Id;
    private String Damage;
    private String Name;

    public Card() {}

    public Card(String id, String name, String damage) {
        this.Id = id;
        this.Name = name;
        this.Damage = damage;
    }

    public String getDamage() {
        return Damage;
    }

    public void setDamage(String damage) {
        Damage = damage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return this.Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
