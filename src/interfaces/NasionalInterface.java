package interfaces;

import java.util.List;
import models.Nasional;

public interface NasionalInterface {
    public boolean insert(Nasional nasional);
    public boolean update(Nasional nasional);
    public boolean delete(Nasional nasional);

    public List<Nasional> getAllNasional();
    public Nasional getByProvinsi(String provinsi);
}
