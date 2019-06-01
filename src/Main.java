import java.util.LinkedList;
import java.util.Queue;
/**
 * @author S.Shayan Daneshvar
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Node curPosition = new Node(0, 2, "");
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(curPosition);

        while (!finished(curPosition, board)) {
            curPosition = goToNextNodes(curPosition, board, nodes);
            System.out.println(curPosition);
        }
        System.out.println(curPosition);

    }

    private static Node goToNextNodes(Node curPosition, Board board, Queue<Node> nodes) {

        nodes.poll();
        if (isValid(curPosition.getNode().getKey() + 1,
                curPosition.getNode().getValue(), board)) {

            Node right = (Node) curPosition.clone();
            right.addToPastNodes(right.getNode().getKey(), right.getNode().getValue());
            nodes.add(new Node(right.getNode().getKey() + 1,
                    right.getNode().getValue(), right.getPastNodes()));
        }
        if (isValid(curPosition.getNode().getKey() - 1,
                curPosition.getNode().getValue(), board)) {
            Node left = (Node) curPosition.clone();
            left.addToPastNodes(left.getNode().getKey(),
                    left.getNode().getValue());
            nodes.add(new Node(left.getNode().getKey() - 1,
                    left.getNode().getValue(), left.getPastNodes()));
        }
        if (isValid(curPosition.getNode().getKey(),
                curPosition.getNode().getValue() + 1, board)) {
            Node up = (Node) curPosition.clone();
            up.addToPastNodes(up.getNode().getKey(),
                    up.getNode().getValue());
            nodes.add(new Node(up.getNode().getKey(),
                    up.getNode().getValue() + 1, up.getPastNodes()));
        }
        if (isValid(curPosition.getNode().getKey(),
                curPosition.getNode().getValue() - 1, board)) {
            Node down = (Node) curPosition.clone();
            down.addToPastNodes(down.getNode().getKey(),
                    down.getNode().getValue());
            nodes.add(new Node(down.getNode().getKey(),
                    down.getNode().getValue() - 1, down.getPastNodes()));
        }
        return nodes.peek();
    }

    private static boolean isValid(Integer x, Integer y, Board board) {
        return x >= 0 && y >= 0 && x < board.getBoard()[0].length && y < board.getBoard().length && board.getBoard()[y][x] != '#';
    }

    private static boolean finished(Node position,
                                    Board board) {
        return board.getBoard()[position.getNode().getValue()][position.getNode().getKey()] == 'E';
    }
}