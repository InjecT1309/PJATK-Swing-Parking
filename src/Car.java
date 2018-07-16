import javax.swing.*;
import java.awt.*;

public class Car extends JToggleButton
{
    private int x, y;
    private CarColor color;
    Car(CarColor color, int x, int y)
    {
        this.x=x;
        this.y=y;
        setColor(color);
    }
    public void setColor(CarColor color)
    {
        this.color = color;
    }
    public CarColor getColor()
    {
        return color;
    }
    public boolean checkIfAdjacent(Car c)
    {
        if((Math.abs(x-c.x)<=1) && (Math.abs(y-c.y)<=1))
            return true;
        return false;
    }
    public void swap(Car other)
    {
        CarColor tmp = other.color;
        other.setColor(this.color);
        other.repaint();
        this.setColor(tmp);
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Color bgclr = isSelected() ? Color.green : null;

        if(color == CarColor.Blue)
        {
            g.drawImage(new ImageIcon("res/BlueCar.png").getImage(),
                    0, 0, getWidth(), getHeight(), bgclr, null);
        }
        else
        {
            g.drawImage(new ImageIcon("res/RedCar.png").getImage(),
                    0, 0, getWidth(), getHeight(), bgclr, null);
        }
    }
}
