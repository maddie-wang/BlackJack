
/**
 * Write a description of class CardOperations here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends CardHand
{
    // instance variables - replace the example below with your own
    private int x;


    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public boolean isGreaterThan(CardHand hand2)
    {
        int t = 0;
        for(Card c: this.getHand())
        {
            t+=c.getValue();
        }
        int t2 = 0;
        for(Card c: hand2.getHand())
        {
            t2+=c.getValue();
        }
        return t>t2;
    }
    public boolean isTied(CardHand hand2)
    {
        int t = 0;
        for(Card c: this.getHand())
        {
            t+=c.getValue();
        }
        int t2 = 0;
        for(Card c: hand2.getHand())
        {
            t2+=c.getValue();
        }
        return t==t2;
    }
    public boolean over21()
    {
        int t = 0;
        for(Card c: this.getHand())
        {
            t+=c.getValue();
        }
        return t>21;
    }
    public int total()
    {
        int t = 0;
        for(Card c: this.getHand())
        {
            t+=c.getValue();
        }
        return t;
    }
    public boolean equal21()
    {
        int t = 0;
        for(Card c: this.getHand())
        {
            t+=c.getValue();
        }
        return t==21;
    }


}
