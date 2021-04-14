package controllers;

import java.util.List;
import java.util.Observable;
import daos.JakpusDAO;
import models.Jakpus;
import interfaces.JakpusInterface;
import models.OperasiCRUD;

public class JakpusController extends Observable{
    private JakpusInterface dao = new JakpusDAO();
    private OperasiCRUD crud;
    
    public void setDAO(JakpusInterface jakpus){
        dao = jakpus;
    }
    
    public void setDml(Jakpus jakpus, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(jakpus); 
                break;
            case UPDATE: hasil = dao.update(jakpus); 
                break;
            case DELETE: hasil = dao.delete(jakpus); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(jakpus);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Jakpus> getAllJakpus(){
        return dao.getAllJakpus();
    }
    
    public Jakpus getByKecamatan(String kecamatan){
        return dao.getByKecamatan(kecamatan);
    }
}
