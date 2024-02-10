import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainWindow extends JFrame implements ActionListener, MouseListener {

    JLabel welcomeLabel = new JLabel("Welcome to IMDb!");
    JTextField textField = new JTextField();
    JButton searchButton = new JButton("Search");
    JPanel panel = new JPanel(null);
    private JLabel fanFavInfoLable, movieInfoLable, seriesInfoLable;

    private MenuWindow menuPage;

    JButton favoritesButton = null, favoritesButton2 = null, favoritesButton3 = null;
    JButton addRatingButton = null, addRatingButton2 = null, addRatingButton3 = null;
    JButton favoritesButtonbar = null;
    JButton addRatingButtonbar = null;

    JPanel panelIMGMovies = new JPanel();
    JScrollPane scrollPaneMovies = new JScrollPane(panelIMGMovies);
    JPanel panelIMGSeries = new JPanel();
    JScrollPane scrollPaneSeries = new JScrollPane(panelIMGSeries);

    MainWindow(User user, List<Movie> movies, List<Series> series, List<Actor> actors) {
        setTitle("Main Window");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        System.out.println(user.getUsername() + ", " + user.getAccountType());

        panel.setBackground(new Color(0x000000));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1366, 3000));

        ImageIcon LogoIcon = new ImageIcon("resources/LOGOx2.png");
        JLabel logoLabel = new JLabel(LogoIcon);

        logoLabel.setBounds(100, 50, 110, 60);
        panel.add(logoLabel);

        welcomeLabel.setBounds(600, 50, 200, 50);
        welcomeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        welcomeLabel.setForeground(new Color(0xFFFFFF));
        panel.add(welcomeLabel);


        SortedSet<Production> sortedProductions = new TreeSet<Production>(new Comparator<Production>() {
            @Override
            public int compare(Production o1, Production o2) {
                int ratingComparison = Double.compare(o2.getAverageRating(), o1.getAverageRating());
                if (ratingComparison != 0) {
                    return ratingComparison;
                } else {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            }
        });
        sortedProductions.addAll(movies);
        sortedProductions.addAll(series);


        JLabel FanFavorites = new JLabel("Fan Favorites");
        FanFavorites.setBounds(100, 120, 200, 50);
        FanFavorites.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        FanFavorites.setForeground(new Color(0xFFFFFF));
        panel.add(FanFavorites);

        JPanel panelIMGFanFavorites = new JPanel();
        panelIMGFanFavorites.setLayout(new BoxLayout(panelIMGFanFavorites, BoxLayout.X_AXIS));
        JScrollPane scrollPaneFanFavorites = new JScrollPane(panelIMGFanFavorites);
        scrollPaneFanFavorites.setWheelScrollingEnabled(false);
        scrollPaneFanFavorites.setBorder(null);
        scrollPaneFanFavorites.setBackground(Color.BLACK);
        scrollPaneFanFavorites.setBounds(100, 170, 1200, 370);
        panel.add(scrollPaneFanFavorites);

        int count = 0;
        for (Production production : sortedProductions)     {

            if (count >= 10) {
                break;
            }

            JPanel productionPanel = new JPanel();
            productionPanel.setLayout(new BoxLayout(productionPanel, BoxLayout.Y_AXIS));
            productionPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
            productionPanel.setBackground(Color.BLACK);

            ImageIcon imageIcon = new ImageIcon(production.imageURL);
            if (imageIcon.getIconWidth() != -1 && imageIcon.getIconHeight() != -1) {
                Image image = imageIcon.getImage().getScaledInstance(205, 303, Image.SCALE_DEFAULT);
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                imageLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String productionInfo = "There is no production info available";
                        if(production instanceof Movie){
                            productionInfo = "<html>Title: " + production.getTitle() + "<br>Director/s: " + production.getDirectors()
                                    + "<br>Actors: " + production.getActors() + "<br>Genres: " + production.getGenres() +
                                    "<br>Description: " + production.description + "<br>Duration: " + ((Movie) production).getDuration() + " min" +
                                    "<br>Launch Year: " + ((Movie) production).getLaunchYear() + "<br>Rating: " + production.getAverageRating() + "</html>";
                        }
                        else if(production instanceof Series){
                            productionInfo = "<html>Title: " + production.getTitle() + "<br>Director/s: " + production.getDirectors()
                                    + "<br>Actors: " + production.getActors() + "<br>Genres: " + production.getGenres() +
                                    "<br>Description: " + production.description + "<br>NumOfSeasons: " + ((Series) production).getNrSeasons() +
                                    "<br>Launch Year: " + ((Series) production).getLaunchYear() + "<br>Rating: " + production.getAverageRating() + "</html>";
                        }
                        if (fanFavInfoLable != null) {
                            panel.remove(fanFavInfoLable);
                        }
                        if( favoritesButton != null){
                            panel.remove(favoritesButton);
                            panel.revalidate();
                            panel.repaint();
                        }
                        if(!user.getFavorites().contains(production)){
                            favoritesButton = new JButton("Add to favorites");
                            favoritesButton.setBounds(340,  660, 150, 30);
                            favoritesButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            favoritesButton.setForeground(new Color(0xFFFFFF));
                            favoritesButton.setFocusable(false);
                            favoritesButton.setBackground(new Color(0x1A1A1A));
                            favoritesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    user.addFavorite(production);
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
                            favoritesButton.setBounds(340,  660, 150, 30);
                            favoritesButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            favoritesButton.setForeground(new Color(0xFFFFFF));
                            favoritesButton.setFocusable(false);
                            favoritesButton.setBackground(new Color(0x1A1A1A));
                            favoritesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    user.removeFavorite(production);
                                    panel.remove(favoritesButton);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                            });
                            panel.add(favoritesButton);
                        }

                        for(Object favorites : user.getFavorites()){
                            if(favorites instanceof Movie){
                                System.out.println(((Movie) favorites).getTitle());
                            }
                            else if(favorites instanceof Series){
                                System.out.println(((Series) favorites).getTitle());
                            }
                        }
                        System.out.println("-----------------");


                        if( addRatingButton != null){
                            panel.remove(addRatingButton);
                            panel.revalidate();
                            panel.repaint();
                        }
                        addRatingButton = new JButton("Add rating");
                        addRatingButton.setBounds(340,  700, 150, 30);
                        addRatingButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                        addRatingButton.setForeground(new Color(0xFFFFFF));
                        addRatingButton.setFocusable(false);
                        addRatingButton.setBackground(new Color(0x1A1A1A));
                        final Production currentProduction = production;
                        addRatingButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                System.out.println(currentProduction.getTitle());
                                JFrame ratingFrame = new JFrame();
                                ratingFrame.setTitle("Add rating");
                                ratingFrame.setSize(300, 300);
                                ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                ratingFrame.setLocationRelativeTo(null);


                                JPanel ratingPanel = new JPanel();
                                ratingPanel.setLayout(null);
                                ratingPanel.setBackground(new Color(0x000000));

                                for (Rating rating : currentProduction.getRatings()) {
                                    if (rating.getUsername().equals(user.getUsername())) {
                                        Object[] options = {"Delete rating", "Cancel"};
                                        int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                        if (response == JOptionPane.YES_OPTION) {
                                            currentProduction.getRatings().remove(rating);
                                            user.getRatings().remove(rating);
                                            JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                        }
                                        return;
                                    }
                                }
                                JLabel ratingLabel = new JLabel("Rating");
                                ratingLabel.setBounds(50, 20, 100, 30);
                                ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                ratingLabel.setForeground(new Color(0xFFFFFF));
                                ratingPanel.add(ratingLabel);

                                JTextField ratingTextField = new JTextField();
                                ratingTextField.setBounds(50, 60, 200, 30);
                                ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                ratingTextField.setForeground(new Color(0x000000));
                                ratingPanel.add(ratingTextField);

                                JLabel commentLabel = new JLabel("Comment");
                                commentLabel.setBounds(50, 100, 100, 30);
                                commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                commentLabel.setForeground(new Color(0xFFFFFF));
                                ratingPanel.add(commentLabel);

                                JTextField commentTextField = new JTextField();
                                commentTextField.setBounds(50, 140, 200, 30);
                                commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                commentTextField.setForeground(new Color(0x000000));
                                ratingPanel.add(commentTextField);

                                JButton ratingButton = new JButton("Add rating");
                                ratingButton.setBounds(50, 190, 200, 30);
                                ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                ratingButton.setForeground(new Color(0xFFFFFF));
                                ratingButton.setFocusable(false);
                                ratingButton.setBackground(new Color(0x1A1A1A));
                                ratingButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String ratingText = ratingTextField.getText();
                                        String comment = commentTextField.getText();

                                        if (ratingText.isEmpty() && comment.isEmpty()) {
                                            JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                            return;
                                        }
                                        long rating;
                                        try {
                                            rating = Long.parseLong(ratingText);
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                            return;
                                        }
                                        try {
                                            Long.parseLong(comment);
                                            JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                            return;
                                        } catch (NumberFormatException ex) {
                                        }
                                        if (rating < 1 || rating > 10) {
                                            JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                            return;
                                        }
                                        List<Production> ReviewdProd = user.getReviewdProductions();
                                        if(!ReviewdProd.contains(currentProduction) && !user.getAccountType().equals(AccountType.ADMIN)){
                                            user.setExperienceStrategy(new ReviewExperienceStrategy());
                                            user.updateExp();
                                            user.addReviewdProduction(currentProduction);
                                        }
                                        Rating newRating = new Rating(user.getUsername(), rating, comment);
                                        user.addRating(rating, comment, currentProduction);
                                        ratingFrame.dispose();
                                    }
                                });
                                ratingPanel.add(ratingButton);
                                ratingFrame.add(ratingPanel);
                                ratingFrame.setVisible(true);
                            }
                        });
                        panel.add(addRatingButton);

                        for(Rating rating : production.ratings) {
                            System.out.println(rating.getUsername());
                            System.out.println(rating.getRating());
                            System.out.println(rating.getComment());
                        }

                        fanFavInfoLable = new JLabel(productionInfo);
                        fanFavInfoLable.setBounds(500,  520, 500, 350);
                        fanFavInfoLable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                        fanFavInfoLable.setForeground(new Color(0xFFFFFF));
                        panel.add(fanFavInfoLable);
                        panel.revalidate();
                        panel.repaint();
                    }

                });
                productionPanel.add(imageLabel);
            } else {
                JLabel titleLabel = new JLabel(production.getTitle());
                titleLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String productionInfo = "There is no production info available";
                        if(production instanceof Movie){
                            productionInfo = "<html>Title: " + production.getTitle() + "<br>Director/s: " + production.getDirectors()
                                    + "<br>Actors: " + production.getActors() + "<br>Genres: " + production.getGenres() +
                                    "<br>Description: " + production.description + "<br>Duration: " + ((Movie) production).getDuration() + " min" +
                                    "<br>Launch Year: " + ((Movie) production).getLaunchYear() + "<br>Rating: " + production.getAverageRating() + "</html>";
                        }
                        else if(production instanceof Series){
                            productionInfo = "<html>Title: " + production.getTitle() + "<br>Director/s: " + production.getDirectors()
                                    + "<br>Actors: " + production.getActors() + "<br>Genres: " + production.getGenres() +
                                    "<br>Description: " + production.description + "<br>NumOfSeasons: " + ((Series) production).getNrSeasons() +
                                    "<br>Launch Year: " + ((Series) production).getLaunchYear() + "<br>Rating: " + production.getAverageRating() + "</html>";
                        }
                        if (fanFavInfoLable != null) {
                            panel.remove(fanFavInfoLable);
                        }
                        fanFavInfoLable = new JLabel(productionInfo);
                        fanFavInfoLable.setBounds(500,  520, 500, 350);
                        fanFavInfoLable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                        fanFavInfoLable.setForeground(new Color(0xFFFFFF));
                        panel.add(fanFavInfoLable);

                        if( favoritesButton != null){
                            panel.remove(favoritesButton);
                            panel.revalidate();
                            panel.repaint();
                        }
                        if(!user.getFavorites().contains(production)){
                            favoritesButton = new JButton("Add to favorites");
                            favoritesButton.setBounds(340,  660, 150, 30);
                            favoritesButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            favoritesButton.setForeground(new Color(0xFFFFFF));
                            favoritesButton.setFocusable(false);
                            favoritesButton.setBackground(new Color(0x1A1A1A));
                            favoritesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    user.addFavorite(production);
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
                            favoritesButton.setBounds(340,  660, 150, 30);
                            favoritesButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            favoritesButton.setForeground(new Color(0xFFFFFF));
                            favoritesButton.setFocusable(false);
                            favoritesButton.setBackground(new Color(0x1A1A1A));
                            favoritesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    user.removeFavorite(production);
                                    panel.remove(favoritesButton);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                            });
                            panel.add(favoritesButton);
                        }

                        if( addRatingButton != null){
                            panel.remove(addRatingButton);
                            panel.revalidate();
                            panel.repaint();
                        }
                        addRatingButton = new JButton("Add rating");
                        addRatingButton.setBounds(340,  700, 150, 30);
                        addRatingButton.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                        addRatingButton.setForeground(new Color(0xFFFFFF));
                        addRatingButton.setFocusable(false);
                        addRatingButton.setBackground(new Color(0x1A1A1A));
                        final Production currentProduction = production;
                        addRatingButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                System.out.println(currentProduction.getTitle());
                                JFrame ratingFrame = new JFrame();
                                ratingFrame.setTitle("Add rating");
                                ratingFrame.setSize(300, 300);
                                ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                ratingFrame.setLocationRelativeTo(null);


                                JPanel ratingPanel = new JPanel();
                                ratingPanel.setLayout(null);
                                ratingPanel.setBackground(new Color(0x000000));

                                for (Rating rating : currentProduction.getRatings()) {
                                    if (rating.getUsername().equals(user.getUsername())) {
                                        Object[] options = {"Delete rating", "Cancel"};
                                        int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                        if (response == JOptionPane.YES_OPTION) {
                                            currentProduction.getRatings().remove(rating);
                                            user.getRatings().remove(rating);
                                            JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                        }
                                        return;
                                    }
                                }
                                JLabel ratingLabel = new JLabel("Rating");
                                ratingLabel.setBounds(50, 20, 100, 30);
                                ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                ratingLabel.setForeground(new Color(0xFFFFFF));
                                ratingPanel.add(ratingLabel);

                                JTextField ratingTextField = new JTextField();
                                ratingTextField.setBounds(50, 60, 200, 30);
                                ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                ratingTextField.setForeground(new Color(0x000000));
                                ratingPanel.add(ratingTextField);

                                JLabel commentLabel = new JLabel("Comment");
                                commentLabel.setBounds(50, 100, 100, 30);
                                commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                commentLabel.setForeground(new Color(0xFFFFFF));
                                ratingPanel.add(commentLabel);

                                JTextField commentTextField = new JTextField();
                                commentTextField.setBounds(50, 140, 200, 30);
                                commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                commentTextField.setForeground(new Color(0x000000));
                                ratingPanel.add(commentTextField);

                                JButton ratingButton = new JButton("Add rating");
                                ratingButton.setBounds(50, 190, 200, 30);
                                ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                ratingButton.setForeground(new Color(0xFFFFFF));
                                ratingButton.setFocusable(false);
                                ratingButton.setBackground(new Color(0x1A1A1A));
                                ratingButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String ratingText = ratingTextField.getText();
                                        String comment = commentTextField.getText();

                                        if (ratingText.isEmpty() && comment.isEmpty()) {
                                            JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                            return;
                                        }
                                        long rating;
                                        try {
                                            rating = Long.parseLong(ratingText);
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                            return;
                                        }
                                        try {
                                            Long.parseLong(comment);
                                            JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                            return;
                                        } catch (NumberFormatException ex) {
                                        }
                                        if (rating < 1 || rating > 10) {
                                            JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                            return;
                                        }
                                        List<Production> ReviewdProd = user.getReviewdProductions();
                                        if(!ReviewdProd.contains(currentProduction) && !user.getAccountType().equals(AccountType.ADMIN)){
                                            user.setExperienceStrategy(new ReviewExperienceStrategy());
                                            user.updateExp();
                                            user.addReviewdProduction(currentProduction);
                                        }
                                        Rating newRating = new Rating(user.getUsername(), rating, comment);
                                        user.addRating(rating, comment, currentProduction);
                                        ratingFrame.dispose();
                                    }
                                });
                                ratingPanel.add(ratingButton);
                                ratingFrame.add(ratingPanel);
                                ratingFrame.setVisible(true);
                            }
                        });
                        panel.add(addRatingButton);

                        panel.revalidate();
                        panel.repaint();
                    }
                });
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productionPanel.add(titleLabel);
            }

            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titlePanel.setBackground(new Color(0x1A1A1A));
            JLabel titleLabel = new JLabel("<html>" + production.getTitle() + "</html>");
            titleLabel.setForeground(Color.WHITE);
            titlePanel.add(titleLabel);
            productionPanel.add(titlePanel);

            panelIMGFanFavorites.add(productionPanel);

            count++;
        }


        loadMovies(movies, user, "All");
        JLabel Movies = new JLabel("Movies");
        Movies.setBounds(100, 860, 200, 50);
        Movies.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        Movies.setForeground(new Color(0xFFFFFF));
        panel.add(Movies);

        panelIMGMovies.setLayout(new BoxLayout(panelIMGMovies, BoxLayout.X_AXIS));
        scrollPaneMovies.setWheelScrollingEnabled(false);
        scrollPaneMovies.setBorder(null);
        scrollPaneMovies.setBounds(100, 920, 1200, 370);

        panel.add(scrollPaneMovies);

        String[] genreOptions = { "All", "ACTION", "ADVENTURE", "COMEDY", "DRAMA", "HORROR", "SF", "FANTASY", "COOKING",
                "ROMANCE", "MYSTERY", "THRILLER", "CRIME", "BIOGRAPHY", "WAR"};
        JComboBox<String> genreComboBox = new JComboBox<>(genreOptions);
        panel.add(genreComboBox);
        genreComboBox.setBounds(410, 870, 200, 30);
        genreComboBox.setFont(new Font("Sans Serif", Font.PLAIN, 13));
        genreComboBox.setForeground(new Color(0xFFFFFF));
        genreComboBox.setFocusable(false);
        genreComboBox.setBackground(new Color(0x1A1A1A));
        genreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGenre = (String) genreComboBox.getSelectedItem();
                loadMovies(movies, user, selectedGenre);
            }
        });

        String[] sortOptions = { "Number of Ratings", "Average Rating", "Duration"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setBounds(190, 870, 200, 30);
        sortComboBox.setFont(new Font("Sans Serif", Font.PLAIN, 13));
        sortComboBox.setForeground(new Color(0xFFFFFF));
        sortComboBox.setFocusable(false);
        sortComboBox.setBackground(new Color(0x1A1A1A));
        panel.add(sortComboBox);
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGenre = (String) genreComboBox.getSelectedItem();
                String selectedSortOption = (String) sortComboBox.getSelectedItem();
                if (selectedSortOption.equals("Number of Ratings")) {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            int ratingComparison = Integer.compare(m2.getRatings().size(), m1.getRatings().size());
                            if (ratingComparison != 0) {
                                return ratingComparison;
                            } else {
                                return m1.getTitle().compareTo(m2.getTitle());
                            }
                        }
                    });
                } else if (selectedSortOption.equals("Average Rating")) {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            int ratingComparison = Double.compare(m2.getAverageRating(), m1.getAverageRating());
                            if (ratingComparison != 0) {
                                return ratingComparison;
                            } else {
                                return m1.getTitle().compareTo(m2.getTitle());
                            }
                        }
                    });
                }else if (selectedSortOption.equals("Duration")) {
                    movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            int ratingComparison = Long.compare(m2.getDuration(), m1.getDuration());
                            if (ratingComparison != 0) {
                                return ratingComparison;
                            } else {
                                return m1.getTitle().compareTo(m2.getTitle());
                            }
                        }
                    });
                }
                loadMovies(movies, user, selectedGenre);
            }
        });

        JLabel seriesLabel= new JLabel("Series");
        seriesLabel.setBounds(100, 1660, 200, 50);
        seriesLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        seriesLabel.setForeground(new Color(0xFFFFFF));
        panel.add(seriesLabel);
        panelIMGSeries.setLayout(new BoxLayout(panelIMGSeries, BoxLayout.X_AXIS));
        scrollPaneSeries.setWheelScrollingEnabled(false);
        scrollPaneSeries.setBorder(null);
        scrollPaneSeries.setBounds(100, 1720, 1200, 370);
        panel.add(scrollPaneSeries);
        loadSeries(series, user, "All");

        JComboBox<String> genreComboBox2 = new JComboBox<>(genreOptions);
        panel.add(genreComboBox2);
        genreComboBox2.setBounds(410, 1670, 200, 30);
        genreComboBox2.setFont(new Font("Sans Serif", Font.PLAIN, 13));
        genreComboBox2.setForeground(new Color(0xFFFFFF));
        genreComboBox2.setFocusable(false);
        genreComboBox2.setBackground(new Color(0x1A1A1A));
        genreComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGenre = (String) genreComboBox2.getSelectedItem();
                loadSeries(series, user, selectedGenre);
            }
        });

        String[] sortOptions2 = { "Number of Ratings", "Average Rating", "NumOfSeasons"};
        JComboBox<String> sortComboBox2 = new JComboBox<>(sortOptions2);
        sortComboBox2.setBounds(190, 1670, 200, 30);
        sortComboBox2.setFont(new Font("Sans Serif", Font.PLAIN, 13));
        sortComboBox2.setForeground(new Color(0xFFFFFF));
        sortComboBox2.setFocusable(false);
        sortComboBox2.setBackground(new Color(0x1A1A1A));
        panel.add(sortComboBox2);
        sortComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGenre = (String) genreComboBox2.getSelectedItem();
                String selectedSortOption = (String) sortComboBox2.getSelectedItem();
                if (selectedSortOption.equals("Number of Ratings")) {
                    series.sort(new Comparator<Series>() {
                        @Override
                        public int compare(Series m1, Series m2) {
                            int ratingComparison = Integer.compare(m2.getRatings().size(), m1.getRatings().size());
                            if (ratingComparison != 0) {
                                return ratingComparison;
                            } else {
                                return m1.getTitle().compareTo(m2.getTitle());
                            }
                        }
                    });
                } else if (selectedSortOption.equals("Average Rating")) {
                    series.sort(new Comparator<Series>() {
                        @Override
                        public int compare(Series m1, Series m2) {
                            int ratingComparison = Double.compare(m2.getAverageRating(), m1.getAverageRating());
                            if (ratingComparison != 0) {
                                return ratingComparison;
                            } else {
                                return m1.getTitle().compareTo(m2.getTitle());
                            }
                        }
                    });
                }else if (selectedSortOption.equals("NumOfSeasons")) {
                    series.sort(new Comparator<Series>() {
                        @Override
                        public int compare(Series m1, Series m2) {
                            int ratingComparison = Long.compare(m2.getNrSeasons(), m1.getNrSeasons());
                            if (ratingComparison != 0) {
                                return ratingComparison;
                            } else {
                                return m1.getTitle().compareTo(m2.getTitle());
                            }
                        }
                    });
                }
                loadSeries(series, user, selectedGenre);
            }
        });

        JLabel searchLabel = new JLabel("Search An Actor or a Production");
        searchLabel.setBounds(580, 2420, 300, 30);
        searchLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        searchLabel.setForeground(new Color(0xFFFFFF));
        panel.add(searchLabel);

        searchButton.setBounds(420, 2520, 100, 30);
        searchButton.setBackground(new Color(0x1A1A1A));
        searchButton.setForeground(new Color(0xFFFFFF));
        searchButton.setFocusable(false);
        createSearchButtonActionListener(user);
        panel.add(searchButton);

        textField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        textField.setColumns(10);
        textField.addActionListener(this);
        textField.setBounds(520, 2520, 500, 30);
        panel.add(textField);

        JLabel menuLabel = new JLabel("Go to Menu");
        menuLabel.setFont(new Font("Sans Serif", Font.PLAIN, 13));
        menuLabel.setForeground(new Color(0xFFFFFF));
        menuLabel.setBounds(100, 2520, 100, 30);
        menuLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                menuPage = new MenuWindow(user);
                menuPage.setVisible(true);
            }
        });
        panel.add(menuLabel);

        JLabel actorLabel = new JLabel("Go to Actors");
        actorLabel.setFont(new Font("Sans Serif", Font.PLAIN, 13));
        actorLabel.setForeground(new Color(0xFFFFFF));
        actorLabel.setBounds(1100, 2520, 100, 30);
        actorLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                ActorsWindow actorsPage = new ActorsWindow(user, IMDB.getInstance().actors);
                actorsPage.setVisible(true);
            }
        });
        panel.add(actorLabel);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.setBackground(new Color(0x000000));
        scrollPane.getVerticalScrollBar().setBackground(new Color(0x000000));
        scrollPane.getHorizontalScrollBar().setBackground(new Color(0x000000));
        add(scrollPane);
        setVisible(true);
    }


    private void loadMovies(List<Movie> movies, User user, String genre) {
        panelIMGMovies.removeAll();
        boolean movieExists = false;
        for (Movie movie : movies) {
            if (genre.equals("All") || movie.getGenres().contains(Genre.valueOf(genre))) {
                movieExists = true;
                JPanel moviePanel = new JPanel();
                moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));
                moviePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
                moviePanel.setBackground(Color.BLACK);

                ImageIcon imageIcon = new ImageIcon(movie.imageURL);
                if (imageIcon.getIconWidth() != -1 && imageIcon.getIconHeight() != -1) {
                    Image image = imageIcon.getImage().getScaledInstance(205, 303, Image.SCALE_DEFAULT);
                    JLabel imageLabel = new JLabel(new ImageIcon(image));
                    imageLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            String movieInfo = "There is no production info available";
                            movieInfo = "<html>Title: " + movie.getTitle() + "<br>Director/s: " + movie.getDirectors()
                                    + "<br>Actors: " + movie.getActors() + "<br>Genres: " + movie.getGenres() +
                                    "<br>Description: " + movie.description + "<br>Duration: " + movie.getDuration() + " min" +
                                    "<br>Launch Year: " + movie.getLaunchYear() + "<br>Rating: " + movie.getAverageRating() + "</html>";
                            if (movieInfoLable != null) {
                                panel.remove(movieInfoLable);
                            }
                            movieInfoLable = new JLabel(movieInfo);
                            movieInfoLable.setBounds(500, 1270, 500, 350);
                            movieInfoLable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                            movieInfoLable.setForeground(new Color(0xFFFFFF));
                            panel.add(movieInfoLable);
                            panel.revalidate();
                            panel.repaint();

                            if (favoritesButton2 != null) {
                                panel.remove(favoritesButton2);
                                panel.revalidate();
                                panel.repaint();
                            }
                            if (!user.getFavorites().contains(movie)) {
                                favoritesButton2 = new JButton("Add to favorites");
                                favoritesButton2.setBounds(340, 1410, 150, 30);
                                favoritesButton2.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButton2.setForeground(new Color(0xFFFFFF));
                                favoritesButton2.setFocusable(false);
                                favoritesButton2.setBackground(new Color(0x1A1A1A));
                                favoritesButton2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.addFavorite(movie);
                                        System.out.println("Added to favorites");
                                        panel.remove(favoritesButton2);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButton2);
                            }
                            else {
                                favoritesButton2 = new JButton("Remove from favorites");
                                favoritesButton2.setBounds(340, 1410, 150, 30);
                                favoritesButton2.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButton2.setForeground(new Color(0xFFFFFF));
                                favoritesButton2.setFocusable(false);
                                favoritesButton2.setBackground(new Color(0x1A1A1A));
                                favoritesButton2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.removeFavorite(movie);
                                        panel.remove(favoritesButton2);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButton2);
                            }
                            if( addRatingButton2 != null){
                                panel.remove(addRatingButton2);
                                panel.revalidate();
                                panel.repaint();
                            }
                            addRatingButton2 = new JButton("Add rating");
                            addRatingButton2.setBounds(340,  1450, 150, 30);
                            addRatingButton2.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            addRatingButton2.setForeground(new Color(0xFFFFFF));
                            addRatingButton2.setFocusable(false);
                            addRatingButton2.setBackground(new Color(0x1A1A1A));
                            final Production currentProduction = movie;
                            addRatingButton2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    System.out.println(currentProduction.getTitle());
                                    JFrame ratingFrame = new JFrame();
                                    ratingFrame.setTitle("Add rating");
                                    ratingFrame.setSize(300, 300);
                                    ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    ratingFrame.setLocationRelativeTo(null);


                                    JPanel ratingPanel = new JPanel();
                                    ratingPanel.setLayout(null);
                                    ratingPanel.setBackground(new Color(0x000000));

                                    for (Rating rating : currentProduction.getRatings()) {
                                        if (rating.getUsername().equals(user.getUsername())) {
                                            Object[] options = {"Delete rating", "Cancel"};
                                            int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                            if (response == JOptionPane.YES_OPTION) {
                                                currentProduction.getRatings().remove(rating);
                                                user.getRatings().remove(rating);
                                                JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                            }
                                            return;
                                        }
                                    }
                                    JLabel ratingLabel = new JLabel("Rating");
                                    ratingLabel.setBounds(50, 20, 100, 30);
                                    ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(ratingLabel);

                                    JTextField ratingTextField = new JTextField();
                                    ratingTextField.setBounds(50, 60, 200, 30);
                                    ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(ratingTextField);

                                    JLabel commentLabel = new JLabel("Comment");
                                    commentLabel.setBounds(50, 100, 100, 30);
                                    commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(commentLabel);

                                    JTextField commentTextField = new JTextField();
                                    commentTextField.setBounds(50, 140, 200, 30);
                                    commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(commentTextField);

                                    JButton ratingButton = new JButton("Add rating");
                                    ratingButton.setBounds(50, 190, 200, 30);
                                    ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingButton.setForeground(new Color(0xFFFFFF));
                                    ratingButton.setFocusable(false);
                                    ratingButton.setBackground(new Color(0x1A1A1A));
                                    ratingButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String ratingText = ratingTextField.getText();
                                            String comment = commentTextField.getText();

                                            if (ratingText.isEmpty() && comment.isEmpty()) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                                return;
                                            }
                                            long rating;
                                            try {
                                                rating = Long.parseLong(ratingText);
                                            } catch (NumberFormatException ex) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                                return;
                                            }
                                            try {
                                                Long.parseLong(comment);
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                                return;
                                            } catch (NumberFormatException ex) {
                                            }
                                            if (rating < 1 || rating > 10) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                                return;
                                            }
                                            List<Production> ReviewdProd = user.getReviewdProductions();
                                            if(!ReviewdProd.contains(currentProduction) && !user.getAccountType().equals(AccountType.ADMIN)){
                                                user.setExperienceStrategy(new ReviewExperienceStrategy());
                                                user.updateExp();
                                                user.addReviewdProduction(currentProduction);
                                            }
                                            Rating newRating = new Rating(user.getUsername(), rating, comment);
                                            user.addRating(rating, comment, currentProduction);
                                            ratingFrame.dispose();
                                        }
                                    });
                                    ratingPanel.add(ratingButton);
                                    ratingFrame.add(ratingPanel);
                                    ratingFrame.setVisible(true);
                                }
                            });
                            panel.add(addRatingButton2);
                        }
                    });

                    panel.revalidate();
                    panel.repaint();
                    moviePanel.add(imageLabel);
                } else {
                    JLabel titleLabel = new JLabel(movie.title);
                    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    titleLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            String movieInfo = "There is no production info available";
                            movieInfo = "<html>Title: " + movie.getTitle() + "<br>Director/s: " + movie.getDirectors()
                                    + "<br>Actors: " + movie.getActors() + "<br>Genres: " + movie.getGenres() +
                                    "<br>Description: " + movie.description + "<br>Duration: " + movie.getDuration() + " min" +
                                    "<br>Launch Year: " + movie.getLaunchYear() + "<br>Rating: " + movie.getAverageRating() + "</html>";
                            if (movieInfoLable != null) {
                                panel.remove(movieInfoLable);
                            }
                            movieInfoLable = new JLabel(movieInfo);
                            movieInfoLable.setBounds(500, 1270, 500, 350);
                            movieInfoLable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                            movieInfoLable.setForeground(new Color(0xFFFFFF));
                            if (favoritesButton2 != null) {
                                panel.remove(favoritesButton2);
                                panel.revalidate();
                                panel.repaint();
                            }
                            if (!user.getFavorites().contains(movie)) {
                                favoritesButton2 = new JButton("Add to favorites");
                                favoritesButton2.setBounds(340, 1410, 150, 30);
                                favoritesButton2.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButton2.setForeground(new Color(0xFFFFFF));
                                favoritesButton2.setFocusable(false);
                                favoritesButton2.setBackground(new Color(0x1A1A1A));
                                favoritesButton2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.addFavorite(movie);
                                        System.out.println("Added to favorites");
                                        panel.remove(favoritesButton2);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButton2);
                            }
                            else {
                                favoritesButton2 = new JButton("Remove from favorites");
                                favoritesButton2.setBounds(340, 1410, 150, 30);
                                favoritesButton2.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButton2.setForeground(new Color(0xFFFFFF));
                                favoritesButton2.setFocusable(false);
                                favoritesButton2.setBackground(new Color(0x1A1A1A));
                                favoritesButton2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.removeFavorite(movie);
                                        panel.remove(favoritesButton2);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButton2);
                            }
                            panel.add(movieInfoLable);

                            if( addRatingButton2 != null){
                                panel.remove(addRatingButton2);
                                panel.revalidate();
                                panel.repaint();
                            }
                            addRatingButton2 = new JButton("Add rating");
                            addRatingButton2.setBounds(340,  1450, 150, 30);
                            addRatingButton2.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            addRatingButton2.setForeground(new Color(0xFFFFFF));
                            addRatingButton2.setFocusable(false);
                            addRatingButton2.setBackground(new Color(0x1A1A1A));
                            final Production currentProduction = movie;
                            addRatingButton2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    System.out.println(currentProduction.getTitle());
                                    JFrame ratingFrame = new JFrame();
                                    ratingFrame.setTitle("Add rating");
                                    ratingFrame.setSize(300, 300);
                                    ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    ratingFrame.setLocationRelativeTo(null);


                                    JPanel ratingPanel = new JPanel();
                                    ratingPanel.setLayout(null);
                                    ratingPanel.setBackground(new Color(0x000000));

                                    for (Rating rating : currentProduction.getRatings()) {
                                        if (rating.getUsername().equals(user.getUsername())) {
                                            Object[] options = {"Delete rating", "Cancel"};
                                            int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                            if (response == JOptionPane.YES_OPTION) {
                                                currentProduction.getRatings().remove(rating);
                                                user.getRatings().remove(rating);
                                                JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                            }
                                            return;
                                        }
                                    }
                                    JLabel ratingLabel = new JLabel("Rating");
                                    ratingLabel.setBounds(50, 20, 100, 30);
                                    ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(ratingLabel);

                                    JTextField ratingTextField = new JTextField();
                                    ratingTextField.setBounds(50, 60, 200, 30);
                                    ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(ratingTextField);

                                    JLabel commentLabel = new JLabel("Comment");
                                    commentLabel.setBounds(50, 100, 100, 30);
                                    commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(commentLabel);

                                    JTextField commentTextField = new JTextField();
                                    commentTextField.setBounds(50, 140, 200, 30);
                                    commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(commentTextField);

                                    JButton ratingButton = new JButton("Add rating");
                                    ratingButton.setBounds(50, 190, 200, 30);
                                    ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingButton.setForeground(new Color(0xFFFFFF));
                                    ratingButton.setFocusable(false);
                                    ratingButton.setBackground(new Color(0x1A1A1A));
                                    ratingButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String ratingText = ratingTextField.getText();
                                            String comment = commentTextField.getText();

                                            if (ratingText.isEmpty() && comment.isEmpty()) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                                return;
                                            }
                                            long rating;
                                            try {
                                                rating = Long.parseLong(ratingText);
                                            } catch (NumberFormatException ex) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                                return;
                                            }
                                            try {
                                                Long.parseLong(comment);
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                                return;
                                            } catch (NumberFormatException ex) {
                                            }
                                            if (rating < 1 || rating > 10) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                                return;
                                            }
                                            List<Production> ReviewdProd = user.getReviewdProductions();
                                            if(!ReviewdProd.contains(currentProduction) && !user.getAccountType().equals(AccountType.ADMIN)){
                                                user.setExperienceStrategy(new ReviewExperienceStrategy());
                                                user.updateExp();
                                                user.addReviewdProduction(currentProduction);
                                            }
                                            Rating newRating = new Rating(user.getUsername(), rating, comment);
                                            user.addRating(rating, comment, currentProduction);
                                            ratingFrame.dispose();
                                        }
                                    });
                                    ratingPanel.add(ratingButton);
                                    ratingFrame.add(ratingPanel);
                                    ratingFrame.setVisible(true);
                                }
                            });
                            panel.add(addRatingButton2);
                            panel.revalidate();
                            panel.repaint();
                        }
                    });
                    moviePanel.add(titleLabel);
                }

                JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                titlePanel.setBackground(new Color(0x1A1A1A));
                JLabel titleLabel = new JLabel("<html>" + movie.title + "</html>");
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                titlePanel.add(titleLabel);

                moviePanel.add(titlePanel);

                panelIMGMovies.add(moviePanel);
            }
            panelIMGMovies.revalidate();
            panelIMGMovies.repaint();
        }
        if (!movieExists) {
            JOptionPane.showMessageDialog(null, "A movie of this genre does not exist.");
        }
        panelIMGMovies.revalidate();
        panelIMGMovies.repaint();
    }

    private void loadSeries(List<Series> series, User user, String genre){
        panelIMGSeries.removeAll();
        boolean serieExists = false;
        for (Series serie : series) {
            if (genre.equals("All") || serie.getGenres().contains(Genre.valueOf(genre))) {
                serieExists = true;
                JPanel seriesPanel = new JPanel();
                seriesPanel.setLayout(new BoxLayout(seriesPanel, BoxLayout.Y_AXIS));
                seriesPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
                seriesPanel.setBackground(Color.BLACK);

                ImageIcon imageIcon = new ImageIcon(serie.imageURL);
                if (imageIcon.getIconWidth() != -1 && imageIcon.getIconHeight() != -1) {
                    Image image = imageIcon.getImage().getScaledInstance(205, 303, Image.SCALE_DEFAULT);
                    JLabel imageLabel = new JLabel(new ImageIcon(image));
                    imageLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            String serieInfo = "There is no production info available";
                            serieInfo = "<html>Title: " + serie.getTitle() + "<br>Director/s: " + serie.getDirectors()
                                    + "<br>Actors: " + serie.getActors() + "<br>Genres: " + serie.getGenres() +
                                    "<br>Description: " + serie.description + "<br>NumOfSeasons: " + serie.getNrSeasons() +
                                    "<br>Launch Year: " + serie.getLaunchYear() + "<br>Rating: " + serie.getAverageRating() + "</html>";
                            if (seriesInfoLable != null) {
                                panel.remove(seriesInfoLable);
                            }
                            seriesInfoLable = new JLabel(serieInfo);
                            seriesInfoLable.setBounds(500, 2070, 500, 350);
                            seriesInfoLable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                            seriesInfoLable.setForeground(new Color(0xFFFFFF));
                            panel.add(seriesInfoLable);
                            panel.revalidate();
                            panel.repaint();
                            if (favoritesButton3 != null) {
                                panel.remove(favoritesButton3);
                                panel.revalidate();
                                panel.repaint();
                            }
                            if (!user.getFavorites().contains(serie)) {
                                favoritesButton3 = new JButton("Add to favorites");
                                favoritesButton3.setBounds(340, 2210, 150, 30);
                                favoritesButton3.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButton3.setForeground(new Color(0xFFFFFF));
                                favoritesButton3.setFocusable(false);
                                favoritesButton3.setBackground(new Color(0x1A1A1A));
                                favoritesButton3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.addFavorite(serie);
                                        System.out.println("Added to favorites");
                                        panel.remove(favoritesButton3);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButton3);
                                if( addRatingButton3 != null){
                                    panel.remove(addRatingButton3);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                                addRatingButton3 = new JButton("Add rating");
                                addRatingButton3.setBounds(340,  2250, 150, 30);
                                addRatingButton3.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                addRatingButton3.setForeground(new Color(0xFFFFFF));
                                addRatingButton3.setFocusable(false);
                                addRatingButton3.setBackground(new Color(0x1A1A1A));
                                final Production currentProduction = serie;
                                addRatingButton3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        System.out.println(currentProduction.getTitle());
                                        JFrame ratingFrame = new JFrame();
                                        ratingFrame.setTitle("Add rating");
                                        ratingFrame.setSize(300, 300);
                                        ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        ratingFrame.setLocationRelativeTo(null);


                                        JPanel ratingPanel = new JPanel();
                                        ratingPanel.setLayout(null);
                                        ratingPanel.setBackground(new Color(0x000000));

                                        for (Rating rating : currentProduction.getRatings()) {
                                            if (rating.getUsername().equals(user.getUsername())) {
                                                Object[] options = {"Delete rating", "Cancel"};
                                                int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                                if (response == JOptionPane.YES_OPTION) {
                                                    currentProduction.getRatings().remove(rating);
                                                    user.getRatings().remove(rating);
                                                    JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                                }
                                                return;
                                            }
                                        }
                                        JLabel ratingLabel = new JLabel("Rating");
                                        ratingLabel.setBounds(50, 20, 100, 30);
                                        ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        ratingLabel.setForeground(new Color(0xFFFFFF));
                                        ratingPanel.add(ratingLabel);

                                        JTextField ratingTextField = new JTextField();
                                        ratingTextField.setBounds(50, 60, 200, 30);
                                        ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        ratingTextField.setForeground(new Color(0x000000));
                                        ratingPanel.add(ratingTextField);

                                        JLabel commentLabel = new JLabel("Comment");
                                        commentLabel.setBounds(50, 100, 100, 30);
                                        commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        commentLabel.setForeground(new Color(0xFFFFFF));
                                        ratingPanel.add(commentLabel);

                                        JTextField commentTextField = new JTextField();
                                        commentTextField.setBounds(50, 140, 200, 30);
                                        commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        commentTextField.setForeground(new Color(0x000000));
                                        ratingPanel.add(commentTextField);

                                        JButton ratingButton = new JButton("Add rating");
                                        ratingButton.setBounds(50, 190, 200, 30);
                                        ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        ratingButton.setForeground(new Color(0xFFFFFF));
                                        ratingButton.setFocusable(false);
                                        ratingButton.setBackground(new Color(0x1A1A1A));
                                        ratingButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                String ratingText = ratingTextField.getText();
                                                String comment = commentTextField.getText();

                                                if (ratingText.isEmpty() && comment.isEmpty()) {
                                                    JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                                    return;
                                                }
                                                long rating;
                                                try {
                                                    rating = Long.parseLong(ratingText);
                                                } catch (NumberFormatException ex) {
                                                    JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                                    return;
                                                }
                                                try {
                                                    Long.parseLong(comment);
                                                    JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                                    return;
                                                } catch (NumberFormatException ex) {
                                                }
                                                if (rating < 1 || rating > 10) {
                                                    JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                                    return;
                                                }
                                                List<Production> ReviewdProd = user.getReviewdProductions();
                                                if(!ReviewdProd.contains(currentProduction) && !user.getAccountType().equals(AccountType.ADMIN)){
                                                    user.setExperienceStrategy(new ReviewExperienceStrategy());
                                                    user.updateExp();
                                                    user.addReviewdProduction(currentProduction);
                                                }
                                                Rating newRating = new Rating(user.getUsername(), rating, comment);
                                                user.addRating(rating, comment, currentProduction);
                                                ratingFrame.dispose();
                                            }
                                        });
                                        ratingPanel.add(ratingButton);
                                        ratingFrame.add(ratingPanel);
                                        ratingFrame.setVisible(true);
                                    }
                                });
                                panel.add(addRatingButton3);
                            } else {
                                favoritesButton3 = new JButton("Remove from favorites");
                                favoritesButton3.setBounds(340, 2210, 150, 30);
                                favoritesButton3.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButton3.setForeground(new Color(0xFFFFFF));
                                favoritesButton3.setFocusable(false);
                                favoritesButton3.setBackground(new Color(0x1A1A1A));
                                favoritesButton3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.removeFavorite(serie);
                                        panel.remove(favoritesButton3);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButton3);

                                if( addRatingButton3 != null){
                                    panel.remove(addRatingButton3);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                                addRatingButton3 = new JButton("Add rating");
                                addRatingButton3.setBounds(340,  2250, 150, 30);
                                addRatingButton3.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                addRatingButton3.setForeground(new Color(0xFFFFFF));
                                addRatingButton3.setFocusable(false);
                                addRatingButton3.setBackground(new Color(0x1A1A1A));
                                final Production currentProduction = serie;
                                addRatingButton3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        System.out.println(currentProduction.getTitle());
                                        JFrame ratingFrame = new JFrame();
                                        ratingFrame.setTitle("Add rating");
                                        ratingFrame.setSize(300, 300);
                                        ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        ratingFrame.setLocationRelativeTo(null);


                                        JPanel ratingPanel = new JPanel();
                                        ratingPanel.setLayout(null);
                                        ratingPanel.setBackground(new Color(0x000000));

                                        for (Rating rating : currentProduction.getRatings()) {
                                            if (rating.getUsername().equals(user.getUsername())) {
                                                Object[] options = {"Delete rating", "Cancel"};
                                                int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                                if (response == JOptionPane.YES_OPTION) {
                                                    currentProduction.getRatings().remove(rating);
                                                    user.getRatings().remove(rating);
                                                    JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                                }
                                                return;
                                            }
                                        }
                                        JLabel ratingLabel = new JLabel("Rating");
                                        ratingLabel.setBounds(50, 20, 100, 30);
                                        ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        ratingLabel.setForeground(new Color(0xFFFFFF));
                                        ratingPanel.add(ratingLabel);

                                        JTextField ratingTextField = new JTextField();
                                        ratingTextField.setBounds(50, 60, 200, 30);
                                        ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        ratingTextField.setForeground(new Color(0x000000));
                                        ratingPanel.add(ratingTextField);

                                        JLabel commentLabel = new JLabel("Comment");
                                        commentLabel.setBounds(50, 100, 100, 30);
                                        commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        commentLabel.setForeground(new Color(0xFFFFFF));
                                        ratingPanel.add(commentLabel);

                                        JTextField commentTextField = new JTextField();
                                        commentTextField.setBounds(50, 140, 200, 30);
                                        commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        commentTextField.setForeground(new Color(0x000000));
                                        ratingPanel.add(commentTextField);

                                        JButton ratingButton = new JButton("Add rating");
                                        ratingButton.setBounds(50, 190, 200, 30);
                                        ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                        ratingButton.setForeground(new Color(0xFFFFFF));
                                        ratingButton.setFocusable(false);
                                        ratingButton.setBackground(new Color(0x1A1A1A));
                                        ratingButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                String ratingText = ratingTextField.getText();
                                                String comment = commentTextField.getText();

                                                if (ratingText.isEmpty() && comment.isEmpty()) {
                                                    JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                                    return;
                                                }
                                                long rating;
                                                try {
                                                    rating = Long.parseLong(ratingText);
                                                } catch (NumberFormatException ex) {
                                                    JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                                    return;
                                                }
                                                try {
                                                    Long.parseLong(comment);
                                                    JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                                    return;
                                                } catch (NumberFormatException ex) {
                                                }
                                                if (rating < 1 || rating > 10) {
                                                    JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                                    return;
                                                }
                                                List<Production> ReviewdProd = user.getReviewdProductions();
                                                if(!ReviewdProd.contains(currentProduction) && !user.getAccountType().equals(AccountType.ADMIN)){
                                                    user.setExperienceStrategy(new ReviewExperienceStrategy());
                                                    user.updateExp();
                                                    user.addReviewdProduction(currentProduction);
                                                }
                                                Rating newRating = new Rating(user.getUsername(), rating, comment);
                                                user.addRating(rating, comment, currentProduction);
                                                ratingFrame.dispose();
                                            }
                                        });
                                        ratingPanel.add(ratingButton);
                                        ratingFrame.add(ratingPanel);
                                        ratingFrame.setVisible(true);
                                    }
                                });
                                panel.add(addRatingButton3);
                            }

                        }
                    });
                    seriesPanel.add(imageLabel);
                } else {
                    JLabel titleLabel = new JLabel(serie.title);
                    titleLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            String serieInfo = "There is no production info available";
                            serieInfo = "<html>Title: " + serie.getTitle() + "<br>Director/s: " + serie.getDirectors()
                                    + "<br>Actors: " + serie.getActors() + "<br>Genres: " + serie.getGenres() +
                                    "<br>Description: " + serie.description + "<br>NumOfSeasons: " + serie.getNrSeasons() +
                                    "<br>Launch Year: " + serie.getLaunchYear() + "<br>Rating: " + serie.getAverageRating() + "</html>";
                            if (seriesInfoLable != null) {
                                panel.remove(seriesInfoLable);
                            }
                            seriesInfoLable = new JLabel(serieInfo);
                            seriesInfoLable.setBounds(500, 2070, 500, 350);
                            seriesInfoLable.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                            seriesInfoLable.setForeground(new Color(0xFFFFFF));
                            panel.add(seriesInfoLable);
                            panel.revalidate();
                            panel.repaint();
                            if (favoritesButton3 != null) {
                                panel.remove(favoritesButton3);
                                panel.revalidate();
                                panel.repaint();
                            }
                            if (!user.getFavorites().contains(serie)) {
                                favoritesButton3 = new JButton("Add to favorites");
                                favoritesButton3.setBounds(340, 2210, 150, 30);
                                favoritesButton3.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButton3.setForeground(new Color(0xFFFFFF));
                                favoritesButton3.setFocusable(false);
                                favoritesButton3.setBackground(new Color(0x1A1A1A));
                                favoritesButton3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.addFavorite(serie);
                                        System.out.println("Added to favorites");
                                        panel.remove(favoritesButton3);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButton3);
                            } else {
                                favoritesButton3 = new JButton("Remove from favorites");
                                favoritesButton3.setBounds(340, 2210, 150, 30);
                                favoritesButton3.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButton3.setForeground(new Color(0xFFFFFF));
                                favoritesButton3.setFocusable(false);
                                favoritesButton3.setBackground(new Color(0x1A1A1A));
                                favoritesButton3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.removeFavorite(serie);
                                        panel.remove(favoritesButton3);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButton3);
                            }
                            if( addRatingButton3 != null){
                                panel.remove(addRatingButton3);
                                panel.revalidate();
                                panel.repaint();
                            }
                            addRatingButton3 = new JButton("Add rating");
                            addRatingButton3.setBounds(340,  2250, 150, 30);
                            addRatingButton3.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            addRatingButton3.setForeground(new Color(0xFFFFFF));
                            addRatingButton3.setFocusable(false);
                            addRatingButton3.setBackground(new Color(0x1A1A1A));
                            final Production currentProduction = serie;
                            addRatingButton3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    System.out.println(currentProduction.getTitle());
                                    JFrame ratingFrame = new JFrame();
                                    ratingFrame.setTitle("Add rating");
                                    ratingFrame.setSize(300, 300);
                                    ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    ratingFrame.setLocationRelativeTo(null);


                                    JPanel ratingPanel = new JPanel();
                                    ratingPanel.setLayout(null);
                                    ratingPanel.setBackground(new Color(0x000000));

                                    for (Rating rating : currentProduction.getRatings()) {
                                        if (rating.getUsername().equals(user.getUsername())) {
                                            Object[] options = {"Delete rating", "Cancel"};
                                            int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                            if (response == JOptionPane.YES_OPTION) {
                                                currentProduction.getRatings().remove(rating);
                                                user.getRatings().remove(rating);
                                                JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                            }
                                            return;
                                        }
                                    }
                                    JLabel ratingLabel = new JLabel("Rating");
                                    ratingLabel.setBounds(50, 20, 100, 30);
                                    ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(ratingLabel);

                                    JTextField ratingTextField = new JTextField();
                                    ratingTextField.setBounds(50, 60, 200, 30);
                                    ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(ratingTextField);

                                    JLabel commentLabel = new JLabel("Comment");
                                    commentLabel.setBounds(50, 100, 100, 30);
                                    commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(commentLabel);

                                    JTextField commentTextField = new JTextField();
                                    commentTextField.setBounds(50, 140, 200, 30);
                                    commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(commentTextField);

                                    JButton ratingButton = new JButton("Add rating");
                                    ratingButton.setBounds(50, 190, 200, 30);
                                    ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingButton.setForeground(new Color(0xFFFFFF));
                                    ratingButton.setFocusable(false);
                                    ratingButton.setBackground(new Color(0x1A1A1A));
                                    ratingButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String ratingText = ratingTextField.getText();
                                            String comment = commentTextField.getText();

                                            if (ratingText.isEmpty() && comment.isEmpty()) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                                return;
                                            }
                                            long rating;
                                            try {
                                                rating = Long.parseLong(ratingText);
                                            } catch (NumberFormatException ex) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                                return;
                                            }
                                            try {
                                                Long.parseLong(comment);
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                                return;
                                            } catch (NumberFormatException ex) {
                                            }
                                            if (rating < 1 || rating > 10) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                                return;
                                            }
                                            List<Production> ReviewdProd = user.getReviewdProductions();
                                            if(!ReviewdProd.contains(currentProduction) && !user.getAccountType().equals(AccountType.ADMIN)){
                                                user.setExperienceStrategy(new ReviewExperienceStrategy());
                                                user.updateExp();
                                                user.addReviewdProduction(currentProduction);
                                            }
                                            Rating newRating = new Rating(user.getUsername(), rating, comment);
                                            user.addRating(rating, comment, currentProduction);
                                            ratingFrame.dispose();
                                        }
                                    });
                                    ratingPanel.add(ratingButton);
                                    ratingFrame.add(ratingPanel);
                                    ratingFrame.setVisible(true);
                                }
                            });
                            panel.add(addRatingButton3);
                        }
                    });

                    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    seriesPanel.add(titleLabel);
                }

                JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                titlePanel.setBackground(new Color(0x1A1A1A));
                JLabel titleLabel = new JLabel("<html>" + serie.title + "</html>");
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                titlePanel.add(titleLabel);

                seriesPanel.add(titlePanel);

                panelIMGSeries.add(seriesPanel);
            }
        }
        if (!serieExists) {
            JOptionPane.showMessageDialog(null, "A Series of this genre does not exist.");
        }
        panelIMGSeries.revalidate();
        panelIMGSeries.repaint();
    }

    private JLabel infoLabel;
    public void createSearchButtonActionListener(User user)     {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                if (e.getSource() == searchButton) {
                    String text = textField.getText();
                    for (Movie movie : IMDB.getInstance().getMovies()) {
                        if (movie.getTitle().equals(text)) {
                            System.out.println("Movie found");
                            String movieInfo = "<html>Title: " + movie.getTitle() + "<br>Director/s: " + movie.getDirectors()
                                    + "<br>Actors: " + movie.getActors() + "<br>Genres: " + movie.getGenres() +
                                    "<br>Description: " + movie.description + "<br>Duration: " + movie.getDuration() + " min" +
                                    "<br>Launch Year: " + movie.getLaunchYear() + "<br>Rating: " + movie.getAverageRating() + "</html>";
                            if (infoLabel != null) {
                                panel.remove(infoLabel);
                            }
                            infoLabel = new JLabel(movieInfo);
                            infoLabel.setBounds(500, 2540, 500, 350);
                            infoLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                            infoLabel.setForeground(new Color(0xFFFFFF));
                            panel.add(infoLabel);

                            if (favoritesButtonbar != null) {
                                panel.remove(favoritesButtonbar);
                                panel.revalidate();
                                panel.repaint();
                            }
                            if (!user.getFavorites().contains(movie)) {
                                favoritesButtonbar = new JButton("Add to favorites");
                                favoritesButtonbar.setBounds(340, 2660, 150, 30);
                                favoritesButtonbar.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButtonbar.setForeground(new Color(0xFFFFFF));
                                favoritesButtonbar.setFocusable(false);
                                favoritesButtonbar.setBackground(new Color(0x1A1A1A));
                                favoritesButtonbar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.addFavorite(movie);
                                        panel.remove(favoritesButtonbar);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButtonbar);
                            } else {
                                favoritesButtonbar = new JButton("Remove from favorites");
                                favoritesButtonbar.setBounds(340, 2660, 150, 30);
                                favoritesButtonbar.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButtonbar.setForeground(new Color(0xFFFFFF));
                                favoritesButtonbar.setFocusable(false);
                                favoritesButtonbar.setBackground(new Color(0x1A1A1A));
                                favoritesButtonbar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.removeFavorite(movie);
                                        panel.remove(favoritesButtonbar);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButtonbar);
                            }

                            if (addRatingButtonbar != null) {
                                panel.remove(addRatingButtonbar);
                                panel.revalidate();
                                panel.repaint();
                            }
                            addRatingButtonbar = new JButton("Add rating");
                            addRatingButtonbar.setBounds(340, 2700, 150, 30);
                            addRatingButtonbar.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            addRatingButtonbar.setForeground(new Color(0xFFFFFF));
                            addRatingButtonbar.setFocusable(false);
                            addRatingButtonbar.setBackground(new Color(0x1A1A1A));
                            final Production currentProduction = movie;
                            addRatingButtonbar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    System.out.println(currentProduction.getTitle());
                                    JFrame ratingFrame = new JFrame();
                                    ratingFrame.setTitle("Add rating");
                                    ratingFrame.setSize(300, 300);
                                    ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    ratingFrame.setLocationRelativeTo(null);


                                    JPanel ratingPanel = new JPanel();
                                    ratingPanel.setLayout(null);
                                    ratingPanel.setBackground(new Color(0x000000));

                                    for (Rating rating : currentProduction.getRatings()) {
                                        if (rating.getUsername().equals(user.getUsername())) {
                                            Object[] options = {"Delete rating", "Cancel"};
                                            int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                            if (response == JOptionPane.YES_OPTION) {
                                                currentProduction.getRatings().remove(rating);
                                                user.getRatings().remove(rating);
                                                JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                            }
                                            return;
                                        }
                                    }
                                    JLabel ratingLabel = new JLabel("Rating");
                                    ratingLabel.setBounds(50, 20, 100, 30);
                                    ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(ratingLabel);

                                    JTextField ratingTextField = new JTextField();
                                    ratingTextField.setBounds(50, 60, 200, 30);
                                    ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(ratingTextField);

                                    JLabel commentLabel = new JLabel("Comment");
                                    commentLabel.setBounds(50, 100, 100, 30);
                                    commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(commentLabel);

                                    JTextField commentTextField = new JTextField();
                                    commentTextField.setBounds(50, 140, 200, 30);
                                    commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(commentTextField);

                                    JButton ratingButton = new JButton("Add rating");
                                    ratingButton.setBounds(50, 190, 200, 30);
                                    ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingButton.setForeground(new Color(0xFFFFFF));
                                    ratingButton.setFocusable(false);
                                    ratingButton.setBackground(new Color(0x1A1A1A));
                                    ratingButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String ratingText = ratingTextField.getText();
                                            String comment = commentTextField.getText();

                                            if (ratingText.isEmpty() && comment.isEmpty()) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                                return;
                                            }
                                            long rating;
                                            try {
                                                rating = Long.parseLong(ratingText);
                                            } catch (NumberFormatException ex) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                                return;
                                            }
                                            try {
                                                Long.parseLong(comment);
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                                return;
                                            } catch (NumberFormatException ex) {
                                            }
                                            if (rating < 1 || rating > 10) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                                return;
                                            }
                                            Rating newRating = new Rating(user.getUsername(), rating, comment);
                                            user.addRating(rating, comment, currentProduction);
                                            ratingFrame.dispose();
                                        }
                                    });
                                    ratingPanel.add(ratingButton);
                                    ratingFrame.add(ratingPanel);
                                    ratingFrame.setVisible(true);
                                }
                            });
                            panel.add(addRatingButtonbar);

                            panel.revalidate();
                            panel.repaint();
                        }
                    }
                    for (Series serie : IMDB.getInstance().getSeries()) {
                        if (serie.getTitle().equals(text)) {
                            System.out.println("Movie found");
                            String serieInfo = "<html>Title: " + serie.getTitle() + "<br>Director/s: " + serie.getDirectors()
                                    + "<br>Actors: " + serie.getActors() + "<br>Genres: " + serie.getGenres() +
                                    "<br>Description: " + serie.description + "<br>NumOfSeasons: " + serie.getNrSeasons() +
                                    "<br>Launch Year: " + serie.getLaunchYear() + "<br>Rating: " + serie.getAverageRating() + "</html>";
                            if (infoLabel != null) {
                                panel.remove(infoLabel);
                            }
                            infoLabel = new JLabel(serieInfo);
                            infoLabel.setBounds(500, 2540, 500, 350);
                            infoLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                            infoLabel.setForeground(new Color(0xFFFFFF));
                            panel.add(infoLabel);


                            if (favoritesButtonbar != null) {
                                panel.remove(favoritesButtonbar);
                                panel.revalidate();
                                panel.repaint();
                            }
                            if (!user.getFavorites().contains(serie)) {
                                favoritesButtonbar = new JButton("Add to favorites");
                                favoritesButtonbar.setBounds(340, 2660, 150, 30);
                                favoritesButtonbar.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButtonbar.setForeground(new Color(0xFFFFFF));
                                favoritesButtonbar.setFocusable(false);
                                favoritesButtonbar.setBackground(new Color(0x1A1A1A));
                                favoritesButtonbar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.addFavorite(serie);
                                        panel.remove(favoritesButtonbar);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButtonbar);
                            } else {
                                favoritesButtonbar = new JButton("Remove from favorites");
                                favoritesButtonbar.setBounds(340, 2660, 150, 30);
                                favoritesButtonbar.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButtonbar.setForeground(new Color(0xFFFFFF));
                                favoritesButtonbar.setFocusable(false);
                                favoritesButtonbar.setBackground(new Color(0x1A1A1A));
                                favoritesButtonbar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.removeFavorite(serie);
                                        panel.remove(favoritesButtonbar);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButtonbar);
                            }

                            if (addRatingButtonbar != null) {
                                panel.remove(addRatingButtonbar);
                                panel.revalidate();
                                panel.repaint();
                            }
                            addRatingButtonbar = new JButton("Add rating");
                            addRatingButtonbar.setBounds(340, 2700, 150, 30);
                            addRatingButtonbar.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                            addRatingButtonbar.setForeground(new Color(0xFFFFFF));
                            addRatingButtonbar.setFocusable(false);
                            addRatingButtonbar.setBackground(new Color(0x1A1A1A));
                            final Production currentProduction = serie;
                            addRatingButtonbar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    System.out.println(currentProduction.getTitle());
                                    JFrame ratingFrame = new JFrame();
                                    ratingFrame.setTitle("Add rating");
                                    ratingFrame.setSize(300, 300);
                                    ratingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    ratingFrame.setLocationRelativeTo(null);


                                    JPanel ratingPanel = new JPanel();
                                    ratingPanel.setLayout(null);
                                    ratingPanel.setBackground(new Color(0x000000));

                                    for (Rating rating : currentProduction.getRatings()) {
                                        if (rating.getUsername().equals(user.getUsername())) {
                                            Object[] options = {"Delete rating", "Cancel"};
                                            int response = JOptionPane.showOptionDialog(ratingFrame, "You have already rated this production. Would you like to delete your rating?", "Rating exists",
                                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                            if (response == JOptionPane.YES_OPTION) {
                                                currentProduction.getRatings().remove(rating);
                                                user.getRatings().remove(rating);
                                                JOptionPane.showMessageDialog(ratingFrame, "Your rating has been deleted.");
                                            }
                                            return;
                                        }
                                    }
                                    JLabel ratingLabel = new JLabel("Rating");
                                    ratingLabel.setBounds(50, 20, 100, 30);
                                    ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(ratingLabel);

                                    JTextField ratingTextField = new JTextField();
                                    ratingTextField.setBounds(50, 60, 200, 30);
                                    ratingTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(ratingTextField);

                                    JLabel commentLabel = new JLabel("Comment");
                                    commentLabel.setBounds(50, 100, 100, 30);
                                    commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentLabel.setForeground(new Color(0xFFFFFF));
                                    ratingPanel.add(commentLabel);

                                    JTextField commentTextField = new JTextField();
                                    commentTextField.setBounds(50, 140, 200, 30);
                                    commentTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    commentTextField.setForeground(new Color(0x000000));
                                    ratingPanel.add(commentTextField);

                                    JButton ratingButton = new JButton("Add rating");
                                    ratingButton.setBounds(50, 190, 200, 30);
                                    ratingButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                    ratingButton.setForeground(new Color(0xFFFFFF));
                                    ratingButton.setFocusable(false);
                                    ratingButton.setBackground(new Color(0x1A1A1A));
                                    ratingButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String ratingText = ratingTextField.getText();
                                            String comment = commentTextField.getText();

                                            if (ratingText.isEmpty() && comment.isEmpty()) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: both fields are empty.");
                                                return;
                                            }
                                            long rating;
                                            try {
                                                rating = Long.parseLong(ratingText);
                                            } catch (NumberFormatException ex) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be a number.");
                                                return;
                                            }
                                            try {
                                                Long.parseLong(comment);
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: comment must be a string.");
                                                return;
                                            } catch (NumberFormatException ex) {
                                            }
                                            if (rating < 1 || rating > 10) {
                                                JOptionPane.showMessageDialog(ratingFrame, "Invalid rating: rating must be between 1 and 10.");
                                                return;
                                            }
                                            Rating newRating = new Rating(user.getUsername(), rating, comment);
                                            user.addRating(rating, comment, currentProduction);
                                            ratingFrame.dispose();
                                        }
                                    });
                                    ratingPanel.add(ratingButton);
                                    ratingFrame.add(ratingPanel);
                                    ratingFrame.setVisible(true);
                                }
                            });
                            panel.add(addRatingButtonbar);

                            panel.revalidate();
                            panel.repaint();
                        }
                    }
                    for (Actor actor : IMDB.getInstance().getActors()) {
                        if (actor.name.equals(text)) {
                            System.out.println("Movie found");
                            String actorInfo = "<html>Name: " + actor.name + "<br>Biography: " + actor.biography +
                                    "<br>Performances: " + actor.performances + "</html>";
                            if (infoLabel != null) {
                                panel.remove(infoLabel);
                            }
                            infoLabel = new JLabel(actorInfo);
                            infoLabel.setBounds(500, 2540, 500, 350);
                            infoLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                            infoLabel.setForeground(new Color(0xFFFFFF));
                            if (favoritesButtonbar != null) {
                                panel.remove(favoritesButtonbar);
                                panel.revalidate();
                                panel.repaint();
                            }
                            if (!user.getFavorites().contains(actor)) {
                                favoritesButtonbar = new JButton("Add to favorites");
                                favoritesButtonbar.setBounds(340, 2660, 150, 30);
                                favoritesButtonbar.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButtonbar.setForeground(new Color(0xFFFFFF));
                                favoritesButtonbar.setFocusable(false);
                                favoritesButtonbar.setBackground(new Color(0x1A1A1A));
                                favoritesButtonbar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.addFavorite(actor);
                                        panel.remove(favoritesButtonbar);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButtonbar);
                            } else {
                                favoritesButtonbar = new JButton("Remove from favorites");
                                favoritesButtonbar.setBounds(340, 2660, 150, 30);
                                favoritesButtonbar.setFont(new Font("Sans Serif", Font.PLAIN, 13));
                                favoritesButtonbar.setForeground(new Color(0xFFFFFF));
                                favoritesButtonbar.setFocusable(false);
                                favoritesButtonbar.setBackground(new Color(0x1A1A1A));
                                favoritesButtonbar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        user.removeFavorite(actor);
                                        panel.remove(favoritesButtonbar);
                                        panel.revalidate();
                                        panel.repaint();
                                    }
                                });
                                panel.add(favoritesButtonbar);
                            }
                            panel.add(infoLabel);
                            panel.revalidate();
                            panel.repaint();
                        }
                    }
                }
            }});
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}