import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Plants are eaten by animals and provide them with nutrients. Plants also try and 
 * survive until the end of the day so that they can reproduce. Poisonous plants 
 * reproduce by surviving and edible plants reproduce by having their seeds eaten.
 * 
 * @author Nathan and Max
 * @version April 12, 2022
 */
public class Plant extends Actor
{
    
    /**
     * Act - do whatever the Plant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //getter required
    protected int toughness;
    protected int totalSeeds;
    protected int health;
    protected int healthGiven;
    protected int fertility;
    protected boolean isToxic;
    protected boolean wantsCarry;
    
    //getter not needed
    protected int healthLimit;
    protected int rateOfGrowth;
    protected int selfHealSpeed;
        
    public void act()
    {
        // Add your action code here.
    }
    
    public void die(){
        
    }
    
    public int getHealth(){
        return health;
    }
    
    public int giveHealth(){
        return healthGiven;
    }
    
    public int getSeeds(){
        return totalSeeds;
    }
    
    public int getToughness(){
        return toughness;
    }
    
    public boolean isToxic(){
        return isToxic;
    }
    
    public boolean wantsCarry(){
        return wantsCarry;
    }
    
    
    
    
    
    
    
    
}
