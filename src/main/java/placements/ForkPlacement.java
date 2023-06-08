package placements;

import boards.TicTacToeBoard;
import game.Cell;
import game.GameInfo;
import game.Player;
import utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placement {

    private ForkPlacement() {

    }

    private static ForkPlacement forkPlacement;

    public synchronized static ForkPlacement get() {
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, ForkPlacement::new);
        return forkPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if (gameInfo.hasAFork()) {
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return CentrePlacement.get();
    }
}
