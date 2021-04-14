package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Login;
import interfaces.LoginInterface;

public class LoginDAO implements LoginInterface {
    @Override
    public boolean insert(Login login) {
        String sql = "INSERT INTO login VALUES(?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, login.getUsername());
            statement.setString(2, login.getPassword());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Login login) {
        String sql = "UPDATE login SET login.password = ? WHERE login.username = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, login.getPassword());
            statement.setString(2, login.getUsername());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Login login) {
        String sql = "DELETE FROM login WHERE login.username = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, login.getUsername());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Login> getAllLogin() {
        List<Login> loginList = new ArrayList<Login>();
        String sql = "SELECT * FROM login";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Login login = new Login(rs.getString(1), rs.getString(2));
                loginList.add(login);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        return loginList;
    }

    @Override
    public Login getByUsername(String username) {
        Login login = null;
        String sql = "SELECT * FROM login WHERE login.username = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                login = new Login(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        return login;
    }
}

