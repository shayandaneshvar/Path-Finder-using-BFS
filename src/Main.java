import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author S.Shayan Daneshvar
 */
public class Main extends Application {
    static Board board;
    static Node curPosition;
    static Queue<Node> nodes;

    static private List<Node> myNodes;//for visualization

    public static void main(String[] args) {
        board = new Board();
        curPosition = new Node(0, 2, "");
        nodes = new LinkedList<>();
        myNodes = new ArrayList<>();
        nodes.add(curPosition);

        while (!finished(curPosition, board)) {
            myNodes.add(curPosition);
            curPosition = goToNextNodes(curPosition, board, nodes);
            System.out.println(curPosition);
        }
        myNodes.add(curPosition);
        launch(args);
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

    ////////////////////////////////Visualizations//////////////////////////////
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50));
        grid.setVgap(2);
        grid.setHgap(2);
//        grid.gridLinesVisibleProperty().setValue(true);
        grid.setAlignment(Pos.CENTER);
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("BFS");
        primaryStage.setScene(scene);
        primaryStage.show();
        AtomicInteger i = new AtomicInteger(0);
        Random rnd = new Random();

        Platform.runLater(() -> {
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.SPACE) {
                    for (Node node : myNodes) {
                        Rectangle circle = new Rectangle(40, 40);
                        Label label = new Label(node.getNode().getKey()
                                + "," + node.getNode().getValue());
                        StackPane stack = new StackPane();
                        label.setStyle("-fx-font-size: 25px;-fx-text-fill: white");
                        circle.setFill(Color.rgb(Math.abs(rnd.nextInt()) % 256,
                                Math.abs(rnd.nextInt()) % 256,
                                Math.abs(rnd.nextInt()) % 256));
                        stack.getChildren().addAll(circle, label);

                        GridPane.setConstraints(stack,
                                node.getNode().getKey() * 10,
                                node.getNode().getValue() * 10);
                        grid.add(stack, node.getNode().getKey() * 10,
                                node.getNode().getValue() * 10);
                    }
                } else {
                    if (i.get() >= myNodes.size()) {
                        return;
                    }
                    Circle circle = new Circle(30);
                    Label label = new Label(myNodes.get(i.get()).getNode().getKey()
                            + "," + myNodes.get(i.get()).getNode().getValue());
                    StackPane stack = new StackPane();
                    label.setStyle("-fx-font-size: 25px;-fx-text-fill: white");
                    if (i.get() + 1 == myNodes.size()) {
                        circle.setRadius(45);
                        label.setStyle("-fx-padding: 10; -fx-background-radius:50%;" +
                                "-fx-background-color: yellowgreen; -fx-fill: red;" +
                                "-fx-font-size: 30px");
                    }
                    circle.setFill(Color.rgb(Math.abs(i.get() * rnd.nextInt()) % 256,
                            Math.abs(i.get() * rnd.nextInt()) % 256,
                            Math.abs(i.get() * rnd.nextInt()) % 256));
                    stack.getChildren().addAll(circle, label);

                    GridPane.setConstraints(stack,
                            myNodes.get(i.get()).getNode().getKey() * 10,
                            myNodes.get(i.get()).getNode().getValue() * 10);
                    grid.add(stack, myNodes.get(i.get()).getNode().getKey() * 10,
                            myNodes.get(i.get()).getNode().getValue() * 10);
                    i.getAndIncrement();
                }
            });
        });
    }
}