package controllers;

import java.util.List;
import java.util.Observable;
import daos.PkelapaDAO;
import models.Pkelapa;
import interfaces.PkelapaInterface;
import models.OperasiCRUD;

public class PkelapaController extends Observable{
    private PkelapaInterface dao = new PkelapaDAO();
    private OperasiCRUD crud;
    
    public void setDAO(PkelapaInterface pkelapa){
        dao = pkelapa;
    }
    
    public void setDml(Pkelapa pkelapa, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(pkelapa); 
                break;
            case UPDATE: hasil = dao.update(pkelapa); 
                break;
            case DELETE: hasil = dao.delete(pkelapa); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(pkelapa);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Pkelapa> getAllPkelapa(){
        return dao.getAllPkelapa();
    }
    
    public Pkelapa getByTPS(String TPS){
        return dao.getByTPS(TPS);
    }
}
