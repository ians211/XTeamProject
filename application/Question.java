package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Question class represents a question that is to be stored in the QuestionDataBank
 *
 */
public class Question
{
    private String meta_data; // String variable storing if the Question has been used in the Quiz
    private String question; // String variable storing the text of the Question to be asked
    private String topic; // String variable storing the topic this question is considered
    private ImageView image; // String variable holding the image filepath
    private ArrayList<Choice> choices; // ArrayList of possible choices for this question

    /**
     * Public question constructor 
     * @param meta_data if the Question has been used
     * @param question the question String
     * @param topic the topic/classification of this question
     * @param imageFilepath the file path of this question's image
     * @param choices the ArrayList of possible choices for this question
     */
    public Question(String meta_data, String question, String topic, String imageFilepath, ArrayList<Choice> choices, boolean outsideImage) {
        this.meta_data = meta_data;
        this.question = question;
        this.topic = topic;
        if(imageFilepath.equals("none"))
        {
            this.image = new ImageView("application/science.jpg");
        }
        else
        {
            try
            {
                this.image = new ImageView(new Image(new FileInputStream(imageFilepath)));
            }
            catch(FileNotFoundException e)
            {
                try
                {
                    this.image = new ImageView(imageFilepath);
                }
                catch(Exception e2)
                {
                    try
                    {
                        this.image = new ImageView("application/" + imageFilepath);
                    }
                    catch(Exception e3)
                    {
                        this.image = new ImageView("application/science.jpg");
                    }
                }
            }
        }
        this.choices = choices;
    
    }

    /**
     * Public question constructor 
     * @param meta_data if the Question has been used
     * @param question the question String
     * @param topic the topic/classification of this question
     * @param image the image to be used for the question
     * @param choices the ArrayList of possible choices for this question
     */
    public Question(String meta_data, String question, String topic, ImageView image, ArrayList<Choice> choices) {
        this.meta_data = meta_data;
        this.question = question;
        this.topic = topic;
        this.image = image;
        this.choices = choices;
    }

    /**
     * Meta-Data getter
     * @return the meta-data for this question
     */
    public String getMeta_data()
    {
        return meta_data;
    }

    /**
     * Question getter
     * @return the string associated with this Question(prompt)
     */
    public String getQuestion()
    {
        return question;
    }

    /**
     * Topic getter
     * @return the topic associated with this question
     */
    public String getTopic()
    {
        return topic;
    }

    /**
     * Image getter
     * @return the image associated with this question
     */
    public ImageView getImage()
    {
        return image;
    }

    /**
     * Choices getter
     * @return the arrayList of choices associated with this question
     */
    public ArrayList<Choice> getChoices()
    {
        return choices;
    }

    /**
     * meta-data setter
     * @param meta_data the string to set meta-data variable to
     */
    public void setMeta_data(String meta_data)
    {
        this.meta_data = meta_data;
    }
}
