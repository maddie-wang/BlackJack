
public class Card
{
    private Suit suit;
    private Face face;
    private String imageFileName;
    private boolean faceUp;
    private boolean its11;
    
    public Card(Suit s, Face f)
    {
        suit = s;
        face = f;
        imageFileName = "cards/" + face.getFileLetter() +
            suit.getSuitFirstLetter() + ".gif";
        faceUp = false;
    }
    
    
    public boolean isFaceUp()
    {
        return faceUp;
    }
    
    public void turnOver()
    {
        faceUp = !faceUp;
    }
    
    public String getImageFileName()
    {
        if(faceUp)
            return imageFileName;
        else
            return "cardBack.jpg";
    }
    
    public String toString()
    {
        return face.toString() + " of " + suit.toString();
    }
    
    public Face getFace()
    {
        return face;
    }
    
    public Suit getSuit()
    {
        return suit;
    }
    
    public int getValue()
    {
        return face.getValue();
    }
    
    public void setValue(int v)
    {
        face.setValue(v);
    }
    public int getRank()
    {
        return face.getRank();
    }
    public boolean itsEleven()
    {
        return its11;
    }
    public void switchItsEleven()
    {
        its11 = !its11;
    }
}
