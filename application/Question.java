package application;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Question
{
    private String meta_data;
    private String question;
    private String topic;
    private ImageView image;
    private ArrayList<Choice> choices;

    public Question(String meta_data, String question, String topic, ImageView image, ArrayList<Choice> choices) {
        this.meta_data = meta_data;
        this.question = question;
        this.topic = topic;
        this.image = image;
        this.choices = choices;
    }

    public String getMeta_data()
    {
        return meta_data;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getTopic()
    {
        return topic;
    }

    public ImageView getImage()
    {
        return image;
    }

    public ArrayList<Choice> getChoices()
    {
        return choices;
    }

    public void setMeta_data(String meta_data)
    {
        this.meta_data = meta_data;
    }
}
