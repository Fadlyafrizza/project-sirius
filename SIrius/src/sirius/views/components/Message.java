package sirius.views.components;

import sirius.utils.Constant;

import javax.swing.*;
import java.awt.*;

/**
 * Toast notifikasi yang melayang di atas semua komponen lain (pojok kanan atas window),
 * mirip toast di aplikasi mobile/web. Otomatis hilang setelah beberapa detik.
 *
 * Cara pakai (dari mana saja yang punya akses ke JFrame-nya, misal DashboardView):
 *   Message.show(this, Message.MessageType.SUCCESS, "Data berhasil disimpan!");
 *
 * Beda dari versi sebelumnya: sekarang gak perlu di-add manual ke panel/layout.
 * Method show() yang urus semuanya -> nempel ke layered pane, posisi, dan hilang sendiri.
 */
public class Message extends JPanel {

    private static final int WIDTH = 320;
    private static final int HEIGHT = 60;
    private static final int MARGIN = 20;
    private static final int DEFAULT_DURATION_MS = 3000;

    public enum MessageType {
        SUCCESS(new Color(39, 174, 96), new Color(214, 245, 226)),
//        ERROR(new Color(192, 57, 43), new Color(250, 219, 216));
        ERROR(Constant.ERROR_COLOR, Constant.SECOND_ERROR_COLOR);


        final Color borderColor;
        final Color backgroundColor;

        MessageType(Color borderColor, Color backgroundColor) {
            this.borderColor = borderColor;
            this.backgroundColor = backgroundColor;
        }
    }

    private final MessageType messageType;
    private final JLabel lbMessage;

    private Message(MessageType messageType, String text) {
        this.messageType = messageType;

        setOpaque(false);
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        lbMessage = new JLabel(text);
        lbMessage.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        lbMessage.setForeground(messageType.borderColor);
        lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lbMessage.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        add(lbMessage, BorderLayout.CENTER);
    }

    /** Tampilkan toast, otomatis hilang setelah 3 detik. */
    public static void show(JFrame frame, MessageType type, String text) {
        show(frame, type, text, DEFAULT_DURATION_MS);
    }

    /** Tampilkan toast dengan durasi custom (ms). */
    public static void show(JFrame frame, MessageType type, String text, int durationMillis) {
        Message toast = new Message(type, text);

        JLayeredPane layeredPane = frame.getLayeredPane();

        int x = Constant.APP_WIDTH / 2 - WIDTH / 2;
        int y = MARGIN;
        toast.setBounds(x, y, WIDTH, HEIGHT);

        // POPUP_LAYER supaya toast selalu di paling atas, gak ketutupan komponen lain
        layeredPane.add(toast, JLayeredPane.POPUP_LAYER);
        layeredPane.repaint();

        Timer autoHideTimer = new Timer(durationMillis, e -> {
            layeredPane.remove(toast);
            layeredPane.repaint();
        });
        autoHideTimer.setRepeats(false);
        autoHideTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(messageType.backgroundColor);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

        g2.setColor(messageType.borderColor);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

        g2.dispose();
    }
}