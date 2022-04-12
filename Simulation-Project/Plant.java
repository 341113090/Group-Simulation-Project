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
    
    /**
     * returns the health of plant
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * returns the health given by the plant
     */
    public int giveHealth(){
        return healthGiven;
    }
    
    /**
     * returns the number of seeds of the plant
     */
    public int getSeeds(){
        return totalSeeds;
    }
    
    /**
     * return the toughness of the plant
     */
    public int getToughness(){
        return toughness;
    }
    
    /**
     * returns whether the plant is toxic or not
     */
    public boolean isToxic(){
        return isToxic;
    }
    
    /**
     * returns whether the plant wants animals to eat it to carry seeds or not
     */
    public boolean wantsCarry(){
        return wantsCarry;
    }
    
    
    
    
    
    
    
    
}
