package controllers;

import java.util.List;
import java.util.Observable;
import daos.JakutDAO;
import models.Jakut;
import interfaces.JakutInterface;
import models.OperasiCRUD;

public class JakutController extends Observable{
    private JakutInterface dao = new JakutDAO();
    private OperasiCRUD crud;
    
    public void setDAO(JakutInterface jakut){
        dao = jakut;
    }
    
    public void setDml(Jakut jakut, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(jakut); 
                break;
            case UPDATE: hasil = dao.update(jakut); 
                break;
            case DELETE: hasil = dao.delete(jakut); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(jakut);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Jakut> getAllJakut(){
        return dao.getAllJakut();
    }
    
    public Jakut getByKecamatan(String kecamatan){
        return dao.getByKecamatan(kecamatan);
    }
}
