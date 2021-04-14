package interfaces;

import java.util.List;
import models.Kpserut;

public interface KpserutInterface {
    public boolean insert(Kpserut kpserut);
    public boolean update(Kpserut kpserut);
    public boolean delete(Kpserut kpserut);

    public List<Kpserut> getAllKpserut();
    public Kpserut getByKelurahan(String kelurahan);
}
