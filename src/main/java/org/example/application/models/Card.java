package org.example.application.models;

import org.example.application.CardType;
import org.example.application.ElementType;

public class Card {
    private String Id;
    private String Damage;
    private String Name;
    private String element;
    private String type;

    public Card() {}

    public Card(String id, String name, String damage, String type, String element) {
        this.Id = id;
        this.Name = name;
        this.Damage = damage;
        this.type = type;
        this.element = element;
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
