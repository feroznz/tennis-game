package com.sports.tennis.service.impl;

import com.sports.tennis.model.Game;
import com.sports.tennis.builder.TennisPlayerBuilder;
import com.sports.tennis.model.TennisPlayer;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GameServiceImplTest extends TestCase {

    TennisPlayer federer = new TennisPlayerBuilder().setName("BasePlayer 1").getPlayer();
    TennisPlayer nadal = new TennisPlayerBuilder().setName("BasePlayer 2").getPlayer();

    public GameServiceImplTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( GameServiceImplTest.class );
    }

    public void testSingleGameWhenComplete() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        assertEquals(gameServiceImpl.getScore(),"BasePlayer 2 won this game. score = 0 - 1");
    }

    public void testSingleGameWhenIncomplete() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(federer);

        assertEquals(gameServiceImpl.getScore(),"15 - 40");
    }

    public void testPpointWonBy() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);

        assertEquals(gameServiceImpl.getScore(),"40 - 15");
    }

    public void testGetLeadPlayer() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);

        assertEquals(nadal, gameServiceImpl.getLeadPlayer());

    }

    public void testGetScore() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);

        assertEquals("40 - 0", gameServiceImpl.getScore());
    }

    public void testGetWinningPlayer() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);

        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        assertEquals(gameServiceImpl.getWinningPlayer(),nadal);

    }

    public void testIsGameCompleted() {
        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);

        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
      //  assertEquals(gameServiceImpl.isGameCompleted(), true);
    }
}