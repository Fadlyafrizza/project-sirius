package sirius.views;

import sirius.utils.Constant;
import sirius.controllers.DashboardController;
import sirius.models.Mahasiswa;
import sirius.views.components.NavItem;
import sirius.views.components.Navbar;
import sirius.views.components.NavigationItem;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class DashboardView extends Frame {

    final private Mahasiswa mahasiswa;

    private Navbar navbar;
    private JPanel contentPanel; // Panel utama yang menggunakan CardLayout
    private CardLayout cardLayout;

    private NavItem navDashboard;
    private NavItem navSemuaTugas;
    private NavItem navTugasSaya;
    private NavItem navBuatTugasBaru;
    private NavItem navAnggotaKelompok;
    private NavItem navPembagianSesi;
    private NavItem navMahasiswa;
    private NavItem navLogout;

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
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);

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

        navMahasiswa = new NavItem("Mahasiswa");
        navbar.addItem(navMahasiswa);
        navItemMap.put(NavigationItem.MAHASISWA_PAGE, navMahasiswa);

        navLogout = new NavItem("Logout");
        navbar.addItem(navLogout);

        return navbar;
    }

    private JPanel createContentPanel() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Constant.SNOW_COLOR);

        // Tambahkan semua panel ke CardLayout
        for (NavigationItem nav : NavigationItem.values()) {
            contentPanel.add(nav.createPanel(mahasiswa.getNama()), nav.name());
        }

        return contentPanel;
    }

    /**
     * Menampilkan panel yang sesuai berdasarkan enum NavigationItem.
     * @param nav Enum item yang akan ditampilkan.
     */
    public void showPage(NavigationItem nav) {
        cardLayout.show(contentPanel, nav.name());
    }

    public NavigationItem findNavigationItem(NavItem navItem) {
        for (Map.Entry<NavigationItem, NavItem> entry : navItemMap.entrySet()) {
            if (entry.getValue() == navItem) {
                return entry.getKey();
            }
        }
        return null;
    }

    public NavItem findNavItem(NavigationItem navigationItem) {
        return navItemMap.get(navigationItem);
    }

    public Navbar getNavbar() {
        return navbar;
    }

    public NavItem getNavLogout() {
        return navLogout;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }
}