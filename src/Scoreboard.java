import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Scoreboard extends JTextArea
{
    File scores;
    ArrayList<Record> records = new ArrayList<Record>();

    private class Record
    {
        int rankign;
        String nick;
        int number_of_moves;
        Record(int rankign, String nick, int number_of_moves)
        {
            this.rankign = rankign;
            this.nick = nick;
            this.number_of_moves = number_of_moves;
        }
        @Override
        public String toString()
        {
            return rankign + " " + nick + " " + number_of_moves + "\n";
        }
    }

    public Scoreboard(int whichScores)
    {
        setEditable(false);
        setText("High scores: \n");

        try
        {
            scores = new File("scores" + whichScores + ".txt");

            if(!scores.exists())
                scores.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println(e.getStackTrace());
        }
        readFromFile();
    }

    public void readFromFile()
    {
        try
        {
            BufferedReader f_read = new BufferedReader(new FileReader(scores));
            String line;
            String args[];
            for(int i=0; i<countLinesInFile(); i++)
            {
                line = f_read.readLine();
                args = line.split(":");
                if(args.length == 3)
                {
                    records.add(new Record(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2])));
                    setText(getText() + records.get(records.size()-1));
                }
            }
        }

        catch(IOException e)
        {
            System.out.println(e.getStackTrace());
        }
    }
    public void writeNewRecord(String nickname, int number_of_moves)
    {
        try
        {
            PrintWriter f_write = new PrintWriter(scores);

            if(records.isEmpty())
                records.add(new Record(1, nickname, number_of_moves));
            else {
                boolean foundRecPlace = false;
                for (int i = 0; i < records.size() && i < 10; i++)
                {
                    if(foundRecPlace)
                        records.get(i).rankign++;
                    else if (number_of_moves < records.get(i).number_of_moves)
                    {
                        records.add(i, new Record(i + 1, nickname, number_of_moves));
                        foundRecPlace = true;
                    }
                }
                if(!foundRecPlace)
                    records.add(new Record(records.size()+1, nickname, number_of_moves));
            }

            String line;
            for(int i=0; i<records.size() && i<10; i++)
            {
                line = records.get(i).toString();
                line = line.replace(' ', ':');
                f_write.print(line);
            }
            f_write.flush();
        }
        catch(IOException e)
        {
            System.out.println(e.getStackTrace());
        }
    }
    private int countLinesInFile()
    {
        int output = 0;
        try
        {
            BufferedReader f_read = new BufferedReader(new FileReader(scores));

            while(f_read.readLine()!=null)
                output++;
        }
        catch(IOException e)
        {
            System.out.println(e.getStackTrace());
        }
        return output;
    }
}
