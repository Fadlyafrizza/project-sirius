package sirius.models;

import java.time.LocalDateTime;

public class AnggotaKelompok {
    private int id;
    private int idKelompok;
    private int idMahasiswa;
    private LocalDateTime createdAt;

    public AnggotaKelompok() {}

    public AnggotaKelompok(int id, int idKelompok, int idMahasiswa, LocalDateTime createdAt) {
        this.id = id;
        this.idKelompok = idKelompok;
        this.idMahasiswa = idMahasiswa;
        this.createdAt = createdAt;
    }

    public AnggotaKelompok(int idKelompok, int idMahasiswa) {
        this.idKelompok = idKelompok;
        this.idMahasiswa = idMahasiswa;
    }

    public int getId() {
        return id;
    }

    public int getIdKelompok() {
        return idKelompok;
    }

    public void setIdKelompok(int idKelompok) {
        this.idKelompok = idKelompok;
    }

    public int getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
