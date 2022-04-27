import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THINGS THAT ANIMALS NEED TO DO:
 * <ul>
 * <li>Tell the Plant if it is eating it or not using setEating()
 * so the plant knows whether to regen or not.</li>
 * </ul>
 * <br>
 * 
 * <p>
 * Plants are eaten by herbivores and provide them with nutrients. Plants also
 * try and
 * survive until the end of the day so that they can reproduce. Poisonous plants
 * reproduce by surviving and edible plants reproduce by having their seeds
 * eaten.
 * </p>
 * 
 * @author Nathan and Max
 * @version April 12, 2022
 */
public abstract class Plant extends Animator {
    // getter required
    protected double toughness;
    protected int maxHealth;
    protected int health;
    protected int healthPerTick;
    protected boolean isToxic;
    protected boolean wantsCarry;
    // getter not needed
    protected int healthLimit;
    protected int selfHealSpeed;
    protected boolean isGettingEaten;

    protected SuperStatBar hpBar;
    // misc
    protected GreenfootImage Plants = new GreenfootImage("Plants.png");

    /**
     * Constructor for plant
     * Default sets health to 1000 and creates an hp bar
     * initializes the plant as not getting eaten
     */
    public Plant() {
        health  = 1000;
        hpBar = new SuperStatBar(health, health, this, 24, 4, 10, Color.GREEN, Color.RED, false, Color.BLACK, 1);
        isGettingEaten = false;
    }

    /**
     * When the polants are added to the world, their hp bar is added to the world at that location and updates
     */
    public void addedToWorld(World w) {
        w.addObject(hpBar, getX(), getY());
        hpBar.update(health);
    }

    /**
     * Plants are used as food for Herbivores, go to method details for more
     * specifications.
     * Act - Plants will continously:
     * <ol>
     * <li>Update plant hp</li>
     * <li>Check if they are being eaten</li>
     * <li>Check if they are fully eaten</li>
     * </ol>
     * 
     */
    public void act() {
        hpBar.update(health);
        if (!isGettingEaten) {
            growing();
        }
        isGettingEaten = false;
        deathCheck();
    }

    // next methods actually are being used
    /**
     * This method checks if the plant has no more health and removes itself.
     */
    public abstract void deathCheck();

    /**
     * This method determines how much damage is being taken when being eaten and
     * returns
     * the amount of health the herbivore regenerates.
     * <br>
     * <br>
     * Note: Plant will give a seed based on the percent of health they lose to the
     * herbivore. Cherry will give three seeds if eaten from full health.
     */
    public double eatPlant() {
        isGettingEaten = true;
        double actualTick = healthPerTick * toughness;// might be a problem later for statbar
        if (health >= actualTick) {
            health -= actualTick;// temporary, will depend on plant health
            return actualTick;
        } else {
            int lastHealth = health;
            health = 0;
            return lastHealth;
        }
    }

    /**
     * 
     * This healing method is called when the plant is not being eaten.
     * <ul>
     * <li>Plant health will never go over a certain amount of health after being
     * eaten.</li>
     * </ul>
     */
    public void growing() {
        if (health <= healthLimit - selfHealSpeed) { // so it doesn't go over limit
            health += selfHealSpeed;
        }
    }

    // these methods are all getters for other classes
    /**
     * Returns the health of plant.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the health given by the plant.
     */
    public int giveHealth() {
        return healthPerTick;
    }
    
    /**
     * Returns max health of plant
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * Return the toughness of the plant.
     */
    public double getToughness() {
        return toughness;
    }

    /**
     * Returns whether the plant is toxic or not.
     */
    public boolean isToxic() {
        return isToxic;
    }

    /**
     * Returns whether the plant wants animals to eat it to carry seeds or not.
     */
    public boolean wantsCarry() {
        return wantsCarry;
    }

    /**
     * Setter method for if the plant is being eaten or not. Use this method in
     * the herbivore class.
     */
    public void setEating(boolean a) {
        isGettingEaten = a;
    }
}
