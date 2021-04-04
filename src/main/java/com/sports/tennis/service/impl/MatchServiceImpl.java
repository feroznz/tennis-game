package com.sports.tennis.service.impl;

import com.sports.tennis.model.*;
import com.sports.tennis.model.BasePlayer;
import com.sports.tennis.model.TennisPlayer;
import com.sports.tennis.service.BaseGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class MatchServiceImpl extends BaseGameService {
    private Logger logger = LoggerFactory.getLogger(Match.class);
    private TennisPlayer player1;
    private TennisPlayer player2;
    private LinkedList<Game> gameList; // need to preserve the order.
    private boolean complete = true;
    private Match match;

    public MatchServiceImpl(Match match) {
        this.player1 = match.getPlayer1();
        this.player2 = match.getPlayer2();
        gameList = new LinkedList<>();
    }

    public LinkedList<Game> getGameList() {
        return gameList;
    }

/**
     * Adds a new game to the match.
     * It doesn't allow to add a new game if the last game wasn't completed.
     * @param game
 */
    public void addGame(Game game) {
        if (this.gameList.size() == 0) {
            this.gameList.add(game);
        } else {
            Game lastGame = this.gameList.getLast();
            if (lastGame.isGameCompleted()) {
                this.gameList.add(game);
            } else {
                logger.info("Cannot start a game when another game is in progress!. Current running Game :" + (lastGame.getGameCounter() - 1));
            }
        }
    }


    @Override
    public BasePlayer getLeadPlayer() {
        return player1.getScore() > player2.getScore() ? player1 : player2;
    }

    @Override
    public String getScore() {
        String result = "";
        int p1WinCount = 0;
        int p2WinCount = 0;

        GameServiceImpl gameServiceImpl;

        String temp = p1WinCount + Separator + p2WinCount;
        for (Game game : gameList) {
            gameServiceImpl = new GameServiceImpl(game);
            if (game.isGameCompleted()) {
                if (player1.equals(gameServiceImpl.getWinningPlayer())) {
                    //p1 won.
                    p1WinCount += 1;
                    temp = p1WinCount + Separator + p2WinCount;
                }
                if (player2.equals(gameServiceImpl.getWinningPlayer())) {
                    // p2 won.
                    p2WinCount += 1;
                    temp = p1WinCount + Separator + p2WinCount;
                }
                // get winning player.

                result = temp;
            } else {
                // incomplete match.
                complete = false;
                result = p1WinCount + Separator + p2WinCount + ", " + gameServiceImpl.getScore();
            }
        }
        logger.debug("Match result : " + result);
        return result;
    }

    @Override
    public BasePlayer getWinningPlayer() {
        int[] scores = getScoreAsArray();

        if (scores[0] == gameWinCount) {
            return this.player1;
        }
        if (scores[1] == gameWinCount) {
            return this.player2;
        }

        // return the player who is ahead so far.
        return getLeadPlayer();
    }

    public boolean isMatchComplete() {
        int[] scores = getScoreAsArray();
        return scores[0] == gameWinCount || scores[1] == gameWinCount;
    }

    // helper methods
    private int[] getScoreAsArray() {
        String matchScore = getScore();
        int[] result = new int[2];
        String tempString;
        // incomplete
        if (!complete) {
            String[] temp = matchScore.trim().split(",");
            tempString = temp[0];
            String[] scores = tempString.trim().split(Separator);
            result[0] = Integer.parseInt(scores[0]);
            result[1] = Integer.parseInt(scores[1]);
        } else {
            String[] scores = matchScore.trim().split(Separator);
            result[0] = Integer.parseInt(scores[0]);
            result[1] = Integer.parseInt(scores[1]);
        }
        return result;
    }

    public String getFormattedMatchScore() {
        String formattedRes = String.format("%s, %s wins!. ", getScore(), getLeadPlayer().getName());
        return formattedRes;
    }
}
