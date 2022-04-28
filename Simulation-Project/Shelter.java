import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Code to copy paste into animal class: 
 * <p>
 * public void checkInShelter(){<br>
 *     //need to add a collision detection to detect the shelter in front<br> 
 *     if(shelter.getCurAnimals()>0){<br>
 *          if(shelter.getTypeAnimal()==this.getClass() && shelter.limitHit() != false){<br>
 *              //animal goes into shelter<br>
 *              shelter.addCurAnimals();<br>
 *          } else{<br>
 *              //move away<br>
 *          }<br>
 *     } else{<br>
 *          shelter.setTypeAnimal(this.getClass()); // not sure if getClass() works<br>
 *          shelter.addCurAnimals();<br>
 *     }<br>
 * }</p><br>
 * 
 * MAJOR THING FOR ANIMAL TO IMPLEMENT:     
 * Animals that want to enter a shelter need to:
 * <ol><br>
 *      <li> Check if there is any animals in the shelter using getCurAnimals()</li>
 *      <li> If there is: 
 *      <ul> 
 *          <li> Check what type of animal is there using getTypeAnimal()</li>
 *          <li> Check if limit has been hit using limitHit()</li>
 *      </ul>
 *      <li> If there is not: </li>
 *      <ul> 
 *          <li> Animal needs to set the typeAnimal using setTypeAnimal</li>
 *          <li> Animal needs to add one to the addCurAnimals()</li>
 *      </ul>
 * </ol>
 * <br>
 * 
 * <p> Shelter comes in when the day ends and animals are finding a place to 
 * stay for the night. There can only be one type of animal per shelter and there
 * is a limit on how many animals can fit in one shelter. </p>
 * 
 * @author (Max) 
 * @version (April 20th, 2022)
 */
public class Shelter extends Actor
{
    /**
     * Act - do whatever the Shelter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int sizeLimit = 3;
    private int curSize = 0;
    private boolean typeAnimal = false; // False is herbivore, true is carnivore
    private static int numShelters = 0;
    GreenfootImage s = new GreenfootImage("Plants.png");
    GreenfootImage shelter = AnimationManager.getSlice(s,3, 1, 48, 48);
    
    private ArrayList<Animal> animals;
    private int size = 0;
    
    public Shelter(){
        numShelters++;
        this.setImage(shelter);
        animals = new ArrayList();
    }
    public Shelter(boolean type){
        numShelters++;
        this.setImage(shelter);
        typeAnimal = type;
        animals = new ArrayList();
    }
    public static int getNumShelters()
    {
        return numShelters;
    }
    
    public static void setNumShelters(int xx)
    {
        numShelters = xx;
    }
    
    public void act()
    {
        // because it isn't clearing properly
        if (animals.size() >0 && !MainWorld.night){
            animals.clear();
        }
    }
    
    /**
     * This method checks when the limit of animals that can fit into a shelter.
     */
    public boolean limitHit(){
        if(curSize >= sizeLimit){
            return true;
        }
        return false;
    }
    
    public boolean getTypeAnimal(){
        return typeAnimal;
    }
    
    public void setTypeAnimal(boolean animal){
        typeAnimal = animal;
    }
    
    public int calcSize(){
        int size = 0;
        for (int i  = 0; i < animals.size(); i++){
            size += animals.get(i).getSize();
        }
        return size;
    }
    public void spawnNewAnimal(){
        for (int i  = 0; i < animals.size(); i++){
            if ((i+1)%2==0){
                Animal a = animals.get(i-1);
                Animal b = animals.get(i);
                
                double speed = (a.getSpeed()+b.getSpeed())/2 + (Greenfoot.getRandomNumber((int)(Animal.randomness*1000))/1000);
                int attack = (a.getAttack()+b.getAttack())/2 + (Greenfoot.getRandomNumber((int)(Animal.randomness*1000))/1000);
                double size = (a.getSize()+b.getSize())/2 + (Greenfoot.getRandomNumber((int)(Animal.randomness*1000))/1000);
                double altruism = (a.getAltruism()+b.getAltruism())/2+ (Greenfoot.getRandomNumber((int)(0.1*1000))/1000);
                if (typeAnimal){
                    getWorld().addObject(new Carnivore(speed, attack, size, altruism), getX(), getY());
                }   else {                    
                    getWorld().addObject(new Herbivore(speed, attack, size, altruism), getX(), getY());
                }
                
                animals.clear();
            }
        }
    }
    
    public boolean addAnimal(Animal a){
        System.out.println(typeAnimal +","+a.getType());
        //System.out.println(size+","+a.getSize());
        if (calcSize()+a.getSize() <= sizeLimit){
            
            System.out.println(a);
            animals.add(a);
            
            return true;
        }   else {
            return false;
        }
    }
    
}
