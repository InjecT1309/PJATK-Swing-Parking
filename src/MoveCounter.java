import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MoveCounter extends JLabel
{
    int howManyMoves = 0;
    Car lastSwapC1;
    Car lastSwapC2;

    MoveCounter()
    {
        setText(Integer.toString(howManyMoves));
        setForeground(Color.blue);
        setFont(new Font("Courier New", Font.PLAIN, 50));
    }

    public void addMove(Car c1, Car c2)
    {
        howManyMoves++;
        lastSwapC1 = c1;
        lastSwapC2 = c2;
        setText(Integer.toString(howManyMoves));
    }
    public void undoLastMove()
    {
        if(lastSwapC1 != null && lastSwapC2 != null)
        {
            lastSwapC1.swap(lastSwapC2);
            lastSwapC1 = null;
            lastSwapC2 = null;
            howManyMoves--;
            setText(Integer.toString(howManyMoves));
        }
    }
}
