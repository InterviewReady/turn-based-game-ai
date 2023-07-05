import game.*;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    GameFactory gameFactory = new GameFactory();

    @Test
    public void timeOutTestForOneMove() {
        Game game = gameFactory.createGame(3000,11000);
        Player x = new Player("X");
        Player o = new Player("O");
        Cell c00 = Cell.getCell(0, 0);
        int ts = 5000;
        game.move(new Move(c00, x), ts);
        Assert.assertEquals(game.getWinner().symbol(), o.symbol());
    }

    @Test
    public void timeOutTestForGame() {
        Game game = gameFactory.createGame(6000,11000);
        Player x = new Player("X");
        Player o = new Player("O");
        Cell c00 = Cell.getCell(0, 0);
        Cell c01 = Cell.getCell(0, 1);
        Cell c02 = Cell.getCell(0, 2);
        Cell c10 = Cell.getCell(1, 0);
        int ts = 5000;
        game.move(new Move(c00, x), ts);
        game.move(new Move(c01, o), ts);
        game.move(new Move(c02, x), ts);
        game.move(new Move(c10, o), 7000);
        Assert.assertEquals(game.getWinner().symbol(), x.symbol());
    }

    @Test
    public void timeOutTestForVictory() {
        Game game = gameFactory.createGame(6000,11000);
        Player x = new Player("X");
        Player o = new Player("O");
        Cell c00 = Cell.getCell(0, 0);
        Cell c01 = Cell.getCell(0, 1);
        Cell c02 = Cell.getCell(0, 2);
        Cell c10 = Cell.getCell(1, 0);
        Cell c11 = Cell.getCell(1, 1);
        Cell c12 = Cell.getCell(1, 2);
        Cell c20 = Cell.getCell(2, 0);
        int ts = 2000;
        game.move(new Move(c00, x), ts);
        game.move(new Move(c01, o), ts);
        game.move(new Move(c02, x), ts);
        game.move(new Move(c10, o), ts);
        game.move(new Move(c11, x), ts);
        game.move(new Move(c12, o), ts);
        game.move(new Move(c20, x), ts);
        Assert.assertEquals(game.getWinner().symbol(), x.symbol());
    }

    @Test
    public void timeOutTestPerGame() {
        Game game = gameFactory.createGame(11000);
        Player x = new Player("X");
        Player o = new Player("O");
        Cell c00 = Cell.getCell(0, 0);
        Cell c01 = Cell.getCell(0, 1);
        Cell c02 = Cell.getCell(0, 2);
        Cell c10 = Cell.getCell(1, 0);
        int ts = 5000;
        game.move(new Move(c00, x), ts);
        game.move(new Move(c01, o), ts);
        game.move(new Move(c02, x), ts);
        game.move(new Move(c10, o), 7000);
        Assert.assertEquals(game.getWinner().symbol(), x.symbol());
    }

    @Test
    public void timeOutTestForOneMoveWithoutMoveTimeout() {
        Game game = gameFactory.createGame(11000);
        Player x = new Player("X");
        Player o = new Player("O");
        Cell c00 = Cell.getCell(0, 0);
        int ts = 5000;
        game.move(new Move(c00, x), ts);
        Assert.assertNull(game.getWinner());
    }
}
