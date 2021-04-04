package com.sports.tennis;


import com.sports.tennis.builder.TennisPlayerBuilder;
import com.sports.tennis.model.Game;
import com.sports.tennis.model.Match;
import com.sports.tennis.model.TennisPlayer;
import com.sports.tennis.service.impl.GameServiceImpl;
import com.sports.tennis.service.impl.MatchServiceImpl;

public class App {
    public static void main(String[] args) {
        TennisPlayer federer = new TennisPlayerBuilder().setName("BasePlayer 1").getPlayer();
        TennisPlayer nadal = new TennisPlayerBuilder().setName("BasePlayer 2").getPlayer();

        //Incomplete match!. Current leading player : BasePlayer 1
        //0 - 0, 40 - 15
        Match match = new Match(federer, nadal);
        incompleteMatch(federer, nadal, match);

        //Incomplete match!. Current leading player : BasePlayer 2
        //0 - 0, Deuce
        Match match2 = new Match(federer, nadal);
        deuce(federer, nadal, match2);

        //Incomplete match!. Current leading player : BasePlayer 2
        //0 - 0, Deuce
        Match match3 = new Match(federer, nadal);
        advantage(federer, nadal, match3);

        // player 1 wins
        //6 - 0, BasePlayer 1 wins!.
        Match match4 = new Match(federer, nadal);
        player1Wins(federer, nadal, match4);

        Match match5 = new Match(federer, nadal);
        player2Wins(federer, nadal, match5);


    }


    private static void player2Wins(TennisPlayer federer, TennisPlayer nadal, Match match) {
        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);

        for (int i = 0; i < 6; i++) {
            Game game = new Game(federer, nadal);
            GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
            gameServiceImpl.pointWonBy(nadal);
            gameServiceImpl.pointWonBy(nadal);
            gameServiceImpl.pointWonBy(nadal);
            gameServiceImpl.pointWonBy(federer);
            gameServiceImpl.pointWonBy(nadal);
            matchServiceImpl.addGame(game);
        }

        printResult(matchServiceImpl);
    }

    private static void player1Wins(TennisPlayer federer, TennisPlayer nadal, Match match) {
        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);
        for (int i = 0; i < 6; i++) {
            Game game = new Game(federer, nadal);
            GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
            gameServiceImpl.pointWonBy(federer);
            gameServiceImpl.pointWonBy(federer);
            gameServiceImpl.pointWonBy(federer);
            gameServiceImpl.pointWonBy(federer);
            matchServiceImpl.addGame(game);
        }

        printResult(matchServiceImpl);
    }

    private static void deuce(TennisPlayer federer, TennisPlayer nadal, Match match) {
        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);

        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        matchServiceImpl.addGame(game);

        printResult(matchServiceImpl);
    }

    private static void advantage(TennisPlayer federer, TennisPlayer nadal, Match match) {

        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);

        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        gameServiceImpl.pointWonBy(nadal);
        matchServiceImpl.addGame(game);

        printResult(matchServiceImpl);
    }

    private static void incompleteMatch(TennisPlayer federer, TennisPlayer nadal, Match match) {
        MatchServiceImpl matchServiceImpl = new MatchServiceImpl(match);

        Game game = new Game(federer, nadal);
        GameServiceImpl gameServiceImpl = new GameServiceImpl(game);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(federer);
        gameServiceImpl.pointWonBy(nadal);
        matchServiceImpl.addGame(game);
        printResult(matchServiceImpl);
    }


    private static void printResult(MatchServiceImpl matchServiceImpl) {
        if (matchServiceImpl.isMatchComplete()) {
            if (matchServiceImpl.getWinningPlayer() != null) {
                System.out.println(matchServiceImpl.getFormattedMatchScore());

            } else {
                System.out.println("No one Won yet!. Current score: " + matchServiceImpl.getScore());
            }
        } else {
            //   System.out.println("Incomplete match!. Current leading player : " + match.getLeadPlayerIfMatchNotComplete().getName());
            System.out.println(matchServiceImpl.getScore());
        }
    }


}
