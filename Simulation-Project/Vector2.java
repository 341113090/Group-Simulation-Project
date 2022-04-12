/**
 * Write a description of class Vector2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector2  
{
    // instance variables - replace the example below with your own
    private double x;
    private double y;

    /**
     * Constructor for objects of class Vector2
     */
    public Vector2(double _x, double _y)
    {
        x = _x;
        y = _y;
    }

    // Get x and y
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    
    // Set x and y
    public void setX(double _x){
        x = _x;
    }
    public void setY(double _y){
        y = _y;
    }
    
    ///// Functions /////
    public double getDistanceTo(Vector2 v){
        return Math.sqrt(Math.pow(getX()-v.getX(),2)+Math.pow(getY()-v.getY(),2));

    }
}
