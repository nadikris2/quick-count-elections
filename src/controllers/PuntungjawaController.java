package controllers;

import java.util.List;
import java.util.Observable;
import daos.PuntungjawaDAO;
import models.Puntungjawa;
import interfaces.PuntungjawaInterface;
import models.OperasiCRUD;

public class PuntungjawaController extends Observable{
    private PuntungjawaInterface dao = new PuntungjawaDAO();
    private OperasiCRUD crud;
    
    public void setDAO(PuntungjawaInterface puntungjawa){
        dao = puntungjawa;
    }
    
    public void setDml(Puntungjawa puntungjawa, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(puntungjawa); 
                break;
            case UPDATE: hasil = dao.update(puntungjawa); 
                break;
            case DELETE: hasil = dao.delete(puntungjawa); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(puntungjawa);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Puntungjawa> getAllPuntungjawa(){
        return dao.getAllPuntungjawa();
    }
    
    public Puntungjawa getByTPS(String TPS){
        return dao.getByTPS(TPS);
    }
}
