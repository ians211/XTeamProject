package application;

public class Choice
{
    private boolean isCorrect;
    private String choice;

    public Choice(boolean isCorrect, String choice)
    {
        this.isCorrect = isCorrect;
        this.choice = choice;
    }

    public boolean isCorrect()
    {
        return isCorrect;
    }

    public String getChoice()
    {
        return choice;
    }
}
