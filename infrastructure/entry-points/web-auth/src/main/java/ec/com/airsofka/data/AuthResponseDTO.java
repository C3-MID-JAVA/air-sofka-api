package ec.com.airsofka.data;

import java.io.Serializable;

public class AuthResponseDTO implements Serializable {
    private String email;
    private String role;
    private String token;
    private String id;

    public AuthResponseDTO(String email, String role, String token) {
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public AuthResponseDTO(String email, String role, String token, String id) {
        this.email = email;
        this.role = role;
        this.token = token;
        this.id = id;
    }

    public AuthResponseDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }
}