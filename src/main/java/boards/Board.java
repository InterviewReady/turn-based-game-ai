package boards;

import game.Move;

public interface Board {
    Board move(Move move);

    Board copy();
}
