import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;

public class LoginWindow extends JFrame implements ActionListener, KeyListener, MouseListener {
    JButton signInButton = new JButton("Sign in");
    JTextField userText = new JTextField(20);
    JPasswordField passwordText = new JPasswordField(20);
    JLabel emailLabel = new JLabel("Email");
    JLabel passwordLabel = new JLabel("Password");
    JLabel signInLabel = new JLabel("Sign in");
    JLabel newUserLabel = new JLabel("New to IMDb?");
    JButton registerButton = new JButton("Create your IMDb account");

    JPanel panel = new JPanel();
    JLabel errorLabel = new JLabel("Invalid credentials");

    public LoginWindow() {
        setTitle("Login");
        setSize(420, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel.setOpaque(false);
        panel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("resources/LOGOx2.png");
        JLabel imageLabel = new JLabel(imageIcon);

        imageLabel.setBounds(150, 10, 110, 60);
        panel.add(imageLabel);

        signInLabel.setFont(new Font("Sans Serif", Font.PLAIN, 28));
        signInLabel.setBounds(50, 90, 150, 50);
        panel.add(signInLabel);

        emailLabel.setFont(new Font("Sans Serif", Font.BOLD, 13));
        emailLabel.setBounds(53, 145, 80, 25);
        panel.add(emailLabel);

        userText.setBounds(53, 170, 300, 35);
        userText.addKeyListener(this);
        panel.add(userText);

        passwordLabel.setFont(new Font("Sans Serif", Font.BOLD, 13));
        passwordLabel.setBounds(53, 215, 80, 25);
        panel.add(passwordLabel);

        passwordText.setBounds(53, 240, 300, 35);
        passwordText.addKeyListener(this);
        panel.add(passwordText);

        signInButton.setBounds(53, 300, 300, 35);
        signInButton.setBackground(new Color(255, 216, 20));
        signInButton.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        signInButton.setFocusable(false);
        signInButton.setBorderPainted(false);
        signInButton.addActionListener(this);
        signInButton.addMouseListener(this);
        panel.add(signInButton);

        newUserLabel.setFont(new Font("Sans Serif", Font.PLAIN, 13));
        newUserLabel.setBounds(160, 370, 150, 25);
        panel.add(newUserLabel);

        registerButton.setBounds(53, 395, 300, 35);
        registerButton.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        registerButton.setFocusable(false);
        registerButton.setBackground(new Color(236, 236, 236));
        registerButton.addMouseListener(new MouseAdapter() {
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


                JLabel genderLabel = new JLabel("Gender");
                genderLabel.setBounds(50, 260, 300, 30);
                genderLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                genderLabel.setForeground(new Color(0xFFFFFF));
                addUserPanel.add(genderLabel);

                JComboBox<Character> userGenderField = new JComboBox<>();
                userGenderField.setBounds(50, 300, 300, 30);
                userGenderField.addItem('M');
                userGenderField.addItem('F');
                userGenderField.addItem('N');
                addUserPanel.add(userGenderField);

                JLabel countryLabel = new JLabel("Country");
                countryLabel.setBounds(50, 340, 300, 30);
                countryLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                countryLabel.setForeground(new Color(0xFFFFFF));
                addUserPanel.add(countryLabel);

                JTextField userCountryField = new JTextField();
                userCountryField.setBounds(50, 380, 300, 30);
                userCountryField.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                userCountryField.setForeground(new Color(0x000000));
                addUserPanel.add(userCountryField);

                JButton addUser = new JButton("Add User");
                addUser.setBounds(100, 420, 200, 30);
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
                        JOptionPane.showMessageDialog(addUserFrame, user.getInformation().getCredentials().getPassword());
                        addUserFrame.dispose();
                    }
                });
                addUserPanel.add(addUser);
                addUserFrame.add(addUserPanel);
                addUserFrame.setVisible(true);
            }
        });
        panel.add(registerButton);

        userText.setFocusTraversalKeysEnabled(false);

        errorLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        errorLabel.setBounds(53, 335, 300, 35);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && userText.isFocusable()) {
            passwordText.requestFocus();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && passwordText.isFocusable()) {
            String username = userText.getText();
            String password = String.valueOf(passwordText.getPassword());
            System.out.println(username + ", " + password);
            for(User user : IMDB.getInstance().getUsers()){
                if(user.getInformation().getCredentials().getEmail().equals(username)
                        && user.getInformation().getCredentials().getPassword().equals(password)){
                    System.out.println("User found");
                    dispose();
                    User currentUser = user;
                    MainWindow newWindow = new MainWindow(currentUser,
                            IMDB.getInstance().getMovies(), IMDB.getInstance().getSeries(), IMDB.getInstance().getActors());
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == signInButton){
            String username = userText.getText();
            String password = String.valueOf(passwordText.getPassword());
            System.out.println(username + ", " + password);
            for(User user : IMDB.getInstance().getUsers()){
                if(user.getInformation().getCredentials().getEmail().equals(username)
                        && user.getInformation().getCredentials().getPassword().equals(password)){
                    System.out.println("User found");
                    User currentUser = user;
                    dispose();
                    MainWindow newWindow = new MainWindow(currentUser,
                            IMDB.getInstance().movies, IMDB.getInstance().series, IMDB.getInstance().actors);
                }
            }
            errorLabel.setVisible(true);
            panel.revalidate();
            panel.repaint();
        }
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
        signInButton.setBackground(new Color(204, 173, 16));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        signInButton.setBackground(new Color(255, 216, 20));
    }
}