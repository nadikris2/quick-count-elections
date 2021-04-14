package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Nasional;
import interfaces.NasionalInterface;

public class NasionalDAO implements NasionalInterface {
    @Override
    public boolean insert(Nasional nasional) {
        String sql = "INSERT INTO nasional VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, nasional.getProvinsi());
            statement.setInt(2, nasional.getCapres1());
            statement.setInt(3, nasional.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Nasional.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Nasional nasional) {
        String sql = "UPDATE nasional SET nasional.capres1 = ?, nasional.capres2 = ? WHERE nasional.provinsi = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, nasional.getCapres1());
            statement.setInt(2, nasional.getCapres2());
            statement.setString(3, nasional.getProvinsi());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Nasional.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Nasional nasional) {
        String sql = "DELETE FROM nasional WHERE nasional.provinsi = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, nasional.getProvinsi());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Nasional.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Nasional> getAllNasional() {
        List<Nasional> nasionalList = new ArrayList<Nasional>();
        String sql = "SELECT * FROM nasional";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Nasional nasional = new Nasional(rs.getString(1), rs.getInt(2), rs.getInt(3));
                nasionalList.add(nasional);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Nasional.class.getName()).log(Level.SEVERE, null, e);
        }
        return nasionalList;
    }

    @Override
    public Nasional getByProvinsi(String provinsi) {
        Nasional nasional = null;
        String sql = "SELECT * FROM nasional WHERE nasional.provinsi = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, provinsi);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                nasional = new Nasional(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Nasional.class.getName()).log(Level.SEVERE, null, e);
        }
        return nasional;
    }
}

