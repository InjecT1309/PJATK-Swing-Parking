import javax.swing.*;
import java.awt.event.*;

public class DeliverCarsButton extends JButton
{
    DeliverCarsButton(PortParking parking, MoveCounter mv_count, Scoreboard scores)
    {
        setText("Deliver cars");
        addActionListener(new DeliverCarsAction(parking, mv_count, scores));
    }
    private class DeliverCarsAction implements ActionListener
    {
        PortParking parking;
        MoveCounter mv_count;
        Scoreboard scores;

        DeliverCarsAction(PortParking parking, MoveCounter mv_count, Scoreboard scores)
        {
            this.parking = parking;
            this.mv_count = mv_count;
            this.scores = scores;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(parking.checkIfOrderedCorrectly())
            {
                String nickname = JOptionPane.showInputDialog(parking, "You win! Enter your name:", "Success!", JOptionPane.PLAIN_MESSAGE);
                int num_of_mvs = Integer.parseInt(mv_count.getText());
                scores.writeNewRecord(nickname, num_of_mvs);
            }
            else
            {
                JOptionPane.showMessageDialog(parking, "You lost.", "Failure", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        }
    }
}
