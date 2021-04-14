package interfaces;

import java.util.List;
import models.Kpseribu;

public interface KpseribuInterface {
    public boolean insert(Kpseribu kpseribu);
    public boolean update(Kpseribu kpseribu);
    public boolean delete(Kpseribu kpseribu);

    public List<Kpseribu> getAllKpseribu();
    public Kpseribu getByKecamatan(String kecamatan);
}
