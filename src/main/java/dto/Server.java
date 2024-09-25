package src.main.java.dto;

public class Server {
    private String id;
    private String address;
    private boolean healthy;

    public Server(String id, String address, boolean healthy) {
        this.id = id;
        this.address = address;
        this.healthy = healthy;
    }

    public String getId() {
        return id;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public String getAddress() {
        return address;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }
}
