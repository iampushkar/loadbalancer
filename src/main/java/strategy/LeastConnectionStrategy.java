package src.main.java.strategy;

import src.main.java.dto.Server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastConnectionStrategy implements LoadBalancingStrategy {
    private final Map<String, Integer> serverConnectionsMap = new HashMap<>();

    public LeastConnectionStrategy(List<Server> servers) {
        servers.forEach(server -> serverConnectionsMap.put(server.getId(), 0));
    }

    @Override
    public Server getNextServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new RuntimeException("Servers are not available");
        }

        int leastConnections = Integer.MAX_VALUE;
        Server selectedServer = null;
        for(Server server: servers ) {
            if (!server.isHealthy()) {
                continue;
            }

            int currentConnections = serverConnectionsMap.getOrDefault(server.getId(), 0);
            if (currentConnections < leastConnections) {
                leastConnections = currentConnections;
                selectedServer = server;
            }
        }

        if (selectedServer == null) {
            throw new RuntimeException("Selected Server is not available");
        }

        // Increasing connection count for selected server
        serverConnectionsMap.put(selectedServer.getId(), serverConnectionsMap.get(selectedServer.getId()) + 1);

        return selectedServer;
    }
}
