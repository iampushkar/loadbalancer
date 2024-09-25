package com.pushkar.lb.strategy;

import com.pushkar.lb.dto.Server;

import java.util.List;

/**
 * Interface for different load balancing strategies
 */
public interface LoadBalancingStrategy {
    Server getNextServer(List<Server> servers);
}
