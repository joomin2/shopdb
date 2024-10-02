package edu.sm.frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConnectionPool {

    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();//사용하고있는 ArrayList
    private static int INITIAL_POOL_SIZE = 3; //3개의 connection을 만들고,사용하고 반납하고를 반복.
    static ResourceBundle rb;
    static {
        rb = null;
        rb = ResourceBundle.getBundle("mysql", Locale.KOREA);//mysql이라는 이름의 properties파일 읽음.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("OK");
            System.out.println(rb.getString("url"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool create() throws SQLException {

        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);//create에 의해 connection 3개가 만들어짐.
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPool(pool);
    }

    public ConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Connection getConnection() {// connecton을 하나 꺼내서 return
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {//쓰고나서 반납.
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
    public int getUseSize() {
        return connectionPool.size();
    }

}