import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cherries are plants that are eaten by herbivores and reproduce by using seeds
 * PUT INTO HERBIVORE
 * IF FULL HEALTH CAN STILL EAT
 * THEN CHANGE THE PLANT HEALTH EATEN TO GET A SEED
 * 
 * @author Max and Nathan
 * @version April 27, 2022
 */
public class Cherry extends Plant
{
    private static int numCherries = 0;
    GreenfootImage cherry = AnimationManager.getSlice(Plants, 13, 7);
    
    /**
     * Constructor for cherry, sets the toughness, health, toxicity, heal speed,
     * health recovery limit and health per tick. 
     */
    public Cherry()
    {
        //increases number of cherries and set their image
        numCherries++;
        this.setImage(cherry);
        //cherries take 40% damage from all sources
        toughness = .4;
        health = 200;
        maxHealth = health;
        //how much health they lose per bite; must be multiplied with toughness to get actual health lost
        healthPerTick = 2;
        //wont hurt herbs that try to eat it
        isToxic = false;
        //isnt a seed reproducing plant
        wantsCarry = false;
        //getter not needed
        healthLimit = 150;
        selfHealSpeed = 2;
        
    }
    
    /**
     * Returns the number of Cherries in the world
     */
    public static int getNumCherries()
    {
        return numCherries;
    }
    
    /**
     * Sets the counter for the number of cherries in the world
     */
    public static void setNumCherries(int xx)
    {
        numCherries = xx;
    }
    
    /**
     * Checks if the cherry has lost all its health; if so, remove it
     */
    public void deathCheck()
    {
        if(health == 0){
            numCherries--;
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Calls superclass act().
     */
    public void act()
    {
        super.act();
    }
}
