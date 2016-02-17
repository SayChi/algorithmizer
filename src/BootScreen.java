// As created by Bastiaan Wuisman on 24-2-2015
// Created using IntelliJ IDEA


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BootScreen extends JPanel implements ActionListener {

    GridBagConstraints c = new GridBagConstraints();

    JFrame frame = new JFrame();

    SpinnerNumberModel arraySizeModel = new SpinnerNumberModel(50, 0, 100000000, 1);
    SpinnerNumberModel speedModel = new SpinnerNumberModel(20, 0, 10000, 1);
    SpinnerNumberModel algSelectModel = new SpinnerNumberModel(1, 1, 5, 1);

    JSpinner arraySizeSpinner = new JSpinner(arraySizeModel);
    JSpinner speedSpinner = new JSpinner(speedModel);
    JSpinner algSelectSpinner = new JSpinner(algSelectModel);

    JButton start = new JButton("Start");

    BootScreen() {
        frame.setTitle("Settings");
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);

        //TODO: make sure the option display without manually resizing

        setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.NONE;
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Array size: "), c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        add(arraySizeSpinner, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Speed delay: "), c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        add(speedSpinner, c);

        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Algorithm: "), c);

        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(algSelectSpinner, c);

        c.gridx = 1;
        c.gridy = 3;
        add(start, c);

        start.addActionListener(this);
    }

    public static void main(String[] Args) {
        new BootScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Algorithm(Integer.parseInt(arraySizeModel.getValue().toString()), Integer.parseInt
                (speedModel.getValue().toString()), Integer.parseInt(algSelectModel.getValue().toString()));
    }
}