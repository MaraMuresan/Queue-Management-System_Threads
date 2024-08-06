package org.example.gui;

import org.example.logic.SimulationManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class SimulationFrame extends JFrame{

    private JLabel labelTitle;
    private JLabel labelNbClients;
    private JLabel labelNbQueues;
    private JLabel labelSimulationInterval;
    private JLabel labelMinArrivalTime;
    private JLabel labelMaxArrivalTime;
    private JLabel labelMinServiceTime;
    private JLabel labelMaxServiceTime;
    private JLabel labelSelectionPolicy;
    private JLabel labelLogOfEvents;
    private JLabel labelAverageWaitingTime1;
    private JLabel labelAverageWaitingTime2;
    private JLabel labelAverageServiceTime1;
    private JLabel labelAverageServiceTime2;
    private JLabel labelPeakHour1;
    private JLabel labelPeakHour2;

    private JTextField textFieldNbClients;
    private JTextField textFieldNbQueues;
    private JTextField textFieldSimulationInterval;
    private JTextField textFieldMinArrivalTime;
    private JTextField textFieldMaxArrivalTime;
    private JTextField textFieldMinServiceTime;
    private JTextField textFieldMaxServiceTime;

    private JComboBox<String> comboBoxSelectionPolicy;

    private JTextArea textAreaLogOfEvents;
    private JScrollPane scrollPaneLogOfEvents;

    private JButton buttonStartSimulation;
    private JButton buttonStopSimulation;

    private SimulationManager simulationManager;
    public SimulationFrame() {

        this.simulationManager = new SimulationManager(this);

        setTitle("Queue Simulation");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        pack();
        setBounds(200, 15, 1200, 800);
        getContentPane().setBackground(new Color(5, 219, 92));

        labelTitle = new JLabel("Queue Simulation");
        labelTitle.setFont(new Font("Georgia", Font.BOLD, 30));
        labelTitle.setForeground(new Color(132, 1, 6));
        labelTitle.setBounds(10, 30, 1200, 35);
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTitle);

        labelNbClients = new JLabel("Number of Clients:");
        labelNbClients.setFont(new Font("Georgia", Font.BOLD, 20));
        labelNbClients.setForeground(new Color(132, 1, 6));
        labelNbClients.setBounds(10, 100, 300, 30);
        labelNbClients.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNbClients);

        textFieldNbClients = new JTextField(60);
        textFieldNbClients.setFont(new Font("Georgia", Font.BOLD, 15));
        textFieldNbClients.setBounds(320, 100, 190, 30);
        textFieldNbClients.setHorizontalAlignment(JTextField.CENTER);
        add(textFieldNbClients);

        labelNbQueues = new JLabel("Number of Queues:");
        labelNbQueues.setFont(new Font("Georgia", Font.BOLD, 20));
        labelNbQueues.setForeground(new Color(132, 1, 6));
        labelNbQueues.setBounds(10, 140, 300, 30);
        labelNbQueues.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNbQueues);

        textFieldNbQueues = new JTextField(60);
        textFieldNbQueues.setFont(new Font("Georgia", Font.BOLD, 15));
        textFieldNbQueues.setBounds(320, 140, 190, 30);
        textFieldNbQueues.setHorizontalAlignment(JTextField.CENTER);
        add(textFieldNbQueues);

        labelSimulationInterval = new JLabel("Simulation Interval (MAX):");
        labelSimulationInterval.setFont(new Font("Georgia", Font.BOLD, 20));
        labelSimulationInterval.setForeground(new Color(132, 1, 6));
        labelSimulationInterval.setBounds(10, 180, 300, 30);
        labelSimulationInterval.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelSimulationInterval);

        textFieldSimulationInterval = new JTextField(60);
        textFieldSimulationInterval.setFont(new Font("Georgia", Font.BOLD, 15));
        textFieldSimulationInterval.setBounds(320, 180, 190, 30);
        textFieldSimulationInterval.setHorizontalAlignment(JTextField.CENTER);
        add(textFieldSimulationInterval);

        labelMinArrivalTime = new JLabel("Minimum Arrival Time:");
        labelMinArrivalTime.setFont(new Font("Georgia", Font.BOLD, 20));
        labelMinArrivalTime.setForeground(new Color(132, 1, 6));
        labelMinArrivalTime.setBounds(10, 220, 300, 30);
        labelMinArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMinArrivalTime);

        textFieldMinArrivalTime = new JTextField(60);
        textFieldMinArrivalTime.setFont(new Font("Georgia", Font.BOLD, 15));
        textFieldMinArrivalTime.setBounds(320, 220, 190, 30);
        textFieldMinArrivalTime.setHorizontalAlignment(JTextField.CENTER);
        add(textFieldMinArrivalTime);

        labelMaxArrivalTime = new JLabel("Maximum Arrival Time:");
        labelMaxArrivalTime.setFont(new Font("Georgia", Font.BOLD, 20));
        labelMaxArrivalTime.setForeground(new Color(132, 1, 6));
        labelMaxArrivalTime.setBounds(10, 260, 300, 30);
        labelMaxArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMaxArrivalTime);

        textFieldMaxArrivalTime = new JTextField(60);
        textFieldMaxArrivalTime.setFont(new Font("Georgia", Font.BOLD, 15));
        textFieldMaxArrivalTime.setBounds(320, 260, 190, 30);
        textFieldMaxArrivalTime.setHorizontalAlignment(JTextField.CENTER);
        add(textFieldMaxArrivalTime);

        labelMinServiceTime = new JLabel("Minimum Service Time:");
        labelMinServiceTime.setFont(new Font("Georgia", Font.BOLD, 20));
        labelMinServiceTime.setForeground(new Color(132, 1, 6));
        labelMinServiceTime.setBounds(10, 300, 300, 30);
        labelMinServiceTime.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMinServiceTime);

        textFieldMinServiceTime = new JTextField(60);
        textFieldMinServiceTime.setFont(new Font("Georgia", Font.BOLD, 15));
        textFieldMinServiceTime.setBounds(320, 300, 190, 30);
        textFieldMinServiceTime.setHorizontalAlignment(JTextField.CENTER);
        add(textFieldMinServiceTime);

        labelMaxServiceTime = new JLabel("Maximum Service Time:");
        labelMaxServiceTime.setFont(new Font("Georgia", Font.BOLD, 20));
        labelMaxServiceTime.setForeground(new Color(132, 1, 6));
        labelMaxServiceTime.setBounds(10, 340, 300, 30);
        labelMaxServiceTime.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMaxServiceTime);

        textFieldMaxServiceTime = new JTextField(60);
        textFieldMaxServiceTime.setFont(new Font("Georgia", Font.BOLD, 15));
        textFieldMaxServiceTime.setBounds(320, 340, 190, 30);
        textFieldMaxServiceTime.setHorizontalAlignment(JTextField.CENTER);
        add(textFieldMaxServiceTime);

        labelSelectionPolicy = new JLabel("Selection Policy:");
        labelSelectionPolicy.setFont(new Font("Georgia", Font.BOLD, 20));
        labelSelectionPolicy.setForeground(new Color(132, 1, 6));
        labelSelectionPolicy.setBounds(10, 380, 300, 30);
        labelSelectionPolicy.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelSelectionPolicy);

        String[] policies = {"SHORTEST_QUEUE", "SHORTEST_TIME"};
        comboBoxSelectionPolicy = new JComboBox<>(policies);
        comboBoxSelectionPolicy.setFont(new Font("Georgia", Font.BOLD, 15));
        comboBoxSelectionPolicy.setBounds(320, 380, 190, 30);
        add(comboBoxSelectionPolicy);

        labelLogOfEvents = new JLabel("Log of Events:");
        labelLogOfEvents.setFont(new Font("Georgia", Font.BOLD, 20));
        labelLogOfEvents.setForeground(new Color(132, 1, 6));
        labelLogOfEvents.setBounds(600, 100, 550, 30);
        labelLogOfEvents.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelLogOfEvents);

        textAreaLogOfEvents = new JTextArea();
        textAreaLogOfEvents.setFont(new Font("Georgia", Font.PLAIN, 15));
        add(textAreaLogOfEvents);

        scrollPaneLogOfEvents = new JScrollPane(textAreaLogOfEvents);
        scrollPaneLogOfEvents.setBounds(600, 140, 550, 600);
        add(scrollPaneLogOfEvents);

        buttonStartSimulation = new JButton("START");
        buttonStartSimulation.setFont(new Font("Georgia", Font.BOLD, 20));
        buttonStartSimulation.setForeground(new Color(132, 1, 6));
        buttonStartSimulation.setBounds(100, 485, 160, 40);
        buttonStartSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaLogOfEvents.setText("");
                labelAverageWaitingTime2.setText("");
                labelAverageServiceTime2.setText("");
                labelPeakHour2.setText("");
                simulationManager.startSimulation();
            }
        });
        add(buttonStartSimulation);

        buttonStopSimulation = new JButton("STOP");
        buttonStopSimulation.setFont(new Font("Georgia", Font.BOLD, 20));
        buttonStopSimulation.setForeground(new Color(132, 1, 6));
        buttonStopSimulation.setBounds(290, 485, 160, 40);
        buttonStopSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationManager.stopSimulation();
            }
        });
        add(buttonStopSimulation);

        labelAverageWaitingTime1 = new JLabel("Average Waiting Time:");
        labelAverageWaitingTime1.setFont(new Font("Georgia", Font.BOLD, 20));
        labelAverageWaitingTime1.setForeground(new Color(132, 1, 6));
        labelAverageWaitingTime1.setBounds(10, 600, 300, 30);
        labelAverageWaitingTime1.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelAverageWaitingTime1);

        labelAverageWaitingTime2 = new JLabel();
        labelAverageWaitingTime2.setFont(new Font("Georgia", Font.BOLD, 20));
        labelAverageWaitingTime2.setBounds(320, 600, 190, 30);
        labelAverageWaitingTime2.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelAverageWaitingTime2);

        labelAverageServiceTime1 = new JLabel("Average Service Time:");
        labelAverageServiceTime1.setFont(new Font("Georgia", Font.BOLD, 20));
        labelAverageServiceTime1.setForeground(new Color(132, 1, 6));
        labelAverageServiceTime1.setBounds(10, 640, 300, 30);
        labelAverageServiceTime1.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelAverageServiceTime1);

        labelAverageServiceTime2 = new JLabel();
        labelAverageServiceTime2.setFont(new Font("Georgia", Font.BOLD, 20));
        labelAverageServiceTime2.setBounds(320, 640, 190, 30);
        labelAverageServiceTime2.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelAverageServiceTime2);

        labelPeakHour1 = new JLabel("Peak Hour:");
        labelPeakHour1.setFont(new Font("Georgia", Font.BOLD, 20));
        labelPeakHour1.setForeground(new Color(132, 1, 6));
        labelPeakHour1.setBounds(10, 680, 300, 30);
        labelPeakHour1.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelPeakHour1);

        labelPeakHour2 = new JLabel();
        labelPeakHour2.setFont(new Font("Georgia", Font.BOLD, 20));
        labelPeakHour2.setBounds(320, 680, 190, 30);
        labelPeakHour2.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelPeakHour2);
    }

    public JTextArea getTextAreaLogOfEvents() {
        return textAreaLogOfEvents;
    }

    public JTextField getTextFieldNbClients() {
        return textFieldNbClients;
    }

    public JTextField getTextFieldNbQueues() {
        return textFieldNbQueues;
    }

    public JTextField getTextFieldSimulationInterval() {
        return textFieldSimulationInterval;
    }

    public JTextField getTextFieldMinArrivalTime() {
        return textFieldMinArrivalTime;
    }

    public JTextField getTextFieldMaxArrivalTime() {
        return textFieldMaxArrivalTime;
    }

    public JTextField getTextFieldMinServiceTime() {
        return textFieldMinServiceTime;
    }

    public JTextField getTextFieldMaxServiceTime() {
        return textFieldMaxServiceTime;
    }

    public JComboBox<String> getComboBoxSelectionPolicy() {
        return comboBoxSelectionPolicy;
    }

    public JLabel getLabelAverageWaitingTime2() { return labelAverageWaitingTime2; }

    public JLabel getLabelAverageServiceTime2() {
        return labelAverageServiceTime2;
    }

    public JLabel getLabelPeakHour2() {
        return labelPeakHour2;
    }
}
