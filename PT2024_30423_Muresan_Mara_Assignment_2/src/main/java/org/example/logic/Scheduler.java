package org.example.logic;
import org.example.model.Server;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private Strategy strategy;

    public Scheduler(int nbServers, SelectionPolicy policy) {
        servers = new ArrayList<>();
        for(int i = 0 ; i < nbServers; i++) {
            Server server = new Server();
            servers.add(server);
            new Thread(server).start();
        }
        changeStrategy(policy);
    }

    public void changeStrategy(SelectionPolicy policy) {
        if(policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchClient(Client client) {
        strategy.addClient(servers, client);
    }

    public void resetAllServers() {
        for (Server server : servers) {
            server.resetServer();
        }
    }


    public List<Server> getServers()  {
        return servers;
    }

}
