package com.sports.tennis.service;

import com.sports.tennis.model.Point;
import com.sports.tennis.model.BasePlayer;
import com.sports.tennis.model.TennisPlayer;

public abstract class BaseGameService {
    protected String Separator = " - ";
    protected int gameWinCount = 6;

    public abstract BasePlayer getLeadPlayer();

    public abstract String getScore();

    public abstract BasePlayer getWinningPlayer();

    /***
     * converts int to actual point score.
     * @param player
     * @return
     */
    protected int convertNumberToPoint(TennisPlayer player) {
        int score = player.getScore();
        int result;
        switch (score) {
            case 0:
                result = Point.ZERO.getValue();
                break;
            case 1:
                result = Point.FIFTEEN.getValue();
                break;
            case 2:
                result = Point.THIRTY.getValue();
                break;
            case 3:
                result = Point.FOURTY.getValue();
                break;
            case 4:
                result = 5; // winner
                break;
            default:
                result = -1;
        }
        return result;
    }

}