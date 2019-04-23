package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color: #80b380");
			Scene scene = new Scene(root,400,400);
			
			// Create a Results label and place it in the center Top 
			Label results = new Label("Results");
			results.setFont(new Font("Palatino Linotype", 75));
			root.setTop(results);
			root.setAlignment(results, Pos.TOP_CENTER);
			
			
			VBox leftBox = new VBox();
			leftBox.setPadding(new Insets(10));
			leftBox.setSpacing(8);
			Label finalScore = new Label("Final Score:");
			Label answered = new Label("You answered");
			Label totalQuestions = new Label("Total questions");
			finalScore.setFont(new Font("Palatino Linotype", 20));
			answered.setFont(new Font("Palatino Linotype", 20));
			totalQuestions.setFont(new Font("Palatino Linotype", 20));
			leftBox.getChildren().addAll(finalScore, answered, totalQuestions);
			root.setLeft(leftBox);
			
			
			
			
			Button exit = new Button("Exit");
			Button newQuiz = new Button("Generate new quiz");
			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
