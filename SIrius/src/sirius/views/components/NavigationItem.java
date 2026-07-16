package sirius.views.components;

/**
 * Enum yang memetakan setiap halaman navigasi ke index tab pada JTabbedPane.
 * halaman baru cukup tambah entry di sini.
 */
public enum NavigationItem {
    DASHBOARD(0, "Dashboard"),
    SEMUA_TUGAS(1, "Semua Tugas"),
    TUGAS_SAYA(2, "Tugas Saya"),
    BUAT_TUGAS_BARU(3, "Buat Tugas Baru"),
    ANGGOTA_KELOMPOK(4, "Anggota Kelompok"),
    PEMBAGIAN_SESI(5, "Pembagian Sesi");

    private final int tabIndex;
    private final String label;

    NavigationItem(int tabIndex, String label) {
        this.tabIndex = tabIndex;
        this.label = label;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Cari NavigationItem berdasarkan tab index.
     * @return NavigationItem yang cocok, atau null jika tidak ditemukan.
     */
    public static NavigationItem fromTabIndex(int index) {
        for (NavigationItem item : values()) {
            if (item.tabIndex == index) {
                return item;
            }
        }
        return null;
    }
}
