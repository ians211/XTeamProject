package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    /**
     * This is the JavaFX main method
     * @param primaryStage the main Stage
     */
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Quiz Generator");
		setupGUI(primaryStage);
		primaryStage.show();
	}

	/**
	 * This is the main method that will call the start method to set up the GUI
	 * @param args command-line arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * This method sets up and displays the home screen GUI
	 * @author Andrew Frank
	 * @param primaryStage the stage for the HUI to be displayed in
	 */
	private void setupGUI(Stage primaryStage)
	{
		// main setup
		VBox mainLayout = new VBox(75);
		mainLayout.setPadding(new Insets(75));
		// quiz generator title
		Label quizGen = new Label("Quiz Generator");
		quizGen.setFont(new Font("Palatino Linotype", 75));
		// import questions with JSON filepath
		TextField jsonFilePath = new TextField();
		jsonFilePath.setPromptText("type in JSON filepath");
		Label loadQuestions = new Label("Load Questions:");
		loadQuestions.setFont(new Font(15));
		HBox loadJSON = new HBox(5);
		loadJSON.getChildren().addAll(loadQuestions, jsonFilePath);
		loadJSON.setAlignment(Pos.CENTER_RIGHT);
		Button load = new Button("Load JSON file");
		HBox loadButtonWrapper = new HBox();
		loadButtonWrapper.getChildren().addAll(load);
		loadButtonWrapper.setAlignment(Pos.CENTER_RIGHT);
		VBox loadVBox = new VBox();
		loadVBox.getChildren().addAll(loadJSON, loadButtonWrapper);
		HBox loadHBox = new HBox();
		loadHBox.getChildren().setAll(loadVBox);
		loadHBox.setAlignment(Pos.CENTER);
		// bottom buttons (add question, generate quiz)
		HBox bottomButtons = new HBox(70);
		Button addQuestion = new Button("Add a Question");
		Button generateQuiz = new Button("Generate Quiz");
		bottomButtons.getChildren().addAll(addQuestion, generateQuiz);
		bottomButtons.setAlignment(Pos.CENTER);
		// adding all nodes to the main layout
		mainLayout.getChildren().addAll(quizGen, loadHBox, bottomButtons);
		mainLayout.setStyle("-fx-background-color: #80b380");
		mainLayout.setAlignment(Pos.CENTER);
		Scene mainScene = new Scene(mainLayout, 800, 600);
		primaryStage.setScene(mainScene);

		addQuestion.setOnAction(e ->
		{
			addQuestion(primaryStage);
		});

		generateQuiz.setOnAction(e ->
        {
            selectQuizTopics(primaryStage);
        });
	}

	/**
	 * This method sets up and displays the GUI for the add question screen
	 * @author Andrew Frank
	 * @param primaryStage the stage for the HUI to be displayed in
	 */
	private void addQuestion(Stage primaryStage)
	{
		// main setup
		int textFieldLength = 390;
		VBox mainLayout = new VBox(20);
		// new question text
		Label newQuestion = new Label("New Question");
		newQuestion.setFont(new Font("Palatino Linotype", 75));
		// textfield VBox
		VBox textFieldVBox = new VBox(10);
		int HBoxSpacing = 10;
		// enter question
		HBox enterQuestionHBox = new HBox(HBoxSpacing);
		Label enterQuestion = new Label("Enter Question:");
		TextField enterQuestionTextField = new TextField();
		enterQuestionTextField.setPromptText("Enter Question Here");
		enterQuestionTextField.setPrefWidth(textFieldLength);
		enterQuestionHBox.getChildren().addAll(enterQuestion, enterQuestionTextField);
		enterQuestionHBox.setAlignment(Pos.CENTER);
		// enter answer
		HBox enterAnswerHBox = new HBox(HBoxSpacing);
		Label enterAnswer = new Label("   Enter Answer:");
		TextField enterAnswerTextField = new TextField();
		enterAnswerTextField.setPromptText("Enter Answer Here");
		enterAnswerTextField.setPrefWidth(textFieldLength);
		enterAnswerHBox.getChildren().addAll(enterAnswer, enterAnswerTextField);
		enterAnswerHBox.setAlignment(Pos.CENTER);
		// enter option 1
		HBox enterOption1HBox = new HBox(HBoxSpacing);
		Label enterOption1 = new Label(" Enter Option 1:");
		TextField enterOption1TextField = new TextField();
		enterOption1TextField.setPromptText("Enter Option 1 Here");
		enterOption1TextField.setPrefWidth(textFieldLength);
		enterOption1HBox.getChildren().addAll(enterOption1, enterOption1TextField);
		enterOption1HBox.setAlignment(Pos.CENTER);
		// enter option 2
		HBox enterOption2HBox = new HBox(HBoxSpacing);
		Label enterOption2 = new Label(" Enter Option 2:");
		TextField enterOption2TextField = new TextField();
		enterOption2TextField.setPromptText("Enter Option 2 Here");
		enterOption2TextField.setPrefWidth(textFieldLength);
		enterOption2HBox.getChildren().addAll(enterOption2, enterOption2TextField);
		enterOption2HBox.setAlignment(Pos.CENTER);
		// enter option 3
		HBox enterOption3HBox = new HBox(HBoxSpacing);
		Label enterOption3 = new Label(" Enter Option 3:");
		TextField enterOption3TextField = new TextField();
		enterOption3TextField.setPromptText("Enter Option 3 Here");
		enterOption3TextField.setPrefWidth(textFieldLength);
		enterOption3HBox.getChildren().addAll(enterOption3, enterOption3TextField);
		enterOption3HBox.setAlignment(Pos.CENTER);
		// topic
		HBox topicHBox = new HBox(HBoxSpacing);
		Label topic = new Label("        Topic:");
		ComboBox<String> topicBox = new ComboBox<>();
		topicBox.setPromptText("Select Topic");
		topicBox.getItems().addAll("Sample topic 1", "Sample topic 2", "Sample topic 3", "...");
		topicHBox.getChildren().addAll(topic, topicBox);
		topicHBox.setAlignment(Pos.CENTER);
		topicHBox.setPadding(new Insets(0, 242, 0, 0));
		// enter filepath to save
		HBox enterFilepathHBox = new HBox(HBoxSpacing);
		Label enterFilepath = new Label("  JSON filepath:");
		TextField enterFilepathTextField = new TextField();
		enterFilepathTextField.setPromptText("Filepath to Save Question");
		enterFilepathTextField.setPrefWidth(textFieldLength);
		enterFilepathHBox.getChildren().addAll(enterFilepath, enterFilepathTextField);
		enterFilepathHBox.setAlignment(Pos.CENTER);
		// populate textfield VBox
		textFieldVBox.getChildren().addAll(enterQuestionHBox, enterAnswerHBox, enterOption1HBox, enterOption2HBox, enterOption3HBox, topicHBox, enterFilepathHBox);
		// bottom buttons (cancel, submit)
		HBox bottomButtons = new HBox(136);
		Button cancel = new Button("Cancel");
		cancel.setStyle("-fx-font: 18 arial;");
		Button submit = new Button("Submit");
		submit.setStyle("-fx-font: 18 arial;");
		bottomButtons.getChildren().addAll(cancel, submit);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.setPadding(new Insets(10));
		// adding all nodes to the main layout
		mainLayout.getChildren().addAll(newQuestion, textFieldVBox, bottomButtons);
		mainLayout.setStyle("-fx-background-color: #80b380");
		mainLayout.setAlignment(Pos.CENTER);
		Scene addQuestionScene = new Scene(mainLayout,800, 600);
		primaryStage.setScene(addQuestionScene);

		cancel.setOnAction(e ->
		{
			setupGUI(primaryStage);
		});

		submit.setOnAction(e ->
		{
			// code to save question to JSON file
			setupGUI(primaryStage);
		});
	}

	private void selectQuizTopics(Stage primaryStage)
    {
        BorderPane root = new BorderPane();

        GridPane choices = new GridPane();
        choices.setPadding(new Insets(5));

        Scene scene = new Scene(root,800,600);

        primaryStage.setTitle("Possible Topics");

        root.setStyle("-fx-background-color: #80b380");
        choices.setStyle("-fx-background-color: #80b380");

        root.setCenter(choices);

        Label message = new Label("SELECT QUIZ TOPICS!");
        message.setFont(new Font("Palatino Linotype", 25));
        message.setPadding(new Insets(10));
        root.setTop(message);



        int numChoices = 5;
        List<CheckBox> checks = new ArrayList<CheckBox>();
        List<Label> options = new ArrayList<Label>();

        for (int i = 0; i < numChoices; i++) {

            checks.add(new CheckBox());
            options.add(new Label("Topic " + (i + 1)));

            options.get(i).setFont(new Font("Palatino Linotype", 18));
            options.get(i).setPadding(new Insets(3));

            choices.add(checks.get(i), 1, i + 1);
            choices.add(options.get(i), 2, i + 1);

        }


        Button cancel = new Button("Cancel");
        Button next = new Button("Start Quiz");

        HBox leftBox = new HBox(cancel);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(leftBox, Priority.ALWAYS);

        HBox rightBox = new HBox(next);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        HBox bottomBox = new HBox(leftBox, rightBox);
        bottomBox.setPadding(new Insets(10));


        root.setBottom(bottomBox);

        primaryStage.setScene(scene);

        cancel.setOnAction(e ->
        {
            setupGUI(primaryStage);
        });

        next.setOnAction(e ->
        {
            quizScreen(primaryStage);
        });
    }

    private void quizScreen(Stage primaryStage)
    {
        // Question
        Label label = new Label("Question #1: What is your name?"); // change to actual question
        label.setFont(Font.font("Palatino Linotype", 30));
        label.setPadding(new Insets(20));

        // Answer choices
        ToggleGroup group = new ToggleGroup();
        RadioButton answerA = new RadioButton("A. Don't choose this answer"); // change to correct options for
        // specific question
        answerA.setToggleGroup(group);
        answerA.setFont(Font.font("Palatino Linotype", 20));

        RadioButton answerB = new RadioButton("B. Don't choose this answer");
        answerB.setToggleGroup(group);
        answerB.setFont(Font.font("Palatino Linotype", 20));

        RadioButton answerC = new RadioButton("C. Choose this answer");
        answerC.setToggleGroup(group);
        answerC.setFont(Font.font("Palatino Linotype", 20));

        RadioButton answerD = new RadioButton("D. Don't choose this answer");
        answerD.setToggleGroup(group);
        answerD.setFont(Font.font("Palatino Linotype", 20));

        VBox vbox = new VBox(50);
        vbox.getChildren().addAll(answerA, answerB, answerC, answerD);
        vbox.setPadding(new Insets(100));
        // keep track of which answer is chosen (mouse over and click)

        // Picture for question
        Image image = new Image("application/science.jpg"); // change to picture specific to image
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(400);

        // Progress of quiz
        ProgressBar pb = new ProgressBar();
        pb.setProgress(0);
        pb.setMinWidth(360);
        pb.setMinHeight(43);
        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            double i = 0;

            public void handle(ActionEvent e) {
                // set progress to different level of progressbar
                i = pb.getProgress() + 0.1; // change 0.1 to 1/number of questions
                pb.setProgress(i);
            }
        };
        EventHandler<ActionEvent> backEvent = new EventHandler<ActionEvent>() {
            double i = 0;

            public void handle(ActionEvent e) {
                // set progress to different level of progressbar
                i = pb.getProgress() - 0.1; // change 0.1 to 1/number of questions
                pb.setProgress(i);
            }
        };

        // Bottom bar of buttons and progress bar
        ButtonBar buttonBar = new ButtonBar();

        Button backButton = new Button("Back");
        ButtonBar.setButtonData(backButton, ButtonBar.ButtonData.BACK_PREVIOUS);
        backButton.setFont(Font.font("Palatino Linotype", 20));
        backButton.setMinWidth(100);
        backButton.setOnAction(backEvent);

        Button nextButton = new Button("Next");
        ButtonBar.setButtonData(nextButton, ButtonBar.ButtonData.NEXT_FORWARD);
        nextButton.setFont(Font.font("Palatino Linotype", 20));
        nextButton.setMinWidth(100);
        nextButton.setOnAction(nextEvent);

        Button submitButton = new Button("Submit");
        ButtonBar.setButtonData(submitButton, ButtonBar.ButtonData.FINISH);
        submitButton.setFont(Font.font("Palatino Linotype", 20));
        submitButton.setMinWidth(150);
        EventHandler<ActionEvent> submitEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                results(primaryStage);
            }
        };
        submitButton.setOnAction(submitEvent);

        buttonBar.getButtons().addAll(backButton, nextButton, submitButton, pb);

        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(backButton, pb, nextButton, submitButton);

        primaryStage.setTitle("Quiz Generator");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
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
        primaryStage.setScene(scene);
    }

	private void results(Stage primaryStage)
    {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #80b380");
        Scene scene = new Scene(root,800,600);

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
        HBox bottomBox = new HBox();
        Pane spacer = new Pane();
        bottomBox.setHgrow(spacer, Priority.ALWAYS);
        bottomBox.getChildren().addAll(exit, spacer, newQuiz);
        root.setBottom(bottomBox);
        bottomBox.setPadding(new Insets(10, 10, 10, 10));
        bottomBox.setSpacing(8);
        primaryStage.setScene(scene);

        newQuiz.setOnAction(e ->
        {
            selectQuizTopics(primaryStage);
        });

        exit.setOnAction(e ->
        {
            setupGUI(primaryStage);
        });
    }
}