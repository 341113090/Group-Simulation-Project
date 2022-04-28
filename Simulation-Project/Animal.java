import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Animal here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Animal extends Animator {
    public enum State {
        Waiting, Searching, Following, Attacking, Running, Night, InShelter
    }

    protected State state;

    protected Actor target;

    // Animal variables
    protected double speed = 2; // Animal movement speed, increases more health decay when moving
    protected double healthDecay = 0.1; // How fast animals health goes down, hunger
    protected int attack = 1; // Animal attack damage, decreases health
    protected double attackDistance = 50;
    
    protected double size = 1;
    protected int maxHealth = 100; // Animal health/hp, decreases speed
    protected double senseRange = 200; // How far animal can detect threats/food, increases health decay
    protected double altruism = 0.5; // Chance of animal giving up its spot

    //
    protected double curHealth;
    protected double rotation;
    
    // Spawning Settings
    protected double sizeHealthModifier = 2;
    protected double speedDecayModifier = 0.1;
    protected double attackDistanceModifier = 1.5;
    protected double attackSenseModifier = 1.5;
    
    public static double randomness = 0.5;

    //
    protected int time;
    protected String direction;
    
    SuperStatBar hpBar;
    public Animal(){
        state = State.Searching;
        curHealth = maxHealth;
        hpBar = new SuperStatBar(maxHealth, (int)curHealth, this, 24, 4, 10, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img, 1000, 1000, 1, 1, 1, 16, 16, "Hidden"));
    }
    
    public Animal(double _speed, int _attack, double _size, double _altruism){
        state = State.Searching;
        curHealth = maxHealth;
        hpBar = new SuperStatBar(maxHealth, (int)curHealth, this, 24, 4, 10, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img, 1000, 1000, 1, 1, 1, 16, 16, "Hidden"));
        
        speed = _speed;
        healthDecay = _speed*speedDecayModifier;
        attack = _attack;
        senseRange = senseRange/(attack*attackSenseModifier);
        attackDistance  = attackDistance/(attack*attackDistanceModifier);
        altruism = _altruism;
        
        curHealth = maxHealth;
    }
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
            case InShelter:
                InShelter();
                break;
        }

        //System.out.println(state);

        Behaviour();
        Animations();
    }


    protected void Behaviour() {
        if (MainWorld.night){
            state = State.Night;
        }
        
        
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
        Shelter target = targetShelter(false);
        // Temp night
        if (MainWorld.getDistance(this, target) <= 5){
            playAnimation("Hidden");
            System.out.println("Hidden");
            state = State.InShelter;
        }   else {
            turnTowards(target.getX(), target.getY());
            rotation = getRotation();

            move(speed);
            System.out.println("Moving");
        }
        if (!MainWorld.night){
            state = State.Searching;
        }
    }
    
    protected void InShelter(){
        
            playAnimation("Hidden");
            if (!MainWorld.night){
            state = State.Searching;
        }
    }

    ////// FUNCTIONS /////
    protected void MoveTowardsObject(Actor target) {
        turnTowards(target.getX(), target.getY());
        rotation = getRotation();

        move(speed);
    }

    
    protected Shelter targetShelter(boolean type) {

        double closestTargetDistance = 0;
        double distanceToActor;
        // Get a list of all plants in the World, cast it to ArrayList
        // for easy management
        

        ArrayList<Shelter> shelters= (ArrayList) getObjectsInRange(10000, Shelter.class);
        
        // Filter out shelters that aren't the correct type
        for (int i = 0; i < shelters.size(); i++){
            if (shelters.get(i).getTypeAnimal() != type){
                shelters.remove(shelters.get(i));
            }
        }
        if (shelters.size() > 0) {
            
            // set the first one as my target
            Shelter targetShelter = shelters.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = MainWorld.getDistance(this, targetShelter);

            // Loop through the objects in the ArrayList to find the closest target
            for (Shelter o : shelters) {

                // Cast for use in generic method
                // Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = MainWorld.getDistance(this, o);
                // If I find a plant closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance) {
                    targetShelter = o;
                    closestTargetDistance = distanceToActor;
                }

            }
            
            return targetShelter;

        }
        return null;

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
    
    ///// Getters /////
    public double getSpeed(){
        return speed;
    }
    public int getAttack(){
        return attack;
    }
    public double getSize(){
        return size;
    }
    public double getAltruism(){
        return altruism;
    }
    
}
