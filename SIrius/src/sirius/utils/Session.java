package sirius.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import sirius.config.DatabaseConnection;
import sirius.dao.MahasiswaDAO;
import sirius.models.Mahasiswa;

import java.util.prefs.Preferences;

public class Session {
    public static final Preferences prefs = Preferences.userRoot().node("com.sirius.session");
    public static final String TOKEN_KEY = "jwt_token";

    private static Mahasiswa currentUser;

    public static void saveToken(String token) {
        prefs.put(TOKEN_KEY, token);
    }

    public static String getToken() {
        return prefs.get(TOKEN_KEY, null);
    }

    public static void clearSession() {
        prefs.remove(TOKEN_KEY);
    }

    public static Mahasiswa getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {

        DecodedJWT decoded = JwtUtil.verifyToken(getToken());

        if (decoded == null) {
            clearSession();
            return false;
        }

        if (getToken().isEmpty()) {
            clearSession();
            return false;
        }

        String email = decoded.getClaim("email").asString();

        if (email.isEmpty()) {
            clearSession();
            return false;
        }

        try {
            MahasiswaDAO mhsDAO = new MahasiswaDAO(DatabaseConnection.getKoneksi());
            Mahasiswa mhs = mhsDAO.findByEmail(email);

            if(mhs == null) {
                clearSession();
                return false;
            }

            currentUser = mhs;
            return true;

        } catch (Exception e) {
            System.err.println("Gagal auto-login, database error: " + e.getMessage());
            return false;
        }

    }
}
