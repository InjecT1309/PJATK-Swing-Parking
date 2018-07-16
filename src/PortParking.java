import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PortParking extends JPanel
{
    private Car[][] car;
    private int width, height;

    PortParking(int height, int width, MoveCounter mv_count) {
        this.width = width;
        this.height = height;
        
        setLayout(new GridLayout(height, width)); //number of rows is height and number of columns is width

        car = new Car[width][height];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                car[x][y] = new Car(CarColor.Blue, x, y);
                add(car[x][y]);
            }
        }

        //Setting up red cars
        int rand_x, rand_y;
        for(int i = 0; i<height; i++)
        {
            rand_x = (int)(Math.random()*width);
            rand_y = (int)(Math.random()*height);

            if(car[rand_x][rand_y].getColor() == CarColor.Blue)
            {
                car[rand_x][rand_y].setColor(CarColor.Red);
            }
            else
            {
                i--;
            }
        }

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                car[x][y].addActionListener(new CarAction(this, car[x][y], mv_count));
                car[x][y].addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if(e.getKeyCode() == KeyEvent.VK_B)
                            mv_count.undoLastMove();
                    }
                });
            }
        }
    }

    public Car getOtherSelected(Car my_car)
    {
        for(int x=0; x<width; x++)
            for(int y=0; y<height; y++)
                if(car[x][y].isSelected() && car[x][y] != my_car)
                    return car[x][y];
        return null;
    }
    public boolean checkIfOrderedCorrectly(){
        for(int i=0; i<height; i++)
        {
            if(car[width-1][i].getColor() != CarColor.Red)  return false;
        }
        return true;
    }
}
