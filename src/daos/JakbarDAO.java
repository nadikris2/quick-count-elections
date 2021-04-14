package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Jakbar;
import interfaces.JakbarInterface;

public class JakbarDAO implements JakbarInterface {
    @Override
    public boolean insert(Jakbar jakbar) {
        String sql = "INSERT INTO jakbar VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jakbar.getKecamatan());
            statement.setInt(2, jakbar.getCapres1());
            statement.setInt(3, jakbar.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakbar.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Jakbar jakbar) {
        String sql = "UPDATE jakbar SET jakbar.capres1 = ?, jakbar.capres2 = ? WHERE jakbar.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, jakbar.getCapres1());
            statement.setInt(2, jakbar.getCapres2());
            statement.setString(3, jakbar.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakbar.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Jakbar jakbar) {
        String sql = "DELETE FROM jakbar WHERE jakbar.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jakbar.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakbar.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Jakbar> getAllJakbar() {
        List<Jakbar> jakbarList = new ArrayList<Jakbar>();
        String sql = "SELECT * FROM jakbar";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jakbar jakbar = new Jakbar(rs.getString(1), rs.getInt(2), rs.getInt(3));
                jakbarList.add(jakbar);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Jakbar.class.getName()).log(Level.SEVERE, null, e);
        }
        return jakbarList;
    }

    @Override
    public Jakbar getByKecamatan(String kecamatan) {
        Jakbar jakbar = null;
        String sql = "SELECT * FROM jakbar WHERE jakbar.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kecamatan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                jakbar = new Jakbar(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Jakbar.class.getName()).log(Level.SEVERE, null, e);
        }
        return jakbar;
    }
}

