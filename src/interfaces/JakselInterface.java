package interfaces;

import java.util.List;
import models.Jaksel;

public interface JakselInterface {
    public boolean insert(Jaksel jaksel);
    public boolean update(Jaksel jaksel);
    public boolean delete(Jaksel jaksel);

    public List<Jaksel> getAllJaksel();
    public Jaksel getByKecamatan(String kecamatan);
}
