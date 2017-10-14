import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;
public class GameScreen extends JApplet implements ActionListener, MouseListener
{
    private Deck d;
    private Player hand1;
    private Dealer hand2;
    Button next, hit, stand;
    Container c;
    int screenNum;
    int op;
    boolean itsEleven;
    boolean signalTimer;
    int count = 1;
    public void init()
    {
        //JFrame.setContentPane(Container contentPane)

  
                addMouseListener(this); 
        c = getContentPane();
        c.setLayout(null);
        next = new Button("Play Game");
        next.addActionListener(this);
        c.add(next);
        next.setSize(200, 55);
        next.setLocation(158,435);
        
        hit = new Button("Hit");
        hit.addActionListener(this);
        stand = new Button("Stand");
        stand.addActionListener(this);
        
        d = new Deck();
        d.shuffle();
        
    }
        
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == next)
        {
            del(next);
            c.add(hit);
            hit.setSize(70, 30);
            hit.setLocation(340,337);
            c.add(stand);
            stand.setSize(70, 30);
            stand.setLocation(420,337);
            //making all the buttons ^
            repaint();
            screenNum++;
            //person's hand
            hand1 = new Player();
            Card nextCard = d.getNextCard();
            nextCard.turnOver();
            hand1.addCardToHand(nextCard);
             Card nextCard2 = d.getNextCard();
            nextCard2.turnOver();
            hand1.addCardToHand(nextCard2);
            
                        //dealer's hand:
            hand2 = new Dealer();
            Card nextCard3 = d.getNextCard();
            nextCard3.turnOver();
            hand2.addCardToHand(nextCard3);
            Card nextCard4 = d.getNextCard();
            nextCard4.turnOver();
            hand2.addCardToHand(nextCard4);
            
            //if dealer or player has 21, they immediately win on start :)
            if(hand1.equal21())
            {
                //win
                screenNum=4;
            }
            if(hand2.equal21())
            {
                //lose
                screenNum=8;
            }
        }
        else if (ae.getSource() == hit){
        //add a card.
        Card nextCard = d.getNextCard();
        nextCard.turnOver();
        hand1.addCardToHand(nextCard);
        
        
        //if the person's total is over 21 then auto lose
        if(hand1.over21())
        {
            //lose
            screenNum=3;
            repaint();
        }
        //if the person's total is 21 then auto win
        else if(hand1.equal21())
        {
            //win
            screenNum=4;
            repaint();
        }
        //dealer chooses to draw a card or nah

        else
        {
            //draw card
             signalTimer = true;
              op = 3;
             Card nextCard2 = d.getNextCard();
                Timer timer = new Timer(4000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (signalTimer) {
                    ((Timer) e.getSource()).stop();
                }
                else
                {
                    
                    if(hand2.inThreshold()) //if dealer's total cards reach their "threshold" 16-19
                    {
                            //stand
                           op=1; //dealer chooses to stand! you wwinnn
                    }  
                    else
                    op=2;
                    nextCard2.turnOver();
                 hand2.addCardToHand(nextCard2);
                              if(nextCard2.getRank()==1) //if it's an ace
                 {
                     if(hand2.aceAs11InThreshold()) //if the ace
                     //is used as a 11 and the hand total meets threshhold
                     {
                         //set the ace as an 11
                         nextCard2.setValue(11);
                         
                     }
    
                 }
             
                 //if the dealer's total is over 21 then auto win
    
                if(hand2.over21())
                {
                    //win
                    screenNum=5;
                }
                
                 //if the dealer's total is 21 then auto lose
                if(hand2.equal21())
                {
                    //lose
                    screenNum=8;
                }
            }
                          repaint();
             signalTimer = false;
             ((Timer) e.getSource()).stop();
                }
                
           
        });
        signalTimer = false;
        timer.start();
        

             
             //dealing with aces.

        }
        repaint();
    }
     else if (ae.getSource() == stand){
         //dealer keeps choosing to draw a card or nah (with delay)
        //if the dealer's total is over 21 then auto lose
        //if the dealer's total is 21 then auto win
        //then when dealer stands....
        //whoever has the highest number wins!!
        
        //do: 1. "dealer is thinking". 2. wait. 3. see if its in threshhold if not draw.
        //while: the dealer's card total isnt in their threshold. 
       
             
              Timer timer2 = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            if (count%2==0) //every 4 seconds it says "Dealer is deciding"
            { 
             op = 3;
             Card nextCard3 = d.getNextCard(); //nextcard3
             repaint();
             count++;
            }
            else //two seconds after Dealer is deciding it says:
            {
                 if(hand2.inThreshold()) //if its in the threshold
                //dealer should stand!
                {
                    op=1;
                    repaint();
                    ((Timer) e.getSource()).stop(); //stops the loop!!
                    
                    if(hand2.isGreaterThan(hand1)) //if the dealer has a greater total
                        screenNum=6;
                    else if(hand1.isGreaterThan(hand2)) 
                        screenNum=7;
                    else //else if they tie!
                        screenNum=9;
                }
                else
                { //continues the loop: no  ((Timer) e.getSource()).stop();
                op=2;
                 Card nextCard3 = d.getNextCard();
                 nextCard3.turnOver();
                 hand2.addCardToHand(nextCard3);
                  if(nextCard3.getRank()==1) //if it's an ace
                 {
                     if(hand2.aceAs11InThreshold()) //if the ace
                     //is used as a 11 and the hand total meets threshhold
                     {
                         //set the ace as an 11
                         nextCard3.setValue(11);
                     }
    
                 }
                //if the dealer's total is over 21 then auto win
                 if(hand2.over21())
                {
                    //win
                    screenNum=5;
                   ((Timer) e.getSource()).stop(); //stops lose
                }
                 //if the dealer's total is 21 then auto lose
                if(hand2.equal21())
                {
                    //lose
                    screenNum=8;
                    ((Timer) e.getSource()).stop(); //stops lose
                }
                count++;
            }
                repaint();
            }

            }
        });
        
            
             op = 3;
             repaint();
            timer2.start();

    }
   }
     public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
     
    }

        
    public void mousePressed(MouseEvent e){
            //we have this event just to find out if the user clicked on an ace
            //and if the user did click on an ace
            //setItsEleven(boolean v) for that card

                        int max = hand1.getHand().size();
            int x = e.getX();
            int y = e.getY();
            int cardnum = (x/30)-1; //first card is "0th" in the arraylist
            //60 to 90 = 65/30 == 2. 
            if(y>=377 && y<=474) //if you clicked in the card dimensions
            {
                if(cardnum<max) //to prevent out of bounds error
                {
                    if(hand1.getHand().get(cardnum).getRank()==1) //if it's an ace
                    {
                        hand1.getHand().get(cardnum).switchItsEleven(); //makes it Eleven or 1 - switches value
                        repaint();
                           
                    }
                }
                //if they select the outer half of the last card available
                else
                {
                    if((x>=max*30) && x<=(max*30+73))
                    {
                        if(hand1.getHand().get(max-1).getRank()==1) //if it's an ace
                        {
                            hand1.getHand().get(max-1).switchItsEleven(); //makes it Eleven or 1 - switches value
                            repaint();
                                        
                        }
                    }
                }
            }

    }
    public void del(Button button1)
    {
            c.remove(button1);
            c.revalidate();
            c.repaint();
    }
    public void paint(Graphics g)
    {
        if(screenNum==0)
        {
            Image cardImage = getImage(getCodeBase(), "blackjackplay.png");
            g.drawImage(cardImage, 0, 0, 500, 500, this);
        }
        if(screenNum==1)
        {
            Color cc = new Color(0, 145, 15);
            g.setColor(cc);
            g.fillRect(0,0,500,500); //background
            Image cardImage = getImage(getCodeBase(), "blackjack.png");
            g.drawImage(cardImage, 0, 0, 500, 500, this);
            
            Font myFont = new Font ("Arial", Font.BOLD, 26);
            g.setFont(myFont);
            g.setColor(Color.white);
            g.drawString("Your current hand:", 0, 357); 
            g.drawString("Dealer's hand:", 0, 55); 
    }
            if(screenNum==3)
        {
            Font myFont = new Font ("Arial", Font.BOLD, 30);
            g.setFont(myFont);
            g.setColor(Color.red);
            g.drawString("You lose! You busted.", 0, 300); 
        }
            if(screenNum==4)
        {
            Font myFont = new Font ("Arial", Font.BOLD, 30);
            g.setFont(myFont);
            g.setColor(Color.cyan);
            g.drawString("You win! You got 21.", 0, 300); 
        }
            if(screenNum==5)
        {
            Font myFont = new Font ("Arial", Font.BOLD, 30);
            g.setFont(myFont);
            g.setColor(Color.cyan);
            g.drawString("You win! Dealer busted.", 0, 300); 
        }
            if(screenNum==6)
        {
            Font myFont = new Font ("Arial", Font.BOLD, 30);
            g.setFont(myFont);
            g.setColor(Color.red);
            g.drawString("You lose! Dealer is closer to 21.", 0, 300); 
        }
            if(screenNum==7)
        {
            Font myFont = new Font ("Arial", Font.BOLD, 30);
            g.setFont(myFont);
            g.setColor(Color.cyan);
            g.drawString("You win! You are closer to 21.", 0, 300); 
        }
            if(screenNum==8)
        {
            Font myFont = new Font ("Arial", Font.BOLD, 30);
            g.setFont(myFont);
            g.setColor(Color.red);
            g.drawString("You lose! Dealer got 21.", 0, 300); 
        }
            if(screenNum==9)
        {
            Font myFont = new Font ("Arial", Font.BOLD, 30);
            g.setFont(myFont);
            g.setColor(Color.yellow);
            g.drawString("It's a tie! You both have the same card total!", 0, 300); 
        }
            if(op==1)
        {
           //delay
            g.setColor(Color.black);
            g.fillRect(0,180,350,30);
            Font myFont = new Font ("Arial", Font.BOLD, 20);
            g.setFont(myFont);
            g.setColor(Color.red);
            g.drawString("Dealer chose to stand.", 0, 200); 
        }
            if(op==2)
        {
            g.setColor(Color.black);
            g.fillRect(0,180,350,30);
            Font myFont = new Font ("Arial", Font.BOLD, 20);
            g.setFont(myFont);
            g.setColor(Color.yellow);
            g.drawString("Dealer chose to hit.", 0, 200);
            
        }
            if(op==3)
        {
            g.setColor(Color.black);
            g.fillRect(0,180,350,30);
            Font myFont = new Font ("Arial", Font.BOLD, 20);
            g.setFont(myFont);
            g.setColor(Color.white);
            g.drawString("Dealer is thinking...", 0, 200);
            
        }
        
    //        super.paint(g);
        //paints the users cards
       int x = 30;
        for(Card c: hand1.getHand())
        {
            Image cardImage = getImage(getCodeBase(), c.getImageFileName());
            g.drawImage(cardImage, x, 377, 73, 97, this);
            if(c.getRank()==1) //if it's an ace.
            {
                if(c.itsEleven()==true)
                {
                    //paint 11 on it.
                    g.setColor(Color.black);
                    g.fillRect(x,420,30,30);
                    Font myFont = new Font ("Arial", Font.BOLD, 14);
                    g.setFont(myFont);
                    g.setColor(Color.white);
                    g.drawString("11", x+6, 443); 
                    c.setValue(11);
                }
                else
                {
                    //paint 1 on it
                    g.setColor(Color.black);
                    g.fillRect(x,420,30,30);
                    Font myFont = new Font ("Arial", Font.BOLD, 14);
                    g.setFont(myFont);
                    g.setColor(Color.white);
                    g.drawString("1", x+6, 443); 
                    c.setValue(1);
                }
            }
            x+=30;
        }
        int y = 30;
        //paints the dealers faced down cards.
        for(Card c: hand2.getHand())
        {
            Image cardImage = getImage(getCodeBase(), "card.gif"); //"card.gif"
            g.drawImage(cardImage, y, 67, 73, 97, this);
            y+=30;
        }
        if (screenNum>=3 && screenNum<=8) //paints the dealer's actual cards
        //in the end..
        {
            int z = 30;
            for(Card c: hand2.getHand())
            {
                Image cardImage = getImage(getCodeBase(), c.getImageFileName()); //"card.gif"
                g.drawImage(cardImage, z, 67, 73, 97, this);
                z+=30;
            }
        }
    }
        
    
    
    
    
    
    
    
    
    
    
    
    
}
