import javafx.util.Pair;

/**
 * @author S.Shayan Daneshvar
 */
public class Node {
    private Pair<Integer, Integer> node;
    private String pastNodes;

    public Node(int x, int y, String pastNodes) {
        this.node = new Pair<>(x, y);
        this.pastNodes = pastNodes;
    }

    public Pair<Integer, Integer> getNode() {
        return node;
    }

    public void setNode(Pair<Integer, Integer> node) {
        addToPastNodes(node.getKey(), node.getValue());
        this.node = node;
    }

    public String getPastNodes() {
        return pastNodes;
    }

    public void addToPastNodes(Integer x, Integer y) {
        this.pastNodes += "(" + x + "," + y + ")";
    }

    @Override
    protected Object clone() {
        return new Node(this.node.getKey(), this.node.getValue(), this.pastNodes);
    }

    @Override
    public String toString() {
        return "+ Current node :" + "(" + node.getKey() + "," + node.getValue() + ")" +
                "\n" +
                "- pastNodes:{" + pastNodes + '}';
    }
}