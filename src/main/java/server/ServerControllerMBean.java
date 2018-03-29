package main.java.server;


@SuppressWarnings("UnusedDeclaration")
public interface ServerControllerMBean {
    public int getUsersLimit();

    public int getUsers();

    public void setUsersLimit(int limit);
}
