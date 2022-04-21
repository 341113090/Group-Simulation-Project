import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Herbivore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Herbivore extends Animal
{
    private Plant targetPlant;
    private ArrayList<Plant> plants;
    
    private int mySpeed = 10;
    
    public Herbivore(){
        
        ///// Setting up animations /////
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img,9*16,0*16,1, 1,1,16,16, "Idle Side"));
        addAnimation(AnimationManager.createAnimation(img,10*16,0*16,1, 1,1,16,16, "Idle Up"));
        addAnimation(AnimationManager.createAnimation(img,11*16,0*16,1, 1,1,16,16, "Idle Down"));
        
        
        addAnimation(AnimationManager.createAnimation(img,9*16,4*16,1, 4,4,16,16, "Walk Side"));
        addAnimation(AnimationManager.createAnimation(img,10*16,4*16,1, 4,4,16,16, "Walk Up"));
        addAnimation(AnimationManager.createAnimation(img,11*16,4*16,1, 4,4,16,16, "Walk Down"));
    
        setImage(animations[1].getImage(0));

        playAnimation("Walk Side");
        state=State.Waiting;
        
        senseRange = 50;
    }
    /**
     * Act - do whatever the Herbivore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        // Add your action code here.
        
        targetClosestPlant();
        
        if (targetPlant != null && targetPlant.getWorld() != null)
            {
                moveTowardOrEatPlant();
            }
            // If I can't find anything to eat, move in a random direction
            else
            {
                moveRandomly();
            }
            
        
        setRotation(0);
    }
    
    public void started(){
        
    }
    
    public void stopped(){
        System.out.println("How");
    }
    
    private void targetClosestPlant (){
        
        double closestTargetDistance = 0;
        double distanceToActor;
        int numplants;
        // Get a list of all plants in the World, cast it to ArrayList
        // for easy management
        
        numplants = getWorld().getObjects(Plant.class).size();
        
        plants = (ArrayList)getObjectsInRange(senseRange, Plant.class);
             
        if (plants.size() > 0)
        {
            
            // set the first one as my target
            targetPlant = plants.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = MainWorld.getDistance(this, targetPlant);
            
            // Loop through the objects in the ArrayList to find the closest target
            for (Plant o : plants)
            {
                
                // Cast for use in generic method
                //Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = MainWorld.getDistance(this, o);
                // If I find a plant closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance)
                {
                    targetPlant = o;
                    closestTargetDistance = distanceToActor;
                }
                
            }
            
        }
       
    }
    
    /**
     * Private method, called by act(), that moves toward the target,
     * or eats it if within range.
     */
    private void moveTowardOrEatPlant ()
    {
        turnTowards(targetPlant.getX(), targetPlant.getY());

        
        if (this.getNeighbours (30, true, Plant.class).size() > 0)
        {
            // If I was able to eat, increase by life by Plant's nibble power
            
            /*
            int tryToEat = targetPlant.nibble();
            if (tryToEat > 0 && hp < maxHP)
            {
                hp += tryToEat;
            }
            */
            
        }
        else
        {
            move (mySpeed);
        }
    }
    
    /**
     * A method to be used for moving randomly if no target is found. Will mostly
     * just move in its current direction, occasionally turning to face a new, random
     * direction.
     */
    private void moveRandomly ()
    {
        if (Greenfoot.getRandomNumber (100) == 50)
        {
            turn (Greenfoot.getRandomNumber(360));
        }
        else
            move (mySpeed);
    }
    
    
    
}
