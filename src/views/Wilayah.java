/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

public enum Wilayah {
    Jakbar(0),
    Jakpus(1),
    Jaksel(2),
    Jaktim(3),
    Jakut(4),
    Kpseribu(5),
    Kpsersel(6),
    Kpserut(7),
    Nasional(8),
    Pharapan(9),
    Pkelapa(10),
    Ppanggang(11),
    Ppari(12),
    Provinsi(13),
    Ptidung(14),
    Puntungjawa(15),
    total(16);
    
    private final int value;

    Wilayah(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
    
    public static Wilayah StringtoWilayah(String item){
        switch(item.toUpperCase()){
            case "JAKARTA BARAT"            : return Jakbar;
            case "JAKARTA PUSAT"            : return Jakpus;
            case "JAKARTA SELATAN"          : return Jaksel;
            case "JAKARTA TIMUR"            : return Jaktim;
            case "JAKARTA UTARA"            : return Jakut;
            case "KEPULAUAN SERIBU"         : return Kpseribu;
            case "KEPULAUAN SERIBU SELATAN" : return Kpsersel;
            case "KEPULAUAN SERIBU UTARA"   : return Kpserut;
            case "NASIONAL"                 : return Nasional;
            case "PULAU HARAPAN"            : return Pharapan;
            case "PULAU KELAPA"             : return Pkelapa;
            case "PULAU PANGGANG"           : return Ppanggang;
            case "PULAU PARI"               : return Ppari;
            case "DKI JAKARTA"              : return Provinsi;
            case "PULAU TIDUNG"             : return Ptidung;
            case "PULAU UNTUNG JAWA"        : return Puntungjawa;
            case "TOTAL"                    : return total;
        }
        return null;
    }
}