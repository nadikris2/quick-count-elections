package controllers;

import java.util.List;
import java.util.Observable;
import daos.JakselDAO;
import models.Jaksel;
import interfaces.JakselInterface;
import models.OperasiCRUD;

public class JakselController extends Observable{
    private JakselInterface dao = new JakselDAO();
    private OperasiCRUD crud;
    
    public void setDAO(JakselInterface jaksel){
        dao = jaksel;
    }
    
    public void setDml(Jaksel jaksel, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(jaksel); 
                break;
            case UPDATE: hasil = dao.update(jaksel); 
                break;
            case DELETE: hasil = dao.delete(jaksel); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(jaksel);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Jaksel> getAllJaksel(){
        return dao.getAllJaksel();
    }
    
    public Jaksel getByKecamatan(String kecamatan){
        return dao.getByKecamatan(kecamatan);
    }
}
