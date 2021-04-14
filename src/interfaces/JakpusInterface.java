package interfaces;

import java.util.List;
import models.Jakpus;

public interface JakpusInterface {
    public boolean insert(Jakpus jakpus);
    public boolean update(Jakpus jakpus);
    public boolean delete(Jakpus jakpus);

    public List<Jakpus> getAllJakpus();
    public Jakpus getByKecamatan(String kecamatan);
}
