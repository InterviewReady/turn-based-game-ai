package placements;

import api.RuleEngine;
import boards.TicTacToeBoard;
import game.Cell;
import game.Player;

import java.util.Optional;

public interface Placement {

    RuleEngine ruleEngine = new RuleEngine();

    Optional<Cell> place(TicTacToeBoard board, Player player);

    Placement next();
}
