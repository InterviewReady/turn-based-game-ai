package game;

import boards.TicTacToeBoard;

public class GameFactory {
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer){
        return new Game(new GameConfig(maxTimePerPlayer, maxTimePerPlayer!=null),
                new TicTacToeBoard(),
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove);
    }

    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer, TicTacToeBoard startingBoard){
        return new Game(new GameConfig(maxTimePerPlayer, maxTimePerPlayer!=null),
                startingBoard,
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove);
    }
}
