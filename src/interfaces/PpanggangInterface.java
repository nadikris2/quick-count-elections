package interfaces;

import java.util.List;
import models.Ppanggang;

public interface PpanggangInterface {
    public boolean insert(Ppanggang ppanggang);
    public boolean update(Ppanggang ppanggang);
    public boolean delete(Ppanggang ppanggang);

    public List<Ppanggang> getAllPpanggang();
    public Ppanggang getByTPS(String TPS);
}
