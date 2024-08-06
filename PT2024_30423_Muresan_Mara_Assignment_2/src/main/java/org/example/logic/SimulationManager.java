package org.example.logic;
import org.example.model.Client;
import org.example.model.Server;
import org.example.gui.SimulationFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SimulationManager implements Runnable {

    public int nbClients;
    public int nbQueues;
    public int simulationInterval;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int minServiceTime;
    public int maxServiceTime;
    public SelectionPolicy selectionPolicy;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Client> generatedClients;
    private PrintWriter writer;
    private boolean running = false;
    private double totalWaitingTime = 0;
    private int totalServiceTime = 0;
    private int[] clientsPerTimeUnit;

    public SimulationManager(SimulationFrame frame) {

        this.frame = frame;

        try {
            writer = new PrintWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean retrieveInputsFromUI() {
        try {
            nbClients = Integer.parseInt(frame.getTextFieldNbClients().getText());
            nbQueues = Integer.parseInt(frame.getTextFieldNbQueues().getText());
            simulationInterval = Integer.parseInt(frame.getTextFieldSimulationInterval().getText());
            minArrivalTime = Integer.parseInt(frame.getTextFieldMinArrivalTime().getText());
            maxArrivalTime = Integer.parseInt(frame.getTextFieldMaxArrivalTime().getText());
            minServiceTime = Integer.parseInt(frame.getTextFieldMinServiceTime().getText());
            maxServiceTime = Integer.parseInt(frame.getTextFieldMaxServiceTime().getText());
            String policy = (String) frame.getComboBoxSelectionPolicy().getSelectedItem();
            selectionPolicy = SelectionPolicy.valueOf(policy);

            scheduler = new Scheduler(nbQueues, selectionPolicy);

            if(minArrivalTime > maxArrivalTime || minServiceTime > maxServiceTime) {
                JOptionPane.showMessageDialog(frame, "Enter valid time intervals!", "Input ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Enter valid numbers in all fields!", "Input ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void generateRandomClients() {
        Random random = new Random();
        for(int i = 1; i <= nbClients; i++) {
            int ID = i;
            int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int serviceTime = random.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;
            generatedClients.add(new Client(ID, arrivalTime, serviceTime));
            totalServiceTime = totalServiceTime + serviceTime;
        }

        generatedClients.sort((c1, c2) -> Integer.compare(c1.getArrivalTime(), c2.getArrivalTime()));
    }

    public boolean startSimulation() {
        if (retrieveInputsFromUI()) {
            if (running) {
                return true;
            }
            scheduler.resetAllServers();
            generatedClients = new ArrayList<>();
            clientsPerTimeUnit = new int[simulationInterval];
            totalWaitingTime = 0;
            totalServiceTime = 0;

            generateRandomClients();

            running = true;
            Thread simulationThread = new Thread(this);
            simulationThread.start();
            return true;
        }
        return false;
    }

    private void updateTextArea(String text) {
        SwingUtilities.invokeLater(() -> frame.getTextAreaLogOfEvents().append(text));
    }

    private void updateLabelAverageServiceTime2(String text) {
        SwingUtilities.invokeLater(() -> frame.getLabelAverageServiceTime2().setText(text));
    }

    private void updateLabelAverageWaitingTime2(String text) {
        SwingUtilities.invokeLater(() -> frame.getLabelAverageWaitingTime2().setText(text));
    }

    private void updateLabelPeakHour2(String text) {
        SwingUtilities.invokeLater(() -> frame.getLabelPeakHour2().setText(text));
    }

    @Override
    public void run() {
        int currentTime = 0;
        writer.println("Simulation Started...");
        updateTextArea("Simulation Started..." + '\n');
        while(running && currentTime < simulationInterval) {

            Iterator<Client> iterator = generatedClients.iterator();
            while(iterator.hasNext()) {
                Client client = iterator.next();
                if(client.getArrivalTime() == currentTime) {
                    scheduler.dispatchClient(client);
                    iterator.remove();
                }
            }

            int currentClientCount = 0;
            for(Server server : scheduler.getServers()) {
                currentClientCount = currentClientCount + server.getNbClientsCurrentlyAtServer().get();
            }
            clientsPerTimeUnit[currentTime] = currentClientCount;

            writer.println('\n' + "Time " + currentTime);
            writer.print("Waiting Clients: ");

            updateTextArea('\n' + "Time " + currentTime + '\n');
            updateTextArea("Waiting Clients: ");
            if(generatedClients.isEmpty()) {
                writer.print("no more clients");
                updateTextArea("no more clients");
            }
            else {
                for(Client client : generatedClients) {
                    writer.print("(" + client.getID() + ", " + client.getArrivalTime() + ", " + client.getServiceTime() + "); ");
                    updateTextArea("(" + client.getID() + ", " + client.getArrivalTime() + ", " + client.getServiceTime() + "); ");
                }
            }
            writer.println();
            updateTextArea("\n");
            int queue = 1;
            for(Server server : scheduler.getServers()) {
                writer.print("Queue " + queue + ": ");
                updateTextArea("Queue " + queue + ": ");
                if(server.getClients().isEmpty()) {
                    writer.print("closed" + '\n');
                    updateTextArea("closed" + '\n');
                }
                else {
                    for(Client client : server.getClients()) {
                        writer.print("(" + client.getID() + ", " + client.getArrivalTime() + ", " + client.getServiceTime() + "); ");
                        updateTextArea("(" + client.getID() + ", " + client.getArrivalTime() + ", " + client.getServiceTime() + "); ");
                    }
                    writer.println();
                    updateTextArea("\n");
                }
                queue++;
            }

            currentTime++;
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Simulation Thread was interrupted!");
                return;
            }
        }
        if(running == true) {
            writer.println('\n' + "Simulation Ended.");
            updateTextArea('\n' + "Simulation Ended.");

            for(Server server : scheduler.getServers())
                server.endSimulation();
            running = false;
        }

        if(nbQueues > 0) {
            for(Server server : scheduler.getServers()) {
                if (server.getNbClientsAtServer().get() > 0) {
                    totalWaitingTime = totalWaitingTime + (double) server.getTotalWaitingTime().get() / server.getNbClientsAtServer().get();
                }
            }

            double averageWaitingTime = totalWaitingTime / nbQueues;
            writer.println("\n" + "Average Waiting Time: " + averageWaitingTime);
            updateLabelAverageWaitingTime2(String.format("%.2f", averageWaitingTime));
        }

        if(nbClients > 0) {

            double averageServiceTime = (double) totalServiceTime / nbClients;
            writer.println("Average Service Time: " + averageServiceTime);
            updateLabelAverageServiceTime2(String.format("%.2f", averageServiceTime));
        }

        int peakHour = 0;
        int maxClients = 0;
        for (int i = 0; i < clientsPerTimeUnit.length; i++) {
            if (clientsPerTimeUnit[i] > maxClients) {
                maxClients = clientsPerTimeUnit[i];
                peakHour = i;
            }
        }

        writer.println("Peak Hour: Time " + peakHour);
        writer.close();
        updateLabelPeakHour2("Time "  + peakHour);
    }

    public void stopSimulation() {
        writer.println('\n' + "Simulation Was Stopped." + '\n');
        writer.close();
        updateTextArea('\n' + "Simulation Was Stopped." + '\n');

        for(Server server : scheduler.getServers())
            server.endSimulation();
        running = false;
    }
}
