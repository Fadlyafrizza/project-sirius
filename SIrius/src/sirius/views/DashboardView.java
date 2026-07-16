package sirius.views;

import sirius.utils.Constant;
import sirius.controllers.DashboardController;
import sirius.models.Mahasiswa;
import sirius.views.components.NavItem;
import sirius.views.components.Navbar;
import sirius.views.components.NavigationItem;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class DashboardView extends Frame {

    final private Mahasiswa mahasiswa;

    private Navbar navbar;
    private JTabbedPane tabbedPane;

    private NavItem navDashboard;
    private NavItem navSemuaTugas;
    private NavItem navTugasSaya;
    private NavItem navBuatTugasBaru;
    private NavItem navAnggotaKelompok;
    private NavItem navPembagianSesi;
    private NavItem navLogout;

    /**
     * Mapping dari NavigationItem enum ke NavItem instance.
     * Digunakan oleh controller untuk sinkronisasi tab → navbar.
     */
    private final Map<NavigationItem, NavItem> navItemMap = new EnumMap<>(NavigationItem.class);

    public DashboardView(Mahasiswa mahasiswa) {
        super("Dashboard");
        this.mahasiswa = mahasiswa;

        setContentPane(createPanel());

        new DashboardController(this);
    }

    private JPanel createPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Constant.SNOW_COLOR);

        mainPanel.add(createNavbar(), BorderLayout.NORTH);
        mainPanel.add(createTabbedPane(), BorderLayout.CENTER);

        return mainPanel;
    }

    private Navbar createNavbar() {
        navbar = new Navbar();

        navDashboard = new NavItem("Dashboard");
        navbar.addItem(navDashboard);
        navItemMap.put(NavigationItem.DASHBOARD, navDashboard);

        NavItem tugas = new NavItem("Tugas");
        navSemuaTugas = new NavItem("Semua Tugas");
        navTugasSaya = new NavItem("Tugas Saya");
        navBuatTugasBaru = new NavItem("Buat Tugas Baru");
        tugas.addChild(navSemuaTugas);
        tugas.addChild(navTugasSaya);
        tugas.addChild(navBuatTugasBaru);
        navbar.addItem(tugas);
        navItemMap.put(NavigationItem.SEMUA_TUGAS, navSemuaTugas);
        navItemMap.put(NavigationItem.TUGAS_SAYA, navTugasSaya);
        navItemMap.put(NavigationItem.BUAT_TUGAS_BARU, navBuatTugasBaru);

        NavItem kelompok = new NavItem("Kelompok");
        navAnggotaKelompok = new NavItem("Anggota Kelompok");
        navPembagianSesi = new NavItem("Pembagian Sesi");
        kelompok.addChild(navAnggotaKelompok);
        kelompok.addChild(navPembagianSesi);
        navbar.addItem(kelompok);
        navItemMap.put(NavigationItem.ANGGOTA_KELOMPOK, navAnggotaKelompok);
        navItemMap.put(NavigationItem.PEMBAGIAN_SESI, navPembagianSesi);

        navLogout = new NavItem("Logout");
        navbar.addItem(navLogout);

        return navbar;
    }

    /**
     * Membuat JTabbedPane dengan tab header disembunyikan.
     * Tab header tidak diperlukan karena navigasi dilakukan melalui custom Navbar.
     */
    private JTabbedPane createTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Constant.SNOW_COLOR);

        // Sembunyikan tab header bawaan — kita pakai Navbar sebagai penggantinya
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0;
            }

            @Override
            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
                // Tidak menggambar tab area
            }
        });

        // Tambahkan tab sesuai urutan NavigationItem enum
        for (NavigationItem nav : NavigationItem.values()) {
            tabbedPane.addTab(nav.getLabel(), createPagePlaceholder(nav));
        }

        return tabbedPane;
    }

    /**
     * Membuat placeholder panel untuk setiap halaman.
     * Nanti bisa diganti dengan panel view yang sebenarnya.
     */
    private JPanel createPagePlaceholder(NavigationItem nav) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Constant.SNOW_COLOR);

        String displayText = nav == NavigationItem.DASHBOARD
                ? "Selamat datang, " + mahasiswa.getNama()
                : "Halaman " + nav.getLabel();

        JLabel label = new JLabel(displayText);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    // === Getters untuk Controller ===

    public Navbar getNavbar() {
        return navbar;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public NavItem getNavLogout() {
        return navLogout;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    /**
     * Cari NavigationItem berdasarkan NavItem instance.
     * @return NavigationItem yang cocok, atau null jika NavItem bukan halaman (misalnya Logout).
     */
    public NavigationItem findNavigationItem(NavItem navItem) {
        for (Map.Entry<NavigationItem, NavItem> entry : navItemMap.entrySet()) {
            if (entry.getValue() == navItem) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Cari NavItem berdasarkan NavigationItem enum.
     * Digunakan controller untuk sinkronisasi tab index → navbar item.
     */
    public NavItem findNavItem(NavigationItem navigationItem) {
        return navItemMap.get(navigationItem);
    }
}
