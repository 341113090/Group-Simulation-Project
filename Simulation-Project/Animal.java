import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animal here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Animal extends Animator {
    public enum State {
        Waiting, Searching, Following, Attacking, Running, Night
    }

    protected State state;

    protected Actor target;

    // Animal variables
    protected double speed; // Animal movement speed, increases more health decay when moving
    protected int attack; // Animal attack damage, decreases health
    protected int maxHealth; // Animal health/hp, decreases speed
    protected int size;
    protected int senseRange; // How far animal can detect threats/food, increases health decay
    protected double healthDecay; // How fast animals health goes down, hunger
    protected double altruism; // Chance of animal giving up its spot

    //
    protected double curHealth;
    protected double rotation;

    //
    protected int time;
    protected String direction;

    /**
     * Act - do whatever the Animal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();
        // Add your action code here.

        // Super basic terrible awful statemanager

        switch (state) {
            case Waiting:
                Waiting();
                break;
            case Searching:
                Searching();
                break;
            case Following:
                Following();
                break;
            case Attacking:
                Attacking();
                break;
            case Night:
                Night();
                break;
        }

        //System.out.println(state);

        Behaviour();
        Animations();
    }

    protected void Behaviour() {

    }

    ///// STATES /////
    protected void Waiting() {

    }

    protected void Searching() {

    }

    protected void Following() {

    }

    protected void Attacking() {

    }

    protected void Night() {

    }

    ////// FUNCTIONS /////
    protected void MoveTowardsObject(Actor target) {

    }

    protected void Animations() {
        if (rotation > 45&& rotation <= 135){
            direction = "Down";
        }   else if (rotation > 135&& rotation <= 225){
            direction = "Left";
        }   else if (rotation > 225&& rotation <= 305){
            direction = "Up";
        }   else{
            direction = "Right";
        }
        
        //System.out.println(direction+","+getRotation());
        
    }
}
