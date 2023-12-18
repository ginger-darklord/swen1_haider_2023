package org.example.application;

public enum ElementType {
    FIRE("fire"),
    WATER("water"),
    NORMAL("normal");

    public String element;
    ElementType(String element) {
        this.element = element;
    }
}
