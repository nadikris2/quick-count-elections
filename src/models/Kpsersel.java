package models;
import java.sql.*;
import interfaces.ModelInterface;

public class Kpsersel implements ModelInterface{
    private String kelurahan;
    private int capres1;
    private int capres2;
    
    public Kpsersel(String kelurahan, int capres1, int capres2){
        this.kelurahan = kelurahan;
        this.capres1 = capres1;
        this.capres2 = capres2;
    }
    
    public String getKelurahan(){
        return kelurahan;
    }
    
    public void setKelurahan(String kelurahan){
        this.kelurahan = kelurahan;
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
        return kelurahan;
    }
}
