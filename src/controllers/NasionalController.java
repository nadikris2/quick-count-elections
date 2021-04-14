package controllers;

import java.util.List;
import java.util.Observable;
import daos.NasionalDAO;
import models.Nasional;
import interfaces.NasionalInterface;
import models.OperasiCRUD;

public class NasionalController extends Observable{
    private NasionalInterface dao = new NasionalDAO();
    private OperasiCRUD crud;
    
    public void setDAO(NasionalInterface nasional){
        dao = nasional;
    }
    
    public void setDml(Nasional nasional, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(nasional); 
                break;
            case UPDATE: hasil = dao.update(nasional); 
                break;
            case DELETE: hasil = dao.delete(nasional); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(nasional);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Nasional> getAllNasional(){
        return dao.getAllNasional();
    }
    
    public Nasional getByProvinsi(String provinsi){
        return dao.getByProvinsi(provinsi);
    }
}
