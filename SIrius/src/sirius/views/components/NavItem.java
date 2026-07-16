package sirius.views.components;

import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;

public class NavItem {

    private final String label;
    private final List<NavItem> children = new ArrayList<>();
    private Runnable onClick;
    private JButton button; // Tambahkan referensi ke JButton
    private boolean active;

    public NavItem(String label) {
        this.label = label;
    }

    public NavItem(String label, Runnable onClick) {
        this.label = label;
        this.onClick = onClick;
    }

    public NavItem addChild(NavItem child) {
        children.add(child);
        return this;
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

    public String getLabel() {
        return label;
    }

    public List<NavItem> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public Runnable getOnClick() {
        return onClick;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public JButton getButton() {
        return button;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}