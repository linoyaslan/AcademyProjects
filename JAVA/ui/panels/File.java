package ui.panels;

import components.BigBrother;
import components.Control.SimulationController;
import components.Driving;
import components.Moked;
import components.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Represent the File menu - This menu include only the "Exit" sub-menu
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class File extends JMenu implements ActionListener {
    private JMenuItem exit;
    private SimulationController simulationController;

    /**
     * The constructor
     */
    public File(SimulationController simulationController) {
        super("File");
        this.simulationController=simulationController;
        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        this.add(exit);
        exit.addActionListener(this);
    }

    /**
     * If the user select the Exit sub menu the GUI is stop and exit
     * @param e - The selection of the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = ((Driving)simulationController).getVehicles().size()-1;
        Driving driving = (Driving)simulationController;
        Vehicle vehicle = driving.getVehicles().get(index);
        BigBrother bigBrother = vehicle.getBigBrother();
        if(bigBrother.getMoked().getReports().size() != 0){
            JOptionPane.showMessageDialog(null, "There is reports that\n was not approved", "You can not get out from the program " + "", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            System.exit(0);
        }
    }
}
