import java.lang.*; //math class
/**
 * Write a description of class Dealer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dealer extends Player
{
    // instance variables - replace the example below with your own
    int limit;
    public Dealer()
    {
        limit = (int)(Math.random()*4)+16; //limit is between 16-19 inclusive
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public boolean inThreshold() //returns true if the dealer's cards are >= to limit (like 18 or 20)
    { //if dealer is in theshhold, they should not draw. they should stand.
        int t = 0;
        for(Card c: this.getHand())
        {
            t+=c.getValue();
        }
        return t>=limit;
        
    }
    public void newLimit()
    {
        limit = (int)(Math.random()*4)+16; //limit is between 16-19 inclusive
    }
    public boolean aceAs11InThreshold() //returns true if the dealer's hands is >= to their threshhold 
    { //with their ace as a "11" and not a 1
        int t = 0;
        for(Card c: this.getHand())
        {
            t+=c.getValue();
        }
        t+=10; //adds 10 because there's already "1" accounted for the in hand.
        //sees
        return t>=limit && t<=21;
        
    }
}
