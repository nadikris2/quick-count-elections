package interfaces;

import java.util.List;
import models.Pharapan;

public interface PharapanInterface {
    public boolean insert(Pharapan pharapan);
    public boolean update(Pharapan pharapan);
    public boolean delete(Pharapan pharapan);

    public List<Pharapan> getAllPharapan();
    public Pharapan getByTPS(String TPS);
}
