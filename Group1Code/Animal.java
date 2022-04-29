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
    protected boolean type; // False == herbivore, true == carnivore

    protected Actor target;

    // Animal variables
    protected double speed = 2; // Animal movement speed, increases more health decay when moving
    protected double healthDecay = 0.1; // How fast animals health goes down, hunger
    protected double attack = 1; // Animal attack damage, decreases health
    protected double attackDistance = 50;
    
    protected double size = 3;
    protected int maxHealth = 300; // Animal health/hp, decreases speed
    protected double senseRange = 500; // How far animal can detect threats/food, increases health decay
    protected double altruism = 0.5; // Chance of animal giving up its spot

    //
    protected double curHealth;
    protected double rotation;
    
    // Spawning Settings
    protected double sizeHealthModifier = 200;
    protected double speedDecayModifier = 0.25;
    protected double attackDistanceModifier = 1.5;
    protected double attackSenseModifier = 1.5;
    
    public static double randomness = 0.3;

    //
    protected int time;
    protected String direction;
    protected boolean isLeft = false;
    
    protected Shelter targetShelter;
    
    SuperStatBar hpBar;
    public Animal(){
        state = State.Searching;
        curHealth = maxHealth;
        hpBar = new SuperStatBar(maxHealth, (int)curHealth, this, 24, 4, 10, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img, 1000, 1000, 1, 1, 1, 16, 16, "Hidden"));
    }
    
    public Animal(double _speed, double _attack, double _size, double _altruism){
        SetValues();
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
        size = _size;
        maxHealth = (int)(size*sizeHealthModifier);
        
        curHealth = maxHealth;
        
        
        //System.out.println(speed);
    }
    
    public void SetValues(){
        speed = 2; // Animal movement speed, increases more health decay when moving
    healthDecay = 0.1; // How fast animals health goes down, hunger
    attack = 1; // Animal attack damage, decreases health
    attackDistance = 50;
    
    size = 3;
    maxHealth = 300; // Animal health/hp, decreases speed
    senseRange = 400; // How far animal can detect threats/food, increases health decay
    altruism = 0.5; // Chance of animal giving up its spot
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
        if (MainWorld.night && state != State.InShelter){
            state = State.Night;
        }
        
        // Health Decay
        if (state != State.InShelter){
            curHealth -= healthDecay;
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
        if (!MainWorld.night){
            state = State.Searching;
            return;
        }
        // Get the closest shelter to start
        if (targetShelter== null ){
            double closestTargetDistance = 0;
            
            
            ArrayList<Shelter> shelters= (ArrayList) getObjectsInRange(10000, Shelter.class);
            
            // Filter out shelters that aren't the correct type
            for (int i = 0; i < shelters.size(); i++){
                if (shelters.get(i).getTypeAnimal() != type){
                    shelters.remove(shelters.get(i));
                }
            }
            if (shelters.size() > 0) {
                
                // set the first one as my target
                targetShelter = shelters.get(0);
                // Use method to get distance to target. This will be used
                // to check if any other targets are closer
                closestTargetDistance = MainWorld.getDistance(this, targetShelter);
    
                // Loop through the objects in the ArrayList to find the closest target
                for (Shelter o : shelters) {
    
                    // Cast for use in generic method
                    // Measure distance from me
                    double distanceToActor = MainWorld.getDistance(this, o);
                    if (distanceToActor < closestTargetDistance) {
                        targetShelter = o;
                        closestTargetDistance = distanceToActor;
                    }
    
                }
            
            }
        }
        
        if (MainWorld.getDistance(this, targetShelter) <= 5){
            // Check if shelter has space
            
            if (targetShelter.addAnimal(this)){
                state = State.InShelter;
                //System.out.println("bruh");
            } else { // If no space find shelter again 
                //Kinda sucks that i need to copy paste the whole ass shelter searching function but I don't have time and can't think of a better solution
                double closestTargetDistance = 0;
                ArrayList<Shelter> shelters= (ArrayList) getObjectsInRange(10000, Shelter.class);
                
                // Filter out shelters that aren't the correct type
                for (int i = 0; i < shelters.size(); i++){
                    if (shelters.get(i).getTypeAnimal() != type){
                        shelters.remove(shelters.get(i));
                    }
                }
                if (shelters.size() > 0) {
                    
                    // grab first target that is not current target
                    Shelter target = shelters.get(0);
                    for (int i = 0; i < shelters.size(); i++){
                        if (shelters.get(i) != targetShelter){
                            target = shelters.get(i);
                        }
                    }
                    
                    // Use method to get distance to target. This will be used
                    // to check if any other targets are closer
                    closestTargetDistance = MainWorld.getDistance(this, targetShelter);
        
                    // Loop through the objects in the ArrayList to find the closest target
                    for (Shelter o : shelters) {
                        // Measure distance from me
                        double distanceToActor = MainWorld.getDistance(this, o);
                        if (o != targetShelter && distanceToActor < closestTargetDistance) {
                            target = o;
                            closestTargetDistance = distanceToActor;
                        }
    
                    }
                    targetShelter = target;
            
                }
                
            }
        }   else { // Move towards currently set shelter
            turnTowards(targetShelter.getX(), targetShelter.getY());
            rotation = getRotation();

            move(speed);
            playAnimation("Walk "+direction);
        }
        
    }
    
    protected void InShelter(){
        playAnimation("Hidden");
        if (!MainWorld.night){
            curHealth = maxHealth;
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
                // Measure distance from me
                distanceToActor = MainWorld.getDistance(this, o);
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
            isLeft = false;
        }   else if (rotation > 135&& rotation <= 225){
            direction = "Left";
            isLeft = true;
        }   else if (rotation > 225&& rotation <= 305){
            direction = "Up";
            isLeft = false;
        }   else{
            direction = "Right";
            isLeft = false;
        }
        
        //System.out.println(direction+","+getRotation());
        
    }
    
    public void setFullHealth(){
        curHealth = maxHealth;
    }
    
    ///// Getters /////
    public double getSpeed(){
        
        return speed;
    }
    public double getAttack(){
        return attack;
    }
    public double getSize(){
        return size;
    }
    public double getAltruism(){
        return altruism;
    }
    public boolean getType(){
        return type;
    }
    public double getHealth(){
        return curHealth;
    }
    public double getMaxHealth(){
        return curHealth;
    }
    
}
