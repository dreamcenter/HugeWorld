package top.dreamcenter.hw.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel {
    public LoginPanel(){
        setSize(500,800);
        setLayout(null);
        Image img = null;
        try {
            BufferedImage bi = null;
            bi = ImageIO.read(new File("sources\\imgs\\Login.jpg"));
            bi = bi.getSubimage(0,350,bi.getWidth(),bi.getHeight()-350);
            img = bi.getScaledInstance(500,
                    (int)(500*1.0/bi.getWidth()*bi.getHeight()),
                    BufferedImage.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel bk = new JLabel(new ImageIcon(img));
        bk.setBounds(0,0,500,800);

        JTextField userName = new JTextField();
        userName.setBounds(100,500,300,30);
        userName.setFont(new Font("",Font.BOLD,20));
        userName.setForeground(Color.orange);
        JPasswordField password = new JPasswordField();
        password.setBounds(100,550,300,30);
        password.setFont(new Font("",Font.BOLD,20));
        password.setForeground(Color.orange);

        JButton login = new JButton("login");
        login.setBounds(100,600,300,30);
        login.setBackground(Color.cyan);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(userName.getText() + String.valueOf(password.getPassword()));
                //  TODO
                if(userName.getText().equals("123")&&String.valueOf(password.getPassword()).equals("123")){
                    setVisible(false);
                }
            }
        });

        add(login);
        add(bk);
        add(userName);
        add(password);
        setVisible(true);
    }
}
