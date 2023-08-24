package api;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

public class OptimalStrategy extends Strategy {
    @Override
    public Cell getOptimalMove(TicTacToeBoard board, Player player) {
        Placement placement = OffensivePlacement.get();
        while (placement.next() != null) {
            Optional<Cell> place = placement.place(board, player);
            if (place.isPresent()) {
                return place.get();
            }
            placement = placement.next();
        }
        return null;
    }
}
