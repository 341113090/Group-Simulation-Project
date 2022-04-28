import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cherry seeds are pooped out by herbivores and will sprout into cherries at the start of each new day
 * 
 * @author Max and Nathan
 * @version April 27, 2022
 */
public class CherrySeed extends Actor
{
    private int seeds;
    private int totalActs, actCounter;
    private static boolean canSprout = false;
    GreenfootImage Plants = new GreenfootImage("Plants.png");
    GreenfootImage cherryseed = AnimationManager.getSlice(Plants,9, 0);
    
    /**
     * Constructor for cherry seed. Creates a cherry with a set image and uses parameter to set the time it takes to sprout
     */
    public CherrySeed (int totalActs){ // when calling this, set it to ~120(2 sec)
        this.setImage(cherryseed);
        cherryseed.scale(30,30);
        this.totalActs = totalActs;
        actCounter = totalActs;
    }
    
    /**
     * CherrySeed will remove itself after two seconds after spawning and drop
     * one cherry in place of it
     */
    public void act()
    {
        if(canSprout)
        {
            if (actCounter > 0){
                actCounter--;
                if (actCounter < 60){
                    cherryseed.setTransparency (actCounter * 2);
                }
            } else if (Cherry.getNumCherries() > 29){
                getWorld().removeObject(this);
            } else if(this.getX() > 700 || this.getX()<100){
                getWorld().removeObject(this);
            }else{
                Cherry cherry = new Cherry();
                getWorld().addObject(cherry, this.getX(), this.getY());
                getWorld().removeObject(this);
            }
        }
    }
    
    /**
     * Method that changes whether the cherry is allowed to sprout or not
     */
    public static void toggleCanSprout(boolean x)
    {
        canSprout = x;
    }
}
