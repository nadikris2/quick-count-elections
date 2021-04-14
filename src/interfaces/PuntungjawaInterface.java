package interfaces;

import java.util.List;
import models.Puntungjawa;

public interface PuntungjawaInterface {
    public boolean insert(Puntungjawa puntungjawa);
    public boolean update(Puntungjawa puntungjawa);
    public boolean delete(Puntungjawa puntungjawa);

    public List<Puntungjawa> getAllPuntungjawa();
    public Puntungjawa getByTPS(String TPS);
}
