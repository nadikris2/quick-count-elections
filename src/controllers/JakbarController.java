package controllers;

import java.util.List;
import java.util.Observable;
import daos.JakbarDAO;
import models.Jakbar;
import interfaces.JakbarInterface;
import models.OperasiCRUD;

public class JakbarController extends Observable{
    private JakbarInterface dao = new JakbarDAO();
    private OperasiCRUD crud;
    
    public void setDAO(JakbarInterface jakbar){
        dao = jakbar;
    }
    
    public void setDml(Jakbar jakbar, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(jakbar); 
                break;
            case UPDATE: hasil = dao.update(jakbar); 
                break;
            case DELETE: hasil = dao.delete(jakbar); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(jakbar);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Jakbar> getAllJakbar(){
        return dao.getAllJakbar();
    }
    
    public Jakbar getByKecamatan(String kecamatan){
        return dao.getByKecamatan(kecamatan);
    }
}
