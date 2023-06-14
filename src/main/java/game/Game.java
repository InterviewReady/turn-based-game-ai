package game;

import boards.Board;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    Player winner;
    private Integer lastMoveTimeInMillis;
    private Integer maxTimePerPlayer;
    private Integer maxTimePerMove;

    public Game(GameConfig gameConfig, Board board, Player winner, Integer lastMoveTimeInMillis, Integer maxTimePerPlayer, Integer maxTimePerMove) {
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTimeInMillis = lastMoveTimeInMillis;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.maxTimePerMove = maxTimePerMove;
    }

    public void move(Move move, int timestampInMillis) {
        int timeTakenSinceLastMove = timestampInMillis - lastMoveTimeInMillis;
        move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
        if (gameConfig.timed) {
            moveForTimedGame(move, timeTakenSinceLastMove);
        } else {
            board.move(move);
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        final int currentTime, endTime;
        if (gameConfig.timePerMove != null) {
            currentTime = timeTakenSinceLastMove;
            endTime = maxTimePerMove;
        } else {
            currentTime = move.getPlayer().getTimeUsedInMillis();
            endTime = maxTimePerPlayer;
        }
        if (currentTime < endTime) {
            board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }

    public void setConfig(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }
}
