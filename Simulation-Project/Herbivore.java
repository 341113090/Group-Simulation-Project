import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Herbivore here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Herbivore extends Animal {
    private Plant targetPlant;
    private ArrayList<Plant> plants;

    private static int numHerbivores = 0;
    
    private int mySpeed = 2;
    
    private int numSeeds = 0;

    ////////// CONSTRUCTOR //////////

    public Herbivore() {
        numHerbivores++;
        ///// Setting up animations /////
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img, 9 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Side"));
        addAnimation(AnimationManager.createAnimation(img, 10 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Up"));
        addAnimation(AnimationManager.createAnimation(img, 11 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Down"));

        addAnimation(AnimationManager.createAnimation(img, 9 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Side"));
        addAnimation(AnimationManager.createAnimation(img, 10 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Up"));
        addAnimation(AnimationManager.createAnimation(img, 11 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Down"));

        setImage(animations[1].getImage(0));

        playAnimation("Walk Side");

        state = State.Searching;

        senseRange = 200;
    }

    ////////// GREENFOOT FUNCTIONS //////////
    /**
     * Act - do whatever the Herbivore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();
        setRotation(rotation);

        setRotation(0);
    }

    public void started() {
    }

    public void stopped() {
        System.out.println("How");
    }

    ////////// STATES //////////
    /**
     * The searching state of Herbivore
     * 
     * Every 600 acts, the animal will switch directions until a plant comes into
     * its sensory range
     * It will then switch its state to following
     */
    private int randomDirectionDelay = 60;

    protected void Searching() {
        if (time < randomDirectionDelay) {
            time++;
        } else {
            time = 0;
            rotation = Greenfoot.getRandomNumber(360);
            
            if (rotation > 45&& rotation <= 135){
               System.out.println("Down");
            }   else if (rotation > 135&& rotation <= 225){
               System.out.println("Left");
            }   else if (rotation > 225&& rotation <= 305){
               System.out.println("Up");
            }   else{
               System.out.println("Right");
            }
        }
        
        // Check if at edge
        if (MainWorld.onEdge(getX(), getY())){
            rotation = Greenfoot.getRandomNumber(360);
            MainWorld.PlaceOnEdge(this);
        }
         
        setRotation(rotation);
        move(mySpeed);
        System.out.println(getRotation());
        

        if (targetClosestPlant() == 1) {
            //state = State.Following;
        }
        
        
        playAnimation("Walk "+direction);
    }

    /**
     * The following state of Herbivore
     * 
     * The herbivore will go towards a preset target until it is within 15 pixels
     */
    ///// TODO: FIGURE OUT IF HERBIVORES SHOULD ATTACK CARNIVORES OR JUST RUN AWAY
    protected void Following() {
        turnTowards(targetPlant.getX(), targetPlant.getY());
        rotation = getRotation();

        move(mySpeed);

        if (this.getNeighbours(10, true, Plant.class).size() > 0) {
            // If I was able to eat, increase by life by Plant's nibble power

            state = State.Attacking;
            /*
             * double tryToEat = targetPlant.eatPlant();
             * if (tryToEat > 0 && curHealth < maxHealth)
             * {
             * curHealth += tryToEat;
             * }
             */

        }
    
        
        playAnimation("Walk "+direction);
    }

    protected void Attacking() {
        double tryToEat = targetPlant.eatPlant();
        if (tryToEat > 0 && curHealth < maxHealth) {
            curHealth += tryToEat;
        }
        if (targetPlant == null || targetPlant.getWorld() == null) {
            state = State.Searching;

        }
        playAnimation("Idle "+direction);
    }

    ////////// FUNCTIONS //////////

    private int targetClosestPlant() {

        double closestTargetDistance = 0;
        double distanceToActor;
        int numplants;
        // Get a list of all plants in the World, cast it to ArrayList
        // for easy management

        numplants = getWorld().getObjects(Plant.class).size();

        plants = (ArrayList) getObjectsInRange(senseRange, Plant.class);

        if (plants.size() > 0) {

            // set the first one as my target
            targetPlant = plants.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = MainWorld.getDistance(this, targetPlant);

            // Loop through the objects in the ArrayList to find the closest target
            for (Plant o : plants) {

                // Cast for use in generic method
                // Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = MainWorld.getDistance(this, o);
                // If I find a plant closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance) {
                    targetPlant = o;
                    closestTargetDistance = distanceToActor;
                    return 1;
                }

            }

        }
        return 0;

    }
    
    public double eatHerbivore(int damage) {
        if (curHealth >= damage) {
            curHealth -= damage;// temporary, will depend on plant health
            return damage;
        } else {
            int lastHealth = (int)curHealth;
            curHealth = 0;
            
            
            getWorld().removeObject(this);
            return lastHealth;
        }
    }
    ////////// OLD //////////

    /**
     * Private method, called by act(), that moves toward the target,
     * or eats it if within range.
     */
    private void moveTowardOrEatPlant() {
        turnTowards(targetPlant.getX(), targetPlant.getY());

        if (this.getNeighbours(30, true, Plant.class).size() > 0) {
            // If I was able to eat, increase by life by Plant's nibble power

            double tryToEat = targetPlant.eatPlant();
            if (tryToEat > 0 && curHealth < maxHealth) {
                curHealth += tryToEat;
            }

        } else {
            move(mySpeed);
        }
    }

    private void moveRandomly() {
        if (Greenfoot.getRandomNumber(1000) == 50) {
            rotation = Greenfoot.getRandomNumber(360);
            setRotation(rotation);
        } else
            move(mySpeed);
    }
    
    public static int getNumHerbivores()
    {
        return numHerbivores;
    }
    
    public static void setNumHerbivores(int xx)
    {
        numHerbivores = xx;
    }
}
