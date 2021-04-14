package controllers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import views.FControl;
import views.Wilayah;

public final class MainController {
    private final static ArrayList<ArrayList<Method>> Cutama = new ArrayList<>();
    private final static ArrayList<Object> Controllers = new ArrayList<>();

    static {
        ArrayList<Class> kelas = new ArrayList<>();
        
        kelas.add(JakbarController.class);
        kelas.add(JakpusController.class);
        kelas.add(JakselController.class);
        kelas.add(JaktimController.class);
        kelas.add(JakutController.class);
        kelas.add(KpseribuController.class);
        kelas.add(KpserselController.class);
        kelas.add(KpserutController.class);
        kelas.add(NasionalController.class);
        kelas.add(PharapanController.class);
        kelas.add(PkelapaController.class);
        kelas.add(PpanggangController.class);
        kelas.add(PpariController.class);
        kelas.add(ProvinsiController.class);
        kelas.add(PtidungController.class);
        kelas.add(PuntungjawaController.class);
        kelas.add(TotalController.class);
        
        for(Class c : kelas){
            try {
                Class<?> d = Class.forName(c.getName());
                Constructor<?> cons = d.getConstructor();
                Controllers.add(cons.newInstance());
            } catch(Exception e){
                System.out.println(e);
            }
            
            Cutama.add(listFungsi(c));
        }
    }
    
    private static ArrayList<Method> listFungsi(Class cek){
        Method[] m = cek.getDeclaredMethods();
        
        ArrayList<Method> s = new ArrayList<>();
        
        for(int i=0;i<m.length;i++)
            s.add(m[i]);
        
        s.sort(new Comparator<Method>() {
            public String cut(String m){
                if(m.contains("get"))
                    return m.substring(m.indexOf("get"), m.indexOf('('));
                if(m.contains("set"))
                    return m.substring(m.indexOf("set"), m.indexOf('('));
                return "";
            }
            @Override
            public int compare(Method a, Method b) {
                return cut(a.toString()).compareTo(cut(b.toString()));
            }
        });
        
        return s;
    }
    
    public static Object callController(Wilayah controller, FControl mode){
        try {
            return Cutama.get(controller.getValue()).get(mode.getValue()).invoke(
                    Controllers.get(controller.getValue())
            );
        } catch(Exception e){
             System.out.println(e);
        }
            
        return null;
    }
    
    public static Object callController(Wilayah controller, FControl mode, Object param){
        try {
            return Cutama.get(controller.getValue()).get(mode.getValue()).invoke(
                    Controllers.get(controller.getValue()),param
            );
        } catch(Exception e){
             System.out.println(e);
        }
            
        return null;
    }
    
    public static void callController(Wilayah controller, FControl mode, Object param1, Object param2){
        try {
            Cutama.get(controller.getValue()).get(mode.getValue()).invoke(
                    Controllers.get(controller.getValue()),param1, param2
            );
        } catch(Exception e){
             System.out.println(e);
        }
    }
}
