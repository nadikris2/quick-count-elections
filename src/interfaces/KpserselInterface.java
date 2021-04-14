package interfaces;

import java.util.List;
import models.Kpsersel;

public interface KpserselInterface {
    public boolean insert(Kpsersel kpsersel);
    public boolean update(Kpsersel kpsersel);
    public boolean delete(Kpsersel kpsersel);

    public List<Kpsersel> getAllKpsersel();
    public Kpsersel getByKelurahan(String kelurahan);
}
