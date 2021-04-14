package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Kpsersel;
import interfaces.KpserselInterface;

public class KpserselDAO implements KpserselInterface {
    @Override
    public boolean insert(Kpsersel kpsersel) {
        String sql = "INSERT INTO kpsersel VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kpsersel.getKelurahan());
            statement.setInt(2, kpsersel.getCapres1());
            statement.setInt(3, kpsersel.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpsersel.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Kpsersel kpsersel) {
        String sql = "UPDATE kpsersel SET kpsersel.capres1 = ?, kpsersel.capres2 = ? WHERE kpsersel.kelurahan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, kpsersel.getCapres1());
            statement.setInt(2, kpsersel.getCapres2());
            statement.setString(3, kpsersel.getKelurahan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpsersel.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Kpsersel kpsersel) {
        String sql = "DELETE FROM kpsersel WHERE kpsersel.kelurahan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kpsersel.getKelurahan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Kpsersel.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Kpsersel> getAllKpsersel() {
        List<Kpsersel> kpserselList = new ArrayList<Kpsersel>();
        String sql = "SELECT * FROM kpsersel";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Kpsersel kpsersel = new Kpsersel(rs.getString(1), rs.getInt(2), rs.getInt(3));
                kpserselList.add(kpsersel);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Kpsersel.class.getName()).log(Level.SEVERE, null, e);
        }
        return kpserselList;
    }

    @Override
    public Kpsersel getByKelurahan(String kelurahan) {
        Kpsersel kpsersel = null;
        String sql = "SELECT * FROM kpsersel WHERE kpsersel.kelurahan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kelurahan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                kpsersel = new Kpsersel(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Kpsersel.class.getName()).log(Level.SEVERE, null, e);
        }
        return kpsersel;
    }
}

