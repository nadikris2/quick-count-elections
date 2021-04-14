package interfaces;

import java.util.List;
import models.Ptidung;

public interface PtidungInterface {
    public boolean insert(Ptidung ptidung);
    public boolean update(Ptidung ptidung);
    public boolean delete(Ptidung ptidung);

    public List<Ptidung> getAllPtidung();
    public Ptidung getByTPS(String TPS);
}
