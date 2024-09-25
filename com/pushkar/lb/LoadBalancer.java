package com.pushkar.lb;

import com.pushkar.lb.dto.Server;
import com.pushkar.lb.strategy.LoadBalancingStrategy;

import java.util.ArrayList;
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
}
