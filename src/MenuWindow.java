import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.util.*;
import java.util.List;

public class MenuWindow extends JFrame implements ActionListener {

    JPanel panel = new JPanel(null);
    JLabel welcomeLabel = new JLabel();

    public MenuWindow(User user) {
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel.setBackground(new Color(0x000000));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1366, 3000));

        ImageIcon LogoIcon = new ImageIcon("resources/LOGOx2.png");
        JLabel logoLabel = new JLabel(LogoIcon);

        logoLabel.setBounds(100, 50, 110, 60);
        panel.add(logoLabel);

        welcomeLabel.setBounds(600, 50, 300, 50);
        welcomeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        welcomeLabel.setForeground(new Color(0xFFFFFF));
        welcomeLabel.setText("Welcome to menu!");
        panel.add(welcomeLabel);

        JLabel Experiencetext = new JLabel("Experience");
        Experiencetext.setBounds(600, 100, 300, 50);
        Experiencetext.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        Experiencetext.setForeground(new Color(0xFFFFFF));
        panel.add(Experiencetext);

        JLabel Experience = new JLabel(String.valueOf(user.getExperience()));
        Experience.setBounds(720, 100, 300, 50);
        Experience.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        Experience.setForeground(new Color(0xFFFFFF));
        panel.add(Experience);


        JLabel viewprod = new JLabel("View Productions details");
        viewprod.setBounds(100, 120, 300, 50);
        viewprod.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        viewprod.setForeground(new Color(0xFFFFFF));
        panel.add(viewprod);
        viewprod.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MainWindow productionsWindow = new MainWindow(user, IMDB.getInstance().movies,
                        IMDB.getInstance().series, IMDB.getInstance().actors);
                productionsWindow.setVisible(true);
            }
        });

        Label viewActors = new Label("View Actors details");
        viewActors.setBounds(100, 170, 200, 50);
        viewActors.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        viewActors.setForeground(new Color(0xFFFFFF));
        panel.add(viewActors);
        viewActors.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                ActorsWindow actorsWindow = new ActorsWindow(user, IMDB.getInstance().actors);
                actorsWindow.setVisible(true);
            }
        });

        Label viewNotifications = new Label("View Notifications");
        viewNotifications.setBounds(100, 220, 200, 50);
        viewNotifications.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        viewNotifications.setForeground(new Color(0xFFFFFF));
        panel.add(viewNotifications);
        viewNotifications.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                NotificationsWindow notificationsWindow = new NotificationsWindow(user);
                notificationsWindow.setVisible(true);
            }
        });

        Label search = new Label("Search an actor or a production: ");
        search.setBounds(100, 270, 300, 50);
        search.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        search.setForeground(new Color(0xFFFFFF));
        panel.add(search);
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MainWindow searchWindow = new MainWindow(user, IMDB.getInstance().movies,
                        IMDB.getInstance().series, IMDB.getInstance().actors);
                searchWindow.setVisible(true);
            }
        });

        JLabel createRequest = new JLabel("Create a request");
        createRequest.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame addRequestFrame = new JFrame();
                addRequestFrame.setTitle("Create Request");
                addRequestFrame.setSize(400, 500);
                addRequestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addRequestFrame.setLocationRelativeTo(null);


                JPanel requestPanel = new JPanel();
                requestPanel.setLayout(null);
                requestPanel.setBackground(new Color(0x000000));

                JLabel requestLabel = new JLabel("Request");
                requestLabel.setBounds(130, 20, 100, 30);
                requestLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                requestLabel.setForeground(new Color(0xFFFFFF));
                requestPanel.add(requestLabel);

                JLabel requestTypeLabel = new JLabel("Request Type");
                requestTypeLabel.setBounds(50, 50, 200, 30);
                requestTypeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                requestTypeLabel.setForeground(new Color(0xFFFFFF));
                requestPanel.add(requestTypeLabel);

                JLabel productionTitleLabel = new JLabel("Production Titile / Actor Name");
                productionTitleLabel.setBounds(50, 140, 400, 30);
                productionTitleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                productionTitleLabel.setForeground(new Color(0xFFFFFF));

                JTextField productionTitleField = new JTextField();
                productionTitleField.setBounds(50, 190, 200, 30);
                productionTitleField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                productionTitleField.setForeground(new Color(0x000000));

                JComboBox<RequestTypes> comboBox = new JComboBox<>(RequestTypes.values());
                comboBox.setBounds(50, 90, 200, 30);
                requestPanel.add(comboBox);
                comboBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            RequestTypes selectedType = (RequestTypes) e.getItem();
                            if (selectedType == RequestTypes.ACTOR_ISSUE || selectedType == RequestTypes.MOVIE_ISSUE) {
                                requestPanel.add(productionTitleField);
                                requestPanel.add(productionTitleLabel);
                            } else {
                                requestPanel.remove(productionTitleLabel);
                                requestPanel.remove(productionTitleField);
                            }
                            requestPanel.revalidate();
                            requestPanel.repaint();
                        }
                    }
                });

                JLabel descriptionLabel = new JLabel("Description");
                descriptionLabel.setBounds(50, 240, 100, 30);
                descriptionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                descriptionLabel.setForeground(new Color(0xFFFFFF));
                requestPanel.add(descriptionLabel);

                JTextField descriptionTextField = new JTextField();
                descriptionTextField.setBounds(50, 290, 200, 30);
                descriptionTextField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                descriptionTextField.setForeground(new Color(0x000000));
                requestPanel.add(descriptionTextField);


                JButton createRequest = new JButton("Create Request");
                createRequest.setBounds(50, 340, 200, 30);
                createRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                createRequest.setForeground(new Color(0xFFFFFF));
                createRequest.setFocusable(false);
                createRequest.setBackground(new Color(0x1A1A1A));
                createRequest.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        RequestTypes requestType = (RequestTypes) comboBox.getSelectedItem();
                        String description = descriptionTextField.getText();
                        String productionTitle = productionTitleField.getText();
                        if (description.isEmpty()) {
                            JOptionPane.showMessageDialog(addRequestFrame, "Invalid request: description must be filled.");
                            return;
                        }
                        try {
                            Long.parseLong(description);
                            JOptionPane.showMessageDialog(addRequestFrame, "Invalid request: comment must be a string.");
                            return;
                        } catch (NumberFormatException ex) {
                        }

                        if (requestType.equals(RequestTypes.DELETE_ACCOUNT)) {
                            RequestHolder requestHolder = new RequestHolder(user.getUsername(), description,
                                    Request.RequestTypes.DELETE_ACCOUNT, null, "ADMIN");
                            requestHolder.registerObserver(user);
                        } else if(requestType.equals(RequestTypes.OTHERS)) {
                            RequestHolder requestHolder = new RequestHolder(user.getUsername(), description,
                                    Request.RequestTypes.OTHERS, null, "ADMIN");
                            requestHolder.registerObserver(user);
                        } else if (requestType.equals(RequestTypes.MOVIE_ISSUE)) {
                            int found = 0;
                            for (Production production : IMDB.getInstance().movies)
                                if (production.getTitle().equals(productionTitle))
                                    found = 1;
                            for (Production production : IMDB.getInstance().series)
                                if (production.getTitle().equals(productionTitle))
                                    found = 1;
                            if (found == 0) {
                                JOptionPane.showMessageDialog(addRequestFrame, "Invalid production title/actor name.");
                                return;
                            }
                            productionTitle = productionTitleField.getText();
                            String solver = null;
                            for (Contributor contributor : IMDB.getInstance().contributors)
                                for (Object obj : contributor.prodOrActors)
                                    if (obj instanceof Movie) {
                                        Movie movie = (Movie) obj;
                                        if (movie.getTitle().equals(productionTitle))
                                            solver = contributor.getUsername();
                                    } else if (obj instanceof Series) {
                                        Series series = (Series) obj;
                                        if (series.getTitle().equals(productionTitle))
                                            solver = contributor.getUsername();
                                    }
                            for (Admin admin : IMDB.getInstance().admins)
                                for (Object obj : admin.prodOrActors)
                                    if (obj instanceof Movie) {
                                        Movie movie = (Movie) obj;
                                        if (movie.getTitle().equals(productionTitle))
                                            solver = admin.getUsername();
                                    } else if (obj instanceof Series) {
                                        Series series = (Series) obj;
                                        if (series.getTitle().equals(productionTitle))
                                            solver = admin.getUsername();
                                    }

                            Request requestact = new Request(user.getUsername(), description, Request.RequestTypes.MOVIE_ISSUE, productionTitle, solver);

                            for (Contributor contributor : IMDB.getInstance().contributors)
                                if (contributor.getUsername().equals(solver)) {
                                    requestact.registerObserver(contributor);
                                    requestact.notifyObservers("A new request has been added to " + requestact.getProdOrActor() + " by " +
                                            user.getUsername() + " -> " + requestact.getDescription());
                                }

                            for (Admin admin : IMDB.getInstance().admins)
                                if (admin.getUsername().equals(solver)) {
                                    requestact.registerObserver(admin);
                                    requestact.notifyObservers("A new request has been added to " + requestact.getProdOrActor() + " by " +
                                            user.getUsername() + " -> " + requestact.getDescription());
                                }

                            requestact.registerObserver(user);
                        } else if (requestType.equals(RequestTypes.ACTOR_ISSUE)) {
                            int found = 0;
                            for (Actor actor : IMDB.getInstance().actors)
                                if (actor.name.equals(productionTitle))
                                    found = 1;
                            if (found == 0) {
                                JOptionPane.showMessageDialog(addRequestFrame, "Invalid production title/actor name.");
                                return;
                            }
                            productionTitle = productionTitleField.getText();
                            String solver = null;
                            for (Contributor contributor : IMDB.getInstance().contributors)
                                for (Object obj : contributor.prodOrActors)
                                    if (obj instanceof Actor) {
                                        Actor actor = (Actor) obj;
                                        if (actor.name.equals(productionTitle))
                                            solver = contributor.getUsername();
                                    }
                            for (Admin admin : IMDB.getInstance().admins)
                                for (Object obj : admin.prodOrActors)
                                    if (obj instanceof Actor) {
                                        Actor actor = (Actor) obj;
                                        if (actor.name.equals(productionTitle))
                                            solver = admin.getUsername();
                                    }

                            Request requestact = new Request(user.getUsername(), description, Request.RequestTypes.ACTOR_ISSUE, productionTitle, solver);

                            for (Contributor contributor : IMDB.getInstance().contributors)
                                if (contributor.requests.contains(requestact)) {
                                    requestact.registerObserver(contributor);
                                    requestact.notifyObservers("A new request has been added to " + requestact.getProdOrActor() + " by " +
                                            user.getUsername() + " -> " + requestact.getDescription());
                                }

                            for (Admin admin : IMDB.getInstance().admins)
                                if (admin.requests.contains(requestact)) {
                                    requestact.registerObserver(admin);
                                    requestact.notifyObservers("A new request has been added to " + requestact.getProdOrActor() + " by " +
                                            user.getUsername() + " -> " + requestact.getDescription());
                                }

                            requestact.registerObserver(user);
                        }
                        addRequestFrame.dispose();
                    }
                });
                requestPanel.add(createRequest);
                addRequestFrame.add(requestPanel);
                addRequestFrame.setVisible(true);
            }
        });


        Label removeRequest = new Label("Remove a request");
        removeRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                JFrame addRequestFrame = new JFrame();
                addRequestFrame.setTitle("Create Request");
                addRequestFrame.setSize(400, 500);
                addRequestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addRequestFrame.setLocationRelativeTo(null);

                int hasRequest = 0;
                for (Request request : IMDB.getInstance().requests) {
                    if(request.getUsername().equals(user.getUsername()))
                    {
                        hasRequest = 1;
                        break;
                    }
                }
                if(hasRequest == 0)
                {
                    JOptionPane.showMessageDialog(addRequestFrame, "You have no requests!");
                    return;
                }

                JPanel requestPanel = new JPanel();
                requestPanel.setLayout(null);
                requestPanel.setBackground(new Color(0x000000));

                JLabel requestLabel = new JLabel("Delete Request");
                requestLabel.setBounds(130, 20, 300, 30);
                requestLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                requestLabel.setForeground(new Color(0xFFFFFF));
                requestPanel.add(requestLabel);

                JLabel chooseRequestLabel = new JLabel("Choose Request to delete");
                chooseRequestLabel.setBounds(50, 100, 300, 30);
                chooseRequestLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseRequestLabel.setForeground(new Color(0xFFFFFF));
                requestPanel.add(chooseRequestLabel);


                JComboBox<String> requestsComboBox = new JComboBox<>();
                for (Request request : IMDB.getInstance().requests) {
                    if(request.getUsername().equals(user.getUsername()))
                    {
                        requestsComboBox.addItem(request.getDescription());
                    }
                }
                requestsComboBox.setBounds(50, 140, 300, 30);
                requestPanel.add(requestsComboBox);

                JButton removeRequest = new JButton("Delete Request");
                removeRequest.setBounds(100, 340, 200, 30);
                removeRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                removeRequest.setForeground(new Color(0xFFFFFF));
                removeRequest.setFocusable(false);
                removeRequest.setBackground(new Color(0x1A1A1A));
                removeRequest.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedRequestDescription = (String) requestsComboBox.getSelectedItem();
                        for (Iterator<Request> iterator = IMDB.getInstance().requests.iterator(); iterator.hasNext();) {
                            Request request = iterator.next();
                            if (request.getUsername().equals(user.getUsername()) && request.getDescription().equals(selectedRequestDescription)) {
                                iterator.remove();
                                break;
                            }
                        }
                        requestsComboBox.removeItem(selectedRequestDescription);
                        addRequestFrame.dispose();
                    }
                });
                requestPanel.add(removeRequest);
                addRequestFrame.add(requestPanel);
                addRequestFrame.setVisible(true);
            }
        });


        JLabel addProduction = new JLabel("Add Production");
        addProduction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame addProductionFrame = new JFrame();
                addProductionFrame.setTitle("Add a Production");
                addProductionFrame.setSize(400, 900);
                addProductionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addProductionFrame.setLocationRelativeTo(null);

                JPanel addProductionPanel = new JPanel();
                addProductionPanel.setLayout(null);
                addProductionPanel.setBackground(new Color(0x000000));

                JLabel addProductionLabel = new JLabel("Add a Production");
                addProductionLabel.setBounds(130, 20, 300, 30);
                addProductionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                addProductionLabel.setForeground(new Color(0xFFFFFF));
                addProductionPanel.add(addProductionLabel);


                JLabel chooseProdType = new JLabel("Choose Production Type");
                chooseProdType.setBounds(50, 100, 300, 30);
                chooseProdType.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseProdType.setForeground(new Color(0xFFFFFF));
                addProductionPanel.add(chooseProdType);

                JComboBox<String> productionType = new JComboBox<>();
                productionType.addItem("Movie");
                productionType.addItem("Series");
                productionType.setBounds(50, 140, 300, 30);


                JLabel productionTitleLabel = new JLabel("Production Title");
                productionTitleLabel.setBounds(50, 180, 300, 30);
                productionTitleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionTitleLabel.setForeground(new Color(0xFFFFFF));

                JTextField productionTitleField = new JTextField();
                productionTitleField.setBounds(50, 220, 300, 30);
                productionTitleField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionTitleField.setForeground(new Color(0x000000));

                JLabel productionDirectorsLabel = new JLabel("Production Directors");
                productionDirectorsLabel.setBounds(50, 260, 300, 30);
                productionDirectorsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionDirectorsLabel.setForeground(new Color(0xFFFFFF));

                JTextField productionDirectorsField = new JTextField();
                productionDirectorsField.setBounds(50, 300, 300, 30);
                productionDirectorsField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionDirectorsField.setForeground(new Color(0x000000));

                JLabel productionActorsLabel = new JLabel("Production Actors");
                productionActorsLabel.setBounds(50, 340, 300, 30);
                productionActorsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionActorsLabel.setForeground(new Color(0xFFFFFF));

                JTextField productionActorsField = new JTextField();
                productionActorsField.setBounds(50, 380, 300, 30);
                productionActorsField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionActorsField.setForeground(new Color(0x000000));

                JLabel productionDurationLabel = new JLabel("Production Duration");
                productionDurationLabel.setBounds(50, 420, 300, 30);
                productionDurationLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionDurationLabel.setForeground(new Color(0xFFFFFF));

                JTextField productionDurationField = new JTextField();
                productionDurationField.setBounds(50, 460, 300, 30);
                productionDurationField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionDurationField.setForeground(new Color(0x000000));

                JLabel productionGenresLabel = new JLabel("Production Genres");
                productionGenresLabel.setBounds(50, 500, 300, 30);
                productionGenresLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionGenresLabel.setForeground(new Color(0xFFFFFF));


                JTextField productionGenresField = new JTextField();
                productionGenresField.setBounds(50, 540, 300, 30);
                productionGenresField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionGenresField.setForeground(new Color(0x000000));


                JLabel productionLaunchYearLabel = new JLabel("Production Launch Year");
                productionLaunchYearLabel.setBounds(50, 580, 300, 30);
                productionLaunchYearLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionLaunchYearLabel.setForeground(new Color(0xFFFFFF));

                JTextField productionLaunchYearField = new JTextField();
                productionLaunchYearField.setBounds(50, 620, 300, 30);
                productionLaunchYearField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionLaunchYearField.setForeground(new Color(0x000000));

                JLabel productionDescriptionLabel = new JLabel("Production Description");
                productionDescriptionLabel.setBounds(50, 660, 300, 30);
                productionDescriptionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionDescriptionLabel.setForeground(new Color(0xFFFFFF));

                JTextField productionDescriptionField = new JTextField();
                productionDescriptionField.setBounds(50, 700, 300, 30);
                productionDescriptionField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionDescriptionField.setForeground(new Color(0x000000));

                JLabel productionNrSeasonsLabel = new JLabel("Production Number of Seasons");
                productionNrSeasonsLabel.setBounds(50, 420, 300, 30);
                productionNrSeasonsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionNrSeasonsLabel.setForeground(new Color(0xFFFFFF));

                JTextField productionNrSeasonsField = new JTextField();
                productionNrSeasonsField.setBounds(50, 460, 300, 30);
                productionNrSeasonsField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                productionNrSeasonsField.setForeground(new Color(0x000000));

                productionType.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED){
                            String selectedType = (String) e.getItem();
                            if(selectedType.equals("Movie")){
                                addProductionPanel.add(productionTitleLabel);
                                addProductionPanel.add(productionTitleField);
                                addProductionPanel.add(productionDirectorsLabel);
                                addProductionPanel.add(productionDirectorsField);
                                addProductionPanel.add(productionActorsLabel);
                                addProductionPanel.add(productionActorsField);
                                addProductionPanel.add(productionGenresLabel);
                                addProductionPanel.add(productionGenresField);
                                addProductionPanel.add(productionDescriptionLabel);
                                addProductionPanel.add(productionDescriptionField);
                                addProductionPanel.add(productionDurationLabel);
                                addProductionPanel.add(productionDurationField);
                                addProductionPanel.add(productionLaunchYearLabel);
                                addProductionPanel.add(productionLaunchYearField);
                                addProductionPanel.remove(productionNrSeasonsField);
                                addProductionPanel.remove(productionNrSeasonsLabel);
                            }
                            else if(selectedType.equals("Series")){
                                addProductionPanel.add(productionTitleField);
                                addProductionPanel.add(productionTitleLabel);
                                addProductionPanel.add(productionNrSeasonsField);
                                addProductionPanel.add(productionNrSeasonsLabel);
                                addProductionPanel.add(productionLaunchYearField);
                                addProductionPanel.add(productionLaunchYearLabel);
                                addProductionPanel.add(productionDirectorsLabel);
                                addProductionPanel.add(productionDirectorsField);
                                addProductionPanel.add(productionActorsLabel);
                                addProductionPanel.add(productionActorsField);
                                addProductionPanel.add(productionGenresLabel);
                                addProductionPanel.add(productionGenresField);
                                addProductionPanel.add(productionDescriptionLabel);
                                addProductionPanel.add(productionDescriptionField);
                                addProductionPanel.remove(productionDurationField);
                                addProductionPanel.remove(productionDurationLabel);
                            }
                            addProductionPanel.revalidate();
                            addProductionPanel.repaint();
                        }
                    }
                });
                addProductionPanel.add(productionType);

                JButton addProduction = new JButton("Add Production");
                addProduction.setBounds(100, 750, 200, 30);
                addProduction.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                addProduction.setForeground(new Color(0xFFFFFF));
                addProduction.setFocusable(false);
                addProduction.setBackground(new Color(0x1A1A1A));
                addProduction.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedType = (String) productionType.getSelectedItem();
                        String title = productionTitleField.getText();
                        java.util.List<String> directors = Arrays.asList(productionDirectorsField.getText().split(","));
                        java.util.List<String> actors = Arrays.asList(productionActorsField.getText().split(","));
                        java.util.List<Genre> genres = new ArrayList<>();
                        String[] genresString = productionGenresField.getText().split(",");
                        for(int i = 0; i < genresString.length; i++){
                            try {
                                genres.add(Genre.valueOf(genresString[i]));
                            } catch (IllegalArgumentException ex) {
                                JOptionPane.showMessageDialog(addProductionFrame, "Invalid genre: " + genresString[i]);
                                return;
                            }
                        }

                        if(title.isEmpty() || directors.isEmpty() || actors.isEmpty() || genres.isEmpty()
                                || productionDescriptionField.getText().isEmpty() ||
                                productionLaunchYearField.getText().isEmpty() ||
                                (selectedType.equals("Movie") && productionDurationField.getText().isEmpty())
                                || (selectedType.equals("Series") && productionNrSeasonsField.getText().isEmpty())){
                            JOptionPane.showMessageDialog(addProductionFrame, "Invalid production: all fields must be filled.");
                            return;
                        }

                        String description = productionDescriptionField.getText();
                        Long launchyear = Long.parseLong(productionLaunchYearField.getText());
                        if(selectedType.equals("Movie")){
                            Long duration = Long.parseLong(productionDurationField.getText());
                            Movie movie = new Movie(title, directors, actors, genres, null, description,
                                    duration, launchyear);
                            if(!user.getAccountType().equals(AccountType.ADMIN)) {
                                user.setExperienceStrategy(new AddedProdOrActor());
                                user.updateExp();
                            }
                            if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
                                Contributor contributor = (Contributor) user;
                                contributor.prodOrActors.add(movie);
                            } else if(user.getAccountType().equals(AccountType.ADMIN)){
                                Admin admin = (Admin) user;
                                admin.prodOrActors.add(movie);
                            }

                        } else if (selectedType.equals("Series")) {
                            Long nrseasons = Long.parseLong(productionNrSeasonsField.getText());
                            Map<String, java.util.List<Episode>> Seasons = new HashMap<>();
                            java.util.List<Episode> episodes = new ArrayList<>();
                            int i = 1;
                            for(i = 1; i < nrseasons; i++){
                                int nrEpisodes = Integer.parseInt(JOptionPane.showInputDialog("Enter number of episodes for season " + i));
                                for(int j = 0; j < nrEpisodes; j++){
                                    String episodeTitle = JOptionPane.showInputDialog("Enter episode title");
                                    String episodeDuration = JOptionPane.showInputDialog("Enter episode duration");
                                    Episode episode = new Episode(episodeTitle, Long.parseLong(episodeDuration));
                                    episodes.add(episode);
                                }
                            }
                            Seasons.put("Season " + i, episodes);
                            Series series = new Series(title, directors, actors, genres, null, description,
                                    launchyear, nrseasons, null);
                            if(!user.getAccountType().equals(AccountType.ADMIN)) {
                                user.setExperienceStrategy(new AddedProdOrActor());
                                user.updateExp();
                            }
                            if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
                                Contributor contributor = (Contributor) user;
                                contributor.prodOrActors.add(series);
                            } else if(user.getAccountType().equals(AccountType.ADMIN)){
                                Admin admin = (Admin) user;
                                admin.prodOrActors.add(series);
                            }
                        }

                        addProductionFrame.dispose();
                    }
                });
                addProductionPanel.add(addProduction);
                addProductionFrame.add(addProductionPanel);
                addProductionFrame.setVisible(true);
            }
        });

        JLabel removeProduction = new JLabel("Remove a Production");
        removeProduction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame removeProductionFrame = new JFrame();
                removeProductionFrame.setTitle("Add a Production");
                removeProductionFrame.setSize(400, 400);
                removeProductionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removeProductionFrame.setLocationRelativeTo(null);

                JPanel removeProductionPanel = new JPanel();
                removeProductionPanel.setLayout(null);
                removeProductionPanel.setBackground(new Color(0x000000));

                JLabel removeProductionLabel = new JLabel("Add a Production");
                removeProductionLabel.setBounds(130, 20, 300, 30);
                removeProductionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                removeProductionLabel.setForeground(new Color(0xFFFFFF));
                removeProductionPanel.add(removeProductionLabel);


                JLabel chooseProdType = new JLabel("Choose a Production");
                chooseProdType.setBounds(50, 100, 300, 30);
                chooseProdType.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseProdType.setForeground(new Color(0xFFFFFF));
                removeProductionPanel.add(chooseProdType);


                JComboBox<String> productionTitle = new JComboBox<>();
                productionTitle.setBounds(50, 140, 300, 30);
                if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
                    Contributor contributor = (Contributor) user;
                    for(Object obj : contributor.prodOrActors){
                        if(obj instanceof Movie){
                            Movie movie = (Movie) obj;
                            productionTitle.addItem(movie.getTitle());
                        } else if(obj instanceof Series){
                            Series serie = (Series) obj;
                            productionTitle.addItem(serie.getTitle());
                        }
                    }
                } else if(user.getAccountType().equals(AccountType.ADMIN)){
                    for(Production production : IMDB.getInstance().movies){
                        productionTitle.addItem(production.getTitle());
                    }
                    for(Production production : IMDB.getInstance().series){
                        productionTitle.addItem(production.getTitle());
                    }
                }
                removeProductionPanel.add(productionTitle);

                JButton removeProduction = new JButton("Remove Production");
                removeProduction.setBounds(75, 190, 250, 30);
                removeProduction.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                removeProduction.setForeground(new Color(0xFFFFFF));
                removeProduction.setFocusable(false);
                removeProduction.setBackground(new Color(0x1A1A1A));
                removeProduction.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedProduction = (String) productionTitle.getSelectedItem();
                        for (Iterator<Movie> iterator = IMDB.getInstance().movies.iterator(); iterator.hasNext();) {
                            Movie production = iterator.next();
                            if (production.getTitle().equals(selectedProduction)) {
                                if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
                                    Contributor contributor = (Contributor) user;
                                    contributor.prodOrActors.remove(iterator);
                                } else if(user.getAccountType().equals(AccountType.ADMIN)){
                                    Admin admin = (Admin) user;
                                    admin.prodOrActors.remove(iterator);
                                }
                                iterator.remove();
                                break;
                            }
                        }
                        for (Iterator<Series> iterator = IMDB.getInstance().series.iterator(); iterator.hasNext();) {
                            Series production = iterator.next();
                            if (production.getTitle().equals(selectedProduction)) {
                                if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
                                    Contributor contributor = (Contributor) user;
                                    contributor.prodOrActors.remove(iterator);
                                } else if(user.getAccountType().equals(AccountType.ADMIN)){
                                    Admin admin = (Admin) user;
                                    admin.prodOrActors.remove(iterator);
                                }
                                iterator.remove();
                                break;
                            }
                        }
                        removeProductionFrame.dispose();
                        for(Production production : IMDB.getInstance().movies){
                            System.out.println(production.getTitle());
                        }
                    }
                });

                removeProductionPanel.add(removeProduction);
                removeProductionFrame.add(removeProductionPanel);
                removeProductionFrame.setVisible(true);
            }
        });

        JLabel addActor = new JLabel("Add Actor");
        addActor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame addActorFrame = new JFrame();
                addActorFrame.setTitle("Add an Actor");
                addActorFrame.setSize(400, 500);
                addActorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addActorFrame.setLocationRelativeTo(null);

                JPanel addActorPanel = new JPanel();
                addActorPanel.setLayout(null);
                addActorPanel.setBackground(new Color(0x000000));

                JLabel addActorLabel = new JLabel("Add an Actor");
                addActorLabel.setBounds(130, 20, 300, 30);
                addActorLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                addActorLabel.setForeground(new Color(0xFFFFFF));
                addActorPanel.add(addActorLabel);

                JLabel actorNameLabel = new JLabel("Actor Name");
                actorNameLabel.setBounds(50, 100, 300, 30);
                actorNameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                actorNameLabel.setForeground(new Color(0xFFFFFF));
                addActorPanel.add(actorNameLabel);

                JTextField actorNameField = new JTextField();
                actorNameField.setBounds(50, 140, 300, 30);
                actorNameField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                actorNameField.setForeground(new Color(0x000000));
                addActorPanel.add(actorNameField);

                JLabel actorBiographyLabel = new JLabel("Biography");
                actorBiographyLabel.setBounds(50, 180, 300, 30);
                actorBiographyLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                actorBiographyLabel.setForeground(new Color(0xFFFFFF));
                addActorPanel.add(actorBiographyLabel);

                JTextField actorBiographyField = new JTextField();
                actorBiographyField.setBounds(50, 220, 300, 30);
                actorBiographyField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                actorBiographyField.setForeground(new Color(0x000000));
                addActorPanel.add(actorBiographyField);

                JLabel actorPerformancesLabel = new JLabel("Actor Performances");
                actorPerformancesLabel.setBounds(50, 260, 300, 30);
                actorPerformancesLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                actorPerformancesLabel.setForeground(new Color(0xFFFFFF));
                addActorPanel.add(actorPerformancesLabel);

                JTextField actorPerformancesField = new JTextField();
                actorPerformancesField.setBounds(50, 300, 300, 30);
                actorPerformancesField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                actorPerformancesField.setForeground(new Color(0x000000));
                addActorPanel.add(actorPerformancesField);

                JButton addActor = new JButton("Add Actor");
                addActor.setBounds(100, 370, 200, 30);
                addActor.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                addActor.setForeground(new Color(0xFFFFFF));
                addActor.setFocusable(false);
                addActor.setBackground(new Color(0x1A1A1A));
                addActor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = actorNameField.getText();
                        String biography = actorBiographyField.getText();
                        java.util.List<Map.Entry<String, String>> performances = new ArrayList<>();
                        String[] performancesString = actorPerformancesField.getText().split(",");
                        for(int i = 0; i < performancesString.length; i++){
                            String[] performance = performancesString[i].split(":");
                            performances.add(new AbstractMap.SimpleEntry<>(performance[0], performance[1]));
                        }
                        if(name.isEmpty() || biography.isEmpty() || performances.isEmpty()){
                            JOptionPane.showMessageDialog(addActorFrame, "Invalid actor: all fields must be filled.");
                            return;
                        }
                        try {
                            Long.parseLong(name);
                            JOptionPane.showMessageDialog(addActorFrame, "Invalid actor: name must be a string.");
                            return;
                        } catch (NumberFormatException ex) {
                        }
                        try {
                            Long.parseLong(biography);
                            JOptionPane.showMessageDialog(addActorFrame, "Invalid actor: biography must be a string.");
                            return;
                        } catch (NumberFormatException ex) {
                        }
                        Actor actor = new Actor(name, biography, performances);
                        if(!user.getAccountType().equals(AccountType.ADMIN)) {
                            user.setExperienceStrategy(new AddedProdOrActor());
                            user.updateExp();
                        }
                        if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
                            Contributor contributor = (Contributor) user;
                            contributor.prodOrActors.add(actor);
                        } else if(user.getAccountType().equals(AccountType.ADMIN)){
                            Admin admin = (Admin) user;
                            admin.prodOrActors.add(actor);
                        }
                        addActorFrame.dispose();
                    }
                });
                addActorPanel.add(addActor);
                addActorFrame.add(addActorPanel);
                addActorFrame.setVisible(true);
            }
        });

        JLabel removeActor = new JLabel("Remove an Actor");
        removeActor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame removeActorFrame = new JFrame();
                removeActorFrame.setTitle("Add a Production");
                removeActorFrame.setSize(400, 400);
                removeActorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removeActorFrame.setLocationRelativeTo(null);

                JPanel removeActorPanel = new JPanel();
                removeActorPanel.setLayout(null);
                removeActorPanel.setBackground(new Color(0x000000));

                JLabel removeActorLabel = new JLabel("Add a Production");
                removeActorLabel.setBounds(130, 20, 300, 30);
                removeActorLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                removeActorLabel.setForeground(new Color(0xFFFFFF));
                removeActorPanel.add(removeActorLabel);


                JLabel chooseActor = new JLabel("Choose an Actor");
                chooseActor.setBounds(50, 100, 300, 30);
                chooseActor.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseActor.setForeground(new Color(0xFFFFFF));
                removeActorPanel.add(chooseActor);


                JComboBox<String> actorName = new JComboBox<>();
                actorName.setBounds(50, 140, 300, 30);
                if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
                    Contributor contributor = (Contributor) user;
                    for(Object obj : contributor.prodOrActors){
                        if(obj instanceof Actor){
                            Actor actor = (Actor) obj;
                            actorName.addItem(actor.name);
                        }
                    }
                } else if(user.getAccountType().equals(AccountType.ADMIN)){
                    for(Actor actor : IMDB.getInstance().actors){
                        actorName.addItem(actor.name);
                    }
                }
                removeActorPanel.add(actorName);

                JButton removeActor = new JButton("Remove Actor");
                removeActor.setBounds(75, 190, 250, 30);
                removeActor.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                removeActor.setForeground(new Color(0xFFFFFF));
                removeActor.setFocusable(false);
                removeActor.setBackground(new Color(0x1A1A1A));
                removeActor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedActor = (String) actorName.getSelectedItem();
                        for (Iterator<Actor> iterator = IMDB.getInstance().actors.iterator(); iterator.hasNext();) {
                            Actor actor = iterator.next();
                            if (actor.name.equals(selectedActor)) {
                                if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
                                    Contributor contributor = (Contributor) user;
                                    contributor.prodOrActors.remove(iterator);
                                } else if(user.getAccountType().equals(AccountType.ADMIN)){
                                    Admin admin = (Admin) user;
                                    admin.prodOrActors.remove(iterator);
                                }
                                iterator.remove();
                                break;
                            }
                        }
                        removeActorFrame.dispose();
                    }
                });

                removeActorPanel.add(removeActor);
                removeActorFrame.add(removeActorPanel);
                removeActorFrame.setVisible(true);
            }
        });


        JLabel updateProduction = new JLabel("Update Production");
        updateProduction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame updateProductionFrame = new JFrame();
                updateProductionFrame.setTitle("Update a Production");
                updateProductionFrame.setSize(400, 900);
                updateProductionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateProductionFrame.setLocationRelativeTo(null);

                JPanel updateProductionPanel = new JPanel();
                updateProductionPanel.setLayout(null);
                updateProductionPanel.setBackground(new Color(0x000000));

                JLabel updateProductionLabel = new JLabel("Update a Production");
                updateProductionLabel.setBounds(130, 20, 300, 30);
                updateProductionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                updateProductionLabel.setForeground(new Color(0xFFFFFF));
                updateProductionPanel.add(updateProductionLabel);

                JLabel chooseProdType = new JLabel("Choose Production Type");
                chooseProdType.setBounds(50, 100, 300, 30);
                chooseProdType.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseProdType.setForeground(new Color(0xFFFFFF));
                updateProductionPanel.add(chooseProdType);

                JComboBox<String> productionTitle = new JComboBox<>();
                productionTitle.setBounds(50, 220, 300, 30);
                updateProductionPanel.add(productionTitle);

                JComboBox<String> productionType = new JComboBox<>();
                productionType.addItem("Movie");
                productionType.addItem("Series");
                productionType.setBounds(50, 140, 300, 30);
                productionType.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedType = (String) productionType.getSelectedItem();
                        productionTitle.removeAllItems();
                        if(selectedType.equals("Movie")){
                            for(Production production : IMDB.getInstance().movies){
                                productionTitle.addItem(production.getTitle());
                            }
                        } else if(selectedType.equals("Series")){
                            for(Production production : IMDB.getInstance().series){
                                productionTitle.addItem(production.getTitle());
                            }
                        }
                    }
                });

                JLabel chooseaProd = new JLabel("Choose a Production to Update");
                chooseaProd.setBounds(50, 180, 300, 30);
                chooseaProd.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseaProd.setForeground(new Color(0xFFFFFF));
                updateProductionPanel.add(chooseaProd);

                JLabel chooseOption = new JLabel("Choose what to update");
                chooseOption.setBounds(50, 260, 300, 30);
                chooseOption.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseOption.setForeground(new Color(0xFFFFFF));
                updateProductionPanel.add(chooseOption);



                JLabel episodesLabel = new JLabel("Episodes");
                episodesLabel.setBounds(50, 420, 300, 30);
                episodesLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                episodesLabel.setForeground(new Color(0xFFFFFF));

                JComboBox<String> episodes = new JComboBox<>();
                episodes.setBounds(50, 460, 300, 30);

                JLabel seasonsLabel = new JLabel("Seasons");
                seasonsLabel.setBounds(50, 340, 300, 30);
                seasonsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                seasonsLabel.setForeground(new Color(0xFFFFFF));

                JComboBox<String> seasons = new JComboBox<>();
                seasons.setBounds(50, 380, 300, 30);

                JComboBox<String> options = new JComboBox<>();
                options.setBounds(50, 300, 300, 30);
                updateProductionPanel.add(options);

                productionTitle.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedType = (String) productionType.getSelectedItem();
                        String selectedProduction = (String) productionTitle.getSelectedItem();
                        if(selectedProduction != null){
                            if(selectedType.equals("Movie")){
                                updateProductionPanel.remove(seasons);
                                updateProductionPanel.remove(episodes);
                                updateProductionPanel.remove(seasonsLabel);
                                updateProductionPanel.remove(episodesLabel);
                                updateProductionPanel.revalidate();
                                updateProductionPanel.repaint();
                                options.addItem("Title");
                                options.addItem("Directors");
                                options.addItem("Actors");
                                options.addItem("Genres");
                                options.addItem("Description");
                                options.addItem("Duration");
                                options.addItem("Launch Year");
                            } else if(selectedType.equals("Series")){
                                updateProductionPanel.add(seasons);
                                updateProductionPanel.add(episodes);
                                updateProductionPanel.add(seasonsLabel);
                                updateProductionPanel.add(episodesLabel);
                                updateProductionPanel.revalidate();
                                updateProductionPanel.repaint();
                                options.addItem("Title");
                                options.addItem("Directors");
                                options.addItem("Actors");
                                options.addItem("Genres");
                                options.addItem("Description");
                                options.addItem("Launch Year");
                                options.addItem("Number of Seasons");
                                options.addItem("Seasons");
                            }
                        }
                    }
                });

                JLabel modificationsLabel = new JLabel("Modifications");
                modificationsLabel.setBounds(50, 580, 300, 30);
                modificationsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                modificationsLabel.setForeground(new Color(0xFFFFFF));
                updateProductionPanel.add(modificationsLabel);

                JTextField modifications = new JTextField();
                modifications.setBounds(50, 620, 300, 30);
                updateProductionPanel.add(modifications);




                options.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOption = (String) options.getSelectedItem();
                        String selectedProduction = (String) productionTitle.getSelectedItem();
                        seasons.removeAllItems();
                        episodes.removeAllItems();
                        SortedSet<String> seasonsSet = new TreeSet<>();
                        for(Series series : IMDB.getInstance().series){
                            if(series.getTitle().equals(selectedProduction)){
                                for (Map.Entry<String, java.util.List<Episode>> season : series.getSeasons().entrySet()) {
                                    seasonsSet.add(season.getKey());
                                    System.out.println(season.getKey());
                                }
                            }
                        }
                        for(String season : seasonsSet){
                            seasons.addItem(season);
                        }
                        modifications.setText("");
                        if(selectedOption.equals("Seasons")){;
                            seasons.setVisible(true);
                            seasonsLabel.setVisible(true);
                            episodes.setVisible(true);
                            episodesLabel.setVisible(true);
                        } else {
                            seasons.setVisible(false);
                            seasonsLabel.setVisible(false);
                            episodes.setVisible(false);
                            episodesLabel.setVisible(false);
                        }

                    }
                });

                seasons.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedSeason = (String) seasons.getSelectedItem();
                        String selectedProduction = (String) productionTitle.getSelectedItem();
                        episodes.removeAllItems();
                        if(selectedSeason != null){
                            java.util.List<String> seasonEpisodes = new ArrayList<>();
                            for(Series series : IMDB.getInstance().series){
                                if(series.getTitle().equals(selectedProduction)){
                                    for(Map.Entry<String, java.util.List<Episode>> season : series.getSeasons().entrySet()){
                                        if(season.getKey().equals(selectedSeason)){
                                            for(Episode episode : season.getValue()){
                                                seasonEpisodes.add(episode.getEpisodeName());
                                            }
                                        }
                                    }
                                }
                            }
                            for(String episode : seasonEpisodes){
                                episodes.addItem(episode);
                            }
                        }
                    }
                });

                JLabel episodeOptionsLabel = new JLabel("Episode Options");
                episodeOptionsLabel.setBounds(50, 500, 300, 30);
                episodeOptionsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                episodeOptionsLabel.setForeground(new Color(0xFFFFFF));
                updateProductionPanel.add(episodeOptionsLabel);

                JComboBox<String> episodeOptions = new JComboBox<>(new String[]{"Title", "Duration"});
                episodeOptions.setBounds(50, 540, 300, 30);
                episodeOptions.setVisible(false);
                updateProductionPanel.add(episodeOptions);

                options.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOption = (String) options.getSelectedItem();
                        if(selectedOption != null && selectedOption.equals("Seasons")){
                            episodeOptions.setVisible(true);
                            episodeOptionsLabel.setVisible(true);
                        } else {
                            episodeOptions.setVisible(false);
                            episodeOptionsLabel.setVisible(false);
                        }
                    }
                });

                JButton modifyButton = new JButton("Modify");
                modifyButton.setBounds(50, 670, 300, 30);
                modifyButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                modifyButton.setForeground(new Color(0xFFFFFF));
                modifyButton.setFocusable(false);
                modifyButton.setBackground(new Color(0x1A1A1A));
                modifyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOption = (String) options.getSelectedItem();
                        String selectedProduction = (String) productionTitle.getSelectedItem();
                        String modification = modifications.getText();
                        String selectedType = (String) productionType.getSelectedItem();

                        if(selectedOption != null && selectedProduction != null && !modification.isEmpty() && selectedType != null){
                            if(selectedType.equals("Movie")){
                                for(Movie movie : IMDB.getInstance().movies){
                                    if(movie.getTitle().equals(selectedProduction)) {
                                        if (selectedOption.equals("Title")) {
                                            movie.setTitle(modification);
                                        }
                                        else if (selectedOption.equals("Directors")) {
                                            movie.setDirectors(Arrays.asList(modification.split(",")));
                                        }
                                        else if (selectedOption.equals("Actors")) {
                                            movie.setActors(Arrays.asList(modification.split(",")));
                                        }
                                        else if (selectedOption.equals("Genres")) {
                                            java.util.List<Genre> genres = new ArrayList<>();
                                            if(modification.contains(",")){
                                                String[] genresString = modification.split(",");
                                                for(String genre : genresString){
                                                    try {
                                                        genres.add(Genre.valueOf(modification));
                                                    } catch (IllegalArgumentException ex) {
                                                        JOptionPane.showMessageDialog(updateProductionFrame, "Invalid genre: " + genre);
                                                        return;
                                                    }
                                                    genres.add(Genre.valueOf(genre));
                                                }
                                            } else {
                                                genres.add(Genre.valueOf(modification));
                                            }
                                            movie.setGenres(genres);
                                        }
                                        if  (selectedOption.equals("Description")) {
                                            movie.setDescription(modification);
                                        }
                                        else if (selectedOption.equals("Duration")) {
                                            movie.setDuration(Long.parseLong(modification));
                                        }
                                        else if (selectedOption.equals("Launch Year")) {
                                            movie.setLaunchYear(Long.parseLong(modification));
                                        }
                                    }
                                }

                            } else if(selectedType.equals("Series")){
                                for(Series series : IMDB.getInstance().series) {
                                    if (series.getTitle().equals(selectedProduction)) {
                                        if (selectedOption.equals("Title")) {
                                            series.setTitle(modification);
                                        }
                                        else if (selectedOption.equals("Directors")) {
                                            series.setDirectors(Arrays.asList(modification.split(",")));
                                        }
                                        else if (selectedOption.equals("Actors")) {
                                            series.setActors(Arrays.asList(modification.split(",")));
                                        }
                                        else if (selectedOption.equals("Genres")) {
                                            java.util.List<Genre> genres = new ArrayList<>();
                                            if (modification.contains(",")) {
                                                String[] genresString = modification.split(",");
                                                for (String genre : genresString) {
                                                    if (Genre.valueOf(genre) == null) {
                                                        JOptionPane.showMessageDialog(updateProductionFrame, "Invalid genre: " + genre);
                                                        return;
                                                    }
                                                    genres.add(Genre.valueOf(genre));
                                                }
                                            } else {
                                                if (Genre.valueOf(modification) == null) {
                                                    JOptionPane.showMessageDialog(updateProductionFrame, "Invalid genre: " + modification);
                                                    return;
                                                }
                                                genres.add(Genre.valueOf(modification));
                                            }
                                            series.setGenres(genres);
                                        }
                                        else if  (selectedOption.equals("Description")) {
                                            series.setDescription(modification);
                                        }
                                        else if (selectedOption.equals("Launch Year")) {
                                            series.setLaunchYear(Long.parseLong(modification));
                                        }
                                        else if (selectedOption.equals("Number of Seasons")) {
                                            series.setNrSeasons(Long.parseLong(modification));
                                        }
                                        else if (selectedOption.equals("Seasons")) {
                                            String selectedSeason = (String) seasons.getSelectedItem();
                                            String selectedEpisode = (String) episodes.getSelectedItem();
                                            String selectedEpisodeOption = (String) episodeOptions.getSelectedItem();
                                            if(selectedSeason != null && selectedEpisode != null && selectedEpisodeOption != null){
                                                for(Map.Entry<String, java.util.List<Episode>> season : series.getSeasons().entrySet()){
                                                    if(season.getKey().equals(selectedSeason)){
                                                        for(Episode episode : season.getValue()){
                                                            if(episode.getEpisodeName().equals(selectedEpisode)){
                                                                if(selectedEpisodeOption.equals("Title")){
                                                                    episode.episodeName = modification;
                                                                } else if(selectedEpisodeOption.equals("Duration")){
                                                                    episode.episodeDuration = Long.parseLong(modification);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        updateProductionFrame.dispose();
                    }
                });
                updateProductionPanel.add(modifyButton);
                updateProductionPanel.add(productionType);
                updateProductionFrame.add(updateProductionPanel);
                updateProductionFrame.setVisible(true);
            }
        });

        JLabel updateActor = new JLabel("Update Actor");
        updateActor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame updateActorFrame = new JFrame();
                updateActorFrame.setTitle("Update an Actor");
                updateActorFrame.setSize(400, 500);
                updateActorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateActorFrame.setLocationRelativeTo(null);

                JPanel updateActorPanel = new JPanel();
                updateActorPanel.setLayout(null);
                updateActorPanel.setBackground(new Color(0x000000));

                JLabel updateActorLabel = new JLabel("Update an Actor");
                updateActorLabel.setBounds(130, 20, 300, 30);
                updateActorLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                updateActorLabel.setForeground(new Color(0xFFFFFF));
                updateActorPanel.add(updateActorLabel);

                JLabel chooseActor = new JLabel("Choose an Actor");
                chooseActor.setBounds(50, 100, 300, 30);
                chooseActor.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseActor.setForeground(new Color(0xFFFFFF));
                updateActorPanel.add(chooseActor);

                JComboBox<String> actorName = new JComboBox<>();
                actorName.setBounds(50, 140, 300, 30);
                for (Actor actor : IMDB.getInstance().actors) {
                    actorName.addItem(actor.name);
                }
                updateActorPanel.add(actorName);

                JLabel chooseOption = new JLabel("Choose what to update");
                chooseOption.setBounds(50, 180, 300, 30);
                chooseOption.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseOption.setForeground(new Color(0xFFFFFF));
                updateActorPanel.add(chooseOption);

                JComboBox<String> options = new JComboBox<>();
                options.setBounds(50, 220, 300, 30);
                options.addItem("Name");
                options.addItem("Biography");
                options.addItem("Performances");
                updateActorPanel.add(options);

                JLabel modificationsLabel = new JLabel("Modifications");
                modificationsLabel.setBounds(50, 260, 300, 30);
                modificationsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                modificationsLabel.setForeground(new Color(0xFFFFFF));
                updateActorPanel.add(modificationsLabel);

                JTextField modifications = new JTextField();
                modifications.setBounds(50, 300, 300, 30);
                updateActorPanel.add(modifications);

                options.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOption = (String) options.getSelectedItem();
                        modifications.setText("");
                        if (selectedOption != null) {
                            modifications.setVisible(true);
                        } else {
                            modifications.setVisible(false);
                        }
                    }
                });

                JButton modifyButton = new JButton("Modify");
                modifyButton.setBounds(50, 380, 300, 30);
                modifyButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                modifyButton.setForeground(new Color(0xFFFFFF));
                modifyButton.setFocusable(false);
                modifyButton.setBackground(new Color(0x1A1A1A));
                modifyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOption = (String) options.getSelectedItem();
                        String selectedActor = (String) actorName.getSelectedItem();
                        String modification = modifications.getText();

                        if (selectedOption != null && selectedActor != null && !modification.isEmpty()) {
                            for (Actor actor : IMDB.getInstance().actors) {
                                if (actor.name.equals(selectedActor)) {
                                    if (selectedOption.equals("Name")) {
                                        actor.name = modification;
                                    } else if (selectedOption.equals("Biography")) {
                                        actor.biography = modification;
                                    } else if (selectedOption.equals("Performances")) {
                                        List<Map.Entry<String, String>> performances = new ArrayList<>();
                                        String[] performancesString = modification.split(",");
                                        for (int i = 0; i < performancesString.length; i++) {
                                            String[] performance = performancesString[i].split(":");
                                            performances.add(new AbstractMap.SimpleEntry<>(performance[0], performance[1]));
                                        }
                                        actor.performances = performances;
                                    }
                                }
                            }
                        }
                        updateActorFrame.dispose();
                    }
                });

                updateActorPanel.add(modifyButton);
                updateActorFrame.add(updateActorPanel);
                updateActorFrame.setVisible(true);
            }
        });

        JLabel solveRequest = new JLabel("Solve Request");
        solveRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame solveRequestFrame = new JFrame();
                solveRequestFrame.setTitle("Solve a Request");
                solveRequestFrame.setSize(400, 450);
                solveRequestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                solveRequestFrame.setLocationRelativeTo(null);

                JPanel solveRequestPanel = new JPanel();
                solveRequestPanel.setLayout(null);
                solveRequestPanel.setBackground(new Color(0x000000));

                JLabel solveRequestLabel = new JLabel("Solve a Request");
                solveRequestLabel.setBounds(130, 20, 300, 30);
                solveRequestLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                solveRequestLabel.setForeground(new Color(0xFFFFFF));
                solveRequestPanel.add(solveRequestLabel);

                JLabel chooseRequest = new JLabel("Choose a Request");
                chooseRequest.setBounds(50, 100, 300, 30);
                chooseRequest.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                chooseRequest.setForeground(new Color(0xFFFFFF));
                solveRequestPanel.add(chooseRequest);

                JComboBox<String> requestName = new JComboBox<>();
                requestName.setBounds(50, 140, 300, 30);
                for(Request request : IMDB.getInstance().requests){
                    if(request.getSolver().equals(user.getUsername()))
                        requestName.addItem(request.getDescription());
                    if(user.getAccountType().equals(AccountType.ADMIN) &&
                            (request.getRequestTypes().equals(Request.RequestTypes.DELETE_ACCOUNT)
                                    || request.getRequestTypes().equals(Request.RequestTypes.OTHERS)))
                        requestName.addItem(request.getDescription());
                }
                solveRequestPanel.add(requestName);

                JLabel usernameLabel = new JLabel("Username: ");
                usernameLabel.setBounds(50, 180, 100, 30);
                usernameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                usernameLabel.setForeground(new Color(0xFFFFFF));
                solveRequestPanel.add(usernameLabel);

                JLabel username = new JLabel();
                username.setBounds(150, 180 , 300, 30);
                username.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                username.setForeground(new Color(0xFFFFFF));
                solveRequestPanel.add(username);

                JLabel requestTypeLabel = new JLabel("Request Type: ");
                requestTypeLabel.setBounds(50, 220, 100, 30);
                requestTypeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                requestTypeLabel.setForeground(new Color(0xFFFFFF));
                solveRequestPanel.add(requestTypeLabel);

                JLabel requestType = new JLabel();
                requestType.setBounds(150, 220, 300, 30);
                requestType.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                requestType.setForeground(new Color(0xFFFFFF));
                solveRequestPanel.add(requestType);

                requestName.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        String selectedRequest = (String) requestName.getSelectedItem();
                        if(selectedRequest != null){
                            for(Request request : IMDB.getInstance().requests){
                                if(request.getDescription().equals(selectedRequest)){
                                    username.setText(request.getUsername());
                                    requestType.setText(request.getRequestTypes().toString());
                                }
                            }
                        }
                    }
                });

                JButton solveRequest = new JButton("Solve Request");
                solveRequest.setBounds(75, 280, 250, 30);
                solveRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                solveRequest.setForeground(new Color(0xFFFFFF));
                solveRequest.setFocusable(false);
                solveRequest.setBackground(new Color(0x1A1A1A));
                solveRequest.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedRequest = (String) requestName.getSelectedItem();
                        if(selectedRequest == null) {
                            JOptionPane.showMessageDialog(solveRequestFrame, "Invalid request: no request selected.");
                            return;
                        }
                        for (Iterator<Request> iterator = IMDB.getInstance().requests.iterator(); iterator.hasNext();) {
                            Request request = iterator.next();
                            if (request.getDescription().equals(selectedRequest)) {
                                if(request.getRequestTypes().equals(Request.RequestTypes.DELETE_ACCOUNT)){
                                    User userToDelete = null;
                                    String username = request.getUsername();
                                    for(User user : IMDB.getInstance().users){
                                        if(user.getUsername().equals(username)){
                                            userToDelete = user;
                                            break;
                                        }
                                    }
                                    System.out.println("A FOST DE STERS SA MA SINUCID");
                                    IMDB.getInstance().users.remove(userToDelete);
                                }
                                String solution = JOptionPane.showInputDialog(solveRequestFrame, "Enter the solution:");
                                if(solution == null || solution.trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(solveRequestFrame, "Invalid solution: no solution entered.");
                                    return;
                                }
                                request.notifyObservers("* Your request has been solved." + "\n" + "Solution: " + solution);
                                if(request.getRequestTypes().equals(Request.RequestTypes.MOVIE_ISSUE) ||
                                        request.getRequestTypes().equals(Request.RequestTypes.ACTOR_ISSUE) ||
                                        request.getRequestTypes().equals(Request.RequestTypes.OTHERS)) {
                                    for (User user1 : IMDB.getInstance().users)
                                        if (user1.getUsername().equals(request.getUsername()) &&
                                                !user1.getAccountType().equals(AccountType.ADMIN)) {
                                            user1.setExperienceStrategy(new RequestSolvedStrategy());
                                            user1.updateExp();
                                        }
                                }
                                iterator.remove();
                                break;
                            }
                        }
                        solveRequestFrame.dispose();
                    }
                });

                JButton rejectRequest = new JButton("Reject Request");
                rejectRequest.setBounds(75, 320, 250, 30);
                rejectRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                rejectRequest.setForeground(new Color(0xFFFFFF));
                rejectRequest.setFocusable(false);
                rejectRequest.setBackground(new Color(0x1A1A1A));
                rejectRequest.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedRequest = (String) requestName.getSelectedItem();
                        if(selectedRequest == null) {
                            JOptionPane.showMessageDialog(solveRequestFrame, "Invalid request: no request selected.");
                            return;
                        }
                        for (Iterator<Request> iterator = IMDB.getInstance().requests.iterator(); iterator.hasNext();) {
                            Request request = iterator.next();
                            if (request.getDescription().equals(selectedRequest)) {
                                iterator.remove();
                                String reason = JOptionPane.showInputDialog(solveRequestFrame, "Enter the reason:");
                                if(reason == null || reason.trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(solveRequestFrame, "Invalid solution: no reason entered.");
                                    return;
                                }
                                request.notifyObservers("* Your request has been rejected." + "\n" + "Reason: " + reason);
                                break;
                            }

                        }
                        solveRequestFrame.dispose();
                    }
                });

                solveRequestPanel.add(rejectRequest);
                solveRequestPanel.add(solveRequest);
                solveRequestFrame.add(solveRequestPanel);
                solveRequestFrame.setVisible(true);
            }
        });


        JLabel logout = new JLabel("Logout");
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {"Close Application", "Switch Account"};
                int response = JOptionPane.showOptionDialog(null, "What do you want to do?",
                        "Logout", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (response == 0) {
                    System.exit(0);
                } else if (response == 1) {
                    dispose();
                    LoginWindow loginWindow = new LoginWindow();
                    loginWindow.setVisible(true);
                }
            }
        });

        if(user.getAccountType().equals(AccountType.ADMIN)){
            setTitle("Admin Menu");

            addProduction.setBounds(100, 320, 200, 50);
            addProduction.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            addProduction.setForeground(new Color(0xFFFFFF));
            panel.add(addProduction);

            removeProduction.setBounds(100, 360, 200, 50);
            removeProduction.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            removeProduction.setForeground(new Color(0xFFFFFF));
            panel.add(removeProduction);

            addActor.setBounds(100, 400, 200, 50);
            addActor.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            addActor.setForeground(new Color(0xFFFFFF));
            panel.add(addActor);

            removeActor.setBounds(100, 440, 200, 50);
            removeActor.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            removeActor.setForeground(new Color(0xFFFFFF));
            panel.add(removeActor);

            solveRequest.setBounds(100, 480, 200, 50);
            solveRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            solveRequest.setForeground(new Color(0xFFFFFF));
            panel.add(solveRequest);

            updateProduction.setBounds(100, 520, 200, 50);
            updateProduction.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            updateProduction.setForeground(new Color(0xFFFFFF));
            panel.add(updateProduction);

            updateActor.setBounds(100, 560, 200, 50);
            updateActor.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            updateActor.setForeground(new Color(0xFFFFFF));
            panel.add(updateActor);


            JLabel addUser = new JLabel("Add User");
            addUser.setBounds(100, 600, 200, 50);
            addUser.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            addUser.setForeground(new Color(0xFFFFFF));
            panel.add(addUser);
            addUser.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    JFrame addUserFrame = new JFrame();
                    addUserFrame.setTitle("Add an User");
                    addUserFrame.setSize(400, 800);
                    addUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    addUserFrame.setLocationRelativeTo(null);

                    JPanel addUserPanel = new JPanel();
                    addUserPanel.setLayout(null);
                    addUserPanel.setBackground(new Color(0x000000));

                    JLabel addUserLabel = new JLabel("Add an User");
                    addUserLabel.setBounds(130, 20, 300, 30);
                    addUserLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                    addUserLabel.setForeground(new Color(0xFFFFFF));
                    addUserPanel.add(addUserLabel);

                    JLabel userNameLabel = new JLabel("Full Name");
                    userNameLabel.setBounds(50, 100, 300, 30);
                    userNameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                    userNameLabel.setForeground(new Color(0xFFFFFF));
                    addUserPanel.add(userNameLabel);

                    JTextField userNameField = new JTextField();
                    userNameField.setBounds(50, 140, 300, 30);
                    userNameField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                    userNameField.setForeground(new Color(0x000000));
                    addUserPanel.add(userNameField);

                    JLabel userBirthdayLabel = new JLabel("Birthday date");
                    userBirthdayLabel.setBounds(50, 180, 300, 30);
                    userBirthdayLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                    userBirthdayLabel.setForeground(new Color(0xFFFFFF));
                    addUserPanel.add(userBirthdayLabel);

                    JComboBox<Long> dayBox = new JComboBox<>();
                    for(long i = 1; i <= 31; i++){
                        dayBox.addItem(i);
                    }
                    dayBox.setBounds(50, 220, 80, 30);
                    addUserPanel.add(dayBox);

                    JComboBox<Long> monthBox = new JComboBox<>();
                    for(long i = 1; i <= 12; i++){
                        monthBox.addItem(i);
                    }
                    monthBox.setBounds(140, 220, 100, 30);
                    addUserPanel.add(monthBox);

                    JComboBox<Long> yearBox = new JComboBox<>();
                    for(long i = Year.now().getValue(); i >= 1900; i--){
                        yearBox.addItem(i);
                    }
                    yearBox.setBounds(250, 220, 80, 30);
                    addUserPanel.add(yearBox);

                    JLabel userAccountTypeLabel = new JLabel("Account Type");
                    userAccountTypeLabel.setBounds(50, 260, 300, 30);
                    userAccountTypeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                    userAccountTypeLabel.setForeground(new Color(0xFFFFFF));
                    addUserPanel.add(userAccountTypeLabel);

                    JComboBox<String> userAccountTypeField = new JComboBox<>();
                    userAccountTypeField.setBounds(50, 300, 300, 30);
                    userAccountTypeField.addItem("Regular");
                    userAccountTypeField.addItem("Contributor");
                    userAccountTypeField.addItem("Admin");
                    addUserPanel.add(userAccountTypeField);

                    JLabel genderLabel = new JLabel("Gender");
                    genderLabel.setBounds(50, 340, 300, 30);
                    genderLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                    genderLabel.setForeground(new Color(0xFFFFFF));
                    addUserPanel.add(genderLabel);

                    JComboBox<Character> userGenderField = new JComboBox<>();
                    userGenderField.setBounds(50, 380, 300, 30);
                    userGenderField.addItem('M');
                    userGenderField.addItem('F');
                    userGenderField.addItem('N');
                    addUserPanel.add(userGenderField);

                    JLabel countryLabel = new JLabel("Country");
                    countryLabel.setBounds(50, 420, 300, 30);
                    countryLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                    countryLabel.setForeground(new Color(0xFFFFFF));
                    addUserPanel.add(countryLabel);

                    JTextField userCountryField = new JTextField();
                    userCountryField.setBounds(50, 460, 300, 30);
                    userCountryField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                    userCountryField.setForeground(new Color(0x000000));
                    addUserPanel.add(userCountryField);

                    JButton addUser = new JButton("Add User");
                    addUser.setBounds(100, 580, 200, 30);
                    addUser.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                    addUser.setForeground(new Color(0xFFFFFF));
                    addUser.setFocusable(false);
                    addUser.setBackground(new Color(0x1A1A1A));
                    addUser.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = userNameField.getText();
                            long day = (long) dayBox.getSelectedItem();
                            long month = (long) monthBox.getSelectedItem();
                            long year = (long) yearBox.getSelectedItem();
                            LocalDateTime birthDateTime = LocalDateTime.of((int) year, (int) month, (int) day, 0, 0);
                            String accountType = (String) userAccountTypeField.getSelectedItem();
                            Character gender = (Character) userGenderField.getSelectedItem();
                            String country = userCountryField.getText();
                            LocalDate birthDate = birthDateTime.toLocalDate();
                            LocalDate now = LocalDate.now();
                            Period period = Period.between(birthDate, now);
                            long age = period.getYears();
                            if(name.isEmpty() || country.isEmpty()){
                                JOptionPane.showMessageDialog(addUserFrame, "Invalid user: all fields must be filled.");
                                return;
                            }
                            try {
                                Long.parseLong(name);
                                JOptionPane.showMessageDialog(addUserFrame, "Invalid user: name must be a string.");
                                return;
                            } catch (NumberFormatException ex) {
                            }
                            try {
                                Long.parseLong(country);
                                JOptionPane.showMessageDialog(addUserFrame, "Invalid user: country must be a string.");
                                return;
                            } catch (NumberFormatException ex) {
                            }
                            if(accountType.equals("Regular")){
                                User.Information information = new User.Information.Builder()
                                        .setBirthDate(birthDateTime)
                                        .setGender(gender)
                                        .setCountry(country)
                                        .setCredentials(new Credentials(name))
                                        .setName(name)
                                        .setAge(age)
                                        .build();
                                Regular user = new Regular(name, information);
                                IMDB.getInstance().users.add(user);
                            } else if (accountType.equals("Contributor")){
                                User.Information information = new User.Information.Builder()
                                        .setBirthDate(birthDateTime)
                                        .setGender(gender)
                                        .setCountry(country)
                                        .setCredentials(new Credentials(name))
                                        .setName(name)
                                        .setAge(age)
                                        .build();
                                IMDB.getInstance().users.add(user);
                                Contributor user = new Contributor(name, information);
                            } else if (accountType.equals("Admin")){
                                User.Information information = new User.Information.Builder()
                                        .setBirthDate(birthDateTime)
                                        .setGender(gender)
                                        .setCountry(country)
                                        .setCredentials(new Credentials(name))
                                        .setName(name)
                                        .setAge(age)
                                        .build();
                                Admin user = new Admin(name, information);
                                IMDB.getInstance().users.add(user);
                            }
                            addUserFrame.dispose();
                        }
                    });
                    addUserPanel.add(addUser);
                    addUserFrame.add(addUserPanel);
                    addUserFrame.setVisible(true);
                }
            });

            JLabel removeUser = new JLabel("Remove User");
            removeUser.setBounds(1100, 120, 200, 50);
            removeUser.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            removeUser.setForeground(new Color(0xFFFFFF));
            panel.add(removeUser);
            removeUser.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    JFrame removeUserFrame = new JFrame();
                    removeUserFrame.setTitle("Remove an User");
                    removeUserFrame.setSize(400, 400);
                    removeUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    removeUserFrame.setLocationRelativeTo(null);

                    JPanel removeUserPanel = new JPanel();
                    removeUserPanel.setLayout(null);
                    removeUserPanel.setBackground(new Color(0x000000));

                    JLabel removeUserLabel = new JLabel("Remove an User");
                    removeUserLabel.setBounds(130, 20, 300, 30);
                    removeUserLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                    removeUserLabel.setForeground(new Color(0xFFFFFF));
                    removeUserPanel.add(removeUserLabel);

                    JLabel chooseUser = new JLabel("Choose an User");
                    chooseUser.setBounds(50, 100, 300, 30);
                    chooseUser.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                    chooseUser.setForeground(new Color(0xFFFFFF));
                    removeUserPanel.add(chooseUser);

                    JComboBox<String> userName = new JComboBox<>();
                    userName.setBounds(50, 140, 300, 30);
                    for(User user : IMDB.getInstance().users){
                        userName.addItem(user.getUsername());
                    }
                    removeUserPanel.add(userName);

                    JButton removeUser = new JButton("Remove User");
                    removeUser.setBounds(75, 190, 250, 30);
                    removeUser.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                    removeUser.setForeground(new Color(0xFFFFFF));
                    removeUser.setFocusable(false);
                    removeUser.setBackground(new Color(0x1A1A1A));
                    removeUser.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selectedUser = (String) userName.getSelectedItem();
                            for (Iterator<User> iterator = IMDB.getInstance().users.iterator(); iterator.hasNext();) {
                                User user1 = iterator.next();
                                if (user1.getUsername().equals(selectedUser)) {
                                    if(user1.getAccountType().equals(AccountType.CONTRIBUTOR)){
                                        Admin admin = (Admin) user;
                                        Contributor contributor = (Contributor) user1;
                                        admin.prodOrActors.addAll(contributor.prodOrActors);
                                    }
                                    iterator.remove();
                                    break;
                                }
                            }
                            removeUserFrame.dispose();
                        }
                    });
                    removeUserPanel.add(removeUser);
                    removeUserFrame.add(removeUserPanel);
                    removeUserFrame.setVisible(true);
                }
            });

            logout.setBounds(1100, 160, 200, 50);
            logout.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            logout.setForeground(new Color(0xFFFFFF));
            panel.add(logout);
        }
        else if(user.getAccountType().equals(AccountType.REGULAR)){
            setTitle("Regular Menu");

            createRequest.setBounds(100, 320, 200, 50);
            createRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            createRequest.setForeground(new Color(0xFFFFFF));
            panel.add(createRequest);

            removeRequest.setBounds(100, 370, 200, 50);
            removeRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            removeRequest.setForeground(new Color(0xFFFFFF));
            panel.add(removeRequest);


            Label addRemoveRating = new Label("Add/Remove rating");
            addRemoveRating.setBounds(100, 420, 200, 50);
            addRemoveRating.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            addRemoveRating.setForeground(new Color(0xFFFFFF));
            panel.add(addRemoveRating);
            addRemoveRating.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose();
                    MainWindow addRemoveRatingWindow = new MainWindow(user, IMDB.getInstance().movies,
                            IMDB.getInstance().series, IMDB.getInstance().actors);
                    addRemoveRatingWindow.setVisible(true);
                }
            });

            logout.setBounds(100, 460, 200, 50);
            logout.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            logout.setForeground(new Color(0xFFFFFF));
            panel.add(logout);
        }
        else if(user.getAccountType().equals(AccountType.CONTRIBUTOR)){
            setTitle("Contributor Menu");

            createRequest.setBounds(100, 320, 200, 50);
            createRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            createRequest.setForeground(new Color(0xFFFFFF));
            panel.add(createRequest);

            removeRequest.setBounds(100, 370, 200, 50);
            removeRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            removeRequest.setForeground(new Color(0xFFFFFF));
            panel.add(removeRequest);

            addProduction.setBounds(100, 420, 200, 50);
            addProduction.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            addProduction.setForeground(new Color(0xFFFFFF));
            panel.add(addProduction);

            removeProduction.setBounds(100, 470, 200, 50);
            removeProduction.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            removeProduction.setForeground(new Color(0xFFFFFF));
            panel.add(removeProduction);

            addActor.setBounds(100, 520, 200, 50);
            addActor.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            addActor.setForeground(new Color(0xFFFFFF));
            panel.add(addActor);

            removeActor.setBounds(100, 570, 200, 50);
            removeActor.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            removeActor.setForeground(new Color(0xFFFFFF));
            panel.add(removeActor);

            solveRequest.setBounds(1100, 160, 200, 50);
            solveRequest.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            solveRequest.setForeground(new Color(0xFFFFFF));
            panel.add(solveRequest);

            updateProduction.setBounds(100, 620, 200, 50);
            updateProduction.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            updateProduction.setForeground(new Color(0xFFFFFF));
            panel.add(updateProduction);

            updateActor.setBounds(1100, 120, 200, 50);
            updateActor.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            updateActor.setForeground(new Color(0xFFFFFF));
            panel.add(updateActor);

            logout.setBounds(1100, 200, 200, 50);
            logout.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            logout.setForeground(new Color(0xFFFFFF));
            panel.add(logout);

        }

        add(panel);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}