import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the main container (GridPane layout)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels
        Label instructionLabel = new Label("Select the conversion type:");
        GridPane.setConstraints(instructionLabel, 0, 0);

        Label inputLabel = new Label("Enter value to convert:");
        GridPane.setConstraints(inputLabel, 0, 1);

        Label resultLabel = new Label("Result:");
        GridPane.setConstraints(resultLabel, 0, 3);

        // ComboBox for selecting conversion type
        ComboBox<String> conversionTypeComboBox = new ComboBox<>();
        conversionTypeComboBox.getItems().addAll("kg to lbs", "oz to g", "mile to km", "in to cm");
        conversionTypeComboBox.setValue("kg to lbs");
        GridPane.setConstraints(conversionTypeComboBox, 1, 0);

        // TextField for input value
        TextField inputTextField = new TextField();
        GridPane.setConstraints(inputTextField, 1, 1);

        // Label to display result
        Label resultOutputLabel = new Label();
        GridPane.setConstraints(resultOutputLabel, 1, 3);

        // Button to perform conversion
        Button convertButton = new Button("Convert");
        GridPane.setConstraints(convertButton, 1, 2);

        // Set the event handler for the button click
        convertButton.setOnAction(e -> {
            try {
                double inputValue = Double.parseDouble(inputTextField.getText());
                String conversionType = conversionTypeComboBox.getValue();
                double result = 0;
                String resultText = "";

                switch (conversionType) {
                    case "kg to lbs":
                        result = inputValue * 2.205;
                        resultText = inputValue + " kg = " + result + " lbs";
                        break;
                    case "oz to g":
                        result = inputValue * 28.3495;
                        resultText = inputValue + " oz = " + result + " g";
                        break;
                    case "mile to km":
                        result = inputValue * 1.60934;
                        resultText = inputValue + " miles = " + result + " km";
                        break;
                    case "in to cm":
                        result = inputValue * 2.54;
                        resultText = inputValue + " in = " + result + " cm";
                        break;
                }

                // Display the result in the label
                resultOutputLabel.setText(resultText);
            } catch (NumberFormatException ex) {
                resultOutputLabel.setText("Invalid input. Please enter a valid number.");
            }
        });

        // Add all components to the grid
        grid.getChildren().addAll(instructionLabel, inputLabel, conversionTypeComboBox, inputTextField, convertButton, resultLabel, resultOutputLabel);

        // Create the scene and set up the stage
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Metric Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
