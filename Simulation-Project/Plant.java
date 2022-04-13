import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Plants are eaten by animals and provide them with nutrients. Plants also try and 
 * survive until the end of the day so that they can reproduce. Poisonous plants 
 * reproduce by surviving and edible plants reproduce by having their seeds eaten.
 * 
 * @author Nathan and Max
 * @version April 12, 2022
 */
public class Plant extends Animator
{
    //getter required
    protected int toughness;
    protected int totalSeeds;
    protected int health;
    protected int healthGiven;
    protected int fertility;
    protected boolean isToxic;
    protected boolean wantsCarry;
    //getter not needed
    protected int healthLimit;
    protected int rateOfGrowth;
    protected int selfHealSpeed;
    protected int healthPerTick;
    protected SuperStatBar hpBar;
        
    public Plant()
    {
        hpBar = new SuperStatBar(health,health,this,48,4,36,Color.GREEN,Color.BLACK,false,Color.RED,1);
    }
    
    public void addedToWorld (World w)
    {
        w.addObject (hpBar, getX(), getY());
        hpBar.update(health);
    }
    
    /**
     * Act - do whatever the Plant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        hpBar.update(health);
        deathCheck();
    }
    
    //next methods actually are being used
    public void deathCheck(){
        if(health == 0){
            getWorld().removeObject(this);
        }
    }
    
    public int takeDamage(){
        if(health>=healthPerTick){
            health-=healthPerTick;//temporary, will depend on plant health
            return healthPerTick;
        }else{
            int lastHealth = health;
            health = 0;
            return lastHealth;
        }
    }
    
    
    //these methods are all getters for other classes    
    /**
     * returns the health of plant
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * returns the health given by the plant
     */
    public int giveHealth(){
        return healthGiven;
    }
    
    /**
     * returns the number of seeds of the plant
     */
    public int getSeeds(){
        return totalSeeds;
    }
    
    /**
     * return the toughness of the plant
     */
    public int getToughness(){
        return toughness;
    }
    
    /**
     * returns whether the plant is toxic or not
     */
    public boolean isToxic(){
        return isToxic;
    }
    
    /**
     * returns whether the plant wants animals to eat it to carry seeds or not
     */
    public boolean wantsCarry(){
        return wantsCarry;
    }
}
