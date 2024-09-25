package src.main.java.strategy;

import src.main.java.dto.Server;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements LoadBalancingStrategy {
    private final Random random = new Random();

    @Override
    public Server getNextServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new RuntimeException("Servers are not available");
        }

        return servers.get(random.nextInt(servers.size()));
    }
}
