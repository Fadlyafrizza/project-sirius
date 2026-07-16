package sirius.dao;

import sirius.models.Mahasiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {
    private final Connection conn;

    public MahasiswaDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Mahasiswa> getAllMahasiswa() throws SQLException {
        final String SQL = "SELECT * FROM mahasiswa";
        List<Mahasiswa> mahasiswaList = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                mahasiswaList.add(mapResultSetToMahasiswa(rs));
            }
        }
        return mahasiswaList;
    }

    public boolean checkDuplicateEmail(String email) throws SQLException {
        final String SQL = "SELECT 1 FROM mahasiswa WHERE email = ? LIMIT 1";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean checkDuplicateNIM(String nim) throws SQLException {
        final String SQL = "SELECT 1 FROM mahasiswa WHERE nim = ? LIMIT 1";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, nim);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Mahasiswa findByEmail(String email) throws SQLException {
        final String SQL = "SELECT * FROM mahasiswa WHERE email = ? LIMIT 1";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMahasiswa(rs);
                }
            }
        }
        return null;
    }

    public void createMahasiswa(String nim, String nama, String email, String password) throws SQLException {
        final String SQL = "INSERT INTO mahasiswa (nim, nama, email, password) VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, nim);
            stmt.setString(2, nama);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.executeUpdate();

//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    mapResultSetToMahasiswa(rs);
//                }
//            }
        }
    }

    private Mahasiswa mapResultSetToMahasiswa(ResultSet rs) throws SQLException {
        Mahasiswa mhs = new Mahasiswa();
        mhs.setId(rs.getInt("id"));
        mhs.setNim(rs.getString("nim"));
        mhs.setNama(rs.getString("nama"));
        mhs.setEmail(rs.getString("email"));
        mhs.setPassword(rs.getString("password"));
        mhs.setRole(Mahasiswa.Role.valueOf(rs.getString("role").toUpperCase()));
        mhs.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        mhs.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return mhs;
    }
}