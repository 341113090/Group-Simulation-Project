import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Shelters take in one type of animal each night and is a place for Animals to
 * breed. 
 * 
 * @author (Max, Lu-Wai) 
 * @version (April 20th, 2022)
 */
public class Shelter extends Actor
{
    private int sizeLimit = 6;
    private int curSize = 0;
    private boolean typeAnimal = false; // False is herbivore, true is carnivore
    private static int numShelters = 0;
    GreenfootImage s = new GreenfootImage("Plants.png");
    GreenfootImage shelter = AnimationManager.getSlice(s,3, 1, 48, 48);

    private ArrayList<Animal> animals;
    private int size = 0;
    
    /**
     * 
     */
    public Shelter(){
        numShelters++;
        this.setImage(shelter);
        animals = new ArrayList();
    }
    
    /**
     * 
     */
    public Shelter(boolean type){
        numShelters++;
        this.setImage(shelter);
        typeAnimal = type;
        animals = new ArrayList();
    }
    
    /**
     * Returns the number of shelters in the world. 
     */
    public static int getNumShelters()
    {
        return numShelters;
    }
    
    /**
     * Sets the number of shelters in the world at the start. 
     */
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

    /**
     * Returns the type of animal in shelter, true for carnivore, false on herbivore.
     */
    public boolean getTypeAnimal(){
        return typeAnimal;
    }

    /**
     * Sets the type of animal in the shelter.
     */
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

    public double generateRandomNumber(){
        return (double)(Greenfoot.getRandomNumber((int)(Animal.randomness*2000))/(double)1000)-(Animal.randomness/2);
    }

    public void spawnNewAnimal(){
        if (animals.size() <= 1 ){
            // Life always finds a way
            if (animals.size() != 0&& !typeAnimal){
                spawn(animals.get(0), animals.get(0));
            }   

            return;
        }
        Animal a = animals.get(0);
        Animal b = animals.get(1);
        int animalSize = animals.size();
        // Get top two animals with the most health
        for (int i = 0; i < animals.size(); i++){
            if (animals.get(i).getHealth() > a.getHealth()){
                a = animals.get(i);
            }   else if (animals.get(i) != a && animals.get(i).getHealth() > b.getHealth()){
                b = animals.get(i);
            }
        }

        // remove all old animals
        for (int i = 0; i < animals.size(); i++){
            if (animals.get(i) != a && animals.get(i) != b){
                Animal anim = animals.get(i);
                animals.remove(anim);
                getWorld().removeObject(anim);
            }
        }

        // Spawn in the original amount of animals + 1
        // question mark operator is to have carnivores spawn less
        for (int i  = 0; i < (typeAnimal?animalSize-1:animalSize+(animalSize/3)); i++){

            spawn(a,b);
        }

        // Set all animals to full health
        for (int i =0; i < animals.size(); i++){
            animals.get(i).setFullHealth();
        }
        animals.clear();
    }

    public void spawn(Animal a, Animal b){
        double speed = (a.getSpeed()+b.getSpeed())/2 + generateRandomNumber();
        double attack = (a.getAttack()+b.getAttack())/2 + generateRandomNumber();
        double size = (a.getSize()+b.getSize())/2 + generateRandomNumber();
        double altruism = (a.getAltruism()+b.getAltruism())/2+ generateRandomNumber();

        if (typeAnimal){
            getWorld().addObject(new Carnivore(speed, attack, size, altruism), getX(), getY());
        }   else {                    
            getWorld().addObject(new Herbivore(speed, attack, size, altruism), getX(), getY());
        }
    }

    public boolean addAnimal(Animal a){
        //System.out.println(typeAnimal +","+a.getType());
        //System.out.println(size+","+a.getSize());
        if (calcSize()+a.getSize() <= sizeLimit){

            //System.out.println(a.getSize());
            animals.add(a);

            return true;
        }   else {
            return false;
        }
    }

}
