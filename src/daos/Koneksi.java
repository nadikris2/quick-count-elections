package daos;

import java.sql.*;
import javax.swing.JOptionPane;

public class Koneksi {
    private static Connection c;
    private static String URL = "jdbc:mysql://localhost:3306/kpu";
    private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    public static Connection openConnection() {
        if (c == null) {
            try {
                Class.forName(DRIVERNAME);
                c = DriverManager.getConnection(URL, USERNAME, null);
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Cannot connect to database!\n" + sqle);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "No Driver found!\n" + ex);
            }
        }
        return c;
    }

    public static Connection closeConnection() {
        if (c != null) {
            try {
                c.close();
                c = null;
            } catch (SQLException sqle) {
                System.out.println("Error: " + sqle);
            }
        }

        return c;
    }

    public static void main(String[] args) {
        new Koneksi();
    }
}
