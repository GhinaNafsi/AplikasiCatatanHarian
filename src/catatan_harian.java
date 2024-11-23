import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class catatan_harian {
    private Connection conn;

    public catatan_harian() {
            // Membuat koneksi ke database
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
    

   
    // Method untuk menambahkan catatan baru
    public void tambahCatatan(String judul, String catatan, String mood) {
        String sqlInsert = "INSERT INTO datahelper (judul, catatan, kategori) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, judul);
            pstmt.setString(2, catatan);
            pstmt.setString(3, mood);
            pstmt.executeUpdate();
            System.out.println("Catatan berhasil ditambahkan.");
        } catch (SQLException e) {
        }
    }

    // Method untuk menghapus catatan berdasarkan ID
    public void hapusCatatan(int id) {
        String sqlDelete = "DELETE FROM datahelper WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Catatan berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus catatan: " + e.getMessage());
        }
    }

    // Method untuk memperbarui catatan berdasarkan ID
    public void updateCatatan(String judul, String catatan, String mood) {
        String sqlUpdate = "UPDATE datahelper SET judul = ?, catatan = ?, kategori = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {      
            pstmt.setString(1, judul);
            pstmt.setString(2, catatan);
            pstmt.setString(3, mood);
            pstmt.executeUpdate();
            System.out.println("Catatan berhasil diperbarui.");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui catatan: " + e.getMessage());
        }
    }

    
    
    // Method untuk mengambil semua catatan dari database
    public List<String[]> getCatatanList() {
        List<String[]> catatanList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM datahelper";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {
            while (rs.next()) {
                catatanList.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("judul"),
                        rs.getString("catatan"),
                        rs.getString("kategori"),
                });
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data catatan: " + e.getMessage());
        }
        return catatanList;
    }

    Connection getConnection() {
        return conn;
    }

}
