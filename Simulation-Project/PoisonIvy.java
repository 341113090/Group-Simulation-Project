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
    public PoisonIvy()
    {
        numPoisonIvy++;
        this.setImage(ivy);
        toughness = .8;//poison ivey is weaker than cherry
        totalSeeds = 1;//poison ivey is less fertile
        health = 200;
        healthPerTick = 8;
        isToxic = true;
        wantsCarry = false;
        //getter not needed
        healthLimit = 100;
        selfHealSpeed = 3;
    }
    
    public static int getNumPoisonIvy()
    {
        return numPoisonIvy;
    }
    
    public static void setNumPoisonIvy(int xx)
    {
        numPoisonIvy = xx;
    }

    
    /**
     * Act - do whatever the Harmful wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
