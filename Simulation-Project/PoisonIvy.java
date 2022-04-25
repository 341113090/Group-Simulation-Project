import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Harmful here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PoisonIvy extends Plant
{
    private static int numPoisonIvy = 0;
    GreenfootImage ivy = AnimationManager.getSlice(Plants,12, 3);
    private double toxicityTakenAway = 10;
    
    public PoisonIvy()
    {
        numPoisonIvy++;
        this.setImage(ivy);
        toughness = .8;//poison ivey is weaker than cherry
        health = 200;
        maxHealth = health;
        healthPerTick = 8;
        isToxic = true;
        wantsCarry = false;
        //getter not needed
        healthLimit = 100;
        selfHealSpeed = 3;
    }
    
    /**
     * Returns the number of Poison Ivy in the world. 
     */
    public static int getNumPoisonIvy()
    {
        return numPoisonIvy;
    }
    
    /**
     * Sets the number of Poison Ivy in the world.
     */
    public static void setNumPoisonIvy(int xx)
    {
        numPoisonIvy = xx;
    }
    
    /**
     * Checks if this instance of Poison Ivy is eaten and subtracts one from
     * the overall number of Poison Ivy in the world.
     */
    public void deathCheck()
    {
        if(health == 0){
            numPoisonIvy--;
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
