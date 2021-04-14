package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Ptidung;
import interfaces.PtidungInterface;

public class PtidungDAO implements PtidungInterface {
    @Override
    public boolean insert(Ptidung ptidung) {
        String sql = "INSERT INTO ptidung VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, ptidung.getTPS());
            statement.setInt(2, ptidung.getCapres1());
            statement.setInt(3, ptidung.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ptidung.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Ptidung ptidung) {
        String sql = "UPDATE ptidung SET ptidung.capres1 = ?, ptidung.capres2 = ? WHERE ptidung.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, ptidung.getCapres1());
            statement.setInt(2, ptidung.getCapres2());
            statement.setString(3, ptidung.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ptidung.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Ptidung ptidung) {
        String sql = "DELETE FROM ptidung WHERE ptidung.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, ptidung.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ptidung.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Ptidung> getAllPtidung() {
        List<Ptidung> ptidungList = new ArrayList<Ptidung>();
        String sql = "SELECT * FROM ptidung";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ptidung ptidung = new Ptidung(rs.getString(1), rs.getInt(2), rs.getInt(3));
                ptidungList.add(ptidung);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Ptidung.class.getName()).log(Level.SEVERE, null, e);
        }
        return ptidungList;
    }

    @Override
    public Ptidung getByTPS(String TPS) {
        Ptidung ptidung = null;
        String sql = "SELECT * FROM ptidung WHERE ptidung.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, TPS);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                ptidung = new Ptidung(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Ptidung.class.getName()).log(Level.SEVERE, null, e);
        }
        return ptidung;
    }
}

