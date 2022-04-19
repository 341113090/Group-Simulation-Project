import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CherrySeed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CherrySeed extends Actor
{
    /**
     * Act - do whatever the CherrySeed wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int seeds;
    private int totalActs, actCounter;
    private GreenfootImage image;
    GreenfootImage Plants = new GreenfootImage("Plants.png");
    GreenfootImage cherryseed = AnimationManager.getSlice(Plants,8, 1);
    
    public CherrySeed (int totalActs){ // when calling this, set it to ~60(1 sec)
        this.setImage(cherryseed);
        this.totalActs = totalActs;
        actCounter = totalActs;
        seeds = 3; 
    }
    
    public void act()
    {
        if (actCounter > 0){
            actCounter--;
            if (actCounter < 60){
                image.setTransparency (actCounter * 2);
            }
        } else {
            getWorld().removeObject(this);
            Cherry cherry = new Cherry();
            getWorld().addObject(cherry, this.getX(), this.getY());
        }
    }
    
    public int getSeeds(){
        return seeds;
    }
}
