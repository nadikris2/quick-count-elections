package interfaces;

import java.util.List;
import models.Ppari;

public interface PpariInterface {
    public boolean insert(Ppari ppari);
    public boolean update(Ppari ppari);
    public boolean delete(Ppari ppari);

    public List<Ppari> getAllPpari();
    public Ppari getByTPS(String TPS);
}
