package views;

import controllers.LoginController;
import controllers.MainController;
import interfaces.ModelInterface;
import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import models.*;

public class Admin extends javax.swing.JFrame implements Observer{
    private static LoginController lc = new LoginController();
    
    public Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Admin Pemilu");
        cbxKota.setVisible(false);
        cbxKecamatan.setVisible(false);
        cbxKelurahan.setVisible(false);
        cbxTPS.setVisible(false);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        btnSimpan.setEnabled(false);
        cbxNasional.setModel(getComboBoxModel(Wilayah.Nasional));
        updateTextField(Wilayah.total);
    }
    
    public void updateTextField(Wilayah w){
        ModelInterface hasil;
        String nama="";
        
        if(w==Wilayah.total){
            hasil = (ModelInterface) ((List<?>)MainController.callController(w, FControl.getAll)).get(0);
        }else{
            switch(w){
                case Nasional:
                    nama = cbxNasional.getSelectedItem().toString();
                    break;
                case Provinsi:
                    nama = cbxKota.getSelectedItem().toString();
                    break;
                case Jakbar:
                case Jakpus:
                case Jaksel:
                case Jaktim:
                case Jakut:
                case Kpseribu:
                    nama = cbxKecamatan.getSelectedItem().toString();
                    break;
                case Kpsersel:
                case Kpserut:
                    nama = cbxKelurahan.getSelectedItem().toString();
                    break;
                case Pharapan:
                case Pkelapa:
                case Ppanggang:
                case Ppari:
                case Ptidung:
                case Puntungjawa:
                    nama = cbxTPS.getSelectedItem().toString();
                    break;
            }
            
            hasil = (ModelInterface) MainController.callController(w, FControl.getByID, nama);
        }
        
        jTextField1.setText(hasil.getCapres1()+"");
        jTextField2.setText(hasil.getCapres2()+"");
    }
    
    public DefaultComboBoxModel getComboBoxModel(Wilayah w){
        List<Object> isi = (List<Object>) MainController.callController(w, FControl.getAll);
        List<String> hasil = new ArrayList<>();
        
        switch(w){
            case Nasional: 
                hasil.add("Nasional"); 
                break;
            case Provinsi: 
                hasil.add("Semua Kabupaten/Kota"); 
                break;
            case Jakbar:
            case Jakpus:
            case Jaksel:
            case Jaktim:
            case Jakut:
            case Kpseribu: 
                hasil.add("Semua Kecamatan"); 
                break;
            case Kpserut:
            case Kpsersel: 
                hasil.add("Semua Kelurahan"); 
                break;
            default: 
                hasil.add("Semua TPS"); 
                break;
        }
        
        if(isi != null){
            for(Object o : isi){
                hasil.add(o.toString());
            }
        }
        
        return new DefaultComboBoxModel(hasil.toArray());
    }
    
    private ModelInterface createModel(String item, Wilayah w, int s1, int s2){
        ModelInterface baru = null;
        switch(w){
            case Jakbar     : baru = new Jakbar     (item, s1, s2); break;
            case Jakpus     : baru = new Jakpus     (item, s1, s2); break;
            case Jaksel     : baru = new Jaksel     (item, s1, s2); break;
            case Jaktim     : baru = new Jaktim     (item, s1, s2); break;
            case Jakut      : baru = new Jakut      (item, s1, s2); break;
            case Kpseribu   : baru = new Kpseribu   (item, s1, s2); break;
            case Kpsersel   : baru = new Kpsersel   (item, s1, s2); break;
            case Kpserut    : baru = new Kpserut    (item, s1, s2); break;
            case Nasional   : baru = new Nasional   (item, s1, s2); break;
            case Pharapan   : baru = new Pharapan   (item, s1, s2); break;
            case Pkelapa    : baru = new Pkelapa    (item, s1, s2); break;
            case Ppanggang  : baru = new Ppanggang  (item, s1, s2); break;
            case Ppari      : baru = new Ppari      (item, s1, s2); break;
            case Provinsi   : baru = new Provinsi   (item, s1, s2); break;
            case Ptidung    : baru = new Ptidung    (item, s1, s2); break;
            case Puntungjawa: baru = new Puntungjawa(item, s1, s2); break;
            case total      : baru = new Total      (   0, s1, s2); break;
        }
        return baru;
    }
    
    private int sumCapres1(List<ModelInterface> models){
        int sum = 0;
        for(ModelInterface m : models){
            sum+=m.getCapres1();
        }
        return sum;
    }
    
    private int sumCapres2(List<ModelInterface> models){
        int sum = 0;
        for(ModelInterface m : models){
            sum+=m.getCapres2();
        }
        return sum;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbxNasional = new javax.swing.JComboBox<>();
        cbxKecamatan = new javax.swing.JComboBox<>();
        cbxKota = new javax.swing.JComboBox<>();
        cbxKelurahan = new javax.swing.JComboBox<>();
        cbxTPS = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("UPDATE DATA");

        cbxNasional.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxNasional.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data Tidak Tersedia" }));
        cbxNasional.setPreferredSize(new java.awt.Dimension(200, 25));
        cbxNasional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNasionalActionPerformed(evt);
            }
        });

        cbxKecamatan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxKecamatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data Tidak Tersedia" }));
        cbxKecamatan.setPreferredSize(new java.awt.Dimension(200, 25));
        cbxKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKecamatanActionPerformed(evt);
            }
        });

        cbxKota.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxKota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data Tidak Tersedia" }));
        cbxKota.setPreferredSize(new java.awt.Dimension(200, 25));
        cbxKota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKotaActionPerformed(evt);
            }
        });

        cbxKelurahan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxKelurahan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data Tidak Tersedia" }));
        cbxKelurahan.setPreferredSize(new java.awt.Dimension(200, 25));
        cbxKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKelurahanActionPerformed(evt);
            }
        });

        cbxTPS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxTPS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data Tidak Tersedia" }));
        cbxTPS.setPreferredSize(new java.awt.Dimension(200, 25));
        cbxTPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTPSActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(200, 25));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Jumlah Suara 01");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Jumlah Suara 02");

        btnKeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnKeluar.setText("Keluar");
        btnKeluar.setPreferredSize(new java.awt.Dimension(200, 25));
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setPreferredSize(new java.awt.Dimension(200, 25));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Wilayah Pemilihan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxNasional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxKota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxKelurahan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTPS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxKecamatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxNasional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(cbxKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbxKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxTPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxNasionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNasionalActionPerformed
        if(cbxNasional.getSelectedItem()=="Nasional"){
            cbxKota.setVisible(false);
            cbxKecamatan.setVisible(false);
            cbxKelurahan.setVisible(false);
            cbxTPS.setVisible(false);
            jTextField1.setEnabled(false);
            jTextField2.setEnabled(false);
            btnSimpan.setEnabled(false);
            updateTextField(Wilayah.total);
        }else{
            updateTextField(Wilayah.Nasional);
            if(cbxNasional.getSelectedItem().toString().compareTo("DKI JAKARTA")==0){
                cbxKota.setVisible(true);
                cbxKota.setEnabled(true);
                cbxKota.setModel(getComboBoxModel(Wilayah.Provinsi));
                cbxKecamatan.setVisible(false);
                cbxKelurahan.setVisible(false);
                cbxTPS.setVisible(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                btnSimpan.setEnabled(false);
            }else{
                cbxKota.setVisible(true);
                cbxKota.setEnabled(false);
                cbxKecamatan.setVisible(false);
                cbxKelurahan.setVisible(false);
                cbxTPS.setVisible(false);
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                btnSimpan.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbxNasionalActionPerformed

    private void cbxKotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKotaActionPerformed
        if(cbxKota.getSelectedItem()=="Semua Kabupaten/Kota"){
            updateTextField(Wilayah.Nasional);
            cbxKecamatan.setVisible(false);
            cbxKelurahan.setVisible(false);
            cbxTPS.setVisible(false);
            jTextField1.setEnabled(false);
            jTextField2.setEnabled(false);
            btnSimpan.setEnabled(false);
        } else {
            Wilayah dbtbl = Wilayah.Provinsi;
            switch(cbxKota.getSelectedItem().toString()){
                case "JAKARTA BARAT"   : dbtbl=Wilayah.Jakbar;   break;
                case "JAKARTA PUSAT"   : dbtbl=Wilayah.Jakpus;   break;
                case "JAKARTA SELATAN" : dbtbl=Wilayah.Jaksel;   break;
                case "JAKARTA TIMUR"   : dbtbl=Wilayah.Jaktim;   break;
                case "JAKARTA UTARA"   : dbtbl=Wilayah.Jakut;    break;
                case "KEPULAUAN SERIBU": dbtbl=Wilayah.Kpseribu; break;
                case "Semua Kecamatan" : dbtbl=Wilayah.Provinsi; break;
            }
            cbxKecamatan.setModel(getComboBoxModel(dbtbl));
            updateTextField(Wilayah.Provinsi);
            cbxKecamatan.setVisible(true);
            cbxKelurahan.setVisible(false);
            cbxTPS.setVisible(false);
            jTextField1.setEnabled(false);
            jTextField2.setEnabled(false);
            btnSimpan.setEnabled(false);   
        }
    }//GEN-LAST:event_cbxKotaActionPerformed

    private void cbxKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKecamatanActionPerformed
        cbxTPS.setVisible(false);
        
        Wilayah[] w = new Wilayah[]{
            Wilayah.Provinsi,
            Wilayah.Jakbar, 
            Wilayah.Jakpus,
            Wilayah.Jaksel, 
            Wilayah.Jaktim,
            Wilayah.Jakut, 
            Wilayah.Kpseribu
        };
        
        if(cbxKecamatan.getSelectedItem().toString().compareTo("Semua Kecamatan")==0){
            updateTextField(Wilayah.Provinsi);
            //panggil tabel provinsi where kota dsb buat pie chart, panggil tabel jakbar/jakpus/jaksel dsb buat jTable
            cbxKelurahan.setVisible(false);
            cbxKelurahan.setEnabled(false);
        }else{
            cbxKelurahan.setVisible(true);
            updateTextField(w[cbxKota.getSelectedIndex()]);
            if(cbxKota.getSelectedItem().toString().equals("KEPULAUAN SERIBU")){
                //panggil tabel jakbar/jakpus/jaksel where kecamatan dsb buat pie chart, panggil tabel kpsersel/kpserut buat jTable
                cbxKelurahan.setEnabled(true);
                btnSimpan.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                Wilayah dbtbl = Wilayah.Kpseribu;
                switch(cbxKecamatan.getSelectedItem().toString()){
                    case "KEPULAUAN SERIBU UTARA"   : dbtbl=Wilayah.Kpserut; break;
                    case "KEPULAUAN SERIBU SELATAN" : dbtbl=Wilayah.Kpsersel; break;
                }
                cbxKelurahan.setModel(getComboBoxModel(dbtbl));
            }else{
                //panggil tabel jakbar/jakpus/jaksel where kecamatan dsb buat pie chart, jTable blank
                btnSimpan.setEnabled(true);
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                cbxKelurahan.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cbxKecamatanActionPerformed

    private void cbxKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKelurahanActionPerformed
        cbxTPS.setVisible(true);
        Wilayah[] w = new Wilayah[]{
            Wilayah.Provinsi,
            Wilayah.Jakbar, 
            Wilayah.Jakpus,
            Wilayah.Jaksel, 
            Wilayah.Jaktim,
            Wilayah.Jakut, 
            Wilayah.Kpseribu
        };
        
        if(cbxKelurahan.getSelectedItem().toString().equals("Semua Kelurahan")){
            //panggil tabel jakbar/jakpus/jaksel where kecamatan dsb buat pie chart, panggil tabel kpsersel/kpserut buat jTable
            updateTextField(w[cbxKota.getSelectedIndex()]);
            cbxTPS.setVisible(false);
        }else{
            //panggil kpsersel/kpserut where kelurahan dsb buat pie chart, panggil tabel pulau buat jTable
            Wilayah dbtbl = Wilayah.Kpseribu;
            Wilayah x1 = Wilayah.Kpseribu;
            switch(cbxKecamatan.getSelectedItem().toString()){
                case "KEPULAUAN SERIBU UTARA":
                    dbtbl = Wilayah.Kpserut;
                    x1 = Wilayah.Kpserut;
                    switch(cbxKelurahan.getSelectedItem().toString()){
                    case "PULAU HARAPAN" : dbtbl=Wilayah.Pharapan; break;
                    case "PULAU KELAPA"  : dbtbl=Wilayah.Pkelapa; break;
                    case "PULAU PANGGANG": dbtbl=Wilayah.Ppanggang; break;
                    }
                    break;
                case "KEPULAUAN SERIBU SELATAN":
                    dbtbl = Wilayah.Kpsersel;
                    x1 = Wilayah.Kpsersel;
                    switch(cbxKelurahan.getSelectedItem().toString()){
                    case "PULAU PARI"       : dbtbl=Wilayah.Ppari; break;
                    case "PULAU TIDUNG"     : dbtbl=Wilayah.Ptidung; break;
                    case "PULAU UNTUNG JAWA": dbtbl=Wilayah.Puntungjawa; break;
                    }
                    break;
            }
            cbxTPS.setModel(getComboBoxModel(dbtbl));
            updateTextField(x1);
        }
    }//GEN-LAST:event_cbxKelurahanActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        this.dispose();
        new Client().setVisible(true);
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void cbxTPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTPSActionPerformed
        Wilayah dbtbl=Wilayah.Kpseribu;
        Wilayah dbtbl1=Wilayah.Kpseribu;
        switch(cbxKecamatan.getSelectedItem().toString()){
            case "KEPULAUAN SERIBU UTARA":
                dbtbl=Wilayah.Kpserut;
                break;
            case "KEPULAUAN SERIBU SELATAN":
                dbtbl=Wilayah.Kpsersel;
                break;
        }
        switch(cbxKelurahan.getSelectedItem().toString()){
            case "PULAU HARAPAN":
                dbtbl1=Wilayah.Pharapan;
                break;

            case "PULAU KELAPA":
                dbtbl1=Wilayah.Pkelapa;
                break;

            case "PULAU PANGGANG":
                dbtbl1=Wilayah.Ppanggang;
                break;

            case "PULAU PARI":
                dbtbl1=Wilayah.Ppari;
                break;

            case "PULAU TIDUNG":
                dbtbl1=Wilayah.Ptidung;
                break;

            case "PULAU UNTUNG JAWA":
                dbtbl1=Wilayah.Puntungjawa;
                break;
        }
        if(cbxTPS.getSelectedItem()=="Semua TPS"){
            updateTextField(dbtbl);
            jTextField1.setEnabled(false);
            jTextField2.setEnabled(false);
            btnSimpan.setEnabled(false);
        }else{
            updateTextField(dbtbl1);
            jTextField1.setEnabled(true);
            jTextField2.setEnabled(true);
            btnSimpan.setEnabled(true);
        }
    }//GEN-LAST:event_cbxTPSActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String item="";
        Wilayah w = Wilayah.Nasional;
        Wilayah w2 = null;
        if(cbxKota.isEnabled()){
            if(cbxKelurahan.isEnabled()){
                switch(cbxKelurahan.getSelectedItem().toString()){
                    case "PULAU HARAPAN":
                        w = Wilayah.Pharapan;
                        break;
                
                    case "PULAU KELAPA":
                        w = Wilayah.Pkelapa;
                        break;

                    case "PULAU PANGGANG":
                        w = Wilayah.Ppanggang;
                        break;

                    case "PULAU PARI":
                        w = Wilayah.Ppari;
                        break;

                    case "PULAU TIDUNG":
                        w = Wilayah.Ptidung;
                        break;

                    case "PULAU UNTUNG JAWA":
                        w = Wilayah.Puntungjawa;
                        break;
                }
                item=cbxTPS.getSelectedItem().toString();
            }else{
                switch(cbxKota.getSelectedItem().toString()){
                    case "JAKARTA BARAT":
                        w = Wilayah.Jakbar;
                        break;
                        
                    case "JAKARTA PUSAT":
                        w = Wilayah.Jakpus;
                        break;
                        
                    case "JAKARTA SELATAN":
                        w = Wilayah.Jaksel;
                        break;
                        
                    case "JAKARTA TIMUR":
                        w = Wilayah.Jaktim;
                        break;
                        
                    case "JAKARTA UTARA":
                        w = Wilayah.Jakut;
                        break;
                }
                item=cbxKecamatan.getSelectedItem().toString();
            }
        }else{
            item=cbxNasional.getSelectedItem().toString();
        }
        if(jTextField1.getText().equals("")&&jTextField2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data masih kosong!", "Fatal", JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocus();
        }else if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data tidak bisa kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            jTextField1.requestFocus();
        }else if(jTextField2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data tidak bisa kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
        }else{
            int s1=-1, s2=-1;
            boolean error=false;
            try{
                s1=Integer.parseInt(jTextField1.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Hanya bisa input angka", "Error", JOptionPane.ERROR_MESSAGE);
                jTextField1.requestFocus();
                error=true;
            }
            try{
                s2=Integer.parseInt(jTextField2.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Hanya bisa input angka", "Error", JOptionPane.ERROR_MESSAGE);
                jTextField2.requestFocus();
                error=true;
            }
            
            if((s1<0 || s2<0) && !error){
                JOptionPane.showMessageDialog(null, "Tidak bisa input bilangan negatif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                error=true;
            }else{
                if(!error){
                    ModelInterface baru=createModel(item, w, s1, s2);
                    
                    if(baru!=null){
                        MainController.callController(w, FControl.setDml, baru, OperasiCRUD.UPDATE);
                        if(cbxTPS.isEnabled() && cbxTPS.isVisible()){
                            item = cbxKelurahan.getSelectedItem().toString();
                            w = Wilayah.StringtoWilayah(cbxKecamatan.getSelectedItem().toString());
                            w2 = Wilayah.StringtoWilayah(item);
                            if(w2 != null){
                                s1 = sumCapres1((List<ModelInterface>)MainController.callController(w2, FControl.getAll));
                                s2 = sumCapres2((List<ModelInterface>)MainController.callController(w2, FControl.getAll));
                                baru = createModel(item, w, s1, s2);
                            
                                MainController.callController(w, FControl.setDml, baru, OperasiCRUD.UPDATE);
                            } else {
                                System.out.println(item+" not found");
                            }
                        }
                        if(cbxKelurahan.isEnabled() && cbxKelurahan.isVisible()){
                            item = cbxKecamatan.getSelectedItem().toString();
                            w = Wilayah.StringtoWilayah(cbxKota.getSelectedItem().toString());
                            w2 = Wilayah.StringtoWilayah(item);
                            if(w2 != null){
                                s1 = sumCapres1((List<ModelInterface>)MainController.callController(w2, FControl.getAll));
                                s2 = sumCapres2((List<ModelInterface>)MainController.callController(w2, FControl.getAll));
                                baru = createModel(item, w, s1, s2);

                                MainController.callController(w, FControl.setDml, baru, OperasiCRUD.UPDATE);
                            } else {
                                System.out.println(item+" not found");
                            }
                        }
                        if(cbxKecamatan.isEnabled() && cbxKecamatan.isVisible()){
                            item = cbxKota.getSelectedItem().toString();
                            w = Wilayah.StringtoWilayah(cbxNasional.getSelectedItem().toString());
                            w2 = Wilayah.StringtoWilayah(item);
                            if(w2 != null){
                                s1 = sumCapres1((List<ModelInterface>)MainController.callController(w2, FControl.getAll));
                                s2 = sumCapres2((List<ModelInterface>)MainController.callController(w2, FControl.getAll));
                                baru = createModel(item, w, s1, s2);
                                
                                MainController.callController(w, FControl.setDml, baru, OperasiCRUD.UPDATE);
                            } else {
                                System.out.println(item+" not found");
                            }
                        }
                        if(cbxKota.isEnabled() && cbxKota.isVisible()){
                            item = cbxNasional.getSelectedItem().toString();
                            w = Wilayah.Nasional;
                            w2 = Wilayah.StringtoWilayah(item);
                            if(w2 != null){
                                s1 = sumCapres1((List<ModelInterface>)MainController.callController(w2, FControl.getAll));
                                s2 = sumCapres2((List<ModelInterface>)MainController.callController(w2, FControl.getAll));
                                baru = createModel(item, w, s1, s2);
                                
                                MainController.callController(w, FControl.setDml, baru, OperasiCRUD.UPDATE);
                            } else {
                                System.out.println(item+" not found");
                            }
                        }
                        
                        s1 = sumCapres1((List<ModelInterface>)MainController.callController(Wilayah.Nasional, FControl.getAll));
                        s2 = sumCapres2((List<ModelInterface>)MainController.callController(Wilayah.Nasional, FControl.getAll));
                        baru = createModel(null, Wilayah.total, s1, s2);

                        MainController.callController(Wilayah.total, FControl.setDml, baru, OperasiCRUD.UPDATE);
                    }
                        
                }
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    public static void akses() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        //ArrayList<String>username = uas.daos.dataDAO.username();
        //ArrayList<String>password = uas.daos.dataDAO.password();
        JOptionPane.showMessageDialog(null, ("Anda harus login untuk mengakses admin"));
        boolean ulang=true;
        do{
            JTextField xField = new JTextField(20);
            JPasswordField yField = new JPasswordField(20);
            JPanel myPanel = new JPanel(new BorderLayout(5,5));
            JPanel label = new JPanel(new GridLayout(0,1,2,2));
            label.add(new JLabel("Username:"));
            label.add(new JLabel("Password:"));
            myPanel.add(label, BorderLayout.WEST);

            JPanel controls = new JPanel(new GridLayout(0,1,2,2));
            controls.add(xField);
            controls.add(yField);
            myPanel.add(controls, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                   "Login", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String pass = new String(yField.getPassword());
                if(xField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Mohon masukkan username anda", "Aduh...", JOptionPane.WARNING_MESSAGE);
                    xField.requestFocus();
                }else if(pass.equals("")){
                    JOptionPane.showMessageDialog(null, "Mohon masukkan password anda", "Aduh...", JOptionPane.WARNING_MESSAGE);
                    yField.requestFocus();
                }else{
                    List<Login> accounts = lc.getAllLogin();
                    
                    List<String> usernames = new ArrayList<>();
                    List<String> passwords = new ArrayList<>();
                    for(Login l : accounts){
                        usernames.add(l.getUsername());
                        passwords.add(l.getPassword());
                    }
                    
                    //username.contains(xField.getText())&&password.contains(pass)){
                    if(usernames.indexOf(xField.getText()) >= 0 && passwords.get(usernames.indexOf(xField.getText())).equals(pass)){
                        ulang=false;
                        java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new Admin().setVisible(true);
                        }
                        });
                    }else{
                        JOptionPane.showMessageDialog(null, "Username atau password yang anda masukkan salah!", "Error", JOptionPane.ERROR_MESSAGE);
                        ulang=true;
                    }
                }
            }else{
                ulang=false;
                new Client().setVisible(true);
            }
        }while(ulang);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cbxKecamatan;
    private javax.swing.JComboBox<String> cbxKelurahan;
    private javax.swing.JComboBox<String> cbxKota;
    private javax.swing.JComboBox<String> cbxNasional;
    private javax.swing.JComboBox<String> cbxTPS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
