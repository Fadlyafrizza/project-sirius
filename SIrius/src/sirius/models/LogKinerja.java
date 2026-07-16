package sirius.models;

import java.time.LocalDateTime;

public class LogKinerja {
    private int id;
    private int idKelompok;
    private int idMahasiswa;
    private int dicatatOleh;
    private String keterangan;
    private LocalDateTime createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDicatatOleh() {
        return dicatatOleh;
    }

    public void setDicatatOleh(int dicatatOleh) {
        this.dicatatOleh = dicatatOleh;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
