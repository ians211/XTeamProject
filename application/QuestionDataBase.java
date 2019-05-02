package application;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents the question database (by utilizing a hashtable for
 * topics and ArrayList of questions) which will contain every question for each
 * specific topic.
 */
public class QuestionDataBase
{
    Hashtable<String, ArrayList<Question>> questionBank; // the hashtable of questions according to topic

    /**
     * This constructor creates the new hashtable.
     */
    public QuestionDataBase()
    {
        questionBank = new Hashtable<>();
    }

    /**
	 * This method adds a new question and topic to the question bank.
	 * 
	 * @param topic    of the question
	 * @param question to be added
	 */
    public void addQuestion(String topic, Question question)
    {
        if(!questionBank.containsKey(topic))
        { // check if topic is not already present
            questionBank.put(topic, new ArrayList<>());
        }
        questionBank.get(topic).add(question); // add question to specific topic
    }

    /**
	 * This method returns all of the questions specific to a certain topic.
	 * 
	 * @param topic of questions requested
	 * @return the ArrayList of questions for that topic
	 */
    public ArrayList<Question> getListOfQuestionsFromTopic(String topic)
    {
        if(!questionBank.containsKey(topic))
        { // check if topic is not present
            return new ArrayList<Question>();
        }
        return questionBank.get(topic);
    }

    /**
	 * This method returns all of the topics available for generating a quiz.
	 * 
	 * @return all topics
	 */
    public ArrayList<String> getTopics()
    {
        Set<String> set = questionBank.keySet();
        Iterator<String> it = set.iterator();
        ArrayList<String> topicList = new ArrayList<>();
        while(it.hasNext())
        { // iterate through topic list
            topicList.add(it.next());
        }
        return topicList;
    }
}
