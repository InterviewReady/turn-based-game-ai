package placements;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;
import utils.Utils;

import java.util.Optional;

public class CentrePlacement implements Placement {

    private static CentrePlacement centrePlacement;

    private CentrePlacement() {

    }

    public synchronized static CentrePlacement get() {
        centrePlacement = (CentrePlacement) Utils.getIfNull(centrePlacement, CentrePlacement::new);
        return centrePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell center = null;
        if (board.getSymbol(1, 1) == null) {
            center= Cell.getCell(1, 1);
        }
        return Optional.ofNullable(center);
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
