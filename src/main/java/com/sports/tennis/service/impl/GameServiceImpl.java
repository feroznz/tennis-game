package com.sports.tennis.service.impl;

import com.sports.tennis.model.*;
import com.sports.tennis.model.BasePlayer;
import com.sports.tennis.service.BaseGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameServiceImpl extends BaseGameService {
    private Logger logger = LoggerFactory.getLogger(Match.class);
    private Game game;
    private TennisPlayer player1;
    private TennisPlayer player2;

    public GameServiceImpl(Game game) {
        this.game = game;
        this.player1 = game.getPlayer1();
        this.player2 = game.getPlayer2();
    }

    public void pointWonBy(TennisPlayer tennisPlayer) {
        if (!game.isGameCompleted()) {
            if (tennisPlayer.equals(player1)) {
                setGameScore(player2, player1);
            } else {
                setGameScore(player1, player2);
            }
        } else {
            // log
           logger.debug("Game " + game.gameCounter + " has finished and was won by player name: " + getWinningPlayer().getName());
        }
    }

    private void setGameScore(TennisPlayer player2, TennisPlayer player1) {
        if (isDeuce() && player2.hasAdvantage()) {
            player2.setHasAdvantage(false); // back to deuce.
        } else if (isDeuce() && !player1.hasAdvantage()) {
            player1.setHasAdvantage(true);
        } else {
            player1.setScore(1);
            // check if player1 will win the game.
            if (hasGameWon(player1)) {
                game.setGameCompleted(true);
            }
        }
    }

    private boolean hasGameWon(TennisPlayer tennisPlayer) {
        return tennisPlayer.getScore() == 4;
    }

    /***
     * checks if the score is level then its deuce.
     * @return
     */
    private boolean isDeuce() {
        return convertNumberToPoint(player1) == Point.FOURTY.getValue()
                && convertNumberToPoint(player2) == Point.FOURTY.getValue();
    }

    @Override
    public BasePlayer getLeadPlayer() {
        return player1.getScore() > player2.getScore() ? player1 : player2;
    }

    @Override
    public String getScore() {
        if (player1.hasAdvantage() || player2.hasAdvantage()) {
            return "Advantage";
        }

        if (isDeuce()) {
            return "Deuce";
        }

        int player1Score = convertNumberToPoint(player1);
        int player2Score = convertNumberToPoint(player2);
        if (player1Score == 5) {
            return player1.getName() + " won this game. score = 1 - 0";
        } else if (player2Score == 5) {
            return player2.getName() + " won this game. score = 0 - 1";

        } else {
            return player1Score + Separator + player2Score;
        }
    }

    @Override
    public BasePlayer getWinningPlayer() {
        if (!isDeuce()) {
            if (player1.getScore() == 4) {
                return player1;
            }
            if (player2.getScore() == 4) {
                return player2;
            }
        }
        return null;
    }
}
