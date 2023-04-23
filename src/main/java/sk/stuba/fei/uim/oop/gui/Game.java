package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {

    public Game() {

        JFrame frame = new JFrame("Water Pipes!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(670,780);

        frame.getContentPane().setBackground(new Color(51,153,255));
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        GameLogic logic = new GameLogic(frame);
        frame.addKeyListener(logic);

        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(Color.LIGHT_GRAY);
        JPanel sideMenu2 = new JPanel();
        sideMenu.setBackground(Color.LIGHT_GRAY);

        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(logic);
        buttonRestart.setFocusable(false);

        JButton checkPathButton = new JButton("CHECK");
        checkPathButton.addActionListener(logic);
        buttonRestart.setFocusable(false);


        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8, 14, 8);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(2);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(logic);

        sideMenu.setLayout(new GridLayout(2, 2));
        sideMenu2.setLayout(new GridLayout(1, 2));
        sideMenu2.add(logic.getLabel());

        buttonRestart.setBackground(Color.GRAY);
        buttonRestart.setForeground(Color.WHITE);
        buttonRestart.setFocusPainted(false);

        checkPathButton.setBackground(Color.GRAY);
        checkPathButton.setForeground(Color.WHITE);
        checkPathButton.setFocusPainted(false);

        sideMenu.add(logic.getLabel());
        sideMenu2.add(checkPathButton);
        sideMenu2.add(buttonRestart);
        sideMenu.add(sideMenu2);
        sideMenu.add(logic.getBoardSizeLabel());
        sideMenu.add(slider);

        frame.add(sideMenu, BorderLayout.PAGE_START);

        frame.setVisible(true);
    }
}
