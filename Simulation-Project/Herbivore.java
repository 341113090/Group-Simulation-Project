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
        addAnimation(AnimationManager.createAnimation(img,3*16,8*16,1, 8,8,16,16, "Idle"));
        setImage(animations[0].getImage(0));
        //playAnimation(0);
        state=State.Waiting;
    }
    /**
     * Act - do whatever the Herbivore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        // Add your action code here.
        playAnimation("Idle");    
        playAnimation(0);
        
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
        
        // If any plants are found
        if (numplants > 50) // If lots of plants are found, search small area
        {
             plants = (ArrayList)getObjectsInRange(80, Plant.class);
        }
        
        else if (numplants > 20) // If less plants are found, search wider radius
        {
            plants = (ArrayList)getObjectsInRange(180, Plant.class);
        }
        else    // If even fewer plants are found, search the whole World
        {    
            plants = (ArrayList)getWorld().getObjects(Plant.class);
        }
             
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
