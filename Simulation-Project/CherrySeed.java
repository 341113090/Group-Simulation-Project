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
    GreenfootImage Plants = new GreenfootImage("Plants.png");
    GreenfootImage cherryseed = AnimationManager.getSlice(Plants,9, 0);
    
    public CherrySeed (int totalActs){ // when calling this, set it to ~60(1 sec)
        this.setImage(cherryseed);
        cherryseed.scale(30,30);
        this.totalActs = totalActs;
        actCounter = totalActs;
    }
    /**
     * CherrySeed will remove itself after one second after spawning and will
     * drop three Cherrys in a 
     */
    public void act()
    {
        if (actCounter > 0){
            actCounter--;
            if (actCounter < 60){
                cherryseed.setTransparency (actCounter * 2);
            }
        } else {
            Cherry cherry = new Cherry();
            if(this.getX()<500 && this.getX()>75){
                getWorld().addObject(cherry, this.getX(), this.getY());
            }
            getWorld().removeObject(this);
        }
    }
}
