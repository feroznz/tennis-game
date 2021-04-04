package com.sports.tennis.model;


import java.util.LinkedList;

public class Match extends BaseGame{

    private LinkedList<Game> gameList; // need to preserve the order.
    private boolean complete = true;

    public Match(TennisPlayer player1, TennisPlayer player2) {
        super(player1,player2);
        gameList = new LinkedList<>();
    }

    public LinkedList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(LinkedList<Game> gameList) {
        this.gameList = gameList;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}