package GUI;

import gameClient.Ex2;
import okhttp3.internal.ws.RealWebSocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Menu extends JFrame implements ActionListener {
    String[] arg = {"123"};
    String[] lvl = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};

    JButton start;
    JButton our_id;
    JButton exit;
    
    JComboBox comboBox;
    JFrame frame;
    JPanel panel;
    ImageIcon picachu;

    JLabel welcomeLabel;
    JLabel lvlLabel;
    JLabel idLabel;



    public Menu() {

        initialize();

        /**
         * Setting the GUI window properties
         */
        frame.setIconImage(picachu.getImage());
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        /**
         * Introduction label
         */
        welcomeLabel.setBounds(10, 5, 300, 25);
        panel.add(welcomeLabel);


        /**
         * Select level
         */
        lvlLabel.setBounds(10, 50, 80, 25);
        panel.add(lvlLabel);
        comboBox.addActionListener(this);
        comboBox.setBounds(100, 50, 80, 25);
        panel.add(comboBox);

        /**
         * After you selecting the level
         * press start to launch the game
         */
        start.setBounds(190, 50, 60, 25);
        start.addActionListener(e -> Ex2.main(arg));
        panel.add(start);


        /**
         * ID presentation
         */
        idLabel.setBounds(10, 100, 80, 25);
        panel.add(idLabel);
        our_id.addActionListener(e -> showMessageDialog(null, "Sean Peer : 313581613\nAmos Something : 131334235"));
        our_id.setBounds(100, 100, 100, 25);
        panel.add(our_id);

        /**
         * Exit Button
         */
        exit.addActionListener(e -> frame.dispose());
        exit.setBounds(100,200,100,25);
        panel.add(exit);


        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e1) {
        Ex2 ex2 = new Ex2();
        if (e1.getSource() == comboBox) {
           ex2.setChoice(comboBox.getSelectedIndex());//Saving the level and using a setter to set Ex2 with the level that selected
        }
    }

    public void initialize(){
        panel = new JPanel();
        frame = new JFrame("Pokemon Game");
        picachu = new ImageIcon("Pokemon-pic.jpg");
        welcomeLabel = new JLabel("Welcome to our Pokemon Algorithm Game !");
        lvlLabel = new JLabel("Choose level ");
        comboBox = new JComboBox(lvl);
        start = new JButton("Play");
        idLabel = new JLabel("Developers ID");
        our_id = new JButton("Click Here");
        exit = new JButton("Exit");
    }

    public static void main(String[] args) {
        new Menu();
    }


}
