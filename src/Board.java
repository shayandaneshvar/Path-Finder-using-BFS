/**
 * @author S.Shayan Daneshvar
 */
public class Board {
    private char[][] board;

    public Board() {
        board = new char[][]{
                 {' ', ' ', ' ', '#', 'E'}
                , {' ', '#', ' ', '#', ' '}
                , {'S', ' ', ' ', ' ', ' '}
        };
    }

    public char[][] getBoard() {
        return board;
    }
}