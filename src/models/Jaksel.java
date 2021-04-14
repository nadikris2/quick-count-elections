package models;
import java.sql.*;
import interfaces.ModelInterface;

public class Jaksel implements ModelInterface{
    private String kecamatan;
    private int capres1;
    private int capres2;
    
    public Jaksel(String kecamatan, int capres1, int capres2){
        this.kecamatan = kecamatan;
        this.capres1 = capres1;
        this.capres2 = capres2;
    }
    
    public String getKecamatan(){
        return kecamatan;
    }
    
    public void setKecamatan(String kecamatan){
        this.kecamatan = kecamatan;
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
        return kecamatan;
    }
}
