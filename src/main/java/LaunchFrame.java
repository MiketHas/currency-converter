package main.java;

import javax.swing.*;

public class LaunchFrame extends JFrame {

    MainPanel myPanel;

    public LaunchFrame() {
        this.setTitle("Currency Converter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        myPanel = new MainPanel();
        this.add(myPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
