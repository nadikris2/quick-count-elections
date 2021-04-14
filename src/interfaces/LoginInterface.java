package interfaces;

import java.util.List;
import models.Login;

public interface LoginInterface {
    public boolean insert(Login login);
    public boolean update(Login login);
    public boolean delete(Login login);

    public List<Login> getAllLogin();
    public Login getByUsername(String username);
}
