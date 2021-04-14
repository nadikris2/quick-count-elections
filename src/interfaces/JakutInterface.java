package interfaces;

import java.util.List;
import models.Jakut;

public interface JakutInterface {
    public boolean insert(Jakut jakut);
    public boolean update(Jakut jakut);
    public boolean delete(Jakut jakut);

    public List<Jakut> getAllJakut();
    public Jakut getByKecamatan(String kecamatan);
}
