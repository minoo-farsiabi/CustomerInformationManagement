package util.old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by m.farsiabi on 6/26/2016.
 */
public class DBUtil {
    private static DBUtil ourInstance = new DBUtil();

    public static DBUtil getInstance() {
        return ourInstance;
    }

    private static final String driverDatabase = "com.mysql.cj.jdbc.Driver";
    private static final String connectionPrefix = "jdbc:mysql://localhost/schooldb?";
    private static final String databaseUsername = "root";
    private static final String databasePassword = "minmin";

    private Connection connect = null;
    private Statement statement = null;

    public Connection getConnect() {
        return connect;
    }

    public Statement getStatement() {
        return statement;
    }

    private DBUtil() {
        try {
            Class.forName(driverDatabase);
            connect = DriverManager.getConnection(connectionPrefix + "user=" + databaseUsername + "&password=" + databasePassword + "&useSSL=false&serverTimezone=UTC&useEncoding=true&characterEncoding=UTF-8&useUnicode=true");
            statement = connect.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}