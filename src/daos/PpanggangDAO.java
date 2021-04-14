package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Ppanggang;
import interfaces.PpanggangInterface;

public class PpanggangDAO implements PpanggangInterface {
    @Override
    public boolean insert(Ppanggang ppanggang) {
        String sql = "INSERT INTO ppanggang VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, ppanggang.getTPS());
            statement.setInt(2, ppanggang.getCapres1());
            statement.setInt(3, ppanggang.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ppanggang.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Ppanggang ppanggang) {
        String sql = "UPDATE ppanggang SET ppanggang.capres1 = ?, ppanggang.capres2 = ? WHERE ppanggang.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, ppanggang.getCapres1());
            statement.setInt(2, ppanggang.getCapres2());
            statement.setString(3, ppanggang.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ppanggang.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Ppanggang ppanggang) {
        String sql = "DELETE FROM ppanggang WHERE ppanggang.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, ppanggang.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ppanggang.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Ppanggang> getAllPpanggang() {
        List<Ppanggang> ppanggangList = new ArrayList<Ppanggang>();
        String sql = "SELECT * FROM ppanggang";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ppanggang ppanggang = new Ppanggang(rs.getString(1), rs.getInt(2), rs.getInt(3));
                ppanggangList.add(ppanggang);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Ppanggang.class.getName()).log(Level.SEVERE, null, e);
        }
        return ppanggangList;
    }

    @Override
    public Ppanggang getByTPS(String TPS) {
        Ppanggang ppanggang = null;
        String sql = "SELECT * FROM ppanggang WHERE ppanggang.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, TPS);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                ppanggang = new Ppanggang(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Ppanggang.class.getName()).log(Level.SEVERE, null, e);
        }
        return ppanggang;
    }
}

