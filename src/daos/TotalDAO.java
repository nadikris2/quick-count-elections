package daos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Total;
import interfaces.TotalInterface;

public class TotalDAO implements TotalInterface {
    @Override
    public boolean insert(Total total) {
        String sql = "INSERT INTO total VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, total.getId());
            statement.setInt(2, total.getCapres1());
            statement.setInt(3, total.getCapres2());
            
            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Total.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean update(Total total) {
        String sql = "UPDATE total SET total.capres1 = ?, total.capres2 = ? WHERE total.id = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, total.getCapres1());
            statement.setInt(2, total.getCapres2());
            statement.setInt(3, total.getId());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Total.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Total total) {
        String sql = "DELETE FROM total WHERE total.id = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, total.getId());

            int row = statement.executeUpdate();
            statement.close();
            
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(Total.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public List<Total> getAllTotal() {
        List<Total> totalList = new ArrayList<Total>();
        String sql = "SELECT * FROM total";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Total total = new Total(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                totalList.add(total);
            }
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(Total.class.getName()).log(Level.SEVERE, null, e);
        }
        return totalList;
    }

    @Override
    public Total getById(int id) {
        Total total = null;
        String sql = "SELECT * FROM total WHERE total.id = ?";
        try {
            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                total = new Total(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (Exception e) {
            Logger.getLogger(Total.class.getName()).log(Level.SEVERE, null, e);
        }
        return total;
    }
}

