import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Carnivore here.
 * 
 * @author Ethan
 * @version April 24, 2022
 */
public class Carnivore extends Animal {

    private Herbivore targetHerbivore;    

    private ArrayList<Herbivore> herbivores;

    private int mySpeed = 3;

    ////////// CONSTRUCTOR //////////
 
    public Carnivore() {

        ///// Setting up animations /////
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img, 0 * 16, 8 * 16, 1, 1, 1, 16, 16, "Idle Side"));
        addAnimation(AnimationManager.createAnimation(img, 1 * 16, 8 * 16, 1, 1, 1, 16, 16, "Idle Down"));
        addAnimation(AnimationManager.createAnimation(img, 2 * 16, 8 * 16, 1, 1, 1, 16, 16, "Idle Up"));

        addAnimation(AnimationManager.createAnimation(img, 0 * 16, 8 * 16, 1, 8, 8, 16, 16, "Walk Side"));
        addAnimation(AnimationManager.createAnimation(img, 1* 16, 8 * 16, 1, 8, 8, 16, 16, "Walk Down"));
        addAnimation(AnimationManager.createAnimation(img, 2 * 16, 8 * 16, 1, 8, 8, 16, 16, "Walk Up"));

        setImage(animations[1].getImage(0));

        playAnimation("Walk Side");

        state = State.Searching;

        senseRange = 600;
    }
    
    ////////// GREENFOOT FUNCTIONS //////////
    /**
     * Act - do whatever the Carnivore wants to do. This method is called whenever
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
     * The searching state of Carnivore
     * 
     * Every 600 acts, the animal will switch directions until a Carnivore comes into
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
        }
        setRotation(rotation);
        move(mySpeed);

        if (targetClosestHerbivore() == 1) {
            state = State.Following;
        }
        
        playAnimation("Walk "+direction);
    }

    /**
     * The following state of Carnivore
     * 
     * The Carnivore will go towards a preset target until it is within 15 pixels
     */
    ///// TODO: FIGURE OUT IF CarnivoreS SHOULD ATTACK CARNIVORES OR JUST RUN AWAY

   protected void Following() {
        turnTowards(targetHerbivore.getX(), targetHerbivore.getY());
        rotation = getRotation();

        move(mySpeed);

        if (this.getNeighbours(10, true, Herbivore.class).size() > 0) {
            // If I was able to eat, increase by life by Herbivore's nibble power

            state = State.Attacking;

            /*
             * double tryToEat = targetHerbivore.eatHerbivore();
             * if (tryToEat > 0 && curHealth < maxHealth)
             * {
             * curHealth += tryToEat;
             * }
             */

        }
        
        playAnimation("Walk "+direction);
    }

    protected void Attacking() {
        double tryToEat = targetHerbivore.eatHerbivore();
        if (tryToEat > 0 && curHealth < maxHealth) {
            curHealth += tryToEat;
        }
        if (targetHerbivore == null || targetHerbivore.getWorld() == null) {
            state = State.Searching;

        }
        
        playAnimation("Idle "+direction);
    }

    ////////// FUNCTIONS //////////
    
    private int targetClosestHerbivore() {

        double closestTargetDistance = 0;
        double distanceToActor;
        int numHerbivores;
        // Get a list of all Herbivores in the World, cast it to ArrayList
        // for easy management

        numHerbivores = getWorld().getObjects(Herbivore.class).size();

        herbivores = (ArrayList) getObjectsInRange(senseRange, Herbivore.class);

        if (herbivores.size() > 0) {

            // set the first one as my target
            targetHerbivore = herbivores.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = MainWorld.getDistance(this, targetHerbivore);

            // Loop through the objects in the ArrayList to find the closest target
            for (Herbivore o : herbivores) {

                // Cast for use in generic method
                // Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = MainWorld.getDistance(this, o);
                // If I find a Herbivore closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance) {
                    targetHerbivore = o;
                    closestTargetDistance = distanceToActor;
                    return 1;
                }

            }

        }
        return 0;

    }

    ////////// OLD //////////

    /**
     * Private method, called by act(), that moves toward the target,
     * or eats it if within range.
     */
 
    private void moveTowardOrEatHerbivore() {
        turnTowards(targetHerbivore.getX(), targetHerbivore.getY());

        if (this.getNeighbours(30, true, Herbivore.class).size() > 0) {
            // If I was able to eat, increase by life by Herbivore's nibble power

            double tryToEat = targetHerbivore.eatHerbivore();
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

}
