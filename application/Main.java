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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
	

    private QuestionDataBase questionBank = new QuestionDataBase();

    /**
     * This is the JavaFX main method
     * @param primaryStage the main Stage
     */
    @Override
    public void start(Stage primaryStage)
    {
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
        primaryStage.setTitle("Quiz Generator");
        
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

        load.setOnAction(e ->
        {
            readFile(jsonFilePath.getText());
        });

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
        ImageView image = new ImageView("science.jpg");
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

        // enter option 4
        HBox enterOption4HBox = new HBox(HBoxSpacing);
        Label enterOption4 = new Label(" Enter Option 4:");
        TextField enterOption4TextField = new TextField();
        enterOption4TextField.setPromptText("Enter Option 4 Here");
        enterOption4TextField.setPrefWidth(textFieldLength);
        enterOption4HBox.getChildren().addAll(enterOption4, enterOption4TextField);
        enterOption4HBox.setAlignment(Pos.CENTER);

        // topic
        HBox topicHBox = new HBox(HBoxSpacing);
        Label topic = new Label("                       Topic:");
        ComboBox<String> topicBox = new ComboBox<>();
        topicBox.setPromptText("Select or Type Topic");
        ArrayList<String> topicList = questionBank.getTopics();
        Collections.sort(topicList);
        topicBox.getItems().addAll(topicList);
        topicBox.setEditable(true);
        topicHBox.getChildren().addAll(topic, topicBox);
        topicHBox.setAlignment(Pos.CENTER);
        topicHBox.setPadding(new Insets(0, 242, 0, 0));

        // choose image
        HBox chooseImageHBox = new HBox(HBoxSpacing);
        Label chooseImage = new Label(" Choose Image(optional):");
        Button chooseImageButton = new Button("Browse");
        Label imagePath = new Label();
        imagePath.setPrefWidth(374);
        chooseImageHBox.getChildren().addAll(chooseImage, chooseImageButton, imagePath);
        chooseImageHBox.setAlignment(Pos.CENTER);
        
        // enter filepath to save
        HBox enterFilepathHBox = new HBox(HBoxSpacing);
        Label enterFilepath = new Label("     JSON filepath:");
        TextField enterFilepathTextField = new TextField();
        enterFilepathTextField.setPromptText("Filepath to Save Question");
        enterFilepathTextField.setPrefWidth(textFieldLength - 100);
        CheckBox save = new CheckBox("save to file?");
        save.setPrefWidth(100);
        enterFilepathHBox.getChildren().addAll(enterFilepath, enterFilepathTextField, save);
        enterFilepathHBox.setAlignment(Pos.CENTER);
        
        // populate textfield VBox
        textFieldVBox.getChildren().addAll(enterQuestionHBox, enterAnswerHBox, enterOption1HBox, enterOption2HBox, enterOption3HBox, enterOption4HBox, topicHBox, chooseImageHBox, enterFilepathHBox);
        
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

        chooseImageButton.setOnAction(e ->
        {
            FileChooser fileChooser = new FileChooser();
            String filepath = "" + fileChooser.showOpenDialog(primaryStage).getAbsolutePath() + "";
            System.out.println(filepath);
            String filename;
            try
            {
                int stringCutoff = filepath.lastIndexOf("\\") + 1;
                if(stringCutoff == 0)
                {
                    filename = filepath;
                }
                else
                {
                    filename = filepath.substring(stringCutoff);
                }
                System.out.println(filename);
                imagePath.setText(filename);
                Image pic = new Image(new FileInputStream(filepath));
                if(pic.getHeight() != 0)
                {
                    image.setImage(pic);
                }
            }
            catch(FileNotFoundException exc)
            {

            }
        });

        cancel.setOnAction(e ->
        {
            setupGUI(primaryStage);
        });

        submit.setOnAction(e ->
        {
            /*ArrayList<Choice> choices = new ArrayList<Choice>();
            choices.add(new Choice(true, enterAnswerTextField.getText()));
            choices.add(new Choice(false, enterOption1TextField.getText()));
            choices.add(new Choice(false, enterOption2TextField.getText()));
            choices.add(new Choice(false, enterOption3TextField.getText()));
            Question currQ = new Question("unused", enterQuestionTextField.getText(), topicBox.getValue(), image, choices);
            questionBank.addQuestion(topicBox.getValue(), currQ);*/


            boolean validInputs = true;//variable to see if valid inputs
            //check if inputs are valid
            if(enterQuestionTextField.getText().equals("")) {
                validInputs=false;
                enterQuestionTextField.setStyle("-fx-prompt-text-fill: #e2091e");
            }
            if(topicBox.getValue() == null) {
                validInputs=false;
                // could not figure out how to change prompt text color of combobox so I changed the prompt text instead
                topicBox.setPromptText("TOPIC REQUIRED");
            }
            if(enterAnswerTextField.getText().equals("")) {
                validInputs=false;
                enterAnswerTextField.setStyle("-fx-prompt-text-fill: #e2091e");
            }
            if(enterOption1TextField.getText().equals("")) {
                validInputs=false;
                enterOption1TextField.setStyle("-fx-prompt-text-fill: #e2091e");
            }
            if(enterFilepathTextField.getText().equals("") && save.isSelected()) {
                validInputs=false;
                enterFilepathTextField.setStyle("-fx-prompt-text-fill: #e2091e");
            }
            else {
                enterFilepathTextField.setStyle("-fx-prompt-text-fill: silver");
            }
            /*if(enterOption2TextField.getText().equals("")) {
                validInputs=false;
            }
            if(enterOption3TextField.getText().equals("")) {
                validInputs=false;
            }
            if(enterOption4TextField.getText().equals("")) {
                validInputs=false;
            }*/
            //if inputs are valid add question to questionBank
            if (validInputs) {
                //create array of choices
                ArrayList<Choice> choices = new ArrayList<Choice>();
                choices.add(new Choice(true, enterAnswerTextField.getText()));
                choices.add(new Choice(false, enterOption1TextField.getText()));
                if(!enterOption2TextField.getText().equals(""))
                    choices.add(new Choice(false, enterOption2TextField.getText()));
                if(!enterOption3TextField.getText().equals(""))
                    choices.add(new Choice(false, enterOption3TextField.getText()));
                if(!enterOption4TextField.getText().equals(""))
                    choices.add(new Choice(false, enterOption4TextField.getText()));
                //add question to bank
                Question currQ = new Question("unused", enterQuestionTextField.getText(), topicBox.getValue(), image, choices);
                questionBank.addQuestion(topicBox.getValue(), currQ);
                //if selected to save file
                if (save.isSelected()) {
                    //create JsonObjects question and add data
                    JSONObject question = new JSONObject();
                    question.put("meta-data", "unused");
                    question.put("questionText", enterQuestionTextField.getText());
                    question.put("topic", topicBox.getValue());
                    question.put("image", "none");
                    //create JSON array of choices
                    JSONArray choicesJSON = new JSONArray();
                    JSONObject answer = new JSONObject();
                    answer.put("isCorrect", "T");
                    answer.put("choice", enterAnswerTextField.getText());
                    JSONObject choice1 = new JSONObject();
                    choice1.put("isCorrect", "F");
                    choice1.put("choice", enterOption1TextField.getText());
                    JSONObject choice2 = new JSONObject();
                    choice2.put("isCorrect", "F");
                    choice2.put("choice", enterOption2TextField.getText());
                    JSONObject choice3 = new JSONObject();
                    choice3.put("isCorrect", "F");
                    choice3.put("choice", enterOption3TextField.getText());
                    JSONObject choice4 = new JSONObject();
                    choice4.put("isCorrect", "F");
                    choice4.put("choice", enterOption3TextField.getText());
                    choicesJSON.add(answer);
                    choicesJSON.add(choice1);
                    if(!enterOption2TextField.getText().equals(""))
                        choicesJSON.add(choice2);
                    if(!enterOption3TextField.getText().equals(""))
                        choicesJSON.add(choice3);
                    if(!enterOption4TextField.getText().equals(""))
                        choicesJSON.add(choice4);
                    question.put("choiceArray", choicesJSON);
                    JSONArray questionBankJSON = new JSONArray();
                    questionBankJSON.add(question); //add question to question bank
                    JSONObject userQuestion = new JSONObject(); //add question bank to file
                    userQuestion.put("questionArray", questionBankJSON);
                    //create a new file with JSON data
                    try (FileWriter file = new FileWriter(enterFilepathTextField.getText())) {

                        file.write(userQuestion.toJSONString());
                        file.close();

                    } catch (IOException d) {
                        d.printStackTrace();
                    }
                }
                setupGUI(primaryStage);
            }
        });
    }

    private void selectQuizTopics(Stage primaryStage)
    {
        
        
        BorderPane root = new BorderPane();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5));

        Scene scene = new Scene(root,800,600);

        //set title
        primaryStage.setTitle("Possible Topics");

        //change color of the background
        root.setStyle("-fx-background-color: #80b380");
        grid.setStyle("-fx-background-color: #80b380");

        root.setCenter(grid);

        //Create a new message
        Label message = new Label("SELECT QUIZ TOPICS!");
        message.setFont(new Font("Palatino Linotype", 25));
        message.setPadding(new Insets(10));
        root.setTop(message);

        
        
        ArrayList<String> topics = questionBank.getTopics();
        Collections.sort(topics);
        int numChoices = topics.size();
        
        ArrayList<CheckBox> checkMarks = new ArrayList<CheckBox>();
        ArrayList<Label> options = new ArrayList<Label>();

        for (int i = 0; i < numChoices; i++) {

            checkMarks.add(new CheckBox());
            options.add(new Label("Topic " + (i + 1) + ": " + topics.get(i)));

            options.get(i).setFont(new Font("Palatino Linotype", 18));
            options.get(i).setPadding(new Insets(3));

            grid.add(checkMarks.get(i), 1, i);
            grid.add(options.get(i), 2, i);

        }
        
        
        // Added textfield for number of questions - Andrew Frank
        Label numQuestionsLabel = new Label("Number of questions in quiz:");
        numQuestionsLabel.setFont(new Font("Palatino Linotype", 18));
        numQuestionsLabel.setPadding(new Insets(10, 0, 0, 0));
        TextField numQuestions = new TextField();
        numQuestions.setPromptText("");
        grid.add(numQuestionsLabel, 2, numChoices + 1);
        grid.add(numQuestions, 2, numChoices + 2);
        


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
            int num = getNumberQuestions(numQuestions);
            if(num == -1)
            {
                numQuestions.setText("");
                numQuestions.setPromptText("You must input a natural number");
                numQuestions.setStyle("-fx-prompt-text-fill: #e2091e");
            }
            else
            {
                quizScreen(primaryStage, initializeTopics(topics, checkMarks), getNumberQuestions(numQuestions));
            }
        });
    }
    
    private int getNumberQuestions(TextField numQuestions) {
      
      int num;
      
      try {
        num = Integer.parseInt(numQuestions.getText());
        if(num <= 0)
        {
            num = -1;
        }
      } catch (Exception e) {
        num = -1;
      }      
      
      return num;
    }
    
    private ArrayList<Question> initializeTopics(ArrayList<String> topics, ArrayList<CheckBox> checkMarks) {
      
      
      //all the possible questions for the quiz
      ArrayList<Question> allQuestions = new ArrayList<Question>();
      
      for (int i = 0; i <checkMarks.size(); i++) {
        
        if (checkMarks.get(i).isSelected()) {
          
          allQuestions.addAll(questionBank.getListOfQuestionsFromTopic(topics.get(i))); 
        
        }
      }
      
      return allQuestions;
    }

    private void quizScreen(Stage primaryStage, ArrayList<Question> allQuestions, int numQuestions) {

        Quiz quiz = new Quiz(allQuestions, numQuestions);

        ArrayList<Question> questionList = quiz.getQuestionList(numQuestions);

        Label label = new Label("Question #1 out of " + quiz.getNumQuestions() + ": " + questionList.get(0).getQuestion());

        label.setWrapText(true);

        label.setFont(Font.font("Palatino Linotype", 12));

        // Answer choices
        ArrayList<Choice> qChoices = questionList.get(0).getChoices();

        Collections.shuffle(qChoices);
        VBox mainVBox = new VBox(50);
        VBox vbox = new VBox(50);
        ToggleGroup group = new ToggleGroup();

        for (int j = 0; j < qChoices.size(); j++) {

            System.out.println(qChoices.size());

            System.out.println(qChoices.get(j).getChoice());

            RadioButton answer = new RadioButton(qChoices.get(j).getChoice()); // change to correct options for

            answer.setToggleGroup(group);

            vbox.getChildren().addAll(answer);

            answer.setFont(Font.font("Palatino Linotype", 10));

        }

        Label correct = new Label("Correct"); // change to actual question,

        correct.setFont(Font.font("Palatino Linotyp", 20));

        Label incorrect = new Label("Incorrect"); // change to actual question,

        incorrect.setFont(Font.font("Palatino Linotyp", 20));

        VBox choiceVBox = new VBox(50);

        choiceVBox.getChildren().addAll(correct, incorrect);

        mainVBox.getChildren().addAll(vbox, choiceVBox);

        // Picture for question

        ImageView imageView = questionList.get(0).getImage(); // change to picture specific to image

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

                i = pb.getProgress() + (1 / numQuestions); // change 0.1 to 1/number of questions

                pb.setProgress(i);

                quiz.setQuestionNum(quiz.getQuestionNum() + 1);
                if(quiz.getQuestionNum() < quiz.getNumQuestions())
                {
                    correct.setFont(Font.font("Palatino Linotyp", 20));
                    incorrect.setFont(Font.font("Palatino Linotyp", 20));
                    setQuestion(questionList.get(quiz.getQuestionNum()), label, vbox, group, quiz, imageView);
                }
                else
                {
                    results(primaryStage, quiz);
                }
            }

        };

        EventHandler<ActionEvent> backEvent = new EventHandler<ActionEvent>() {

            double i = 0;

            public void handle(ActionEvent e) {

                // set progress to different level of progressbar

                i = pb.getProgress() - (1 / numQuestions); // change 0.1 to 1/number of questions

                pb.setProgress(i);

                if (i > 0) {
                    i = i - 2;
                } else {
                    selectQuizTopics(primaryStage);
                }

            }

        };

        // Bottom bar of buttons and progress bar

        ButtonBar buttonBar = new ButtonBar();

        Button backButton = new Button("Back");

        ButtonBar.setButtonData(backButton, ButtonBar.ButtonData.BACK_PREVIOUS);

        backButton.setFont(Font.font("Palatino Linotyp", 15));

        backButton.setMinWidth(100);

        backButton.setMinHeight(43);

        backButton.setOnAction(backEvent);

        // questionCount--;

        Button nextButton = new Button("Next");

        ButtonBar.setButtonData(nextButton, ButtonBar.ButtonData.NEXT_FORWARD);

        nextButton.setFont(Font.font("Palatino Linotyp", 15));

        nextButton.setMinWidth(100);

        nextButton.setMinHeight(43);

        nextButton.setOnAction(nextEvent);

        // questionCount++;

        Button submitButton = new Button("Submit");

        ButtonBar.setButtonData(submitButton, ButtonBar.ButtonData.FINISH);

        submitButton.setFont(Font.font("Palatino Linotyp", 15));

        submitButton.setMinWidth(100);

        submitButton.setMinHeight(43);

        /*EventHandler<ActionEvent> submitEvent = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                // go to results screen if clicked

                results(primaryStage);

            }

        };*/

        //submitButton.setOnAction(submitEvent);

        Button enterButton = new Button("Enter");

        ButtonBar.setButtonData(enterButton, ButtonBar.ButtonData.APPLY);

        enterButton.setFont(Font.font("Palatino Linotyp", 15));

        enterButton.setMinWidth(100);

        enterButton.setMinHeight(43);

        EventHandler<ActionEvent> enterEvent = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                // if question correct on enter

                String correctAnswer = "";
                for(int x = 0; x < questionList.get(quiz.getQuestionNum()).getChoices().size(); x++)
                {
                    if(questionList.get(quiz.getQuestionNum()).getChoices().get(x).isCorrect())
                    {
                        correctAnswer = questionList.get(quiz.getQuestionNum()).getChoices().get(x).getChoice();
                    }
                }

                for(int x = 0; x < vbox.getChildren().size(); x++)
                {
                    if(vbox.getChildren().get(x) instanceof RadioButton)
                    {
                        RadioButton radioButton = (RadioButton)vbox.getChildren().get(x);
                        if(radioButton.isSelected())
                        {
                            if(radioButton.getText().equals(correctAnswer))
                            {
                                System.out.println("correct");
                                correct.setFont(Font.font("Verdana Bold", 35));
                                quiz.setNumberCorrect(quiz.getNumberCorrect() + 1);
                                quiz.setNumberAnswered(quiz.getNumberAnswered() + 1);
                            }
                            else
                            {
                                System.out.println("incorrect");
                                incorrect.setFont(Font.font("Verdana Bold", 35));
                                quiz.setNumberAnswered(quiz.getNumberAnswered() + 1);
                            }
                        }
                    }
                }

                for(int x = 0; x < vbox.getChildren().size(); x++)
                {
                    if(vbox.getChildren().get(x) instanceof RadioButton)
                    {
                        RadioButton radioButton = (RadioButton)vbox.getChildren().get(x);
                        radioButton.setDisable(true);
                    }
                }

            }

        };

        enterButton.setOnAction(enterEvent);

        buttonBar.getButtons().addAll(backButton, enterButton, nextButton, submitButton, pb);

        HBox hbox = new HBox(10);

        hbox.getChildren().addAll(enterButton, nextButton);

        hbox.setAlignment(Pos.CENTER_RIGHT);

        primaryStage.setTitle("Quiz Generator");

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 800, 600);

        // background color

        root.setStyle("-fx-background-color: #80b380;");

        // layout

        root.setBottom(hbox);

        root.setRight(mainVBox);

        root.setAlignment(mainVBox, Pos.CENTER_RIGHT);

        root.setLeft(imageView);

        root.setAlignment(imageView, Pos.CENTER_LEFT);

        root.setTop(label);

        root.setAlignment(label, Pos.TOP_CENTER);

        primaryStage.setScene(scene);


    }

    private void results(Stage primaryStage, Quiz quiz)
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
        Label finalScore = new Label("Final Score: " + quiz.getPercentCorrect() + "%");
        Label answered = new Label("You answered " + quiz.getNumberAnswered() + " questions");
        Label answeredCorrectly = new Label("You answered " + quiz.getNumberCorrect() + " questions correctly");
        Label totalQuestions = new Label("Total questions: " + quiz.getNumQuestions());
        finalScore.setFont(new Font("Palatino Linotype", 20));
        answered.setFont(new Font("Palatino Linotype", 20));
        answeredCorrectly.setFont(new Font("Palatino Linotype", 20));
        totalQuestions.setFont(new Font("Palatino Linotype", 20));
        leftBox.getChildren().addAll(finalScore, answeredCorrectly, answered, totalQuestions);
        root.setLeft(leftBox);




        Button home = new Button("Home");
        Button newQuiz = new Button("Generate new quiz");
        Button exit = new Button("Exit");
        Button exitS = new Button("Exit and save");
        HBox bottomBox = new HBox();
        Pane spacer = new Pane();
        bottomBox.setHgrow(spacer, Priority.ALWAYS);
        bottomBox.getChildren().addAll(home, exit, exitS, newQuiz);
        root.setBottom(bottomBox);
        bottomBox.setPadding(new Insets(10, 10, 10, 10));
        bottomBox.setSpacing(8);
        primaryStage.setScene(scene);

        newQuiz.setOnAction(e ->
        {
            selectQuizTopics(primaryStage);
        });

        home.setOnAction(e ->
        {
            setupGUI(primaryStage);
        });
        exit.setOnAction(e ->
        {
            ((Stage)primaryStage.getScene().getWindow()).close();
        });
        exitS.setOnAction(e ->
        {

            ArrayList<String> topics=questionBank.getTopics();
            JSONArray questionBankJSON = new JSONArray();
            for(int i=0; i<topics.size();i++) {
                ArrayList<Question> currQuestions = questionBank.getListOfQuestionsFromTopic(topics.get(i));
                for(int j=0; j<currQuestions.size();j++) {
                    JSONObject question = new JSONObject();
                    question.put("meta-data", currQuestions.get(j).getMeta_data());
                    question.put("questionText", currQuestions.get(j).getQuestion());
                    question.put("topic", topics.get(i));
                    question.put("image", currQuestions.get(j).getImage());
                    //create JSON array of choices
                    ArrayList<Choice> choices = currQuestions.get(j).getChoices();
                    JSONArray choicesJSON = new JSONArray();
                    for(int k=0; k<choices.size();k++) {
                        JSONObject choice = new JSONObject();
                        choice.put("isCorrect", choices.get(k).getCorrect());
                        choice.put("choice", choices.get(k).getChoice());
                        choicesJSON.add(choice);
                    }
                    question.put("choiceArray", choicesJSON);
                    questionBankJSON.add(question); //add question to question bank
                }
            }
            JSONObject userQuestion = new JSONObject(); //add question bank to file
            userQuestion.put("questionArray", questionBankJSON);
            //create a new file with JSON data
            try (FileWriter file = new FileWriter("questionBank.json")) {

                file.write(userQuestion.toJSONString());
                file.close();

            } catch (IOException d) {
                d.printStackTrace();
            }

            ((Stage)primaryStage.getScene().getWindow()).close();
        });
    }

    public void readFile(String jsonPath) {
        
      
        JSONParser jp = new JSONParser(); // create new jsonParser
        try {
            
            Object ob = jp.parse(new FileReader(jsonPath)); // new file reader cast to object
            JSONObject jo = (JSONObject) ob; // cast object to jsonobject
            JSONArray ja = (JSONArray) jo.get("questionArray"); // get the first question in questionArray
            
            for(int j = 0; j < ja.size(); j++) {
              
                JSONObject question = (JSONObject) ja.get(j); // get first question, jsonObject
                
                String meta = (String) question.get("meta-data"); // get metadata  
                
                String qText = (String) question.get("questionText"); // get questiontext
                
                String topic = (String) question.get("topic"); // get the topic
                
                String imageFileName = (String) question.get("imageFilename"); // get the imageFile name
                
                if (imageFileName == null) {
                  imageFileName = (String) question.get("image");
                }
                
                if (imageFileName == null) {
                  imageFileName = (String) question.get("imageFile");
                }
                
                ArrayList<Choice> choices = new ArrayList<Choice>(); // new Choices array list
                JSONArray jsonChoices = (JSONArray) question.get("choiceArray"); // cast to JSONArray, get the choices json array               
                
                for (int i = 0; i < jsonChoices.size(); i++) {
                    
                    JSONObject jsonChoice = (JSONObject) jsonChoices.get(i); // get the next choice
                    String isCorrect = (String) jsonChoice.get("isCorrect"); // get String F or T
                    String choiceText = (String) jsonChoice.get("choiceText"); // get choice text

                    if(choiceText == null)
                    {
                        choiceText = (String) jsonChoice.get("choice");
                    }

                    Boolean correctChoice; // boolean variable for correct choice
                    

                    if(isCorrect.equals("F")) { // set the boolean variable
                        correctChoice = false;
                    }
                    
                    else {
                        correctChoice = true;
                    }
                    

                    Choice newChoice = new Choice(correctChoice, choiceText); // create a new choice
                    choices.add(newChoice); // add choice to choices arrayList                  
                
                }
                
                Question newQuestion = new Question(meta, qText, topic, imageFileName, choices); // create the new question               
                questionBank.addQuestion(topic, newQuestion);
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setQuestion(Question question, Label label, VBox vbox, ToggleGroup group, Quiz quiz, ImageView imageView)
    {
        label.setText("Question #" + (quiz.getQuestionNum() + 1) + " out of " + quiz.getNumQuestions() + ": " + question.getQuestion());
        ArrayList<Choice> qChoices = question.getChoices();
        vbox.getChildren().clear();
        for (int j = 0; j < qChoices.size(); j++) {

            System.out.println(qChoices.size());

            System.out.println(qChoices.get(j).getChoice());

            RadioButton answer = new RadioButton(qChoices.get(j).getChoice()); // change to correct options for

            answer.setToggleGroup(group);

            vbox.getChildren().addAll(answer);

            answer.setFont(Font.font("Palatino Linotype", 10));

        }

        imageView.setImage(question.getImage().getImage()); // change to picture specific to image

        imageView.setFitHeight(300);

        imageView.setFitWidth(400);
    }
}


