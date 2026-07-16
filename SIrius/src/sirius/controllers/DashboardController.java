package sirius.controllers;

import sirius.utils.Session;
import sirius.views.DashboardView;
import sirius.views.auth.LoginView;
import sirius.views.components.NavItem;
import sirius.views.components.NavigationItem;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DashboardController {

    private final DashboardView view;

    /**
     * Guard flag untuk mencegah recursive update antara navbar dan JTabbedPane.
     * Ketika true, ChangeListener pada JTabbedPane akan skip update navbar
     * karena perubahan tab sedang dilakukan oleh controller sendiri.
     */
    private boolean isNavigating = false;

    public DashboardController(DashboardView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        registerNavbarListener();
        registerTabChangeListener();

        // Set halaman default
        navigateTo(NavigationItem.DASHBOARD);
    }

    /**
     * Daftarkan callback pada Navbar.
     * Setiap kali NavItem diklik, controller menentukan aksi yang tepat.
     */
    private void registerNavbarListener() {
        view.getNavbar().setOnNavItemClick(this::handleNavItemClick);
    }

    /**
     * Daftarkan ChangeListener pada JTabbedPane.
     * Listener ini menangani kasus ketika tab berubah secara programmatic
     * (misalnya via setSelectedIndex) — navbar harus ikut ter-update.
     */
    private void registerTabChangeListener() {
        view.getTabbedPane().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (isNavigating) return;
                syncNavbarFromTab();
            }
        });
    }

    /**
     * Handle klik pada NavItem.
     * Jika item adalah Logout, jalankan handleLogout.
     * Jika item adalah halaman navigasi, pindah ke tab yang sesuai.
     */
    private void handleNavItemClick(NavItem clickedItem) {
        if (clickedItem == view.getNavLogout()) {
            handleLogout();
            return;
        }

        NavigationItem target = view.findNavigationItem(clickedItem);
        if (target == null) return;

        navigateTo(target);
    }

    /**
     * Pindahkan ke halaman tertentu.
     * Method ini menjadi satu-satunya entry point untuk navigasi,
     * memastikan state JTabbedPane dan Navbar selalu sinkron.
     */
    public void navigateTo(NavigationItem target) {
        isNavigating = true;

        try {
            view.getTabbedPane().setSelectedIndex(target.getTabIndex());

            NavItem navItem = view.findNavItem(target);
            if (navItem != null) {
                view.getNavbar().setActive(navItem);
            }
        } finally {
            isNavigating = false;
        }
    }

    /**
     * Sinkronisasi navbar berdasarkan tab yang sedang aktif.
     * Dipanggil oleh ChangeListener ketika tab berubah secara programmatic
     * tanpa melalui navigateTo() (edge case safety).
     */
    private void syncNavbarFromTab() {
        int selectedIndex = view.getTabbedPane().getSelectedIndex();
        NavigationItem current = NavigationItem.fromTabIndex(selectedIndex);
        if (current == null) return;

        NavItem navItem = view.findNavItem(current);
        if (navItem != null) {
            view.getNavbar().setActive(navItem);
        }
    }

    private void handleLogout() {
        Session.clearSession();
        view.dispose();
        new LoginView().setVisible(true);
    }
}
