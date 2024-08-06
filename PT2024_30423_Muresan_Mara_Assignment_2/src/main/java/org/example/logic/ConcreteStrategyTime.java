package org.example.logic;
import org.example.model.Server;
import org.example.model.Client;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addClient(List<Server> servers, Client client) {
        Server bestServer = servers.get(0);
        for(Server server : servers) {
            if(server.getWaitingPeriod().get() < bestServer.getWaitingPeriod().get()) {
                bestServer = server;
            }
        }
        bestServer.addClient(client);
        if(bestServer.getSimulationEnded().get()) {
            bestServer.setSimulationEnded(false);
            Thread t = new Thread(bestServer);
            t.start();
        }
    }
}
