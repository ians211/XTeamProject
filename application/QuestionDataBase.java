package application;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

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

    public ArrayList<String> getTopics()
    {
        Set<String> set = questionBank.keySet();
        Iterator<String> it = set.iterator();
        ArrayList<String> topicList = new ArrayList<>();
        while(it.hasNext())
        {
            topicList.add(it.next());
        }
        return topicList;
    }
}
