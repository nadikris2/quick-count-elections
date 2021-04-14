package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Ppari;
import interfaces.PpariInterface;

public class PpariDAO implements PpariInterface {
    @Override
    public boolean insert(Ppari ppari) {
        String sql = "INSERT INTO ppari VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, ppari.getTPS());
            statement.setInt(2, ppari.getCapres1());
            statement.setInt(3, ppari.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ppari.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Ppari ppari) {
        String sql = "UPDATE ppari SET ppari.capres1 = ?, ppari.capres2 = ? WHERE ppari.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, ppari.getCapres1());
            statement.setInt(2, ppari.getCapres2());
            statement.setString(3, ppari.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ppari.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Ppari ppari) {
        String sql = "DELETE FROM ppari WHERE ppari.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, ppari.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Ppari.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Ppari> getAllPpari() {
        List<Ppari> ppariList = new ArrayList<Ppari>();
        String sql = "SELECT * FROM ppari";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ppari ppari = new Ppari(rs.getString(1), rs.getInt(2), rs.getInt(3));
                ppariList.add(ppari);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Ppari.class.getName()).log(Level.SEVERE, null, e);
        }
        return ppariList;
    }

    @Override
    public Ppari getByTPS(String TPS) {
        Ppari ppari = null;
        String sql = "SELECT * FROM ppari WHERE ppari.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, TPS);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                ppari = new Ppari(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Ppari.class.getName()).log(Level.SEVERE, null, e);
        }
        return ppari;
    }
}

