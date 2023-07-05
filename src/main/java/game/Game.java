package game;

import api.RuleEngine;
import boards.Board;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    Player winner;
    private Integer lastMoveTimeInMillis;
    private Integer maxTimePerPlayer;
    private Integer maxTimePerMove;
    private RuleEngine ruleEngine = new RuleEngine();

    public Game(GameConfig gameConfig, Board board, Player winner, Integer lastMoveTimeInMillis, Integer maxTimePerPlayer, Integer maxTimePerMove) {
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTimeInMillis = lastMoveTimeInMillis;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.maxTimePerMove = maxTimePerMove;
    }

    public void move(Move move, int timeTakenSinceLastMove) {
        if (winner == null) {
            move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
            if (gameConfig.timed) {
                moveForTimedGame(move, timeTakenSinceLastMove);
            } else {
                board = board.move(move);
            }
            if (winner == null && ruleEngine.getState(board).isOver()) {
                winner = move.getPlayer();
            }
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        if (move.getPlayer().getTimeUsedInMillis() < maxTimePerPlayer
                && (gameConfig.timePerMove == null || timeTakenSinceLastMove < maxTimePerMove)) {
            board = board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }

    public void setConfig(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public Player getWinner() {
        return winner;
    }
}
