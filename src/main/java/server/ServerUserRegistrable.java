package main.java.server;

public interface ServerUserRegistrable {
    void addUser();

    void removeUser();

    int getUsersCount();

    int getUsersLimit();

    void getUsersLimit(int limit);

}
