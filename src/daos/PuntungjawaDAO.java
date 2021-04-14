package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Puntungjawa;
import interfaces.PuntungjawaInterface;

public class PuntungjawaDAO implements PuntungjawaInterface {
    @Override
    public boolean insert(Puntungjawa puntungjawa) {
        String sql = "INSERT INTO puntungjawa VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, puntungjawa.getTPS());
            statement.setInt(2, puntungjawa.getCapres1());
            statement.setInt(3, puntungjawa.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Puntungjawa.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Puntungjawa puntungjawa) {
        String sql = "UPDATE puntungjawa SET puntungjawa.capres1 = ?, puntungjawa.capres2 = ? WHERE puntungjawa.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, puntungjawa.getCapres1());
            statement.setInt(2, puntungjawa.getCapres2());
            statement.setString(3, puntungjawa.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Puntungjawa.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Puntungjawa puntungjawa) {
        String sql = "DELETE FROM puntungjawa WHERE puntungjawa.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, puntungjawa.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Puntungjawa.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Puntungjawa> getAllPuntungjawa() {
        List<Puntungjawa> puntungjawaList = new ArrayList<Puntungjawa>();
        String sql = "SELECT * FROM puntungjawa";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Puntungjawa puntungjawa = new Puntungjawa(rs.getString(1), rs.getInt(2), rs.getInt(3));
                puntungjawaList.add(puntungjawa);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Puntungjawa.class.getName()).log(Level.SEVERE, null, e);
        }
        return puntungjawaList;
    }

    @Override
    public Puntungjawa getByTPS(String TPS) {
        Puntungjawa puntungjawa = null;
        String sql = "SELECT * FROM puntungjawa WHERE puntungjawa.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, TPS);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                puntungjawa = new Puntungjawa(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Puntungjawa.class.getName()).log(Level.SEVERE, null, e);
        }
        return puntungjawa;
    }
}

