package main.java.server;

public class ServerUserRegistr implements ServerUserRegistrable {

    private int usersCount;
    private int usersLimit;

    public ServerUserRegistr(int usersLimit) {
        this.usersCount = 0;
        this.usersLimit = usersLimit;
    }

    @Override
    public void addUser() {
        usersCount += 1;
    }


    @Override
    public void removeUser() {
        usersCount -= 1;

    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void getUsersLimit(int limit) {
        this.usersLimit = usersLimit;
    }
}
