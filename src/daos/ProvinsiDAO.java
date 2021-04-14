package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Provinsi;
import interfaces.ProvinsiInterface;

public class ProvinsiDAO implements ProvinsiInterface {
    @Override
    public boolean insert(Provinsi provinsi) {
        String sql = "INSERT INTO provinsi VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, provinsi.getKota());
            statement.setInt(2, provinsi.getCapres1());
            statement.setInt(3, provinsi.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Provinsi.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Provinsi provinsi) {
        String sql = "UPDATE provinsi SET provinsi.capres1 = ?, provinsi.capres2 = ? WHERE provinsi.kota = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, provinsi.getCapres1());
            statement.setInt(2, provinsi.getCapres2());
            statement.setString(3, provinsi.getKota());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Provinsi.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Provinsi provinsi) {
        String sql = "DELETE FROM provinsi WHERE provinsi.kota = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, provinsi.getKota());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Provinsi.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Provinsi> getAllProvinsi() {
        List<Provinsi> provinsiList = new ArrayList<Provinsi>();
        String sql = "SELECT * FROM provinsi";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Provinsi provinsi = new Provinsi(rs.getString(1), rs.getInt(2), rs.getInt(3));
                provinsiList.add(provinsi);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Provinsi.class.getName()).log(Level.SEVERE, null, e);
        }
        return provinsiList;
    }

    @Override
    public Provinsi getByKota(String kota) {
        Provinsi provinsi = null;
        String sql = "SELECT * FROM provinsi WHERE provinsi.kota = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kota);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                provinsi = new Provinsi(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Provinsi.class.getName()).log(Level.SEVERE, null, e);
        }
        return provinsi;
    }
}

