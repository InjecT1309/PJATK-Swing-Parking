import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    public static void main(String args[])
    {
        new MainFrame();
    }
    private MainFrame()
    {
        setTitle("Push the car"); //TODO: good name
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String options[] = {
                "Small ship",
                "Medium ship",
                "Big ship"
        };
        int parking_size = JOptionPane.showOptionDialog(this, "The goal of the game is to put red " +
                "cars in the last row. Pick a game type: ", "Welcome!", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Dimension park_dim;
        switch(parking_size)
        {
            case 0:
                park_dim = new Dimension(4, 4);
                break;
            case 1:
                park_dim = new Dimension(7, 7);
                break;
            case 2:
                park_dim = new Dimension(10, 10);
                break;
            default:
                park_dim = new Dimension(1, 1);
                System.exit(0);
        }

        JLabel ship_params = new JLabel("Ship parameters: " + park_dim.width + "x" + park_dim.height);
        Image icon = new ImageIcon("res/Ship.png").getImage()
                .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ship_params.setIcon(new ImageIcon(icon));
        MoveCounter mv_count = new MoveCounter();
        PortParking parking = new PortParking(park_dim.height, park_dim.width, mv_count);
        Scoreboard scores = new Scoreboard(parking_size);
        DeliverCarsButton button = new DeliverCarsButton(parking, mv_count, scores);

        JPanel top_panel = new JPanel();
        top_panel.setLayout(new BoxLayout(top_panel, BoxLayout.X_AXIS));
        top_panel.add(Box.createGlue());
        top_panel.add(ship_params);
        top_panel.add(Box.createGlue());
        top_panel.add(mv_count);
        add(top_panel, BorderLayout.NORTH);
        add(parking, BorderLayout.CENTER);
        add(scores, BorderLayout.WEST);
        add(button, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
}
