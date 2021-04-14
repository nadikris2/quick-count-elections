package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Jakpus;
import interfaces.JakpusInterface;

public class JakpusDAO implements JakpusInterface {
    @Override
    public boolean insert(Jakpus jakpus) {
        String sql = "INSERT INTO jakpus VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jakpus.getKecamatan());
            statement.setInt(2, jakpus.getCapres1());
            statement.setInt(3, jakpus.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakpus.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Jakpus jakpus) {
        String sql = "UPDATE jakpus SET jakpus.capres1 = ?, jakpus.capres2 = ? WHERE jakpus.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, jakpus.getCapres1());
            statement.setInt(2, jakpus.getCapres2());
            statement.setString(3, jakpus.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakpus.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Jakpus jakpus) {
        String sql = "DELETE FROM jakpus WHERE jakpus.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jakpus.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakpus.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Jakpus> getAllJakpus() {
        List<Jakpus> jakpusList = new ArrayList<Jakpus>();
        String sql = "SELECT * FROM jakpus";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jakpus jakpus = new Jakpus(rs.getString(1), rs.getInt(2), rs.getInt(3));
                jakpusList.add(jakpus);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Jakpus.class.getName()).log(Level.SEVERE, null, e);
        }
        return jakpusList;
    }

    @Override
    public Jakpus getByKecamatan(String kecamatan) {
        Jakpus jakpus = null;
        String sql = "SELECT * FROM jakpus WHERE jakpus.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kecamatan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                jakpus = new Jakpus(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Jakpus.class.getName()).log(Level.SEVERE, null, e);
        }
        return jakpus;
    }
}

