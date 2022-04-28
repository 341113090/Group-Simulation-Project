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
    
    // Base stats
    private int numSeeds;
    protected double speed = 2; // Animal movement speed, increases more health decay when moving
    protected int attack = 1; // Animal attack damage, decreases health
    protected int maxHealth = 100; // Animal health/hp, decreases speed
    protected double  size = 1;
    protected int senseRange = 200; // How far animal can detect threats/food, increases health decay
    protected double healthDecay = 0.1; // How fast animals health goes down, hunger
    protected double altruism = 0.5; // Chance of animal giving up its spot
    protected int attackDistance = 50;
    private int poopTime;
    private int specialTimer = 0;
    private boolean isPoopTime = false;
    private int currentTime = 0;
    private double healthEaten = 0;
    private static int numHerbivores = 0;
    ////////// CONSTRUCTOR /////////

    public Herbivore() {
        super();
        
        numHerbivores++;
        ///// Setting up animations /////
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img, 9 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Side"));
        addAnimation(AnimationManager.createAnimation(img, 10 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Down"));
        addAnimation(AnimationManager.createAnimation(img, 11 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Up"));

        addAnimation(AnimationManager.createAnimation(img, 9 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Side"));
        addAnimation(AnimationManager.createAnimation(img, 10 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Down"));
        addAnimation(AnimationManager.createAnimation(img, 11 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Up"));

        setImage(animations[1].getImage(0));

        playAnimation("Walk Side");
        curHealth = maxHealth;
        
    }
    
    public Herbivore(double _speed, int _attack, double _size, double _altruism) {
        super(_speed, _attack, _size, _altruism);
        
        numHerbivores++;
        ///// Setting up animations /////
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img, 9 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Side"));
        addAnimation(AnimationManager.createAnimation(img, 10 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Down"));
        addAnimation(AnimationManager.createAnimation(img, 11 * 16, 0 * 16, 1, 1, 1, 16, 16, "Idle Up"));

        addAnimation(AnimationManager.createAnimation(img, 9 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Side"));
        addAnimation(AnimationManager.createAnimation(img, 10 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Down"));
        addAnimation(AnimationManager.createAnimation(img, 11 * 16, 4 * 16, 1, 4, 4, 16, 16, "Walk Up"));

        setImage(animations[1].getImage(0));

        playAnimation("Walk Side");
        
    }

    ////////// GREENFOOT FUNCTIONS //////////
    /**
     * Act - do whatever the Herbivore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        hpBar.update((int)curHealth);
        super.act();
        setRotation(rotation);
        animalPoop();
        setRotation(0);
        System.out.println("number seeds:" + numSeeds);
        // Health Decay
        if (state != State.InShelter)curHealth -= healthDecay;
        
        // Check if dead
        if (curHealth <=0){
            getWorld().removeObject(this);    
            numHerbivores--;
        }
    }
    
    public void addedToWorld(World w) {
        w.addObject(hpBar, getX(), getY());
        hpBar.update((int)curHealth);
    }

    public void started() {
    }

    public void stopped() {
        //System.out.println("How");
    }

    ////////// STATES //////////
    /**
     * The searching state of Herbivore
     * 
     * Every 600 acts, the animal will switch directions until a plant comes into
     * its sensory range
     * It will then switch its state to following
     */
    private int randomDirectionDelay=60;
    private int waitTime;

    protected void Searching() {
        if (time < randomDirectionDelay) {
            time++;
        } else {
            time = 0;
            waitTime = Greenfoot.getRandomNumber(randomDirectionDelay)+randomDirectionDelay/2;
            rotation = Greenfoot.getRandomNumber(360);
            
        }
        
        // Check if at edge
        if (MainWorld.onEdge(getX(), getY())){
            rotation = Greenfoot.getRandomNumber(360);
            MainWorld.PlaceOnEdge(this);
        }
         
        setRotation(rotation);
        move(speed);
        

        if (targetClosestPlant() == 1) {
            state = State.Following;
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
        // Prevent error and animal from eating already eaten plant
        if (targetPlant == null || targetPlant.getWorld() == null){
            state = State.Searching;
            //System.out.println("stop");
            return;
        }
        
        // Move towards selected plant
        MoveTowardsObject(targetPlant);
        turnTowards(targetPlant.getX(), targetPlant.getY());
        rotation = getRotation();

        move(speed);

        if (this.getNeighbours(10, true, Plant.class).size() > 0) {
            // If I was able to eat, increase by life by Plant's nibble power

            state = State.Attacking;
        }
    
        
        playAnimation("Walk "+direction);
    }

    protected void Attacking() {
        
        if (targetPlant == null || targetPlant.getWorld() == null){
            state = State.Searching;
            return;
        }
        
        double tryToEatPlant = targetPlant.eatPlant();
        if (tryToEatPlant > 0) {
            curHealth += tryToEatPlant;
            healthEaten += tryToEatPlant;
            //System.out.println("Health eaten:" + healthEaten);
            if(healthEaten >= (targetPlant.getMaxHealth()/2))
            {
                this.numSeeds++;
                healthEaten = 0;
            }
        }
        if (MainWorld.getDistance(targetPlant, this) > attackDistance || targetPlant == null || targetPlant.getWorld() == null) {
            state = State.Searching;

        }
        playAnimation("Idle "+direction);
    }
    protected void Night() {
        Shelter target = targetShelter(false);
        // Temp night
        if (MainWorld.getDistance(this, target) <= 5){
            playAnimation("Hidden");
        }   else {
            turnTowards(target.getX(), target.getY());
            rotation = getRotation();

            move(speed);
            playAnimation("Walk "+direction);
        }
        if (!MainWorld.night){
            state = State.Searching;
            playAnimation("Walk "+direction);
        }
    }   
    ////////// FUNCTIONS //////////

    
    private int targetClosestPlant() {

        double closestTargetDistance = 0;
        double distanceToActor;
        int numplants;
        // Get a list of all plants in the World, cast it to ArrayList
        // for easy management

        numplants = getWorld().getObjects(Shelter.class).size();

        plants = (ArrayList) getObjectsInRange(senseRange, Plant.class);
        //System.out.println(plants.size());  
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
                }

            }
                    return 1;

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
            move(speed);
        }
    }

    private void moveRandomly() {
        if (Greenfoot.getRandomNumber(1000) == 50) {
            rotation = Greenfoot.getRandomNumber(360);
            setRotation(rotation);
        } else
            move(speed);
    }
    
    public static int getNumHerbivores()
    {
        return numHerbivores;
    }
    
    public static void setNumHerbivores(int xx)
    {
        numHerbivores = xx;
    }
    
    public void dropSeed()
    {
        int seedX = this.getX();
        int seedY = this.getY();
        MainWorld main = (MainWorld)this.getWorld();
        CherrySeed seed = new CherrySeed(1);
        main.addObject(seed, seedX, seedY);
    }
    
    public void animalPoop()
    {
        currentTime = ((MainWorld)getWorld()).getCurrentTime();
        if(currentTime == 600)
        {
            if(numSeeds!=0)
            {
                poopTime = 50/this.numSeeds;
                //isPoopTime = true;
            }
        }
        if(currentTime >=600)
        {
            specialTimer++;
            if((specialTimer == poopTime) && (numSeeds > 0))
            {
                specialTimer = 0;
                CherrySeed seed = new CherrySeed(60);
                getWorld().addObject(seed, getX(),getY());
                numSeeds--;
            }
        }
        if(currentTime == 0)
        {
            specialTimer = 0;
            numSeeds = 0;
        }
        
       //testing do not return to real
    }
    
    public void setIsPoopTime(boolean x)
    {
        this.isPoopTime = x;
    }
    
    public void setSpecialTimer(int x)
    {
        specialTimer = x;
    }
    
    public int getPoopTime()
    {
        return poopTime;
    }
    
    public void setPoopTime(int x)
    {
        poopTime = x;
    }
    
    public int getNumSeeds()
    {
        return numSeeds;
    }
}
