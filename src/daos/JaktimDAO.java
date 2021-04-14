package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Jaktim;
import interfaces.JaktimInterface;

public class JaktimDAO implements JaktimInterface {
    @Override
    public boolean insert(Jaktim jaktim) {
        String sql = "INSERT INTO jaktim VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jaktim.getKecamatan());
            statement.setInt(2, jaktim.getCapres1());
            statement.setInt(3, jaktim.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jaktim.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Jaktim jaktim) {
        String sql = "UPDATE jaktim SET jaktim.capres1 = ?, jaktim.capres2 = ? WHERE jaktim.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, jaktim.getCapres1());
            statement.setInt(2, jaktim.getCapres2());
            statement.setString(3, jaktim.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jaktim.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Jaktim jaktim) {
        String sql = "DELETE FROM jaktim WHERE jaktim.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, jaktim.getKecamatan());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Jaktim.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Jaktim> getAllJaktim() {
        List<Jaktim> jaktimList = new ArrayList<Jaktim>();
        String sql = "SELECT * FROM jaktim";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jaktim jaktim = new Jaktim(rs.getString(1), rs.getInt(2), rs.getInt(3));
                jaktimList.add(jaktim);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Jaktim.class.getName()).log(Level.SEVERE, null, e);
        }
        return jaktimList;
    }

    @Override
    public Jaktim getByKecamatan(String kecamatan) {
        Jaktim jaktim = null;
        String sql = "SELECT * FROM jaktim WHERE jaktim.kecamatan = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setString(1, kecamatan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                jaktim = new Jaktim(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Jaktim.class.getName()).log(Level.SEVERE, null, e);
        }
        return jaktim;
    }
}

