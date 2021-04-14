package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Jakut;
import interfaces.JakutInterface;

public class JakutDAO implements JakutInterface {
    @Override
    public boolean insert(Jakut jakut) {
        String sql = "INSERT INTO jakut VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jakut.getKecamatan());
            statement.setInt(2, jakut.getCapres1());
            statement.setInt(3, jakut.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakut.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Jakut jakut) {
        String sql = "UPDATE jakut SET jakut.capres1 = ?, jakut.capres2 = ? WHERE jakut.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, jakut.getCapres1());
            statement.setInt(2, jakut.getCapres2());
            statement.setString(3, jakut.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakut.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Jakut jakut) {
        String sql = "DELETE FROM jakut WHERE jakut.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jakut.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jakut.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Jakut> getAllJakut() {
        List<Jakut> jakutList = new ArrayList<Jakut>();
        String sql = "SELECT * FROM jakut";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jakut jakut = new Jakut(rs.getString(1), rs.getInt(2), rs.getInt(3));
                jakutList.add(jakut);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Jakut.class.getName()).log(Level.SEVERE, null, e);
        }
        return jakutList;
    }

    @Override
    public Jakut getByKecamatan(String kecamatan) {
        Jakut jakut = null;
        String sql = "SELECT * FROM jakut WHERE jakut.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kecamatan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                jakut = new Jakut(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Jakut.class.getName()).log(Level.SEVERE, null, e);
        }
        return jakut;
    }
}

