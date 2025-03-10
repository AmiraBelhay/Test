package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {

    private final String url ="jdbc:mysql://localhost:5000/pi_dev";
    private final String username = "root";
    private final String pwd = "Sonya123*";

    private Connection con;

    public static MyDatabase instance;

    private MyDatabase(){
        try {
            con = DriverManager.getConnection(url,username,pwd);
            System.out.println("connected!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static MyDatabase getInstance(){
        if (instance == null)
            instance = new MyDatabase();
        return instance;
    }


    public Connection getCon() {
        return con;
    }
}
