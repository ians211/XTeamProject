package XTeamProject.application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// Question 
			Label label = new Label("Question #1: What is your name?");
			label.setFont(Font.font("Palatino Linotyp", 30));

			// Answer choices
			ToggleGroup group = new ToggleGroup();
			RadioButton answerA = new RadioButton("A. Don't choose this answer");
			answerA.setToggleGroup(group);
			answerA.setFont(Font.font("Palatino Linotyp", 20));
			
			RadioButton answerB = new RadioButton("B. Don't choose this answer");
			answerB.setToggleGroup(group);
			answerB.setFont(Font.font("Palatino Linotyp", 20));
			
			RadioButton answerC = new RadioButton("C. Choose this answer");
			answerC.setToggleGroup(group);
			answerC.setFont(Font.font("Palatino Linotyp", 20));
			
			RadioButton answerD = new RadioButton("D. Don't choose this answer");
			answerD.setToggleGroup(group);
			answerD.setFont(Font.font("Palatino Linotyp", 20));

			VBox vbox = new VBox(50);
			vbox.getChildren().addAll(answerA, answerB, answerC, answerD);
			vbox.setPadding(new Insets(60));

			// Picture for question
			Image image = new Image("science.jpg");
			ImageView imageView = new ImageView(image);
			imageView.setX(50);
			imageView.setY(25);
			imageView.setFitHeight(300);
			imageView.setFitWidth(400);

			// Progress of quiz
			ProgressBar pb = new ProgressBar();
			pb.setMinWidth(320);
			pb.setMinHeight(43);
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
				double i = 0;
				public void handle(ActionEvent e) {
					// set progress to different level of progressbar
					i = i + 0.1;
					pb.setProgress(i);
				}
			};
			EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
				double i = 0;
				public void handle(ActionEvent e) {
					// set progress to different level of progressbar
					i = i - 0.1;
					pb.setProgress(i);
				}
			};

			// Buttons for bottom ButtonBar
			ButtonBar buttonBar = new ButtonBar();

			Button backButton = new Button("Back");
			ButtonBar.setButtonData(backButton, ButtonData.BACK_PREVIOUS);
			backButton.setFont(Font.font("Palatino Linotyp", 20));
			backButton.setMinWidth(100);
			backButton.setOnAction(event2);

			Button nextButton = new Button("Next");
			ButtonBar.setButtonData(nextButton, ButtonData.NEXT_FORWARD);
			nextButton.setFont(Font.font("Palatino Linotyp", 20));
			nextButton.setMinWidth(100);
			nextButton.setOnAction(event);

			Button submitButton = new Button("Submit");
			ButtonBar.setButtonData(submitButton, ButtonData.FINISH);
			submitButton.setFont(Font.font("Palatino Linotyp", 20));
			submitButton.setMinWidth(150);

			buttonBar.getButtons().addAll(backButton, nextButton, submitButton, pb);

			HBox hbox = new HBox(30); // spacing = 8
			hbox.getChildren().addAll(backButton, pb, nextButton, submitButton);

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 750, 500);
			// background color
			root.setStyle("-fx-background-color: #80b380;");
			// layout
			root.setBottom(hbox);
			root.setRight(vbox);
			root.setAlignment(vbox, Pos.CENTER_RIGHT);
			root.setLeft(imageView);
			root.setAlignment(imageView, Pos.CENTER_LEFT);
			root.setTop(label);
			root.setAlignment(label, Pos.TOP_CENTER);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
