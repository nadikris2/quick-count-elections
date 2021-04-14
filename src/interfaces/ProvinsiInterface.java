package interfaces;

import java.util.List;
import models.Provinsi;

public interface ProvinsiInterface {
    public boolean insert(Provinsi provinsi);
    public boolean update(Provinsi provinsi);
    public boolean delete(Provinsi provinsi);

    public List<Provinsi> getAllProvinsi();
    public Provinsi getByKota(String kota);
}
