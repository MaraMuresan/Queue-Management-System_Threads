package org.example;

import org.example.gui.SimulationFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimulationFrame().setVisible(true));
    }
}
