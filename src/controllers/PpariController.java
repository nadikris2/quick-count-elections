package controllers;

import java.util.List;
import java.util.Observable;
import daos.PpariDAO;
import models.Ppari;
import interfaces.PpariInterface;
import models.OperasiCRUD;

public class PpariController extends Observable{
    private PpariInterface dao = new PpariDAO();
    private OperasiCRUD crud;
    
    public void setDAO(PpariInterface ppari){
        dao = ppari;
    }
    
    public void setDml(Ppari ppari, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(ppari); 
                break;
            case UPDATE: hasil = dao.update(ppari); 
                break;
            case DELETE: hasil = dao.delete(ppari); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(ppari);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Ppari> getAllPpari(){
        return dao.getAllPpari();
    }
    
    public Ppari getByTPS(String TPS){
        return dao.getByTPS(TPS);
    }
}
