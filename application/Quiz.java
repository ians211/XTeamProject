package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Quiz
{

    ArrayList<Question> allQuestions;
    ArrayList<Question> questionList;

    int numQuestions;
    int questionNum;
    int numberAnswered;
    int numberCorrect;

    public Quiz(ArrayList<Question> questions, int numQuestions)
    {
        allQuestions = questions;
        numberAnswered = 0;
        numberCorrect = 0;
        questionNum = 0;

        this.numQuestions = numQuestions;

    }

    public ArrayList<Question> getQuestions()
    {
        return allQuestions;
    }

    public int getNumberAnswered()
    {
        return numberAnswered;
    }

    public int getNumberCorrect()
    {
        return numberCorrect;
    }

    public int getPercentCorrect()
    {
        return (int)((((double)(numberCorrect))/((double)(numberAnswered))) * 100);
    }

    public ArrayList<Question> getQuestionList(int numQuestions) {

        Random rand = new Random();

        this.questionList = new ArrayList<Question>();

        if (numQuestions >= allQuestions.size()) {

            questionList.addAll(allQuestions);
            //Collections.shuffle(questionList);
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

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }
}
