package com.sports.tennis.model;

public class TennisPlayer extends BasePlayer {
    private boolean hasAdvantage;

    public TennisPlayer(String name) {
        super(name);
    }

    public boolean hasAdvantage() {
        return hasAdvantage;
    }

    public void setHasAdvantage(boolean hasAdvantage) {
        this.hasAdvantage = hasAdvantage;
    }
}
