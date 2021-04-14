package interfaces;

import java.util.List;
import models.Jakbar;

public interface JakbarInterface {
    public boolean insert(Jakbar jakbar);
    public boolean update(Jakbar jakbar);
    public boolean delete(Jakbar jakbar);

    public List<Jakbar> getAllJakbar();
    public Jakbar getByKecamatan(String kecamatan);
}
