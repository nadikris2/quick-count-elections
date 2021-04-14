package controllers;

import java.util.List;
import java.util.Observable;
import daos.PharapanDAO;
import models.Pharapan;
import interfaces.PharapanInterface;
import models.OperasiCRUD;

public class PharapanController extends Observable{
    private PharapanInterface dao = new PharapanDAO();
    private OperasiCRUD crud;
    
    public void setDAO(PharapanInterface pharapan){
        dao = pharapan;
    }
    
    public void setDml(Pharapan pharapan, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(pharapan); 
                break;
            case UPDATE: hasil = dao.update(pharapan); 
                break;
            case DELETE: hasil = dao.delete(pharapan); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(pharapan);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Pharapan> getAllPharapan(){
        return dao.getAllPharapan();
    }
    
    public Pharapan getByTPS(String TPS){
        return dao.getByTPS(TPS);
    }
}
