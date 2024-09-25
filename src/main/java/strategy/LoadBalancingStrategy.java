package src.main.java.strategy;

import src.main.java.dto.Server;

import java.util.List;

/**
 * Interface for different load balancing strategies
 */
public interface LoadBalancingStrategy {
    Server getNextServer(List<Server> servers);
}
