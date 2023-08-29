package api;

import boards.Board;
import boards.TicTacToeBoard;
import game.*;
import strategy.StrategyFactory;

public class AIEngine {

    private final StrategyFactory strategyFactory = new StrategyFactory();

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard b = (TicTacToeBoard) board;
            Cell suggestion = strategyFactory.getStrategy(b, player).getOptimalMove(b, player);
            if (suggestion != null) {
                return new Move(suggestion, player);
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
