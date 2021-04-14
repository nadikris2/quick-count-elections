package controllers;

import java.util.List;
import java.util.Observable;
import daos.KpserselDAO;
import models.Kpsersel;
import interfaces.KpserselInterface;
import models.OperasiCRUD;

public class KpserselController extends Observable{
    private KpserselInterface dao = new KpserselDAO();
    private OperasiCRUD crud;
    
    public void setDAO(KpserselInterface kpsersel){
        dao = kpsersel;
    }
    
    public void setDml(Kpsersel kpsersel, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(kpsersel); 
                break;
            case UPDATE: hasil = dao.update(kpsersel); 
                break;
            case DELETE: hasil = dao.delete(kpsersel); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(kpsersel);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Kpsersel> getAllKpsersel(){
        return dao.getAllKpsersel();
    }
    
    public Kpsersel getByKelurahan(String kelurahan){
        return dao.getByKelurahan(kelurahan);
    }
}
