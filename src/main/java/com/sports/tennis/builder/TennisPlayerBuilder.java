package com.sports.tennis.builder;

import com.sports.tennis.model.TennisPlayer;

public class TennisPlayerBuilder {
    private String name;
    private int rank;
    private String country;

    public TennisPlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TennisPlayerBuilder setRank(int rank){
        this.rank = rank;
        return this;
    }

    public TennisPlayerBuilder setCountry(String country){
        this.country = country;
        return this;
    }

    public TennisPlayer getPlayer() {
        return new TennisPlayer(name);
    }
}
