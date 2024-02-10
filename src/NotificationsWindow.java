import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class NotificationsWindow extends JFrame {
    JPanel panel = new JPanel(null);
    JLabel welcomeLabel = new JLabel("This are all the Notifications!");

    public NotificationsWindow(User user){
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


        JLabel goMenu = new JLabel("Go to menu");
        goMenu.setBounds(600, 50, 300, 50);
        goMenu.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        goMenu.setForeground(new Color(0xFFFFFF));
        goMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MenuWindow menuWindow = new MenuWindow(user);
                menuWindow.setVisible(true);
            }
        });
        panel.add(goMenu);


        int y = 120;
        List<String> usernotifications = user.getNotifications();
        if(usernotifications.isEmpty()){
            Label noNotifications = new Label("There are no notifications!");
            noNotifications.setBounds(100, 120, 300, 50);
            noNotifications.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            noNotifications.setForeground(new Color(0xFFFFFF));
            panel.add(noNotifications);
        } else {
            for(String notification : usernotifications){
                JLabel notificationLabel = new JLabel(notification);
                notificationLabel.setBounds(100, y, 900, 50);
                notificationLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
                notificationLabel.setForeground(new Color(0xFFFFFF));
                panel.add(notificationLabel);
                y += 50;
            }
        }
        add(panel);
        setVisible(true);
    }
}