import java.util.*;

public class CardHand
{
    private ArrayList <Card> hand;
    
    public CardHand()
    {
        hand = new ArrayList <Card> ();
    }
    
    public void addCardToHand(Card c)
    {
        hand.add(c);
    }
    
    public void addCardInNumOrder(Card c)
    {
        //adds cards in numerical order
    }
    
    public void addCardSuitThenNumOrder(Card c)
    {
        //cards are grouped by suit and
        //in numerical order within the suit
    }
    
    
    public ArrayList <Card> getHand()
    {
        return hand;
    }
}
