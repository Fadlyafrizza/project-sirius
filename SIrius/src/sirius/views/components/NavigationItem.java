package sirius.views.components;

import sirius.utils.Constant;

import javax.swing.*;
import java.awt.*;

/**
 * Enum yang merepresentasikan setiap halaman/panel dalam aplikasi.
 * Setiap item enum memiliki label yang akan ditampilkan dan bertanggung jawab
 * untuk membuat instance panelnya sendiri.
 */
public enum NavigationItem {
    DASHBOARD("Dashboard"),
    SEMUA_TUGAS("Semua Tugas"),
    TUGAS_SAYA("Tugas Saya"),
    BUAT_TUGAS_BARU("Buat Tugas Baru"),
    ANGGOTA_KELOMPOK("Anggota Kelompok"),
    PEMBAGIAN_SESI("Pembagian Sesi"),
    MAHASISWA_PAGE("Mahasiswa");

    private final String label;

    NavigationItem(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Membuat dan mengembalikan instance panel yang sesuai untuk item navigasi ini.
     * @param mahasiswaName Nama mahasiswa untuk ditampilkan di dashboard.
     * @return JPanel yang baru dibuat.
     */
    public JPanel createPanel(String mahasiswaName) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Constant.SNOW_COLOR);

        String displayText;
        if (this == DASHBOARD) {
            displayText = "Selamat datang, " + mahasiswaName;
        } else {
            displayText = "Halaman " + this.label;
        }

        JLabel label = new JLabel(displayText);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }
}