package src.main.java;

import src.main.java.dto.Server;
import src.main.java.strategy.LeastConnectionStrategy;
import src.main.java.strategy.LoadBalancingStrategy;
import src.main.java.strategy.RandomStrategy;
import src.main.java.strategy.RoundRobinStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("LLD of Load Balancer");

        Server serverOne = new Server("1", "http://server1.com", true);
        Server serverTwo = new Server("2", "http://server2.com", true);
        Server serverThree = new Server("3", "http://server3.com", true);
        List<Server> servers = List.of(serverOne, serverTwo, serverThree);

        LoadBalancer loadBalancer = new LoadBalancer(servers);

        routeRequestToLB(loadBalancer, new RandomStrategy());
        routeRequestToLB(loadBalancer, new RoundRobinStrategy());
        routeRequestToLB(loadBalancer, new LeastConnectionStrategy(servers));
    }

    private static void routeRequestToLB(LoadBalancer loadBalancer, LoadBalancingStrategy strategy) {
        loadBalancer.setStrategy(strategy);
        var strategyName = strategy.getClass().getName().substring(24);
        System.out.println("Using Strategy : " + strategyName);
        for (int request = 1; request < 5; request++) {
            Server nextServer = loadBalancer.getNextServer();
            System.out.println("Request " + request + " is sent to " + nextServer.getAddress());
        }
    }
}
