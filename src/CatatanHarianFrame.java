
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;



//Encapsolation
public class CatatanHarianFrame extends javax.swing.JFrame {
 private final catatan_harian dbHelper;
   

    
  public CatatanHarianFrame() {
      
    initComponents();

     cmbMood.setSelectedItem(null);
             
      dbHelper = new catatan_harian(); // Inisialisasi dbHelper di sini
        KontakData(); // Memuat data kontak pada saat form pertama kali dibuka
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        btnUbah.addActionListener(this::btnUbahActionPerformed);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        btnEkspor.addActionListener(e -> eksporCatatanKeCSV());
        btnImpor.addActionListener(e -> ImporDariCSV());
        
        tableCatatan.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting() && tableCatatan.getSelectedRow() != -1) {
        int selectedRow = tableCatatan.getSelectedRow();
        TxtJudul.setText((String) tableCatatan.getValueAt(selectedRow, 1)); // Kolom Nama
        TxtCatatan.setText((String) tableCatatan.getValueAt(selectedRow, 2)); // Kolom Nomor
        cmbMood.setSelectedItem((String) tableCatatan.getValueAt(selectedRow, 3)); // Kolom Kategori
    }
});
  }
  
private void loadCatatanData() {
      
    DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
    model.setRowCount(0); // Reset tabel

    
    }

private void eksporCatatanKeCSV() {
     JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave + ".csv"))) {
                // Menulis header kolom
                bw.write("ID,Tanggal,Kegiatan\n"); // Sesuaikan dengan kolom tabel Anda

                // Menulis data dari model tabel
                DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        bw.write(model.getValueAt(i, j).toString());
                        if (j < model.getColumnCount() - 1) {
                            bw.write(",");
                        }
                    }
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Data berhasil diekspor!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengekspor data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
}
  
  private void ImporDariCSV() {
   JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(fileToOpen))) {
                String line;
                br.readLine(); // Skip header line (if exists)
                DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 3) { // Minimal 3 kolom (ID, Tanggal, Kegiatan)
                        String judul = data[1];
                        String catatan = data[2];
                        String mood = data[3];
                        dbHelper.tambahCatatan( judul, catatan, mood);
                    }
                }
                loadCatatanData();
                JOptionPane.showMessageDialog(this, "catatan berhasil diimpor!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengimpor Catatan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
}

  
  
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Catatantext = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtCatatan = new javax.swing.JTextPane();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnEkspor = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnImpor = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        tanggaltext = new javax.swing.JLabel();
        Judul = new javax.swing.JLabel();
        TxtJudul = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableCatatan = new javax.swing.JTable();
        cariTxt = new javax.swing.JTextField();
        cmbMood = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(232, 33, 126));
        jLabel1.setText("Aplikasi Catatan Harian");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 212, 240));

        Catatantext.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Catatantext.setText("Catatan");

        jScrollPane1.setViewportView(TxtCatatan);

        btnTambah.setBackground(new java.awt.Color(255, 101, 165));
        btnTambah.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setBackground(new java.awt.Color(255, 101, 165));
        btnUbah.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUbah.setText("Edit");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnEkspor.setBackground(new java.awt.Color(255, 255, 204));
        btnEkspor.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        btnEkspor.setText("Ekspor");
        btnEkspor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEksporActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 101, 165));
        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnImpor.setBackground(new java.awt.Color(255, 255, 204));
        btnImpor.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        btnImpor.setText("Impor");
        btnImpor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImporActionPerformed(evt);
            }
        });

        btnSearch.setText("Cari");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tanggaltext.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        tanggaltext.setText("Mood");

        Judul.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Judul.setText("Judul");

        TxtJudul.setBackground(new java.awt.Color(255, 247, 233));

        tableCatatan.setBackground(new java.awt.Color(210, 234, 250));
        tableCatatan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Judul", "Catatan", "Mood"
            }
        ));
        jScrollPane3.setViewportView(tableCatatan);

        cariTxt.setBackground(new java.awt.Color(255, 240, 204));

        cmbMood.setBackground(new java.awt.Color(255, 153, 255));
        cmbMood.setFont(new java.awt.Font("Roboto Cn", 2, 18)); // NOI18N
        cmbMood.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sangat Senang", "Cukup Senang", "Senang", "Sedih", "Cukup Sedih", "Sangat Sedih" }));
        cmbMood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMoodActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sangat Senang", "Cukup Senang", "Senang", "Sedih", "Cukup Sedih", "Sangat Sedih" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Judul)
                            .addComponent(Catatantext)
                            .addComponent(tanggaltext)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSearch)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEkspor)
                                    .addComponent(btnImpor))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TxtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExit)
                                .addGap(331, 331, 331))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(cmbMood, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnTambah))
                            .addComponent(cariTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnUbah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(148, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Judul)
                        .addGap(84, 84, 84)
                        .addComponent(Catatantext)
                        .addGap(201, 201, 201))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnExit)
                        .addGap(116, 116, 116)
                        .addComponent(btnImpor)
                        .addGap(18, 18, 18)
                        .addComponent(btnEkspor)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(TxtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah)
                            .addComponent(btnUbah)
                            .addComponent(btnDelete)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tanggaltext)
                            .addComponent(cmbMood, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cariTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
       String judul = TxtJudul.getText();
    String catatan = TxtCatatan.getText();
    String kategori = (String) cmbMood.getSelectedItem();
    
    if (!judul.isEmpty() && !catatan.isEmpty() && kategori != null ) {
        dbHelper.tambahCatatan( judul, catatan, kategori);
        KontakData(); // Refresh data di JTable
        TxtJudul.setText("");
        TxtCatatan.setText("");
        cmbMood.setSelectedIndex(0);
    } else {
        JOptionPane.showMessageDialog(this, "Kontak Berhasil Ditambahkan!");
    }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
    String keyword = TxtJudul.getText();
DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
tableCatatan.setRowSorter(sorter);

if (keyword.trim().isEmpty()) {
sorter.setRowFilter(null);
} else {
sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
}
     
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        int selectedRow = tableCatatan.getSelectedRow();
    if (selectedRow != -1) {
        tableCatatan.setValueAt(TxtJudul.getText(), selectedRow, 0);
        tableCatatan.setValueAt(TxtCatatan.getText(), selectedRow, 1);
        tableCatatan.setValueAt(cmbMood.getSelectedItem().toString(), selectedRow, 2);
        JOptionPane.showMessageDialog(this, "Catatan berhasil diubah!");
    } else {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diubah!");
    }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tableCatatan.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
        model.removeRow(selectedRow);
        JOptionPane.showMessageDialog(this, "Catatan berhasil dihapus!");
    } else {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEksporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEksporActionPerformed
         JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Simpan File");
int userSelection = fileChooser.showSaveDialog(this);

if (userSelection == JFileChooser.APPROVE_OPTION) {
File fileToSave = fileChooser.getSelectedFile();

try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave + ".csv"))) {
     DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
     for (int i = 0; i < model.getColumnCount(); i++) {
         bw.write(model.getColumnName(i) + ",");
     }
     bw.newLine();
     for (int i = 0; i < model.getRowCount(); i++) {
         for (int j = 0; j < model.getColumnCount(); j++) {
             bw.write(model.getValueAt(i, j).toString() + ",");
         }
         bw.newLine();
     }
     JOptionPane.showMessageDialog(this, "Data berhasil diekspor!");
 } catch (IOException ex) {
     JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
 }
}
    }//GEN-LAST:event_btnEksporActionPerformed

    private void btnImporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImporActionPerformed
        try (BufferedReader reader = new BufferedReader(new FileReader("catatan_harian.csv"))) {
        DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            model.addRow(new Object[]{data[0], data[1], data[2]});
        }
        JOptionPane.showMessageDialog(this, "Data berhasil diimpor!");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Gagal mengimpor data!");
    }
    }//GEN-LAST:event_btnImporActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah ingin keluar dari frorm?", "keluar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void cmbMoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMoodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMoodActionPerformed


   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CatatanHarianFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatatanHarianFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatatanHarianFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatatanHarianFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CatatanHarianFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Catatantext;
    private javax.swing.JLabel Judul;
    private javax.swing.JTextPane TxtCatatan;
    private javax.swing.JTextField TxtJudul;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEkspor;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnImpor;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JTextField cariTxt;
    private javax.swing.JComboBox<String> cmbMood;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableCatatan;
    private javax.swing.JLabel tanggaltext;
    // End of variables declaration//GEN-END:variables

 

    private void KontakData() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Judul", "Catatan", "Kategori"}, 0);
        dbHelper.getCatatanList().forEach((kontak) -> {
            model.addRow(kontak);
        });
        tableCatatan.setModel(model);
    }
    

    
    

   
}

