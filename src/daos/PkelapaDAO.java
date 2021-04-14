package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Pkelapa;
import interfaces.PkelapaInterface;

public class PkelapaDAO implements PkelapaInterface {
    @Override
    public boolean insert(Pkelapa pkelapa) {
        String sql = "INSERT INTO pkelapa VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, pkelapa.getTPS());
            statement.setInt(2, pkelapa.getCapres1());
            statement.setInt(3, pkelapa.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Pkelapa.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Pkelapa pkelapa) {
        String sql = "UPDATE pkelapa SET pkelapa.capres1 = ?, pkelapa.capres2 = ? WHERE pkelapa.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, pkelapa.getCapres1());
            statement.setInt(2, pkelapa.getCapres2());
            statement.setString(3, pkelapa.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Pkelapa.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Pkelapa pkelapa) {
        String sql = "DELETE FROM pkelapa WHERE pkelapa.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, pkelapa.getTPS());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Pkelapa.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Pkelapa> getAllPkelapa() {
        List<Pkelapa> pkelapaList = new ArrayList<Pkelapa>();
        String sql = "SELECT * FROM pkelapa";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Pkelapa pkelapa = new Pkelapa(rs.getString(1), rs.getInt(2), rs.getInt(3));
                pkelapaList.add(pkelapa);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Pkelapa.class.getName()).log(Level.SEVERE, null, e);
        }
        return pkelapaList;
    }

    @Override
    public Pkelapa getByTPS(String TPS) {
        Pkelapa pkelapa = null;
        String sql = "SELECT * FROM pkelapa WHERE pkelapa.TPS = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, TPS);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                pkelapa = new Pkelapa(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Pkelapa.class.getName()).log(Level.SEVERE, null, e);
        }
        return pkelapa;
    }
}

