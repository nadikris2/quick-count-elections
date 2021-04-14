package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Jaksel;
import interfaces.JakselInterface;

public class JakselDAO implements JakselInterface {
    @Override
    public boolean insert(Jaksel jaksel) {
        String sql = "INSERT INTO jaksel VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jaksel.getKecamatan());
            statement.setInt(2, jaksel.getCapres1());
            statement.setInt(3, jaksel.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jaksel.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Jaksel jaksel) {
        String sql = "UPDATE jaksel SET jaksel.capres1 = ?, jaksel.capres2 = ? WHERE jaksel.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, jaksel.getCapres1());
            statement.setInt(2, jaksel.getCapres2());
            statement.setString(3, jaksel.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jaksel.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Jaksel jaksel) {
        String sql = "DELETE FROM jaksel WHERE jaksel.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jaksel.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jaksel.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Jaksel> getAllJaksel() {
        List<Jaksel> jakselList = new ArrayList<Jaksel>();
        String sql = "SELECT * FROM jaksel";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jaksel jaksel = new Jaksel(rs.getString(1), rs.getInt(2), rs.getInt(3));
                jakselList.add(jaksel);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Jaksel.class.getName()).log(Level.SEVERE, null, e);
        }
        return jakselList;
    }

    @Override
    public Jaksel getByKecamatan(String kecamatan) {
        Jaksel jaksel = null;
        String sql = "SELECT * FROM jaksel WHERE jaksel.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kecamatan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                jaksel = new Jaksel(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Jaksel.class.getName()).log(Level.SEVERE, null, e);
        }
        return jaksel;
    }
}

