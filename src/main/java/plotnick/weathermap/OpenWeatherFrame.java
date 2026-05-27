package plotnick.weathermap;

import javax.swing.*;
import java.awt.*;

public class OpenWeatherFrame extends JFrame
{
    public OpenWeatherFrame()
    {
        setSize(1000, 1000);
        setTitle("Weather Map");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());



    }

    public static void main(String[] args)
    {
        OpenWeatherFrame frame = new OpenWeatherFrame();
        frame.setVisible(true);
    }

}
