import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private int limitAnimals;
    private int curAnimals;
    private Animal typeAnimal;
    GreenfootImage Plants = new GreenfootImage("Plants.png");
    GreenfootImage shelter = AnimationManager.getSlice(Plants,3, 0, 48, 48);
    public Shelter(){
        limitAnimals = 2;
        curAnimals = 0;
        this.setImage(shelter);
    }
    
    public void act()
    {
        
    }
    
    /**
     * This method checks when the limit of animals that can fit into a shelter.
     */
    public boolean limitHit(){
        if(curAnimals == limitAnimals){
            return true;
        }
        return false;
    }
    
    public Animal getTypeAnimal(){
        return typeAnimal;
    }
    
    public void setTypeAnimal(Animal animal){
        typeAnimal = animal;
    }
    
    public int getCurAnimals(){
        return curAnimals;
    }
    
    public void addCurAnimals(){
        curAnimals++;
    }
}
