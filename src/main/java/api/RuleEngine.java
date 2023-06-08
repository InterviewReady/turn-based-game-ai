package api;

import boards.Board;
import boards.CellBoard;
import boards.TicTacToeBoard;
import game.*;
import placements.DefensivePlacement;
import placements.OffensivePlacement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static boards.TicTacToeBoard.Symbol;
import static boards.TicTacToeBoard.getRules;

public class RuleEngine {

    Map<String, RuleSet> ruleMap = new HashMap<>();

    public GameInfo getInfo(CellBoard board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState(ticTacToeBoard);
            for (Symbol symbol : Symbol.values()) {
                Player player = new Player(symbol.marker());
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTacToeBoard.getSymbol(i, j) != null) {
                            TicTacToeBoard b = ticTacToeBoard.move(new Move(new Cell(i, j), player));
                            //force opponent to make a defensive move
                            //we still after that move
                            DefensivePlacement defense = DefensivePlacement.get();
                            Optional<Cell> defensiveCell = defense.place(b, player.flip());
                            if (defensiveCell.isPresent()) {
                                b = b.move(new Move(defensiveCell.get(), player.flip()));
                                OffensivePlacement offense = OffensivePlacement.get();
                                Optional<Cell> offensiveCell = offense.place(b, player);
                                if (offensiveCell.isPresent()) {
                                    return new GameInfoBuilder()
                                            .isOver(gameState.isOver())
                                            .winner(gameState.getWinner())
                                            .hasFork(true)
                                            .forkCell(new Cell(i, j))
                                            .player(player)
                                            .build();
                                }
                            }
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public RuleEngine() {
        ruleMap.put(TicTacToeBoard.class.getName(), getRules());
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard b = (TicTacToeBoard) board;
            for (Rule r : ruleMap.get(TicTacToeBoard.class.getName())) {
                GameState gameState = r.condition.apply(b);
                if (gameState.isOver()) {
                    return gameState;
                }
            }
            return new GameState(false, "-");
        } else {
            throw new IllegalArgumentException();
        }
    }
}

