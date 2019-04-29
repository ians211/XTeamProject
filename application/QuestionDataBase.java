package application;

import java.util.ArrayList;
import java.util.Hashtable;

public class QuestionDataBase
{
    Hashtable<String, ArrayList<Question>> questionBank;

    public QuestionDataBase()
    {
        questionBank = new Hashtable<>();
    }

    public void addQuestion(String topic, Question question)
    {
        if(!questionBank.containsKey(topic))
        {
            questionBank.put(topic, new ArrayList<>());
        }
        questionBank.get(topic).add(question);
    }

    public ArrayList<Question> getListOfQuestionsFromTopic(String topic)
    {
        if(!questionBank.containsKey(topic))
        {
            return new ArrayList<Question>();
        }
        return questionBank.get(topic);
    }
}
