package sirius.models;

import java.time.LocalDateTime;

public class LogNotifikasi {
    private int id;
    private int kelompokId;
    private String pesan;
    private boolean dibaca;
    private LocalDateTime createdAt;

    public LogNotifikasi() {
    }

    public LogNotifikasi(
            int id,
            int kelompokId,
            String pesan,
            boolean dibaca,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.kelompokId = kelompokId;
        this.pesan = pesan;
        this.dibaca = dibaca;
        this.createdAt = createdAt;
    }

    public LogNotifikasi(int kelompokId, String pesan) {
        this.kelompokId = kelompokId;
        this.pesan = pesan;
        this.dibaca = false;
    }

    public int getId() {
        return id;
    }

    public int getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(int kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public boolean isDibaca() {
        return dibaca;
    }

    public void setDibaca(boolean dibaca) {
        this.dibaca = dibaca;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
