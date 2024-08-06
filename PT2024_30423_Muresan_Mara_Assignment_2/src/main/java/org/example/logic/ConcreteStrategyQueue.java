package org.example.logic;

import org.example.model.Client;
import org.example.model.Server;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addClient(List<Server> servers, Client client) {
        Server fewClientsServer = servers.get(0);
        for(Server server : servers) {
            if(server.getClients().size() < fewClientsServer.getClients().size()) {
                fewClientsServer = server;
            }
        }
        fewClientsServer.addClient(client);
        if(fewClientsServer.getSimulationEnded().get()) {
            fewClientsServer.setSimulationEnded(false);
            Thread t = new Thread(fewClientsServer);
            t.start();
        }
    }
}
