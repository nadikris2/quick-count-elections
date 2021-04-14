package models;
import java.sql.*;
import interfaces.ModelInterface;

public class Nasional implements ModelInterface{
    private String provinsi;
    private int capres1;
    private int capres2;
    
    public Nasional(String provinsi, int capres1, int capres2){
        this.provinsi = provinsi;
        this.capres1 = capres1;
        this.capres2 = capres2;
    }
    
    public String getProvinsi(){
        return provinsi;
    }
    
    public void setProvinsi(String provinsi){
        this.provinsi = provinsi;
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
    
    @Override
    public String toString() {
        return provinsi;
    }
}
