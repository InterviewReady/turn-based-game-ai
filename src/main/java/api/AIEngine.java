package api;

import boards.Board;
import boards.TicTacToeBoard;
import game.*;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

public class AIEngine {

    RuleEngine ruleEngine = new RuleEngine();

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard b = (TicTacToeBoard) board;
            Cell suggestion;
            int threshold = 3;
            if (countMoves(b) < threshold) {
                suggestion = getBasicMove(b);
            } else if (countMoves(b) < threshold + 1) {
                suggestion = getCellToPlay(player, b);
            } else if(player.getTimeUsedInMillis() > 100000) {
                suggestion = getBasicMove(b);
            } else {
                suggestion = getOptimalMove(player, b);
            }
            if (suggestion != null) {
                return new Move(suggestion, player);
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Cell getOptimalMove(Player player, TicTacToeBoard board) {
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

    private Cell getCellToPlay(Player player, TicTacToeBoard board) {
        //Victorious moves
        Cell best = offense(player, board);
        if (best != null) return best;
        //Defensive moves
        best = defense(player, board);
        if (best != null) return best;
        return getBasicMove(board);
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard b = board.move(move);
                    if (ruleEngine.getState(b).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    private Cell defense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private Cell getBasicMove(TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }

    private int countMoves(TicTacToeBoard board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count;
    }
}
