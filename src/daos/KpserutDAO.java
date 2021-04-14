package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Kpserut;
import interfaces.KpserutInterface;

public class KpserutDAO implements KpserutInterface {
    @Override
    public boolean insert(Kpserut kpserut) {
        String sql = "INSERT INTO kpserut VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kpserut.getKelurahan());
            statement.setInt(2, kpserut.getCapres1());
            statement.setInt(3, kpserut.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpserut.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Kpserut kpserut) {
        String sql = "UPDATE kpserut SET kpserut.capres1 = ?, kpserut.capres2 = ? WHERE kpserut.kelurahan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, kpserut.getCapres1());
            statement.setInt(2, kpserut.getCapres2());
            statement.setString(3, kpserut.getKelurahan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpserut.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Kpserut kpserut) {
        String sql = "DELETE FROM kpserut WHERE kpserut.kelurahan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kpserut.getKelurahan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpserut.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Kpserut> getAllKpserut() {
        List<Kpserut> kpserutList = new ArrayList<Kpserut>();
        String sql = "SELECT * FROM kpserut";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Kpserut kpserut = new Kpserut(rs.getString(1), rs.getInt(2), rs.getInt(3));
                kpserutList.add(kpserut);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Kpserut.class.getName()).log(Level.SEVERE, null, e);
        }
        return kpserutList;
    }

    @Override
    public Kpserut getByKelurahan(String kelurahan) {
        Kpserut kpserut = null;
        String sql = "SELECT * FROM kpserut WHERE kpserut.kelurahan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kelurahan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                kpserut = new Kpserut(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Kpserut.class.getName()).log(Level.SEVERE, null, e);
        }
        return kpserut;
    }
}

