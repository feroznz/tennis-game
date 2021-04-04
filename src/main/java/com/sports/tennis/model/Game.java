
package com.sports.tennis.model;

public class Game extends BaseGame{

    public static int gameCounter;
    private boolean gameCompleted;

    public Game(TennisPlayer player1, TennisPlayer player2) {
        super(player1,player2);

        // reset player stats.
        resetPlayerInfo(player1);
        resetPlayerInfo(player2);
        gameCounter++;
    }

    public static int getGameCounter() {
        return gameCounter;
    }

    public boolean isGameCompleted() {
        return gameCompleted;
    }

    public void setGameCompleted(boolean value) {
        this.gameCompleted = value;
    }

    /***
     * Reset's player's score and other records keepings when a new game starts.
     * @param player
     */

    private void resetPlayerInfo(TennisPlayer player) {
        player.setHasAdvantage(false);
        player.resetScore();
    }
}
