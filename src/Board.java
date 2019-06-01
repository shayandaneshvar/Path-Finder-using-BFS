/**
 * @author S.Shayan Daneshvar
 */
public class Board {
    private char[][] board;

    public Board() {
        board = new char[][]{
                {' ', ' ', ' ', '#', ' '}
                , {' ', '#', ' ', ' ', ' '}
                , {'S', ' ', ' ', '#', ' '}
        };
    }

    public char[][] getBoard() {
        return board;
    }
}