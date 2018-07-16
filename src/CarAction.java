import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarAction implements ActionListener {
    Car my_c;
    PortParking parent;
    MoveCounter mv_count;

    CarAction(PortParking par, Car c, MoveCounter move_counter)
    {
        parent = par;
        my_c = c;
        mv_count = move_counter;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Car other_c = parent.getOtherSelected(my_c);

        if(other_c != null)
        {
            if (my_c.checkIfAdjacent(other_c))
            {
                my_c.swap(other_c);
                my_c.setSelected(false);
                other_c.setSelected(false);
                mv_count.addMove(my_c, other_c);
            }
            else
            {
                my_c.setSelected(true);
                other_c.setSelected(false);
            }
        }
    }
}
