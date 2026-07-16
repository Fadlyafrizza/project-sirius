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

import java.awt.event.MouseAdapter;
import java.sql.SQLException;

public class LoginController {
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initialize();
    }

    private void initialize() {
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

}
