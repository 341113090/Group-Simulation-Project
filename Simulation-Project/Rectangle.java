import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * These rectangles are used in the title screen class to darken options 
 * and also to detect for buttons
 * 
 * @author Nathan Thian 
 * @version April 24, 2022
 */
public class Rectangle extends Actor
{
    //width and height of rectangles
    private int width = 0;
    private int height = 0;
    
    /**
     * Constructor for rectangle.
     * xx is the width
     * yy is height
     * trans is the transparency of the rectangle's image
     */
    public Rectangle(int xx, int yy, int trans)
    {
        this.width = xx;
        this.height = yy;
        GreenfootImage image = new GreenfootImage(width,height);
        //all rectangles are black
        image.setColor(Color.BLACK);
        image.fillRect(0,0, width, height);
        //can customize transparency desired
        image.setTransparency(trans);
        setImage(image);
    }

    /**
     * The rectangle has no act method
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