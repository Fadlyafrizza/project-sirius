package sirius.controllers;

import sirius.utils.BCrypt;
import sirius.utils.Constant;
import sirius.config.DatabaseConnection;
import sirius.dao.MahasiswaDAO;
import sirius.views.auth.LoginView;
import sirius.views.auth.RegisterView;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JTextField;

import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import java.sql.SQLException;

public class RegisterController {

    private final RegisterView registerView;

    public RegisterController(RegisterView view) {
        this.registerView = view;

        initialize();
    }

    private void initialize() {
        setupEmailField();

        registerView.getRegisterButton().addActionListener(event -> handleRegister());
        registerView.getLoginLink().addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {
                        handleLoginLink();
                    }
                }
        );
    }

    private void setupEmailField() {
        JTextField emailField = registerView.getEmailField();
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

    private void handleLoginLink() {
        new LoginView().setVisible(true);
        registerView.dispose();
    }

    private void handleRegister() {
        String nama = registerView.getNamaField().getText().trim();
        String email = registerView.getEmailField().getText().trim();
        String nim = registerView.getNimField().getText().trim();
        String password = new String(registerView.getPasswordField().getPassword());
        String rePassword = new String(registerView.getRePasswordField().getPassword());
        String hashedPassword = null;

        String prefixEmail = Constant.PREFIX_EMAIL(email);
        String extractedValue = "";

        if (prefixEmail.contains(".")) {
            extractedValue = prefixEmail.substring(prefixEmail.lastIndexOf('.') + 1);
        } else {
            extractedValue = prefixEmail;
        }

        boolean isPasswordValid = password.equals(rePassword);

        if (nama.isEmpty() || email.equals(Constant.SUFFIX_EMAIL) || email.isEmpty() || nim.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Constant.showError("Semua field tidak boleh kosong.", registerView);
            return;
        }

        if (!isPasswordValid) {
            Constant.showError("Password tidak sama", registerView);
            return;
        }

        if (isPasswordValid) {
            hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        }

        try {
            MahasiswaDAO mhsDAO = new MahasiswaDAO(DatabaseConnection.getKoneksi());

            if(mhsDAO.checkDuplicateEmail(email)) {
                Constant.showError("Email sudah terdaftar.", registerView);
                return;
            }

            if(!nim.equals(extractedValue)) {
                Constant.showError("NIM tidak sama dengan email.", registerView);
                return;
            }

            if(mhsDAO.checkDuplicateNIM(nim)) {
                Constant.showError("Nim sudah terdaftar.", registerView);
                return;
            }

            mhsDAO.createMahasiswa(nim, nama, email, hashedPassword);

            new LoginView().setVisible(true);
            registerView.dispose();

        } catch (SQLException e) {
            System.err.println("Database error saat login: " + e.getMessage());
            Constant.showError("Terjadi kesalahan pada server. Coba lagi nanti.", registerView);
        }

    }
}