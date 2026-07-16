package sirius.controllers;

import sirius.utils.Session;
import sirius.views.DashboardView;
import sirius.views.auth.LoginView;
import sirius.views.components.NavItem;
import sirius.views.components.NavigationItem;

public class DashboardController {

    private final DashboardView view;

    public DashboardController(DashboardView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        // Set listener untuk semua item navigasi
        view.getNavbar().setOnNavItemClick(this::handleNavigation);

        // Tampilkan halaman default
        handleNavigation(view.findNavItem(NavigationItem.DASHBOARD));
    }

    /**
     * Menangani event klik pada item navigasi.
     * @param navItem Item yang diklik.
     */
    private void handleNavigation(NavItem navItem) {
        if (navItem == view.getNavLogout()) {
            handleLogout();
            return;
        }

        NavigationItem targetPage = view.findNavigationItem(navItem);
        if (targetPage != null) {
            // Ganti halaman di CardLayout
            view.showPage(targetPage);
            // Set item aktif di Navbar
            view.getNavbar().setActive(navItem);
        }
    }

    private void handleLogout() {
        Session.clearSession();
        view.dispose();
        new LoginView().setVisible(true);
    }
}