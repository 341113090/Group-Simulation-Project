import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Rectangle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rectangle extends Actor
{
    private int width = 0;
    private int height = 0;
    /**
     * Constructor for rectangle
     */
    public Rectangle(int xx, int yy, int trans)
    {
        this.width = xx;
        this.height = yy;
        GreenfootImage image = new GreenfootImage(width,height);
        image.setColor(Color.BLACK);
        image.fillRect(0,0, width, height);
        image.setTransparency(trans);
        setImage(image);
    }

    /**
     * Act - do whatever the Rectangle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }

    /**
     * Returns an array of all the textboxes that the rectangle is touching
     */
    public ArrayList<SuperTextBox> getIntersectingTextBoxes (){
        return (ArrayList<SuperTextBox>)getIntersectingObjects(SuperTextBox.class);
    }
}
