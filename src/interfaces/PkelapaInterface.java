package interfaces;

import java.util.List;
import models.Pkelapa;

public interface PkelapaInterface {
    public boolean insert(Pkelapa pkelapa);
    public boolean update(Pkelapa pkelapa);
    public boolean delete(Pkelapa pkelapa);

    public List<Pkelapa> getAllPkelapa();
    public Pkelapa getByTPS(String TPS);
}
