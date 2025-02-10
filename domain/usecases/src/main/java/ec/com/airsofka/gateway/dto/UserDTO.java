package ec.com.airsofka.gateway.dto;

import ec.com.airsofka.user.values.objects.DocumentType;
import ec.com.airsofka.user.values.objects.Role;

import java.time.LocalDateTime;

public class UserDTO {

    private String userId;
    private LocalDateTime birthDate;
    private String documentNumber;
    private DocumentType documentType;
    private String email;
    private String firstLastName;
    private Boolean frequent;
    private String lastLastName;
    private String name;
    private Integer numberOfFlights;
    private String password;
    private String phone;
    private String prefix;
    private Role role;
    private String title;

    public UserDTO(String userId, LocalDateTime birthDate, String documentNumber, DocumentType documentType, String email, String firstLastName, Boolean frequent, String lastLastName, String name, Integer numberOfFlights, String password, String phone, String prefix, Role role, String title) {
        this.userId = userId;
        this.birthDate = birthDate;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.email = email;
        this.firstLastName = firstLastName;
        this.frequent = frequent;
        this.lastLastName = lastLastName;
        this.name = name;
        this.numberOfFlights = numberOfFlights;
        this.password = password;
        this.phone = phone;
        this.prefix = prefix;
        this.role = role;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public Boolean getFrequent() {
        return frequent;
    }

    public void setFrequent(Boolean frequent) {
        this.frequent = frequent;
    }

    public String getLastLastName() {
        return lastLastName;
    }

    public void setLastLastName(String lastLastName) {
        this.lastLastName = lastLastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(Integer numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
