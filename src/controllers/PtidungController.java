package controllers;

import java.util.List;
import java.util.Observable;
import daos.PtidungDAO;
import models.Ptidung;
import interfaces.PtidungInterface;
import models.OperasiCRUD;

public class PtidungController extends Observable{
    private PtidungInterface dao = new PtidungDAO();
    private OperasiCRUD crud;
    
    public void setDAO(PtidungInterface ptidung){
        dao = ptidung;
    }
    
    public void setDml(Ptidung ptidung, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(ptidung); 
                break;
            case UPDATE: hasil = dao.update(ptidung); 
                break;
            case DELETE: hasil = dao.delete(ptidung); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(ptidung);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Ptidung> getAllPtidung(){
        return dao.getAllPtidung();
    }
    
    public Ptidung getByTPS(String TPS){
        return dao.getByTPS(TPS);
    }
}
