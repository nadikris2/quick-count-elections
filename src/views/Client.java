package views;

import controllers.MainController;
import interfaces.ModelInterface;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class Client extends javax.swing.JFrame implements Observer {
    ArrayList<Double> values = new ArrayList<Double>();
    DefaultTableModel tabelModel1, tabelModel2;
    
    public Client() {
        initComponents();
        this.setTitle("Sistem Informasi Pemilu");
        values.add(new Double(1));
        values.add(new Double(1));
        this.setLocationRelativeTo(null);
        jTable1.getTableHeader().setBackground(new Color(52, 58, 64));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable2.getTableHeader().setBackground(new Color(52, 58, 64));
        jTable2.getTableHeader().setForeground(Color.WHITE);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextPane1.setEnabled(false);
        jTextPane2.setEnabled(false);
        jTextField2.setVisible(false);
        cbxKota.setVisible(false);
        cbxKecamatan.setVisible(false);
        cbxKelurahan.setVisible(false);
        cbxTPS.setVisible(false);
        
        cbxNasional.setModel(getComboBoxModel(Wilayah.Nasional));
        setChartData(Wilayah.total);
        setTableData(Wilayah.Nasional);
    }
    // <editor-fold defaultstate="collapsed" desc="getComboboxModel(Wilayah)">
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
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="setChartData(Wilayah)">
    public void setChartData(Wilayah w){
        String nama = "";
        int suara1, suara2;
        
        ModelInterface hasil;
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
        
        suara1 = hasil.getCapres1();
        suara2 = hasil.getCapres2();
        
        float persen1, persen2;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        persen1=Float.valueOf(decimalFormat.format(((float)suara1/((float)suara1+(float)suara2))*100));
        persen2=Float.valueOf(decimalFormat.format(((float)suara2/((float)suara1+(float)suara2))*100));
        float twoDigitsFR = Float.valueOf(decimalFormat.format(persen1));
        lblSuara1.setText("Perolehan Suara: "+NumberFormat.getNumberInstance(Locale.US).format(suara1));
        lblSuara2.setText("Perolehan Suara: "+NumberFormat.getNumberInstance(Locale.US).format(suara2));
        jLabel4.setText("KH. MA'RUF AMIN ("+Float.toString(persen1)+"%)");
        jLabel6.setText("SALAHUDIN UNO ("+Float.toString(persen2)+"%)");
        
        values.clear();
        values.add(new Double(persen2));
        values.add(new Double(persen1));
 
        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(new Color(142,214,255));
        colors.add(new Color(255,227,176));

        PieChart pieChart = new PieChart(values, colors);
        jPanel2.add(pieChart);
        jPanel2.setVisible(true);
        pieChart.setVisible(true);
        pieChart.setSize(400,400);
        pieChart.setLocation(440,150);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="setTableData(wilayah)">
    private void setTableData(Wilayah w){
        tabelModel1 = new DefaultTableModel();
        tabelModel2 = new DefaultTableModel();
        
        String[] kolom = new String[] {
            "WILAYAH",
            "Capres 01",
            "Cpares 02"
        };
        
        tabelModel1.setColumnIdentifiers(kolom);
        tabelModel2.setColumnIdentifiers(kolom);
        
        List<ModelInterface> listData = new ArrayList<>();
        listData = (List<ModelInterface>)MainController.callController(w, FControl.getAll);
        
        int count=0;
        for(ModelInterface m : listData){
            Object[] o = new Object[]{
                    m.toString(),
                    NumberFormat.getNumberInstance(Locale.US).format(m.getCapres1()),
                    NumberFormat.getNumberInstance(Locale.US).format(m.getCapres2())
            };
            
            if(count < listData.size()/2) tabelModel1.addRow(o);
                else tabelModel2.addRow(o);
            count++;
        }
        
        jTable1.setModel(tabelModel1);
        jTable2.setModel(tabelModel2);
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="tblForm()">
    public void tblForm1(){
        if(cbxKelurahan.isVisible()){
            cbxKelurahan.setSelectedItem((String)tabelModel1.getValueAt(jTable1.getSelectedRow(),0));
        }else if(cbxKecamatan.isVisible()){
            cbxKecamatan.setSelectedItem((String)tabelModel1.getValueAt(jTable1.getSelectedRow(),0));
        }else if(cbxKota.isVisible()){
            cbxKota.setSelectedItem((String)tabelModel1.getValueAt(jTable1.getSelectedRow(),0));
        }else{
            cbxNasional.setSelectedItem((String)tabelModel1.getValueAt(jTable1.getSelectedRow(),0));
        }
    }
    
    public void tblForm2(){
        if(cbxKelurahan.isVisible()){
            cbxKelurahan.setSelectedItem((String)tabelModel2.getValueAt(jTable2.getSelectedRow(),0));
        }else if(cbxKecamatan.isVisible()){
            cbxKecamatan.setSelectedItem((String)tabelModel2.getValueAt(jTable2.getSelectedRow(),0));
            
        }else if(cbxKota.isVisible()){
            cbxKota.setSelectedItem((String)tabelModel2.getValueAt(jTable2.getSelectedRow(),0));
        }else{
            cbxNasional.setSelectedItem((String)tabelModel2.getValueAt(jTable2.getSelectedRow(),0));
        }
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        cbxNasional = new javax.swing.JComboBox<>();
        cbxKota = new javax.swing.JComboBox<>();
        cbxKecamatan = new javax.swing.JComboBox<>();
        cbxKelurahan = new javax.swing.JComboBox<>();
        cbxTPS = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        lblTingkat = new javax.swing.JLabel();
        lblSuara1 = new javax.swing.JLabel();
        lblSuara2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        btnAdmin = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBackground(new java.awt.Color(255, 193, 7));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Komisi Pemilihan Umum");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/index1.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        btnReset.setBackground(new java.awt.Color(255, 193, 7));
        btnReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReset.setText("Reset Filter");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnReset)
                        .addComponent(jLabel1)))
                .addGap(22, 22, 22))
        );

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILPRES" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(200, 21));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hitung Suara" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(200, 21));

        cbxNasional.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxNasional.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nasional" }));
        cbxNasional.setPreferredSize(new java.awt.Dimension(200, 21));
        cbxNasional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNasionalActionPerformed(evt);
            }
        });

        cbxKota.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxKota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Kabupaten/Kota" }));
        cbxKota.setPreferredSize(new java.awt.Dimension(200, 21));
        cbxKota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKotaActionPerformed(evt);
            }
        });

        cbxKecamatan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxKecamatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Kecamatan" }));
        cbxKecamatan.setPreferredSize(new java.awt.Dimension(200, 21));
        cbxKecamatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxKecamatanMouseClicked(evt);
            }
        });
        cbxKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKecamatanActionPerformed(evt);
            }
        });

        cbxKelurahan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxKelurahan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Kelurahan" }));
        cbxKelurahan.setPreferredSize(new java.awt.Dimension(200, 21));
        cbxKelurahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxKelurahanMouseClicked(evt);
            }
        });
        cbxKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKelurahanActionPerformed(evt);
            }
        });

        cbxTPS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxTPS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua TPS" }));
        cbxTPS.setPreferredSize(new java.awt.Dimension(200, 21));
        cbxTPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTPSActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1280, 2000));

        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 2000));

        jLabel2.setText("(01) Ir. H. JOKO WIDODO - Prof. Dr. (H.C)");

        jLabel4.setText("KH. MA'RUF AMIN");

        jLabel5.setText("(02) H. PRABOWO SUBIANTO - H. SANDIAGA");

        jLabel6.setText("SALAHUDIN UNO");

        jTextField1.setBackground(new java.awt.Color(108, 117, 125));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("HASIL HITUNG SUARA PEMILU PRESIDEN & WAKIL PRESIDEN RI 2019");
        jTextField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        lblTingkat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTingkat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTingkat.setText("TINGKAT NASIONAL");

        lblSuara1.setText("0");

        lblSuara2.setText("0");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "WILAYAH", "(01) Ir. H. JOKO WIDODO - Prof. Dr. (H.C) KH. MA'RUF AMIN", "(02) H. PRABOWO SUBIANTO - H. SANDIAGA SALAHUDIN UNO"
            }
        ));
        jTable1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "WILAYAH", "(01) Ir. H. JOKO WIDODO - Prof. Dr. (H.C) KH. MA'RUF AMIN", "(02) H. PRABOWO SUBIANTO - H. SANDIAGA SALAHUDIN UNO"
            }
        ));
        jTable2.setShowGrid(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jTextField2.setBackground(new java.awt.Color(255, 255, 153));
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Data Belum Tersedia");
        jTextField2.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Disclaimer");

        jScrollPane4.setPreferredSize(new java.awt.Dimension(983, 110));

        jTextPane1.setBackground(new java.awt.Color(238, 238, 238));
        jTextPane1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextPane1.setText("1. Data entri yang ditampilkan pada Menu Hitung Suara adalah data yg disalin apa adanya/sesuai dengan angka yang tertulis pada Salinan Formulir C1 yang diterima KPU Kabupaten/Kota dari KPPS.\n2. Apabila terdapat kekeliruan pengisian data pada Formulir C1, dapat dilakukan perbaikan pada rapat pleno terbuka rekapitulasi di tingkat kecamatan.\n3. Apabila terdapat perbedaan data antara entri di Situng dan Salinan Formulir C1, akan dilakukan koreksi sesuai data yang tertulis di Salinan Formulir C1.\n4. Data yang ditampilkan di Situng bukan merupakan hasil resmi penghitungan perolehan suara. Penetapan hasil rekapitulasi penghitungan perolehan suara dilakukan secara berjenjang sesuai tingkatannya dalam rapat pleno terbuka. ");
        jTextPane1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextPane1.setPreferredSize(new java.awt.Dimension(983, 110));
        jScrollPane4.setViewportView(jTextPane1);

        jTextField3.setBackground(new java.awt.Color(52, 58, 64));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("        Kontak");
        jTextField3.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        jScrollPane5.setPreferredSize(new java.awt.Dimension(1265, 160));

        jTextPane2.setBackground(new java.awt.Color(52, 58, 64));
        jTextPane2.setBorder(null);
        jTextPane2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextPane2.setForeground(new java.awt.Color(255, 255, 255));
        jTextPane2.setText("\n\t\t\t\t\t\t     \t       Call Center: 021 319 025 67 / 021 319 025 77\n\t\t\t\t\t\t\t      \tWhatsapp: 0812 1177 2443\n\t\t\t\t\t\t\t\tEmail: bagianteknis@kpu.go.id\n\n\t\t\t\t\t\t\t        \tMulai pukul 08.00 - 18.00\n\t\t\t\t\t\t\t                \t#KPUTransparan");
        jTextPane2.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextPane2.setPreferredSize(new java.awt.Dimension(1265, 160));
        jScrollPane5.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(lblSuara1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addComponent(lblSuara2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(114, 114, 114))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(594, 594, 594)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1)
                            .addComponent(lblTingkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTingkat)
                .addGap(181, 181, 181)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSuara2)
                    .addComponent(lblSuara1))
                .addGap(248, 248, 248)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)))
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(580, 580, 580))
        );

        jScrollPane1.setViewportView(jPanel2);

        btnAdmin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdmin.setText("Admin");
        btnAdmin.setPreferredSize(new java.awt.Dimension(200, 21));
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxKecamatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxKelurahan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxTPS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxNasional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxKota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxNasional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxTPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //<editor-fold defaultstate="collapsed" desc="EventFunctions">
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        String tblTingkat="total", sql = "SELECT * FROM total", dbtbl="nasional";
        cbxNasional.setSelectedIndex(0);
        setChartData(Wilayah.total);
        setTableData(Wilayah.Nasional);
    }//GEN-LAST:event_btnResetActionPerformed

    private void cbxNasionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNasionalActionPerformed
        cbxKecamatan.setVisible(false);
        cbxKelurahan.setVisible(false);
        cbxTPS.setVisible(false);
        jTable1.setVisible(true);
        jTable1.getTableHeader().setVisible(true);
        jTable2.setVisible(true);
        jTable2.getTableHeader().setVisible(true);
        jTextField2.setVisible(false);
        if(cbxNasional.getSelectedItem().toString().equals("Nasional")){
            //panggil tabel total buat pie chart, panggil tabel nasional buat jTable
            lblTingkat.setText("TINGKAT NASIONAL");
            cbxKota.setVisible(false);
            setChartData(Wilayah.total);
            setTableData(Wilayah.Nasional);
        }else{
            //panggil tabel nasional where provinsi buat pie chart
            lblTingkat.setText("WILAYAH PEMILIHAN PROV. "+cbxNasional.getSelectedItem());
            setChartData(Wilayah.Nasional);
            if(cbxNasional.getSelectedItem().toString().equals("DKI JAKARTA")){
                //panggil tabel provinsi buat jTable
                cbxKota.setVisible(true);
                cbxKota.setEnabled(true);
                cbxKota.setModel(getComboBoxModel(Wilayah.Provinsi));
                setTableData(Wilayah.Provinsi);
            }else{
                //jTable blank
                cbxKota.setVisible(true);
                cbxKota.setEnabled(false);
                jTable1.setVisible(false);
                jTable1.getTableHeader().setVisible(false);
                jTable2.setVisible(false);
                jTable2.getTableHeader().setVisible(false);
                jTextField2.setVisible(true);
            }
        }
    }//GEN-LAST:event_cbxNasionalActionPerformed
    
    private void cbxTPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTPSActionPerformed

    private void cbxKotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKotaActionPerformed
        lblTingkat.setText("WILAYAH PEMILIHAN KOTA "+cbxKota.getSelectedItem()+" - PROV. DKI JAKARTA");
        cbxKota.setVisible(true);
        cbxKota.setEnabled(true);
        cbxKelurahan.setVisible(false);
        cbxTPS.setVisible(false);
        jTable1.setVisible(true);
        jTable1.getTableHeader().setVisible(true);
        jTable2.setVisible(true);
        jTable2.getTableHeader().setVisible(true);
        jTextField2.setVisible(false);
        
        Wilayah dbtbl = Wilayah.Provinsi;
        switch(cbxKota.getSelectedItem().toString()){
            case "JAKARTA BARAT"   : dbtbl=Wilayah.Jakbar; break;
            case "JAKARTA PUSAT"   : dbtbl=Wilayah.Jakpus; break;
            case "JAKARTA SELATAN" : dbtbl=Wilayah.Jaksel; break;
            case "JAKARTA TIMUR"   : dbtbl=Wilayah.Jaktim; break;
            case "JAKARTA UTARA"   : dbtbl=Wilayah.Jakut;break;
            case "KEPULAUAN SERIBU": dbtbl=Wilayah.Kpseribu; break;
            case "Semua Kecamatan" : dbtbl=Wilayah.Provinsi; break;
        }
        
        if(cbxKota.getSelectedItem().toString().equals("Semua Kabupaten/Kota")){
            setChartData(Wilayah.Nasional);
            setTableData(Wilayah.Provinsi);
            //panggil tabel nasional where provinsi buat pie chart, panggil tabel provinsi buat jTable
            cbxKecamatan.setVisible(false);
            //sql = sql+"nasional WHERE provinsi='DKI JAKARTA'";
            lblTingkat.setText("WILAYAH PEMILIHAN PROV. DKI JAKARTA");
        }else{
            setChartData(Wilayah.Provinsi);
            //panggil tabel provinsi where kota dsb buat pie chart, panggil tabel jakbar/jakpus/jaksel dsb buat jTable
            //sql=sql+"provinsi WHERE kota= '"+cbxKota.getSelectedItem()+"'";
            cbxKecamatan.setVisible(true);
            cbxKecamatan.setModel(getComboBoxModel(dbtbl));
            setTableData(dbtbl);
        }
    }//GEN-LAST:event_cbxKotaActionPerformed

    private void cbxKelurahanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxKelurahanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxKelurahanMouseClicked

    private void cbxKecamatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxKecamatanMouseClicked
        
    }//GEN-LAST:event_cbxKecamatanMouseClicked

    private void cbxKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKecamatanActionPerformed
        cbxTPS.setVisible(false);
        jTable1.setVisible(true);
        jTable1.getTableHeader().setVisible(true);
        jTable2.setVisible(true);
        jTable2.getTableHeader().setVisible(true);
        jTextField2.setVisible(false);
        
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
            setChartData(Wilayah.Provinsi);
            setTableData(w[cbxKota.getSelectedIndex()]);
            //panggil tabel provinsi where kota dsb buat pie chart, panggil tabel jakbar/jakpus/jaksel dsb buat jTable
            cbxKelurahan.setVisible(false);
            lblTingkat.setText("WILAYAH PEMILIHAN KOTA "+cbxKota.getSelectedItem()+" - PROV. DKI JAKARTA");
        }else{
            cbxKelurahan.setVisible(true);
            lblTingkat.setText("WILAYAH PEMILIHAN KEC. "+cbxKecamatan.getSelectedItem()+" - KOTA "+cbxKota.getSelectedItem()+" - PROV. DKI JAKARTA");
            setChartData(w[cbxKota.getSelectedIndex()]);
            if(cbxKota.getSelectedItem().toString().equals("KEPULAUAN SERIBU")){
                //panggil tabel jakbar/jakpus/jaksel where kecamatan dsb buat pie chart, panggil tabel kpsersel/kpserut buat jTable
                cbxKelurahan.setEnabled(true);
                Wilayah dbtbl = Wilayah.Kpseribu;
                switch(cbxKecamatan.getSelectedItem().toString()){
                    case "KEPULAUAN SERIBU UTARA"   : dbtbl=Wilayah.Kpserut; break;
                    case "KEPULAUAN SERIBU SELATAN" : dbtbl=Wilayah.Kpsersel; break;
                }
                cbxKelurahan.setModel(getComboBoxModel(dbtbl));
                
                setTableData(dbtbl);
            }else{
                //panggil tabel jakbar/jakpus/jaksel where kecamatan dsb buat pie chart, jTable blank
                cbxKelurahan.setEnabled(false);
                jTable1.setVisible(false);
                jTable1.getTableHeader().setVisible(false);
                jTable2.setVisible(false);
                jTable2.getTableHeader().setVisible(false);
                jTextField2.setVisible(true);
            }
        }
    }//GEN-LAST:event_cbxKecamatanActionPerformed

    private void cbxKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKelurahanActionPerformed
        jTable1.setVisible(true);
        jTable1.getTableHeader().setVisible(true);
        jTable2.setVisible(true);
        jTable2.getTableHeader().setVisible(true);
        jTextField2.setVisible(false);
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
            setChartData(w[cbxKota.getSelectedIndex()]);
            cbxTPS.setVisible(false);
            switch(cbxKecamatan.getSelectedItem().toString()){
                case "KEPULAUAN SERIBU UTARA": setTableData(Wilayah.Kpserut); break;
                case "KEPULAUAN SERIBU SELATAN": setTableData(Wilayah.Kpsersel); break;
            }
            lblTingkat.setText("WILAYAH PEMILIHAN KEC. "+cbxKecamatan.getSelectedItem()+" - KOTA "+cbxKota.getSelectedItem()+" - PROV. DKI JAKARTA");
        }else{
            //panggil kpsersel/kpserut where kelurahan dsb buat pie chart, panggil tabel pulau buat jTable
            lblTingkat.setText("WILAYAH PEMILIHAN KEL. "+cbxKelurahan.getSelectedItem()+" - KEC. "+cbxKecamatan.getSelectedItem()+" - KOTA "+cbxKota.getSelectedItem()+" - PROV. DKI JAKARTA");
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
            setTableData(dbtbl);
            setChartData(x1);
        }
    }//GEN-LAST:event_cbxKelurahanActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        views.Admin.akses();
        this.dispose();
    }//GEN-LAST:event_btnAdminActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        tblForm2();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tblForm1();
    }//GEN-LAST:event_jTable1MouseClicked
    //</editor-fold>
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Variable Declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAdmin;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cbxKecamatan;
    private javax.swing.JComboBox<String> cbxKelurahan;
    private javax.swing.JComboBox<String> cbxKota;
    private javax.swing.JComboBox<String> cbxNasional;
    private javax.swing.JComboBox<String> cbxTPS;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JLabel lblSuara1;
    private javax.swing.JLabel lblSuara2;
    private javax.swing.JLabel lblTingkat;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
    @Override
    public void update(Observable arg0, Object arg1) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
