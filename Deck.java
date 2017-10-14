
public class Deck
{
    private Card [] cards;
    private Suit [] suits = Suit.values();
    private Face [] faces = Face.values();
    private int nextCardIndex;
    
    public Deck()
    {
        nextCardIndex = 0;
        cards = new Card[52];
        
        int i = 0;  //index of cards in deck
        for(int s = 0; s < suits.length; s++)
            for(int f = 0; f < faces.length; f++)
            {
                cards[i] = new Card(suits[s], faces[f]);
                i++;
            }
    }
    
    
    public Card getNextCard()
    {
        if(nextCardIndex < cards.length)
        {
            Card c = cards[nextCardIndex];
            nextCardIndex++;
            return c;
        }
        return null;    //returns null if no Cards left
    }
    
    public void cut()
    {
        int c = (int)(Math.random()*52);
      
        Card [] cutCards = new Card[52];
        
        int count = c;
        int i = 0;
        while(count < cards.length)
        {
            cutCards[i] = cards[count];
            count++;
            i++;
        }
        count = 0;
        while(count < c)
        {
            cutCards[i] = cards[count];
            count++;
            i++;
        }
        
        cards = cutCards;
    }
    
    public void singleShuffle()
    {
        int mid = (int)(12*Math.random()+20);
        
        int count1 = 0; //pos in first half of deck
        int count2 = mid + 1;  //pos in second half of deck
        
        Card [] shuffled = new Card[52];
        
        int i = 0;
        while(count1 <= mid && count2 < shuffled.length)
        {
            shuffled[i] = cards[count1];
            shuffled[i+1] = cards[count2];
            count1++;
            count2++;
            i+=2;
        }
        //only reach if extra cards in the first half
        //filling in extra cards from 1st half
        while(count1 <= mid) 
        {
            shuffled[i] = cards[count1];
            count1++;
            i++;
        }
        //only reach if extra cards in the second half
        //filling in extra cards from 2nd half
        while(count2 < shuffled.length) 
        {
            shuffled[i] = cards[count2];
            count2++;
            i++;
        }
            
        cards = shuffled;
    }
    
    public void shuffle()
    {
        cut();
        for(int i = 0; i < 3; i++)
            singleShuffle();
        cut();
    }
    
    public void printFullDeck()
    {
        for(Card c: cards)
            System.out.println(c);
    }
      
}
