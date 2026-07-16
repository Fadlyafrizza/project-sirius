package sirius.models;

import java.time.LocalDateTime;

public class SesiPembagian {
    private int id;
    private int idMatkul;
    private String namaSesi;
    private int idMahasiswa;
    private String passwordSesi;
    private LocalDateTime createdAt;

    public SesiPembagian() {}

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMatkul() {
        return idMatkul;
    }

    public void setIdMatkul(int idMatkul) {
        this.idMatkul = idMatkul;
    }

    public SesiPembagian(int id, String namaSesi ,
                         int idMahasiswa, LocalDateTime createdAt) {
        this.id = id;
        this.namaSesi = namaSesi;
        this.idMahasiswa = idMahasiswa;
        this.createdAt = createdAt;
    }

    public SesiPembagian(String namaSesi, int idMahasiswa) {
        this.namaSesi = namaSesi;
        this.idMahasiswa = idMahasiswa;
    }

    public int getId() {
        return id;
    }

    public String getNamaSesi() {
        return namaSesi;
    }

    public void setNamaSesi(String namaSesi) {
        this.namaSesi = namaSesi;
    }

    public int getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public String getPasswordSesi() {
        return passwordSesi;
    }

    public void setPasswordSesi(String passwordSesi) {
        this.passwordSesi = passwordSesi;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
