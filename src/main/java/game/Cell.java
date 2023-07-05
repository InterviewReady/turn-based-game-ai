package game;

public class Cell {
    int row, col;

    static Cell cells[][] = new Cell[][]{
            {new Cell(0, 0), new Cell(0, 1), new Cell(0, 2)},
            {new Cell(1, 0), new Cell(1, 1), new Cell(1, 2)},
            {new Cell(2, 0), new Cell(2, 1), new Cell(2, 2)}
    };

    public static Cell getCell(int row, int col) {
        return cells[row][col];
    }

    private Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
