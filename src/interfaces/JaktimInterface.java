package interfaces;

import java.util.List;
import models.Jaktim;

public interface JaktimInterface {
    public boolean insert(Jaktim jaktim);
    public boolean update(Jaktim jaktim);
    public boolean delete(Jaktim jaktim);

    public List<Jaktim> getAllJaktim();
    public Jaktim getByKecamatan(String kecamatan);
}
