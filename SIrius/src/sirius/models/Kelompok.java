package sirius.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Kelompok {
    private int id;
    private int idSesi;
    private String namaKelompok;
    private int idMahasiswa;
    private LocalDateTime createdAt;

    private List<Mahasiswa> anggota = new ArrayList<>();

    public Kelompok() {}

    public Kelompok(int id, int idSesi, String namaKelompok, int idMahasiswa, LocalDateTime createdAt) {
        this.id = id;
        this.idSesi = idSesi;
        this.namaKelompok = namaKelompok;
        this.idMahasiswa = idMahasiswa;
        this.createdAt = createdAt;
    }

    public Kelompok(int idSesi, String namaKelompok, int idMahasiswa) {
        this.idSesi = idSesi;
        this.namaKelompok = namaKelompok;
        this.idMahasiswa = idMahasiswa;
    }

    public int getId() {
        return id;
    }

    public int getIdSesi() {
        return idSesi;
    }

    public void setIdSesi(int idSesi) {
        this.idSesi = idSesi;
    }

    public String getNamaKelompok() {
        return namaKelompok;
    }

    public void setNamaKelompok(String namaKelompok) {
        this.namaKelompok = namaKelompok;
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

    public List<Mahasiswa> getAnggota() {
        return anggota;
    }

    public void setAnggota(List<Mahasiswa> anggota) {
        this.anggota = anggota;
    }
}
