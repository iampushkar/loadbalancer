package src.main.java.strategy;

import src.main.java.dto.Server;

import java.util.List;

public class RoundRobinStrategy implements LoadBalancingStrategy {

    private int index = 0;

    @Override
    public Server getNextServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new RuntimeException("Servers are not available");
        }

        synchronized (this) {
            Server selectedServer = servers.get(index);
            index = (index + 1) % servers.size();
            return selectedServer;
        }
    }
}
