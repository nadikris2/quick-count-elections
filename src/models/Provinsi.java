package models;
import java.sql.*;
import interfaces.ModelInterface;

public class Provinsi implements ModelInterface{
    private String kota;
    private int capres1;
    private int capres2;
    
    public Provinsi(String kota, int capres1, int capres2){
        this.kota = kota;
        this.capres1 = capres1;
        this.capres2 = capres2;
    }
    
    public String getKota(){
        return kota;
    }
    
    public void setKota(String kota){
        this.kota = kota;
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
        return kota;
    }
}
