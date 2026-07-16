package sirius.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "lK!@J-d13jasdlkn12___2912(j2+&&#";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static final String ISSUER = "Sirius";

    public static String generateToken(String email, String role) {
        long expirationTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000);

        String jwtKey = JWT.create()
                .withIssuer(ISSUER)
                .withClaim("email", email)
                .withClaim("role", role)
                .withExpiresAt(new Date(expirationTime))
                .sign(ALGORITHM);

        System.out.println("Generated JWT Key: " + jwtKey);

        return jwtKey;
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}