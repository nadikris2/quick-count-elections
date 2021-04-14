package controllers;

import java.util.List;
import java.util.Observable;
import daos.KpserutDAO;
import models.Kpserut;
import interfaces.KpserutInterface;
import models.OperasiCRUD;

public class KpserutController extends Observable{
    private KpserutInterface dao = new KpserutDAO();
    private OperasiCRUD crud;
    
    public void setDAO(KpserutInterface kpserut){
        dao = kpserut;
    }
    
    public void setDml(Kpserut kpserut, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(kpserut); 
                break;
            case UPDATE: hasil = dao.update(kpserut); 
                break;
            case DELETE: hasil = dao.delete(kpserut); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(kpserut);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Kpserut> getAllKpserut(){
        return dao.getAllKpserut();
    }
    
    public Kpserut getByKelurahan(String kelurahan){
        return dao.getByKelurahan(kelurahan);
    }
}
