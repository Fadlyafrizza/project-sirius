package sirius.utils;

import sirius.views.components.Message;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class Constant {
    public static final int APP_WIDTH =1614;
    public static final int APP_HEIGHT = 900;

    public static final Color PRIMARY_COLOR = Color.decode("#277943");
    public static final Color LIGHT_PRIMARY_COLOR = Color.decode("#C9EED5");
    public static final Color SNOW_COLOR = Color.decode("#F7F7F7");
    public static final Color ZINC_COLOR = Color.decode("#27272a");
    public static final Color NOTFOCUS_COLOR = Color.decode("#C8C8C8");
    public static final Color ERROR_COLOR = Color.decode("#cf717b");
    public static final Color SECOND_ERROR_COLOR = new Color(250, 219, 216);
    public static final Color FOREGROUND_GRAY_COLOR = new Color(100, 100, 100);

    public static final Font FIELD_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font FIELD_LABEL_FONT = new Font("Arial", Font.PLAIN, 12);

    public static final String SUFFIX_EMAIL = "@mahasiswa.unikom.ac.id";
    public static String PREFIX_EMAIL(String email) {return email.replace(Constant.SUFFIX_EMAIL, "");}

    public static void onFocus (JTextField field, JLabel label) {
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Constant.ZINC_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        label.setForeground(Constant.ZINC_COLOR);
    }

    public static void onFocusLost (JTextField field, JLabel label) {
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Constant.NOTFOCUS_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        label.setForeground(Constant.FOREGROUND_GRAY_COLOR);
    }

    public static void showError(String message, JFrame view) {
        Message.show(view, Message.MessageType.ERROR, message);}
}
