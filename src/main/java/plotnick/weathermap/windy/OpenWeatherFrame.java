package plotnick.weathermap.windy;

import plotnick.weathermap.OpenWeatherMapService;
import plotnick.weathermap.WeatherMapFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenWeatherFrame extends JFrame
{
    public OpenWeatherFrame()
    {
        setSize(500, 300);
        setLocationRelativeTo(null);
        setTitle("Weather Map");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final OpenWeatherMapService openWeatherMapService = new WeatherMapFactory().create();
        final WindyService windyService = new WindyServiceFactory().create();

        JTextField search = new JTextField("New York", 20);
        final JButton button = new JButton("Search");

        JLabel lat = new JLabel(" ");
        JLabel lon = new JLabel(" ");

        final JLabel templabel = new JLabel("Temperature: ");
        JLabel temp = new JLabel(" ");

        final JLabel feels_likelabel = new JLabel("Feels Like: ");
        JLabel feels_like = new JLabel(" ");

        final JLabel descriptionlabel = new JLabel("Description: ");
        JLabel description = new JLabel(" ");

        JLabel title = new JLabel("Weather Now");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        final int maxNumPics = 5;

        final JLabel[] picLabels = new JLabel[maxNumPics];
        for (int i = 0; i < maxNumPics; i++)
        {
            picLabels[i] = new JLabel();
        }


        OpenWeatherMapController controller = new OpenWeatherMapController(openWeatherMapService, windyService,
                                                                            search,lat, lon,
                                                                        temp, feels_like,description, picLabels);



        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.doSearch();


            }
        });



        setLayout(new GridBagLayout());

        addComponent(button,2, 1, GridBagConstraints.NORTH  );
        addComponent(search, 3, 1 , GridBagConstraints.NORTH);

        addComponent(templabel, 2, 4, GridBagConstraints.NORTH);
        addComponent(temp, 3, 4, GridBagConstraints.NORTH);

        addComponent(feels_likelabel, 2, 5, GridBagConstraints.NORTH);
        addComponent(feels_like, 3, 5, GridBagConstraints.NORTH);

        addComponent(descriptionlabel, 2, 6, GridBagConstraints.NORTH);
        addComponent(description, 3, 6, GridBagConstraints.NORTH);
        for (int i = 0; i < maxNumPics; i++)
        {
            addPicLabel(picLabels[i], 2 + i / 3, (7 * i) % 21);
        }

        addComponent(title, 2, 0, GridBagConstraints.CENTER);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font valueFont = new Font("Arial", Font.PLAIN, 14);

        templabel.setFont(labelFont);
        feels_likelabel.setFont(labelFont);
        descriptionlabel.setFont(labelFont);

        temp.setFont(valueFont);
        feels_like.setFont(valueFont);
        description.setFont(valueFont);


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
            int anchor) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
//      gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        add(component, gbc);
    }

    private void addPicLabel(JComponent picLabel, int x, int y)
    {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = 7;
        constraints.anchor = GridBagConstraints.NORTH;
        add(picLabel, constraints);
    }

}
