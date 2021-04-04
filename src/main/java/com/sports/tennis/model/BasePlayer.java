package com.sports.tennis.model;

public class BasePlayer {

    private String name;
    private int score;

    public BasePlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void resetScore() {
        this.score = 0;
    }

}
