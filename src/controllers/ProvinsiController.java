package controllers;

import java.util.List;
import java.util.Observable;
import daos.ProvinsiDAO;
import models.Provinsi;
import interfaces.ProvinsiInterface;
import models.OperasiCRUD;

public class ProvinsiController extends Observable{
    private ProvinsiInterface dao = new ProvinsiDAO();
    private OperasiCRUD crud;
    
    public void setDAO(ProvinsiInterface provinsi){
        dao = provinsi;
    }
    
    public void setDml(Provinsi provinsi, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(provinsi); 
                break;
            case UPDATE: hasil = dao.update(provinsi); 
                break;
            case DELETE: hasil = dao.delete(provinsi); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(provinsi);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Provinsi> getAllProvinsi(){
        return dao.getAllProvinsi();
    }
    
    public Provinsi getByKota(String kota){
        return dao.getByKota(kota);
    }
}
