package com.sports.tennis.model;

/***
 * Enum to hold the String and it's int value.
 */
public enum Point {
    ZERO(0),
    FIFTEEN(15),
    THIRTY(30),
    FOURTY(40);

    private int value;

    Point(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}