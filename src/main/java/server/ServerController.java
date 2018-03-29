package main.java.server;

public class ServerController implements ServerControllerMBean {
    private final ServerUserRegistrable serverUserRegistrable;

    public ServerController(ServerUserRegistrable serverUserRegistrable) {
        this.serverUserRegistrable = serverUserRegistrable;
    }

    @Override
    public int getUsersLimit() {
        return serverUserRegistrable.getUsersLimit();
    }

    @Override
    public int getUsers() {
        return serverUserRegistrable.getUsersCount();
    }

    @Override
    public void setUsersLimit(int limit) {
        serverUserRegistrable.getUsersLimit(limit);
    }
}
