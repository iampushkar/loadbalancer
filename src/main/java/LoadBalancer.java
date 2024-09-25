package src.main.java;

import src.main.java.dto.Server;
import src.main.java.strategy.LoadBalancingStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoadBalancer {

    private List<Server> servers = new ArrayList<>();
    private LoadBalancingStrategy strategy;

    public LoadBalancer(List<Server> servers) {
        this.servers = servers;
    }

    public Server getNextServer() {
        return strategy.getNextServer(servers);
    }

    public void setStrategy(LoadBalancingStrategy strategy) {
        this.strategy = strategy;
    }

    public void addServerToLB(Server server) {
        servers.add(server);
    }

    public void removeServerFromLB(Server server) {
        servers.remove(server);
    }

    public void checkHealth() {
        servers.removeIf(server -> {
            if (!server.isHealthy()) {
                System.out.println("Removing Server " + server.getAddress() + " as it is unhealthy");
                return true;  // Remove this server
            }
            return false;  // Keep this server
        });
    }
}
