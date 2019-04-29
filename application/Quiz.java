package application;

import java.util.ArrayList;

public class Quiz
{
    ArrayList<Question> questions;

    int numberAnswered;
    int numberCorrect;

    public Quiz(ArrayList<Question> questions)
    {
        this.questions = questions;
        numberAnswered = 0;
        numberCorrect = 0;
    }

    public ArrayList<Question> getQuestions()
    {
        return questions;
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
        return 0;
    }
}
