package controllers;

import java.util.List;
import java.util.Observable;
import daos.KpseribuDAO;
import models.Kpseribu;
import interfaces.KpseribuInterface;
import models.OperasiCRUD;

public class KpseribuController extends Observable{
    private KpseribuInterface dao = new KpseribuDAO();
    private OperasiCRUD crud;
    
    public void setDAO(KpseribuInterface kpseribu){
        dao = kpseribu;
    }
    
    public void setDml(Kpseribu kpseribu, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(kpseribu); 
                break;
            case UPDATE: hasil = dao.update(kpseribu); 
                break;
            case DELETE: hasil = dao.delete(kpseribu); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(kpseribu);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Kpseribu> getAllKpseribu(){
        return dao.getAllKpseribu();
    }
    
    public Kpseribu getByKecamatan(String kecamatan){
        return dao.getByKecamatan(kecamatan);
    }
}
