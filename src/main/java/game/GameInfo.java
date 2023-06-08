package game;

public class GameInfo {
    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;

    private int numberOfMoves;
    private Cell forkCell;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, int numberOfMoves, Cell forkCell) {
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.numberOfMoves = numberOfMoves;
        this.forkCell = forkCell;
    }

    public boolean hasAFork() {
        return hasFork;
    }

    public Cell getForkCell() {
        return forkCell;
    }
}

