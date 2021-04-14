package interfaces;

import java.util.List;
import models.Total;

public interface TotalInterface {
    public boolean insert(Total total);
    public boolean update(Total total);
    public boolean delete(Total total);

    public List<Total> getAllTotal();
    public Total getById(int id);
}
