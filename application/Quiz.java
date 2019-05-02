package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class represents a quiz that the user will generate
 */
public class Quiz
{

    ArrayList<Question> allQuestions; // all of the questions that can be pulled for the quiz
    ArrayList<Question> questionList; // the questions that will be in the quiz

    int numQuestions; // the number of questions in the quiz
    int questionNum; // the question that the user is on

    int numberAnswered; // the number of questions that the user has answered
    int numberCorrect; // the number of questions that the user has answered correctly

    /**
     * Constructor for quiz
     * @param questions all questions that can be in the quiz
     * @param numQuestions the number of questions in the quiz
     */
    public Quiz(ArrayList<Question> questions, int numQuestions)
    {
        allQuestions = questions;
        numberAnswered = 0;
        numberCorrect = 0;
        questionNum = 0;

        this.numQuestions = numQuestions;

    }

    /**
     * @return number of questions answered
     */
    public int getNumberAnswered()
    {
        return numberAnswered;
    }

    /**
     * @return number of questions answered correctly
     */
    public int getNumberCorrect()
    {
        return numberCorrect;
    }

    /**
     * @return the percent correct
     */
    public int getPercentCorrect()
    {
        return (int)((((double)(numberCorrect))/((double)(numQuestions))) * 100);
    }

    /**
     * @param numQuestions the number of questions in the quiz
     * @return the questions that will be in the quiz
     */
    public ArrayList<Question> getQuestionList(int numQuestions) {

        Random rand = new Random();

        this.questionList = new ArrayList<Question>();

        if (numQuestions >= allQuestions.size()) {

            questionList.addAll(allQuestions);
            Collections.shuffle(questionList);
            this.numQuestions = allQuestions.size();

        }

        else {

            ArrayList<Integer> randStuff = new ArrayList<Integer>();
            int numAdded = 0;
            while (numAdded < numQuestions) {

                int randomQuestion = rand.nextInt(numQuestions);

                if (randStuff.contains(randomQuestion)) {
                    continue;
                }

                randStuff.add(randomQuestion);
                questionList.add(allQuestions.get(randomQuestion));
                numAdded++;
            }
        }

        return questionList;
    }

    /**
     * @return the question number the user is on
     */
    public int getQuestionNum() {
        return questionNum;
    }

    /**
     * @param questionNum the question number the user is on
     */
    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    /**
     * @return the number of questions in the quiz
     */
    public int getNumQuestions() {
        return numQuestions;
    }

    /**
     * @param numberAnswered the number of questions answered
     */
    public void setNumberAnswered(int numberAnswered) {
        this.numberAnswered = numberAnswered;
    }

    /**
     * @param numberCorrect the number of questions answered correctly
     */
    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }
}
