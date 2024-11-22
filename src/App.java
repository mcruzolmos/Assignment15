import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    
    private static final int SIZE = 5; // 5x5 grid
    private String[][] board = new String[SIZE][SIZE];
    private String currentPlayer = "X"; // Start with player X
    private boolean gameOver = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        
        // Initialize the board with empty strings
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = "";
                Button cellButton = new Button();
                cellButton.setPrefSize(80, 80);
                cellButton.setStyle("-fx-font-size: 20;");
                final int row = i;
                final int col = j;
                
                // Handle button click
                cellButton.setOnAction(e -> handleCellClick(row, col, cellButton));
                
                grid.add(cellButton, j, i);
            }
        }

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setTitle("Tic Tac Toe 5x5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Handle button click
    private void handleCellClick(int row, int col, Button button) {
        if (gameOver || !board[row][col].equals("")) {
            return; // Don't do anything if the cell is already occupied or the game is over
        }

        // Mark the cell
        board[row][col] = currentPlayer;
        button.setText(currentPlayer);
        
        // Check for a winner
        if (checkWinner(row, col)) {
            gameOver = true;
            showWinnerAlert(currentPlayer);
        } else if (isBoardFull()) {
            gameOver = true;
            showDrawAlert();
        } else {
            // Switch player
            currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        }
    }

    // Check if there are 5 consecutive marks in any direction
    private boolean checkWinner(int row, int col) {
        // Check horizontal, vertical, and diagonal directions for a win
        return checkDirection(row, col, 1, 0) ||  // Horizontal
               checkDirection(row, col, 0, 1) ||  // Vertical
               checkDirection(row, col, 1, 1) ||  // Diagonal /
               checkDirection(row, col, 1, -1);   // Diagonal \
    }

    // Check a specific direction for 5 consecutive marks
    private boolean checkDirection(int row, int col, int rowDir, int colDir) {
        int count = 0;
        
        // Check one direction
        for (int i = -4; i <= 4; i++) {
            int r = row + i * rowDir;
            int c = col + i * colDir;
            if (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c].equals(currentPlayer)) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    // Check if the board is full (draw condition)
    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Show winner alert
    private void showWinnerAlert(String winner) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Player " + winner + " wins!");
        alert.setContentText("Congratulations " + winner + "!");
        alert.showAndWait();
    }

    // Show draw alert
    private void showDrawAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("It's a draw!");
        alert.setContentText("The game ended in a draw.");
        alert.showAndWait();
    }
}
