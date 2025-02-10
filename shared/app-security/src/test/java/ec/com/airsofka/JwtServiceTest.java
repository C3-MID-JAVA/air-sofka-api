package ec.com.airsofka;

import ec.com.airsofka.data.UserEntity;
import ec.com.airsofka.user.values.objects.DocumentType;
import ec.com.airsofka.user.values.objects.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    UserEntity user;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService(null);
        user = new UserEntity(null, LocalDateTime.now(), "1", DocumentType.ID_CARD, "test@gmail.com", "Jhon", false, "Doe", "Jhon Doe", 5, "test", "000000", "test", Role.USER, "test");
    }

    @Test
    void generateToken_validUserDetails_tokenGenerated() {
        String token = jwtService.generateToken(user);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void extractUsername_validToken_returnsUsername() {
        String token = jwtService.generateToken(user);

        String extractedUsername = jwtService.extractUsername(token);

        assertEquals("test@gmail.com", extractedUsername);
    }

    @Test
    void isTokenValid_validToken_returnsTrue() {
        String token = jwtService.generateToken(user);

        boolean isValid = jwtService.isTokenValid(token, user);

        assertTrue(isValid);
    }

    @Test
    void isTokenExpired_expiredToken_returnsTrue() {
        String expiredToken = Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 30))
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 15))
                .compact();


        ExpiredJwtException exception = assertThrows(ExpiredJwtException.class, () -> jwtService.extractAllClaims(expiredToken));

        assertEquals(ExpiredJwtException.class, exception.getClass());

    }

    @Test
    void extractClaim_validToken_returnsSpecificClaim() {
        String token = jwtService.generateToken(user);

        String extractedClaim = jwtService.extractClaim(token, Claims::getSubject);

        assertEquals("test@gmail.com", extractedClaim);
    }

}