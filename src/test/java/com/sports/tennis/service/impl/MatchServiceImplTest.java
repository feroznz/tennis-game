package com.sports.tennis.service.impl;

import com.sports.tennis.model.Game;
import com.sports.tennis.model.Match;
import com.sports.tennis.builder.TennisPlayerBuilder;
import com.sports.tennis.model.TennisPlayer;
import junit.framework.TestCase;

public class MatchServiceImplTest extends TestCase {
    TennisPlayer federer = new TennisPlayerBuilder().setName("BasePlayer 1").getPlayer();
    TennisPlayer nadal = new TennisPlayerBuilder().setName("BasePlayer 2").getPlayer();
    Match match = new Match(federer, nadal);

    public void testGetGameList() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);
        matchServiceImpl.addGame(game);
        assertEquals(matchServiceImpl.getGameList().size(), 1);

    }

    public void testAddGame() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);
        matchServiceImpl.addGame(game);

        assertEquals(matchServiceImpl.getGameList().size(), 1);
    }

    public void testGetLeadPlayer() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);
        matchServiceImpl.addGame(game);
        assertEquals(matchServiceImpl.getLeadPlayer(), federer);
    }

    public void testGetScore() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);
        matchServiceImpl.addGame(game);

        assertEquals(matchServiceImpl.getScore(), "0 - 0, 40 - 15");
    }

    public void testGetWinningPlayer() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);
        matchServiceImpl.addGame(game);

        assertEquals(matchServiceImpl.getWinningPlayer(), federer);
    }

    public void testIsMatchComplete() {
        Match match2 = new Match(federer, nadal);
        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match2);

        for (int i = 0; i < 6; i++) {
            Game game = new Game(federer, nadal);
            GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
            gameServiceImpl.pointWonBy(federer);
            gameServiceImpl.pointWonBy(federer);
            gameServiceImpl.pointWonBy(federer);
            gameServiceImpl.pointWonBy(federer);
            gameServiceImpl.pointWonBy(nadal);

            matchServiceImpl.addGame(game);

        }
        assertTrue(matchServiceImpl.isMatchComplete());
    }
    public void testAddGame_Should_Fail_Incomplete_First_Game() {

        Game game = new Game(federer, nadal);
        Game game2 = new Game(federer, nadal);
        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);

        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        matchServiceImpl.addGame(game);

        GameServiceImpl gameServiceImpl2 = new GameServiceImpl(game2);
        gameServiceImpl2.pointWonBy(federer);
        gameServiceImpl2.pointWonBy(federer);
        gameServiceImpl2.pointWonBy(nadal);
        gameServiceImpl2.pointWonBy(nadal);
        matchServiceImpl.addGame(game2);


        assertEquals(matchServiceImpl.getGameList().size(), 1);
    }

    public void testGetFormattedMatchScore() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);
        matchServiceImpl.addGame(game);

        String formattedRes = String.format("%s, %s wins!. ", matchServiceImpl.getScore(), matchServiceImpl.getLeadPlayer().getName());

        assertEquals(matchServiceImpl.getFormattedMatchScore(),formattedRes);
    }
}