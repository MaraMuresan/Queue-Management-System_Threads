package org.example.logic;
import org.example.model.Server;
import org.example.model.Client;

import java.util.List;

public interface Strategy {
    public void addClient(List<Server> servers, Client client);
}
