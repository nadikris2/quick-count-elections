package controllers;

import java.util.List;
import java.util.Observable;
import daos.LoginDAO;
import models.Login;
import interfaces.LoginInterface;
import models.OperasiCRUD;

public class LoginController extends Observable{
    private LoginInterface dao = new LoginDAO();
    private OperasiCRUD crud;
    
    public void setDAO(LoginInterface login){
        dao = login;
    }
    
    public void setDml(Login login, OperasiCRUD c){
        boolean hasil = false;
        this.crud = c;
        switch(c){
            case INSERT: hasil = dao.insert(login); 
                break;
            case UPDATE: hasil = dao.update(login); 
                break;
            case DELETE: hasil = dao.delete(login); 
                break;
        }
        setChanged();
        
        if(hasil){
            notifyObservers(login);
        }else{
            notifyObservers();
        }
    }
    
    public OperasiCRUD getCrudState(){
        return crud;
    }    
    
    public List<Login> getAllLogin(){
        return dao.getAllLogin();
    }
    
    public Login getByUsername(String username){
        return dao.getByUsername(username);
    }
}
