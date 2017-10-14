
public enum Face
{
    ACE("a",1,1), TWO("2",2,2), THREE("3",3,3), 
    FOUR("4",4,4), FIVE("5",5,5), SIX("6",6,6), 
    SEVEN("7",7,7), 
    EIGHT("8",8,8),NINE("9",9,9), TEN("t",10,10), 
    JACK("j",11,10), QUEEN("q",12,10),KING("k",13,10);
    
    private String fileLetter;
    private int rank, value;
    
    private Face(String s, int r, int v)
    {
        fileLetter = s;
        rank = r;
        value = v;
    }
    
    public int getRank()
    {
        return rank;
    }
    
    public void setRank(int r)
    {
        rank = r;
    }
    
    public void setValue(int v)
    {
        value = v;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public String getFileLetter()
    {
        return fileLetter;
    }
}
    

