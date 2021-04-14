package controllers;

import java.util.List;
import java.util.Observable;
import daos.TotalDAO;
import models.Total;
import interfaces.TotalInterface;
import models.OperasiCRUD;

public class TotalController extends Observable{
    private TotalInterface dao = new TotalDAO();
    private OperasiCRUD crud;
    
    public void setDAO(TotalInterface total){
        dao = total;
    }
    
    public void setDml(Total total, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(total); 
                break;
            case UPDATE: hasil = dao.update(total); 
                break;
            case DELETE: hasil = dao.delete(total); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(total);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Total> getAllTotal(){
        return dao.getAllTotal();
    }
    
    public Total getById(int id){
        return dao.getById(id);
    }
}
