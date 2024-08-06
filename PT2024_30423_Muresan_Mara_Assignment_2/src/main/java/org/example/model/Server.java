package org.example.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server implements Runnable{

    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private AtomicBoolean simulationEnded;
    private AtomicInteger totalWaitingTime;
    private AtomicInteger nbClientsAtServer;
    private AtomicInteger nbClientsCurrentlyAtServer;

    public Server() {
        this.clients = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
        this.simulationEnded = new AtomicBoolean(false);
        this.totalWaitingTime = new AtomicInteger(0);
        this.nbClientsAtServer = new AtomicInteger(0);
        this.nbClientsCurrentlyAtServer = new AtomicInteger(0);
    }

    public void addClient(Client newClient) {

        try {
            clients.put(newClient);
            waitingPeriod.addAndGet(newClient.getServiceTime());
            totalWaitingTime.addAndGet(newClient.getServiceTime());
            nbClientsAtServer.addAndGet(1);
            nbClientsCurrentlyAtServer.addAndGet(1);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted while adding a client. ID: " + newClient.getID() + '\n');
        }

    }

    @Override
    public void run() {

        while(!simulationEnded.get()) {
            try {
                if(!clients.isEmpty()) {
                    Client client = clients.peek();
                    if(client != null) {
                        if (client.getServiceTime() == 1) {
                            clients.take();
                            if(clients.isEmpty()) endSimulation();
                            nbClientsCurrentlyAtServer.addAndGet(-1);
                        }
                        client.setServiceTime(client.getServiceTime() - 1);
                        waitingPeriod.addAndGet(-1);
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Server thread was interrupted.");
            }
        }
    }

    public void resetServer() {
        clients.clear();
        nbClientsAtServer.set(0);
        nbClientsCurrentlyAtServer.set(0);
        totalWaitingTime.set(0);
        waitingPeriod.set(0);
        simulationEnded.set(false);
    }

    public void endSimulation() { simulationEnded.set(true); }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public AtomicInteger getTotalWaitingTime() { return totalWaitingTime; }

    public AtomicInteger getNbClientsAtServer() { return nbClientsAtServer; }

    public AtomicInteger getNbClientsCurrentlyAtServer() { return nbClientsCurrentlyAtServer; }

    public AtomicBoolean getSimulationEnded() { return simulationEnded; }

    public void setSimulationEnded(boolean simulationEnded) { this.simulationEnded.set(simulationEnded); }
}
