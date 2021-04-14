package controllers;

import java.util.List;
import java.util.Observable;
import daos.PpanggangDAO;
import models.Ppanggang;
import interfaces.PpanggangInterface;
import models.OperasiCRUD;

public class PpanggangController extends Observable{
    private PpanggangInterface dao = new PpanggangDAO();
    private OperasiCRUD crud;
    
    public void setDAO(PpanggangInterface ppanggang){
        dao = ppanggang;
    }
    
    public void setDml(Ppanggang ppanggang, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(ppanggang); 
                break;
            case UPDATE: hasil = dao.update(ppanggang); 
                break;
            case DELETE: hasil = dao.delete(ppanggang); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(ppanggang);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Ppanggang> getAllPpanggang(){
        return dao.getAllPpanggang();
    }
    
    public Ppanggang getByTPS(String TPS){
        return dao.getByTPS(TPS);
    }
}
