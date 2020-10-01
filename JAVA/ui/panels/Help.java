package ui.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Help menu - if the user select this a message will pop
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class Help extends JMenu implements ActionListener {
    private JMenuItem help;
    private JFrame massage;

    /**
     * The constructor
     */
    public Help() {
        super("Help");
        help = new JMenuItem("Help");
        this.add(help);
        help.addActionListener(this);
    }

    /**
     * If the user select this menu - a message will pop
     * @param e - The selection of the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Home Work 3\n GUI @ Threads", "Message " + "", JOptionPane.INFORMATION_MESSAGE);

    }
}
