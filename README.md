# AplikasiCatatanHarian
 GhinaNafsi-2210010324-UTS-PBO2

1. Deskripsi Program
- Aplikasi ini memungkinkan pengguna untuk:
  pengguna menyimpan catatan harian dan bisa menambahkan mood nya seperti keadaaan suasana hati dan mengorganisasi aktivitas, pemikiran, atau kejadian sehari-hari. Aplikasi ini dapat digunakan untuk berbagai keperluan, mulai dari menulis jurnal pribadi, mencatat ide kreatif, hingga mencatat tugas harian.

2. Komponen GUI: JFrame, JPanel, JLabel, JTextField, JTextArea, JButton, JList, JComboBox, JTable, JScrollPane
- JFrame: Window utama aplikasi.
- JPanel: Panel untuk menampung komponen-komponen.
- JLabel: informasi atau tanda untuk setiap komponen
- JTextField: Untuk Input Judul dan cari
- JTextArea: Untuk meinput catatan 
- JButton: Tombol untuk menambah, mengedit, menghapus, keluar, mencari, mengekspor, dan mengimpor.
- JComboBox: Dropdown untuk memilih kategori mood.
- JTable: Tabel untuk menampilkan catatan yang ditambah.

3. Logika Program: database SQLite, fitur CRUD (Create, Read, Update, Delete)
Menggunakan database SQLite untuk menyimpan data catatan.
Menampilkan List catatan di JTable.
Menyimpan dan memuat catatan dari file CSV.

4. Events:
ActionListener untuk tombol Tambah, Edit, Hapus, dan Cari judul catatan.

A. Kode Program

1. Create Table untuk database Catatan
   ~~~
    try {
        conn = DriverManager.getConnection("jdbc:sqlite:datahelper.db");
        createTable(); // Memastikan tabel diperbarui saat inisialisasi
    } catch (SQLException e) {
        System.out.println("Gagal menghubungkan ke database: " + e.getMessage());
    }
    }

    private void createTable() {
        // Membuat tabel jika belum ada
    String sqlCreateTable = "CREATE TABLE IF NOT EXISTS datahelper (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "judul TEXT NULL, " +
                            "catatan TEXT NULL, " +
                            "kategori TEXT)"; // Kolom kategori untuk ComboBox

    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sqlCreateTable);
        System.out.println("Tabel datahelper berhasil dibuat atau diperbarui.");
    } catch (SQLException e) {
        System.out.println("Gagal membuat atau memperbarui tabel: " + e.getMessage());
    }
    }


2. Tombol Tambah Catatan
     private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {
   ~~~                                      
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
    }   

3. Tombol Update atau edit
     private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {
   ~~~                                
        int selectedRow = tableCatatan.getSelectedRow();
    if (selectedRow != -1) {
        tableCatatan.setValueAt(TxtJudul.getText(), selectedRow, 0);
        tableCatatan.setValueAt(TxtCatatan.getText(), selectedRow, 1);
        tableCatatan.setValueAt(cmbMood.getSelectedItem().toString(), selectedRow, 2);
        JOptionPane.showMessageDialog(this, "Catatan berhasil diubah!");
    } else {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diubah!");
    }
    }


   4. Tombol Hapus
   private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {    
   
        int selectedRow = tableCatatan.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
        model.removeRow(selectedRow);
        JOptionPane.showMessageDialog(this, "Catatan berhasil dihapus!");
    } else {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
    }
    }     

   
5. TOmbol Ekspor dan Impor
   -Eksopor
    private void btnEksporActionPerformed(java.awt.event.ActionEvent evt) {
   ~~~                                       
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
   }}
    }   
    ~~~

   - Impor
      private void btnImporActionPerformed(java.awt.event.ActionEvent evt) {
~~~
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
    }
~~~
6. Tombol Keluar
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {                                        
~~~
      int confirm = JOptionPane.showConfirmDialog(this, "Apakah ingin keluar dari frorm?", "keluar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
~~~

7. Cari
 private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
~~~
 String keyword = TxtJudul.getText();
DefaultTableModel model = (DefaultTableModel) tableCatatan.getModel();
TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
tableCatatan.setRowSorter(sorter);

if (keyword.trim().isEmpty()) {
sorter.setRowFilter(null);
} else {
sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
}
     
    }
~~~

  ##Tampilan Form saat dijalankan
<img width="741" alt="Catatan HArian" src="https://github.com/user-attachments/assets/646c9d7e-e2f9-44c1-bca7-00a2ddee81b5">

## Indikator Penilaian:

| No  | Komponen                        |  Persentase  |
| :-: | --------------                  |   :-----:    |
|  1  | Fungsional Aplikasi             |    20        |
|  2  | Desain dan UX                   |    30        |
|  3  | Penerapan konsep OOP            |    15        |
|  4  | Kreatifitas dan inovasi fitur   |    15        |
|  5  | Dokumentasi Kode                |    20        |
|     | TOTAL                           |    80        |

Tantangan                                   20

NAMA : GHINA NAFSI
NPM : 2210010324
KELAS 5A TI REG PAGIBJM
