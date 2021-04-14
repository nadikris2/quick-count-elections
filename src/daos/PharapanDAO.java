package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Pharapan;
import interfaces.PharapanInterface;

public class PharapanDAO implements PharapanInterface {
    @Override
    public boolean insert(Pharapan pharapan) {
        String sql = "INSERT INTO pharapan VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, pharapan.getTPS());
            statement.setInt(2, pharapan.getCapres1());
            statement.setInt(3, pharapan.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Pharapan.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Pharapan pharapan) {
        String sql = "UPDATE pharapan SET pharapan.capres1 = ?, pharapan.capres2 = ? WHERE pharapan.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, pharapan.getCapres1());
            statement.setInt(2, pharapan.getCapres2());
            statement.setString(3, pharapan.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Pharapan.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Pharapan pharapan) {
        String sql = "DELETE FROM pharapan WHERE pharapan.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, pharapan.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Pharapan.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Pharapan> getAllPharapan() {
        List<Pharapan> pharapanList = new ArrayList<Pharapan>();
        String sql = "SELECT * FROM pharapan";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Pharapan pharapan = new Pharapan(rs.getString(1), rs.getInt(2), rs.getInt(3));
                pharapanList.add(pharapan);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Pharapan.class.getName()).log(Level.SEVERE, null, e);
        }
        return pharapanList;
    }

    @Override
    public Pharapan getByTPS(String TPS) {
        Pharapan pharapan = null;
        String sql = "SELECT * FROM pharapan WHERE pharapan.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, TPS);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                pharapan = new Pharapan(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Pharapan.class.getName()).log(Level.SEVERE, null, e);
        }
        return pharapan;
    }
}

