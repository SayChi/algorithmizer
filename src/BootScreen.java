// As created by Bastiaan Wuisman on 24-2-2015
// Created using IntelliJ IDEA


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BootScreen extends JPanel implements ActionListener {

    private GridBagConstraints c = new GridBagConstraints();

    private JFrame frame = new JFrame();

    private SpinnerNumberModel arraySizeModel = new SpinnerNumberModel(50, 0, 100000000, 1);
    private SpinnerNumberModel speedModel = new SpinnerNumberModel(20, 0, 10000, 1);

    private JSpinner arraySizeSpinner = new JSpinner(arraySizeModel);
    private JSpinner speedSpinner = new JSpinner(speedModel);
    private JComboBox algSelectDropDown = new JComboBox(Algorithm.algNames);

    private JButton start = new JButton("Start");

    public BootScreen() {
        frame.setTitle("Settings");
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this);

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
        add(algSelectDropDown, c);

        c.gridx = 1;
        c.gridy = 3;
        add(start, c);

        frame.setVisible(true);
        start.addActionListener(this);
    }

    public static void main(String[] Args) {
        new BootScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Algorithm(Integer.parseInt(arraySizeModel.getValue().toString()), Integer.parseInt
                (speedModel.getValue().toString()), algSelectDropDown.getSelectedIndex());
    }
}