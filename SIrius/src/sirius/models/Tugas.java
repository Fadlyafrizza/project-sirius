package sirius.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tugas {
    public enum Status {
        BELUM_DIKERJAKAN,
        SEDANG_DIKERJAKAN,
        SELESAI
    }

    private int id;
    private int idKelompok;
    private String namaTugas;
    private String deskripsi;
    private LocalDate deadline;
    private int status;
    private int idMahasiswa;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Tugas() {
    }

    public Tugas(
            int id,
            int idKelompok,
            String namaTugas,
            String deskripsi,
            LocalDate deadline,
            int status,
            int idMahasiswa,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.idKelompok = idKelompok;
        this.namaTugas = namaTugas;
        this.deskripsi = deskripsi;
        this.deadline = deadline;
        this.status = status;
        this.idMahasiswa = idMahasiswa;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Tugas (
            int idKelompok,
            String namaTugas,
            String deskripsi,
            LocalDate deadline,
            int idMahasiswa) {
        this.idKelompok = idKelompok;
        this.namaTugas = namaTugas;
        this.deskripsi = deskripsi;
        this.deadline = deadline;
        this.idMahasiswa = idMahasiswa;
        this.status = 0;
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

    public String getNamaTugas() {
        return namaTugas;
    }

    public void setNamaTugas(String namaTugas) {
        this.namaTugas = namaTugas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
