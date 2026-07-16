package sirius.controllers;

import sirius.utils.BCrypt;
import sirius.utils.Constant;
import sirius.utils.JwtUtil;
import sirius.utils.Session;
import sirius.config.DatabaseConnection;
import sirius.dao.MahasiswaDAO;
import sirius.models.Mahasiswa;
import sirius.views.DashboardView;
import sirius.views.auth.LoginView;
import sirius.views.auth.RegisterView;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginController {
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initialize();
    }

    private void initialize() {
        setupEmailField();

        loginView.getLoginButton().addActionListener(event -> handleLogin());

        loginView.getRegisterLink().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleRegisterLink();
            }
        });

    }

    private void handleLogin() {
        String email = loginView.getEmailField().getText().trim();
        String password = new String(loginView.getPasswordField().getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            Constant.showError("Email dan password tidak boleh kosong.", loginView);
            return;
        }

        try {
            MahasiswaDAO mhsDAO = new MahasiswaDAO(DatabaseConnection.getKoneksi());

            Mahasiswa mahasiswa = mhsDAO.findByEmail(email);

            if (mahasiswa == null) {
                Constant.showError("Email atau password salah.", loginView);
                return;
            }

            boolean isPasswordValid = BCrypt.checkpw(password, mahasiswa.getPassword());

            if (!isPasswordValid) {
                Constant.showError("Email atau password salah.", loginView);
                return;
            }

            String token = JwtUtil.generateToken(mahasiswa.getEmail(), mahasiswa.getRole().name());
            Session.saveToken(token);
            System.out.println(Session.getToken());

            new DashboardView(mahasiswa).setVisible(true);
            loginView.dispose();

            loginView.getPasswordField().setText("");


        } catch (SQLException e) {
            System.err.println("Database error saat Register: " + e.getMessage());
            Constant.showError("Terjadi kesalahan pada server. Coba lagi nanti.", loginView);
        }
    }

    private void handleRegisterLink() {
        new RegisterView().setVisible(true);
        loginView.dispose();
    }

    private void setupEmailField() {
        JTextField emailField = loginView.getEmailField();
        // Set teks awal
        emailField.setText(Constant.SUFFIX_EMAIL);
        // Tempatkan kursor di depan suffix
        emailField.setCaretPosition(0);

        // Tambahkan DocumentFilter untuk mencegah penghapusan / modifikasi pada suffix email
        ((AbstractDocument) emailField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                // Cegah penambahan teks di area suffix
                if (offset <= fb.getDocument().getLength() - Constant.SUFFIX_EMAIL.length()) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Cegah penghapusan atau penggantian di area suffix
                if (offset < fb.getDocument().getLength() - Constant.SUFFIX_EMAIL.length() + 1) {
                    int maxReplaceLength = Math.min(length, (fb.getDocument().getLength() - Constant.SUFFIX_EMAIL.length()) - offset);
                    if (maxReplaceLength >= 0) {
                        super.replace(fb, offset, maxReplaceLength, text, attrs);
                    }
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                // Cegah penghapusan di area suffix
                if (offset < fb.getDocument().getLength() - Constant.SUFFIX_EMAIL.length()) {
                    int maxRemoveLength = Math.min(length, (fb.getDocument().getLength() - Constant.SUFFIX_EMAIL.length()) - offset);
                    super.remove(fb, offset, maxRemoveLength);
                }
            }
        });

        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                int suffixLength = Constant.SUFFIX_EMAIL.length();
                int textLength = emailField.getDocument().getLength();
                int maxCaretPosition = Math.max(0, textLength - suffixLength);

                // Memastikan caret tidak melebihi batas editable
                if (emailField.getCaretPosition() > maxCaretPosition) {
                    emailField.setCaretPosition(maxCaretPosition);
                }
            }
        });

        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                int textLength = emailField.getDocument().getLength();
                int maxCaret = Math.max(0, textLength - Constant.SUFFIX_EMAIL.length());
                emailField.setCaretPosition(maxCaret);
            }
        });

        emailField.addCaretListener(e -> {
            int editableLength = emailField.getDocument().getLength() - Constant.SUFFIX_EMAIL.length();
            if (emailField.getCaretPosition() > editableLength) {
                emailField.setCaretPosition(editableLength);
            }
        });
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int suffixStart = emailField.getDocument().getLength() - Constant.SUFFIX_EMAIL.length();

                if (e.getKeyCode() == KeyEvent.VK_RIGHT &&
                        emailField.getCaretPosition() >= suffixStart) {
                    e.consume(); // blokir gerakan ke kanan
                }
            }
        });
    }

}
