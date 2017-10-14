
import java.awt.*;
import javax.swing.*;


public class ShowOneCard extends JApplet
{
    private Deck d;
    private Card c;
    
    public void init() 
    {
        d = new Deck();
        d.shuffle();
        
        c = d.getNextCard();
        c.turnOver();
    }
    
    public void paint(Graphics g)
    {
        
        Color cc = new Color(0, 145, 15);
        g.setColor(cc);
        g.fillRect(0,0,500,500); //background
        
        Font myFont = new Font ("Arial", Font.BOLD, 36);
        g.setFont(myFont);
        g.setColor(Color.white);
        g.drawString("Your current hand:", 0, 350); 
        
        Image cardImage = getImage(getCodeBase(), c.getImageFileName());
        g.drawImage(cardImage,0 ,377 ,73, 97,this);

    }
    
        
}
