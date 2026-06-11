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

        GridBagConstraints constraints;






    }

    public static void main(String[] args)
    {
        OpenWeatherFrame frame = new OpenWeatherFrame();
        frame.setVisible(true);
    }


    private void addComponent(
            Component component,
            int x,
            int y,
            int width) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.fill = GridBagConstraints.BOTH;

        add(component, gbc);
    }

}
