package sirius.views.components;

import sirius.utils.Constant;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Navbar extends JPanel {

    private static final Font ITEM_FONT = new Font("SansSerif", Font.PLAIN, 12);
    private static final int NAVBAR_HEIGHT = 45;
    private final List<NavItem> navItems = new ArrayList<>();
    private Consumer<NavItem> onNavItemClick;

    public Navbar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 6));
        setBackground(Constant.SNOW_COLOR);
        Border borderBottom = BorderFactory.createMatteBorder(0, 0, 1, 0, Constant.ZINC_COLOR);

        setBorder(BorderFactory.createCompoundBorder(borderBottom, BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        setPreferredSize(new Dimension(0, NAVBAR_HEIGHT));
    }

    /**
     * Daftarkan callback yang dipanggil setiap kali NavItem diklik.
     * Controller menggunakan ini untuk menerima event navigasi.
     */
    public void setOnNavItemClick(Consumer<NavItem> listener) {
        this.onNavItemClick = listener;
    }

    public void addItem(NavItem item) {
        navItems.add(item);
        add(createMenuButton(item));
    }

    private RoundedButton createMenuButton(NavItem item) {
        ImageIcon suffix = null;
        if (item.hasChildren()) {
            URL iconUrl = this.getClass().getResource("/sirius/resources/chevron-down.png");
            if (iconUrl != null) {
                ImageIcon originalIcon = new ImageIcon(iconUrl);
                Image scaledImg = originalIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH);
                suffix = new ImageIcon(scaledImg);
            }
        }

        RoundedButton button = new RoundedButton(item.getLabel(), 8);
        item.setButton(button); // Simpan referensi tombol di NavItem

        if (suffix != null) {
            button.setIcon(suffix);
            button.setHorizontalTextPosition(SwingConstants.LEFT);
            button.setVerticalTextPosition(SwingConstants.CENTER);
            button.setIconTextGap(8);
        }

        styleButton(button, item);

        if (item.hasChildren()) {
            JPopupMenu submenu = buildSubmenu(item);
            button.addActionListener(e -> submenu.show(button, 0, button.getHeight()));
        } else {
            button.addActionListener(e -> notifyNavItemClick(item));
        }

        return button;
    }

    private JPopupMenu buildSubmenu(NavItem parent) {
        JPopupMenu popup = new JPopupMenu();
        popup.setBackground(Constant.PRIMARY_COLOR);
        popup.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));

        for (NavItem child : parent.getChildren()) {
            JMenuItem menuItem = new JMenuItem(child.getLabel());
            menuItem.setFont(ITEM_FONT);
            menuItem.setForeground(Constant.ZINC_COLOR);
            menuItem.setBackground(Constant.PRIMARY_COLOR);
            menuItem.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 30));
            menuItem.setOpaque(true);

            menuItem.addActionListener(e -> notifyNavItemClick(child));
            popup.add(menuItem);
        }
        return popup;
    }

    /**
     * Notifikasi controller bahwa NavItem diklik.
     * Tidak langsung mengubah state visual — controller yang menentukan.
     */
    private void notifyNavItemClick(NavItem item) {
        if (onNavItemClick != null) {
            onNavItemClick.accept(item);
        }
    }

    private void styleButton(RoundedButton button, NavItem item) {
        button.setFont(ITEM_FONT);
        button.setForeground(Constant.ZINC_COLOR);
        button.setBackground(Constant.SNOW_COLOR);

        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        Dimension prefSize = button.getPreferredSize();
        button.setPreferredSize(new Dimension(prefSize.width, NAVBAR_HEIGHT - 15));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (item.isActive()) return;
                button.setBackground(Constant.PRIMARY_COLOR);
                button.setForeground(Constant.SNOW_COLOR);
                button.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (item.isActive()) return;
                button.setBackground(Constant.SNOW_COLOR);
                button.setForeground(Constant.ZINC_COLOR);
                button.repaint();
            }
        });
    }

    /**
     * Set NavItem yang aktif. Hanya satu item yang boleh aktif pada satu waktu.
     * Mendukung child items — jika child aktif, parent button yang di-highlight.
     */
    public void setActive(NavItem activeItem) {
        // Reset semua item (top-level + children)
        for (NavItem item : navItems) {
            item.setActive(false);
            applyInactiveStyle(item);

            for (NavItem child : item.getChildren()) {
                child.setActive(false);
            }
        }

        // Cari dan aktifkan item yang tepat
        for (NavItem item : navItems) {
            // Cek apakah top-level item yang dipilih
            if (item == activeItem) {
                item.setActive(true);
                applyActiveStyle(item);
                return;
            }

            // Cek apakah salah satu child yang dipilih
            for (NavItem child : item.getChildren()) {
                if (child == activeItem) {
                    child.setActive(true);
                    // Highlight parent button karena child tidak punya button di navbar
                    item.setActive(true);
                    applyActiveStyle(item);
                    return;
                }
            }
        }
    }

    private void applyActiveStyle(NavItem item) {
        if (item.getButton() == null) return;
        item.getButton().setBackground(Constant.PRIMARY_COLOR);
        item.getButton().setForeground(Constant.SNOW_COLOR);
        item.getButton().repaint();
    }

    private void applyInactiveStyle(NavItem item) {
        if (item.getButton() == null) return;
        item.getButton().setBackground(Constant.SNOW_COLOR);
        item.getButton().setForeground(Constant.ZINC_COLOR);
        item.getButton().repaint();
    }

    public List<NavItem> getNavItems() {
        return navItems;
    }
}