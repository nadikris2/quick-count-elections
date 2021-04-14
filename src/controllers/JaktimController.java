package controllers;

import java.util.List;
import java.util.Observable;
import daos.JaktimDAO;
import models.Jaktim;
import interfaces.JaktimInterface;
import models.OperasiCRUD;

public class JaktimController extends Observable{
    private JaktimInterface dao = new JaktimDAO();
    private OperasiCRUD crud;
    
    public void setDAO(JaktimInterface jaktim){
        dao = jaktim;
    }
    
    public void setDml(Jaktim jaktim, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(jaktim); 
                break;
            case UPDATE: hasil = dao.update(jaktim); 
                break;
            case DELETE: hasil = dao.delete(jaktim); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(jaktim);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Jaktim> getAllJaktim(){
        return dao.getAllJaktim();
    }
    
    public Jaktim getByKecamatan(String kecamatan){
        return dao.getByKecamatan(kecamatan);
    }
}
