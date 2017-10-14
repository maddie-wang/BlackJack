
public enum Suit
{
    HEARTS, DIAMONDS, CLUBS, SPADES;
    
    public String getSuitFirstLetter()
    {
        return this.toString().toLowerCase().substring(0,1);
    }
}
