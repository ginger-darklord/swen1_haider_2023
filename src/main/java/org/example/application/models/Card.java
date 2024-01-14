package org.example.application.models;

import org.example.application.CardType;
import org.example.application.ElementType;

public class Card {
    private String Id;
    private int Damage;
    private String Name;
    private String element;
    private String type;
    private String username;

    public Card() {}

    //for tests
    public Card(String id, String name, int damage, String type, String element, String username) {
        this.Id = id;
        this.Name = name;
        this.Damage = damage;
        this.type = type;
        this.element = element;
        this.username = username;
    }

    public Card(String id, String name, int damage, String type, String element) {
        this.Id = id;
        this.Name = name;
        this.Damage = damage;
        this.type = type;
        this.element = element;
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
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

    public String getElement() {
        return element;
    }

    public void setElementType(ElementType elementType) {
        this.element = elementType.element;
    }

    public String getType() {
        return type;
    }

    public void setCardType(CardType cardType) {
        this.type = cardType.type;
    }
}
