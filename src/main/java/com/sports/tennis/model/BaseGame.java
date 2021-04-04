package com.sports.tennis.model;

public class BaseGame {

    private TennisPlayer player1;
    private TennisPlayer player2;

    public BaseGame(TennisPlayer player1, TennisPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public TennisPlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(TennisPlayer player1) {
        this.player1 = player1;
    }

    public TennisPlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(TennisPlayer player2) {
        this.player2 = player2;
    }
}
