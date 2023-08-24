package api;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;

public abstract class Strategy {

    public abstract Cell getOptimalMove(TicTacToeBoard board, Player player);
}
