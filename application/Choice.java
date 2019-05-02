package application;

/**
 * 
 * Choice class representing a quiz choice (possible answer)
 * Contains a boolean variable signifying if it is the 
 * correct answer and a String variable of the choice text
 *
 */
public class Choice
{
    private boolean isCorrect; // boolean isCorrect variable
    private String choice; // String choice text to be displayed

    /**
     * public constructor for Choice
     * @param isCorrect true if this choice is the correct option
     * @param choice the text for this choice
     */
    public Choice(boolean isCorrect, String choice)
    {
        this.isCorrect = isCorrect;
        this.choice = choice;
    }

    /**
     * Returns the boolean in the isCorrect field
     * @return true if correct false otherwise
     */
    public boolean isCorrect()
    {
        return isCorrect;
    }

    /**
     * Returns the String in the choice fields
     * @return the String to be displayed for this choice
     */
    public String getChoice()
    {
        return choice;
    }

    public String getCorrect()
    {
        if(isCorrect)
        {
            return "T";
        }
        else
        {
            return "F";
        }
    }
}