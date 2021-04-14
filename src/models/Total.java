package models;
import java.sql.*;
import interfaces.ModelInterface;

public class Total implements ModelInterface{
    private int id;
    private int capres1;
    private int capres2;
    
    public Total(int id, int capres1, int capres2){
        this.id = id;
        this.capres1 = capres1;
        this.capres2 = capres2;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getCapres1(){
        return capres1;
    }
    
    public void setCapres1(int capres1){
        this.capres1 = capres1;
    }
    
    public int getCapres2(){
        return capres2;
    }
    
    public void setCapres2(int capres2){
        this.capres2 = capres2;
    }
}
