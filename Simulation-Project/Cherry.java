import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cherry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cherry extends Plant
{
    private static int numCherries = 0;
    GreenfootImage cherry = AnimationManager.getSlice(Plants, 13, 7);
    
    public Cherry()
    {
        numCherries++;
        this.setImage(cherry);
        toughness = .4;
        health = 1000;
        maxHealth = health;
        healthPerTick = 8;
        isToxic = false;
        wantsCarry = false;
        //getter not needed
        healthLimit = 750;
        selfHealSpeed = 3;
        
    }
    
    public static int getNumCherries()
    {
        return numCherries;
    }
    
    public static void setNumCherries(int xx)
    {
        numCherries = xx;
    }
    
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
