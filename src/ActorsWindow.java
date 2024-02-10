import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ActorsWindow extends JFrame implements ActionListener {

    JPanel panel = new JPanel(null);
    JLabel welcomeLabel = new JLabel("This are all the Actors!");
    private JLabel actorInfoLable;
    JButton favoritesButton = null;
    public ActorsWindow(User user, List<Actor> actors){
        setTitle("Actors Window");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        SortedSet<Actor> sortedActors = new TreeSet<Actor>(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        sortedActors.addAll(actors);

        panel.setBackground(new Color(0x000000));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1366, 1000));

        ImageIcon LogoIcon = new ImageIcon("resources/LOGOx2.png");
        JLabel logoLabel = new JLabel(LogoIcon);

        logoLabel.setBounds(100, 50, 110, 60);
        panel.add(logoLabel);

        welcomeLabel.setBounds(600, 50, 200, 50);
        welcomeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        welcomeLabel.setForeground(new Color(0xFFFFFF));
        panel.add(welcomeLabel);

        JLabel Actors = new JLabel("Actors");
        Actors.setBounds(100, 110, 200, 50);
        Actors.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        Actors.setForeground(new Color(0xFFFFFF));
        panel.add(Actors);

        JPanel panelIMGActors = new JPanel();
        panelIMGActors.setLayout(new BoxLayout(panelIMGActors, BoxLayout.X_AXIS));
        JScrollPane scrollPaneActors = new JScrollPane(panelIMGActors);
        scrollPaneActors.setWheelScrollingEnabled(false);
        scrollPaneActors.setBorder(null);
        scrollPaneActors.setBounds(100, 170, 1200, 370);
        panel.add(scrollPaneActors);
        for (Actor actor : sortedActors) {
            JPanel actorPanel = new JPanel();
            actorPanel.setLayout(new BoxLayout(actorPanel, BoxLayout.Y_AXIS));
            actorPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
            actorPanel.setBackground(Color.BLACK);

            ImageIcon imageIcon = new ImageIcon(actor.imageURL);
            if (imageIcon.getIconWidth() != -1 && imageIcon.getIconHeight() != -1) {
                Image image = imageIcon.getImage().getScaledInstance(205, 303, Image.SCALE_DEFAULT);
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                imageLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String actorinfo = "There is no actor info available";
                        actorinfo = "<html>Name: " + actor.name + "<br>Biography: " + actor.biography
                                + "<br>Performances: " + actor.performances + "</html>";
                        if (actorInfoLable != null) {
                            panel.remove(actorInfoLable);
                        }
                        actorInfoLable = new JLabel(actorinfo);
                        actorInfoLable.setBounds(500,  520, 500, 350);
                        actorInfoLable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                        actorInfoLable.setForeground(new Color(0xFFFFFF));
                        panel.add(actorInfoLable);
                        panel.revalidate();
                        panel.repaint();

                        if( favoritesButton != null){
                            panel.remove(favoritesButton);
                            panel.revalidate();
                            panel.repaint();
                        }
                        if(!user.getFavorites().contains(actor)){
                            favoritesButton = new JButton("Add to favorites");
                            favoritesButton.setBounds(340,  660, 150, 30);
                            favoritesButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            favoritesButton.setForeground(new Color(0xFFFFFF));
                            favoritesButton.setFocusable(false);
                            favoritesButton.setBackground(new Color(0x1A1A1A));
                            favoritesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    user.addFavorite(actor);
                                    panel.remove(favoritesButton);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                            });
                            panel.add(favoritesButton);
                        }
                        else{
                            favoritesButton = new JButton("Remove from favorites");
                            favoritesButton.setBounds(340,  660, 150, 30);
                            favoritesButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            favoritesButton.setForeground(new Color(0xFFFFFF));
                            favoritesButton.setFocusable(false);
                            favoritesButton.setBackground(new Color(0x1A1A1A));
                            favoritesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    user.removeFavorite(actor);
                                    panel.remove(favoritesButton);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                            });
                            panel.add(favoritesButton);
                        }
                    }
                });

                actorPanel.add(imageLabel);
            } else {
                JLabel titleLabel = new JLabel(actor.name);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                titleLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String actorinfo = "There is no production info available";
                        actorinfo = "<html>Name: " + actor.name + "<br>Biography/s: " + actor.biography
                                + "<br>Performances: " + actor.performances + "</html>";
                        if (actorInfoLable != null) {
                            panel.remove(actorInfoLable);
                        }
                        actorInfoLable = new JLabel(actorinfo);
                        actorInfoLable.setBounds(500,  520, 500, 350);
                        actorInfoLable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                        actorInfoLable.setForeground(new Color(0xFFFFFF));
                        if( favoritesButton != null){
                            panel.remove(favoritesButton);
                            panel.revalidate();
                            panel.repaint();
                        }
                        if(!user.getFavorites().contains(actor)){
                            favoritesButton = new JButton("Add to favorites");
                            favoritesButton.setBounds(340,  560, 150, 30);
                            favoritesButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            favoritesButton.setForeground(new Color(0xFFFFFF));
                            favoritesButton.setFocusable(false);
                            favoritesButton.setBackground(new Color(0x1A1A1A));
                            favoritesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    user.addFavorite(actor);
                                    System.out.println("Added to favorites");
                                    panel.remove(favoritesButton);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                            });
                            panel.add(favoritesButton);
                        }
                        else{
                            favoritesButton = new JButton("Remove from favorites");
                            favoritesButton.setBounds(340,  560, 150, 30);
                            favoritesButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            favoritesButton.setForeground(new Color(0xFFFFFF));
                            favoritesButton.setFocusable(false);
                            favoritesButton.setBackground(new Color(0x1A1A1A));
                            favoritesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    user.removeFavorite(actor);
                                    panel.remove(favoritesButton);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                            });
                            panel.add(favoritesButton);
                        }

                        panel.add(actorInfoLable);
                        panel.revalidate();
                        panel.repaint();
                    }
                });
                actorPanel.add(titleLabel);
            }

            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titlePanel.setBackground(new Color(0x1A1A1A));
            JLabel titleLabel = new JLabel("<html>" + actor.name + "</html>");
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titlePanel.add(titleLabel);

            actorPanel.add(titlePanel);

            panelIMGActors.add(actorPanel);
        }

        JLabel toMenu = new JLabel("Back to Menu");
        toMenu.setBounds(600, 900, 200, 50);
        toMenu.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        toMenu.setForeground(new Color(0xFFFFFF));
        panel.add(toMenu);
        toMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MenuWindow menuWindow = new MenuWindow(user);
                menuWindow.setVisible(true);
            }
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.setBackground(new Color(0x000000));
        scrollPane.getVerticalScrollBar().setBackground(new Color(0x000000));
        scrollPane.getHorizontalScrollBar().setBackground(new Color(0x000000));
        add(scrollPane);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}