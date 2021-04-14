package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Kpseribu;
import interfaces.KpseribuInterface;

public class KpseribuDAO implements KpseribuInterface {
    @Override
    public boolean insert(Kpseribu kpseribu) {
        String sql = "INSERT INTO kpseribu VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kpseribu.getKecamatan());
            statement.setInt(2, kpseribu.getCapres1());
            statement.setInt(3, kpseribu.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpseribu.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Kpseribu kpseribu) {
        String sql = "UPDATE kpseribu SET kpseribu.capres1 = ?, kpseribu.capres2 = ? WHERE kpseribu.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, kpseribu.getCapres1());
            statement.setInt(2, kpseribu.getCapres2());
            statement.setString(3, kpseribu.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpseribu.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Kpseribu kpseribu) {
        String sql = "DELETE FROM kpseribu WHERE kpseribu.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kpseribu.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpseribu.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Kpseribu> getAllKpseribu() {
        List<Kpseribu> kpseribuList = new ArrayList<Kpseribu>();
        String sql = "SELECT * FROM kpseribu";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Kpseribu kpseribu = new Kpseribu(rs.getString(1), rs.getInt(2), rs.getInt(3));
                kpseribuList.add(kpseribu);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Kpseribu.class.getName()).log(Level.SEVERE, null, e);
        }
        return kpseribuList;
    }

    @Override
    public Kpseribu getByKecamatan(String kecamatan) {
        Kpseribu kpseribu = null;
        String sql = "SELECT * FROM kpseribu WHERE kpseribu.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kecamatan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                kpseribu = new Kpseribu(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Kpseribu.class.getName()).log(Level.SEVERE, null, e);
        }
        return kpseribu;
    }
}

